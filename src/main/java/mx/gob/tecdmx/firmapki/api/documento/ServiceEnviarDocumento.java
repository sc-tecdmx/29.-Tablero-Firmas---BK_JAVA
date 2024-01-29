package mx.gob.tecdmx.firmapki.api.documento;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceValidationsMethods;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.utils.RestClient;
import mx.gob.tecdmx.firmapki.utils.dto.DTOPayloadNotificacionesEmail;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceEnviarDocumento {
	
	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;
	
	@Autowired
	ServiceValidationsMethods serviceValidationsMethods;
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;
	
	@Value("${firma.url.notificacion}")
	private String sendNotificacionUrl;
	
	public boolean enviarDocumento(int idTabDocumento, DTOResponseUserInfo userInfo, DTOResponse res) {

		TabDocumentos documentoStored = serviceConsultaMethods.findTabDocumento(idTabDocumento, res);
		if (documentoStored == null) {
			return false;
		}

		boolean isAllowedToSend = validateAllowedToEnviarDocumento(documentoStored, userInfo, res);
		if (!isAllowedToSend) {
			return false;
		}

		List<TabDocsFirmantes> firmantes = serviceConsultaMethods.getTabDocsFirmantes(documentoStored.getId());
		List<TabDocDestinatarios> destinatarios = serviceConsultaMethods.getTabDocsDestinatarios(documentoStored.getId());
		List<TabDocumentosAdjuntos> documentosAdjuntos = serviceConsultaMethods.getTabDocumentosAdjuntos(documentoStored);

		boolean alguienHaFirmado = serviceValidationsMethods.alguienYaFirmo(documentoStored, res);
		if (!alguienHaFirmado & res.getStatus() != null) {
			return false;
		}

		Collections.sort(firmantes, (o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

		if (documentoStored.getEnOrden() == 1) {
			// Si ya lo ha firmado el firmante creador del documento
			if (alguienHaFirmado) {

				boolean updatedPkiDocumento = serviceAlmacenarMethods.updateDataEnviadoInPkiDocumentosAdjuntos(documentosAdjuntos,
						new Date(), userInfo.getData().getEmpleado(), res);
				if (!updatedPkiDocumento) {
					return false;
				}

				PkiCatFirmaAplicada firmaAplicada = serviceConsultaMethods.findFirmaAplicada(EnumPkiCatFirmaAplicada.FIRMADO.getOpcion(), res);
				if (firmaAplicada == null) {
					return false;
				}

				TabDocsFirmantes currentFirmante = null;
				for (TabDocsFirmantes firmante : firmantes) {
					boolean haFirmado = serviceValidationsMethods.validateFirmanteHaFirmado(
							documentosAdjuntos.get(0).getDocumentoHash(), firmante.getIntEmpleado(), firmaAplicada);
					if (!haFirmado) {
						currentFirmante = firmante;
					}
				}
				if (currentFirmante == null) {
					res.setMessage("ya no restan firmantes por firmar");
					res.setStatus("Success");
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = serviceConsultaMethods.findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = serviceAlmacenarMethods.storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, currentFirmante, res);
				if (!documentoFirmantesStored) {
					return false;
				} else {
					/// aqui va el envío de la notificacion
					Optional<InstEmpleado> empleado = instEmpleadoRepository
							.findById(currentFirmante.getIdNumEmpleado());

					enviarNotificacion(empleado.get());

				}

				TabCatEtapaDocumento etapaDoc_enviado = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceAlmacenarMethods.storeWorkFlow(documentoStored,
						etapaDoc_enviado, userInfo.getData().getEmpleado(), res);
				if (workflowStored_Enviado == null) {
					return false;
				}
				res.setMessage("El documento se ha enviado satisfactoriamente");
				res.setStatus("Success");
				return true;
			} else {
				// Si nadie ha firmado el documento
				TabDocsFirmantes currentFirmante = firmantes.get(0);

				boolean archivosPkiDocumentoStored = serviceAlmacenarMethods.storePkiDocumento(documentosAdjuntos,
						EnumTabCatEtapaDocumento.ENVIADO.getOpcion(), documentoStored.getEnOrden() == 1 ? true : false,
						userInfo.getData().getEmpleado(), userInfo.getData().getEmpleado(), new Date(), new Date(),
						res);
				if (!archivosPkiDocumentoStored) {
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = serviceConsultaMethods.findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = serviceAlmacenarMethods.storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, currentFirmante, res);
				if (!documentoFirmantesStored) {
					return false;
				} else {
					/// aqui va el envío de la notificacion
					Optional<InstEmpleado> empleado = instEmpleadoRepository
							.findById(currentFirmante.getIdNumEmpleado());

					enviarNotificacion(empleado.get());

				}

				TabCatEtapaDocumento etapaDoc_enviado = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceAlmacenarMethods.storeWorkFlow(documentoStored,
						etapaDoc_enviado, userInfo.getData().getEmpleado(), res);
				if (workflowStored_Enviado == null) {
					return false;
				}

				res.setMessage("El documento se ha enviado satisfactoriamente");
				res.setStatus("Success");
				return true;
			}
		} else {// Si no hay un orden
			if (alguienHaFirmado) {
				boolean updatedPkiDocumento = serviceAlmacenarMethods.updateDataEnviadoInPkiDocumentosAdjuntos(documentosAdjuntos,
						new Date(), userInfo.getData().getEmpleado(), res);
				if (!updatedPkiDocumento) {
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = serviceConsultaMethods.findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = serviceAlmacenarMethods.storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, firmantes, res);
				if (!documentoFirmantesStored) {
					return false;
				}

				TabCatEtapaDocumento etapaDoc_enviado = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceAlmacenarMethods.storeWorkFlow(documentoStored,
						etapaDoc_enviado, userInfo.getData().getEmpleado(), res);
				if (workflowStored_Enviado == null) {
					return false;
				}
				res.setMessage("El documento se ha enviado satisfactoriamente");
				res.setStatus("Success");
				return true;
			} else {
				boolean archivosPkiDocumentoStored = serviceAlmacenarMethods.storePkiDocumento(documentosAdjuntos,
						EnumTabCatEtapaDocumento.ENVIADO.getOpcion(), documentoStored.getEnOrden() == 1 ? true : false,
						userInfo.getData().getEmpleado(), userInfo.getData().getEmpleado(), new Date(), new Date(),
						res);
				if (!archivosPkiDocumentoStored) {
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = serviceConsultaMethods.findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = serviceAlmacenarMethods.storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, firmantes, res);
				if (!documentoFirmantesStored) {
					return false;
				}

				TabCatEtapaDocumento etapaDoc_enviado = serviceConsultaMethods.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceAlmacenarMethods.storeWorkFlow(documentoStored,
						etapaDoc_enviado, userInfo.getData().getEmpleado(), res);
				if (workflowStored_Enviado == null) {
					return false;
				}
				res.setMessage("El documento se ha enviado satisfactoriamente");
				res.setStatus("Success");
				return true;
			}

		}

	}
	
	public boolean validateAllowedToEnviarDocumento(TabDocumentos documentoStored, DTOResponseUserInfo userInfo,
			DTOResponse res) {

		if (documentoStored.getIdUsuarioCreador() != userInfo.getData().getUser()) {
			res.setMessage("No puedes enviar el documento porque no eres el creador");
			res.setStatus("fail");
			return false;
		}

		String etapaSequence = serviceConsultaMethods.getSequenceWorkflow(documentoStored, res);
		if (etapaSequence == null) {
			return false;
		}

		if (etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion())
				|| etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())
				|| etapaSequence.equals(
						EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-" + EnumTabCatEtapaDocumento.ENVIADO.getOpcion()
								+ "-" + EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())
				|| etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.ENVIADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())) {
			return true;
		} else {
			res.setMessage(
					"No puedes enviar el documento porque este no se encuentra en una etapa permitida para el envío, contacta a tu administrador.");
			res.setStatus("fail");
			return false;
		}
	}
	
	public boolean enviarNotificacion(InstEmpleado empleado) {
		RestClient restClient = new RestClient();
		DTOPayloadNotificacionesEmail notificaciones = null;
		
		notificaciones = new DTOPayloadNotificacionesEmail();
		if (empleado.getEmailInst() != null) {
			notificaciones.setEmailDestino(empleado.getEmailInst());

		} else if (empleado.getEmailPers() != null) {
			notificaciones.setEmailDestino(empleado.getEmailPers());
		}
		notificaciones.setAsunto("Nuevo documento");
		notificaciones.setTexto("Haz recibido un nuevo documento, favor de revisar tu perfil en Tablero de Firmas Electrónicas y atender la notificación");

		Gson gson = new Gson();
		String json = gson.toJson(notificaciones);
		String respPost = restClient.sendPostRequestForNotificacionesEmail(sendNotificacionUrl, json);

		return true;
		
	}


}
