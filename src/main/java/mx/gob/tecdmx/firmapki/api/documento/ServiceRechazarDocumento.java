package mx.gob.tecdmx.firmapki.api.documento;

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
import mx.gob.tecdmx.firmapki.api.firma.ServiceFirmar;
import mx.gob.tecdmx.firmapki.api.firma.ServiceFirmarAhora;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoDestino;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatFirmaAplicadaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoDestinoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocDestinatariosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentoWorkflowRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.utils.RestClient;
import mx.gob.tecdmx.firmapki.utils.dto.DTOPayloadNotificacionesEmail;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceRechazarDocumento {

	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;

	@Autowired
	PkiDocumentoDestinoRepository pkiDocumentoDestRepository;

	@Autowired
	PkiCatFirmaAplicadaRepository pkiCatFirmaAplicadaRepository;

	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;

	@Autowired
	TabDocumentosRepository tabDocumentosRepository;

	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;

	@Autowired
	TabDocDestinatariosRepository tabDocDestinatariosRepository;
	
	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;

	@Autowired
	TabDocumentoWorkflowRepository tabDocumentoWorkflowRepository;

	@Autowired
	ServiceValidationsMethods serviceValidacionesMetodos;
	
	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;
	
	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;
	
	@Value("${firma.url.notificacion}")
	private String sendNotificacionUrl;

	public boolean rechazoDocumentoByFirmante(HashDocumentoIdUsuarioIdTransaccionID idDocumento,
			PkiCatFirmaAplicada firmaAplicada, String descripción) {
		Optional<PkiDocumentoFirmantes> docFirmantes = pkiDocumentoFirmantesRepository.findById(idDocumento);
		if (docFirmantes.isPresent()) {
			// actualiza el registro del firmante con el rechazo de la firma
			docFirmantes.get().setCadenaFirma(null);
			docFirmantes.get().setFechaFirma(new Date());
			docFirmantes.get().setIdFirmaAplicada(firmaAplicada);
			docFirmantes.get().setDescripcion(descripción);
			pkiDocumentoFirmantesRepository.save(docFirmantes.get());

			return true;

		}
		return false;

	}

	public boolean rechazoDocumentoByDestinatario(HashDocumentoIdUsuarioIdTransaccionID idDocumento,
			PkiCatFirmaAplicada firmaAplicada, String descripción) {
		Optional<PkiDocumentoDestino> docDestinos = pkiDocumentoDestRepository.findById(idDocumento);
		if (docDestinos.isPresent()) {
			// actualiza el registro del firmante con el rechazo de la firma
			docDestinos.get().setFechaLectura(new Date());
			docDestinos.get().setIdFirmaAplicada(firmaAplicada);
			docDestinos.get().setDescripcion(descripción);
			pkiDocumentoDestRepository.save(docDestinos.get());

			return true;

		}
		return false;

	}

	public DTOResponse rechazarDocumento(PayloadRechazarDocumento payload, DTOResponse res,
			DTOResponseUserInfo userInfo) {
//		MethodsValidationUtils validacionesMetodos = new MethodsValidationUtils();

		Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(userInfo.getData().getIdEmpleado());
		if (!empleado.isPresent()) {
			res.setMessage("No se puede encontrar el empleado");
			res.setStatus("fail");
			return res;
		}

		boolean docRechazadoByFirmante = false;
		boolean docRechazadoByDestinatario = false;
		// obtengo el registro correspondiente a la tabla tabDocumento
		Optional<TabDocumentos> documentoExist = tabDocumentosRepository.findById(payload.getIdDocumento());

		TabCatEtapaDocumento etapaDoc_creado = serviceConsultaMethods
				.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.RECHAZADO.getOpcion(), res);

		PkiCatFirmaAplicada firmaAplicada = serviceConsultaMethods
				.findFirmaAplicada(EnumPkiCatFirmaAplicada.RECHAZADO.getOpcion(), res);

		List<TabDocumentosAdjuntos> docsAdjuntos = tabDocumentosAdjuntosRepository
				.findByIdDocument(documentoExist.get());

		for (TabDocumentosAdjuntos tabDocAd : docsAdjuntos) {
			HashDocumentoIdUsuarioIdTransaccionID idDocumento = new HashDocumentoIdUsuarioIdTransaccionID();
			idDocumento.setHashDocumento(tabDocAd.getDocumentoHash());
			idDocumento.setIdUsuario(userInfo.getData().getIdUsuario());

			if (payload.getTipoUsuario().equals("firmante")) {

				docRechazadoByFirmante = rechazoDocumentoByFirmante(idDocumento, firmaAplicada,
						payload.getDescripcion());

			} else if (payload.getTipoUsuario().equals("destinatario")) {

				docRechazadoByDestinatario = rechazoDocumentoByFirmante(idDocumento, firmaAplicada,
						payload.getDescripcion());

			} else {
				res.setData(null);
				res.setStatus("fail");
				res.setMessage("La Accion del usuario no fué reconocida");

				return res;
			}

		}
		if (docRechazadoByFirmante) {

			TabCatEtapaDocumento etapaDoc_rechazado = serviceConsultaMethods
					.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.RECHAZADO.getOpcion(), res);

			TabDocumentoWorkflow workflowStored_Creado = serviceAlmacenarMethods.storeWorkFlow(documentoExist.get(),
					etapaDoc_rechazado, userInfo.getData().getEmpleado(), res);
			
			List<TabDocsFirmantes> firmantes = tabDocsFirmantesRepository.findByIdDocumento(documentoExist.get().getId());
			boolean notificacionRechazo = notificacionRechazoDocumento(firmantes);

		} else if (docRechazadoByDestinatario) {
			boolean isTerminado = serviceValidacionesMetodos.verificaDocumentoIsTerminadoDestinatarios(docsAdjuntos);
			if (isTerminado) {
				TabCatEtapaDocumento etapaDoc_Terminado = serviceConsultaMethods
						.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.TERMINADO.getOpcion(), res);

				TabDocumentoWorkflow workflowStored_Creado = serviceAlmacenarMethods.storeWorkFlow(documentoExist.get(),
						etapaDoc_Terminado, userInfo.getData().getEmpleado(), res);
			}

		}

		res.setData(null);
		res.setStatus("Succes");
		res.setMessage("El documento ha sido Rechazado");

		return res;

	}

	private boolean notificacionRechazoDocumento(List<TabDocsFirmantes> firmantes) {
		for(TabDocsFirmantes firmante : firmantes) {
			Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(firmante.getIdNumEmpleado());
			
			boolean notifEnviada = enviarNotificacionRechazo(empleado.get());
			if(!notifEnviada) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean enviarNotificacionRechazo(InstEmpleado empleado) {
		RestClient restClient = new RestClient();
		DTOPayloadNotificacionesEmail notificaciones = null;
		
		notificaciones = new DTOPayloadNotificacionesEmail();
		if (empleado.getEmailInst() != null) {
			notificaciones.setEmailDestino(empleado.getEmailInst());

		} else if (empleado.getEmailPers() != null) {
			notificaciones.setEmailDestino(empleado.getEmailPers());
		}
		notificaciones.setAsunto("Documento Rechazado");
		notificaciones.setTexto("Se ha rechado un documento sobre el cual tenías una asignación, puedes consultarlo el Tablero de Firmas Electrónicas");

		Gson gson = new Gson();
		String json = gson.toJson(notificaciones);
		String respPost = restClient.sendPostRequestForNotificacionesEmail(sendNotificacionUrl, json);

		return true;
		
	}

}
