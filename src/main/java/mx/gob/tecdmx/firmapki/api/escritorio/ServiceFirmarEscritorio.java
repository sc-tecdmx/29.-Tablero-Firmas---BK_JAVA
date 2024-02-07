package mx.gob.tecdmx.firmapki.api.escritorio;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceAlmacenarMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceConsultaMethods;
import mx.gob.tecdmx.firmapki.api.Metodos.ServiceValidationsMethods;
import mx.gob.tecdmx.firmapki.api.firma.ResponseBodyFirma;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgLogSesion;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoDestinoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiTransaccionRepository;
import mx.gob.tecdmx.firmapki.repository.seg.SegOrgLogSesionRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.dto.PayloadFirma;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceFirmarEscritorio {

	@Autowired
	PkiDocumentoRepository pkiDocumentoRepository;

	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;

	@Autowired
	PkiDocumentoDestinoRepository pkiDocumentoDestRepository;

	@Autowired
	PkiTransaccionRepository pkiTransaccionRepository;
	
	@Autowired
	SegOrgLogSesionRepository segOrgLogSesionRepository;

	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;

	@Autowired
	ServiceValidationsMethods serviceValidacionesMetodos;

	@Autowired
	ServiceConsultaMethods serviceConsultaMethods;

	@Autowired
	ServiceAlmacenarMethods serviceAlmacenarMethods;

	@Value("${firma.document.encryption}")
	private String encryptionAlgorithm;

	@Value("${firma.document.pdf.firmados.path}")
	private String documentFirmadosPath;

	public void firmaEscritorio(String hashDocumento, byte[] documentoFirmadoBase64, String filePath) {
		CertificateUtils utils = new CertificateUtils();
		try {
			try {

				Optional<TabDocumentosAdjuntos> doc = tabDocumentosAdjuntosRepository
						.findByDocumentoHash(hashDocumento);
				if (doc.isPresent()) {
					doc.get().setDocumentoBase64(Base64.getEncoder().encodeToString(documentoFirmadoBase64));
					tabDocumentosAdjuntosRepository.save(doc.get());
					System.out.println("se actualizó el base64 del doc firmado.");
				}
				boolean storeInFolder = false;
				if (storeInFolder) {
					utils.writePdfFile(documentoFirmadoBase64, filePath);
				}

				System.out.println("PDF firmado guardado con éxito.");
			} catch (IOException e) {
				System.err.println("Error al guardar el PDF firmado:");
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Implementa esta lógica
	}

	public boolean firmarDocumentoEscritorio(PayloadFirma payload, DTOResponse res, DTOResponseUserInfo userInfo) {
		ResponseBodyFirma responde = new ResponseBodyFirma();

		// Sección de actualización de PKI documentos_firmantes
		HashDocumentoIdUsuarioIdTransaccionID idDocumentoFirmantes = new HashDocumentoIdUsuarioIdTransaccionID();
		idDocumentoFirmantes.setHashDocumento(payload.getHashDocumento());
		idDocumentoFirmantes.setIdUsuario(userInfo.getData().getIdUsuario());

		Optional<PkiDocumentoFirmantes> docFirmantesPki = pkiDocumentoFirmantesRepository
				.findById(idDocumentoFirmantes);
		if (!docFirmantesPki.isPresent()) {
			res.setData(null);
			res.setStatus("Fail");
			res.setMessage("No puedes firmar este documento, ya que no te fue asignado para firmar");
			return false;
		}

		PkiCatFirmaAplicada firmaAplicada_firmado = serviceConsultaMethods
				.findFirmaAplicada(EnumPkiCatFirmaAplicada.FIRMADO.getOpcion(), res);
		if (firmaAplicada_firmado == null) {
			return false;
		}

		try {
			docFirmantesPki.get().setCadenaFirma(payload.getCadenaFirma().toString());
			docFirmantesPki.get().setFechaFirma(new Date());
			docFirmantesPki.get().setIdFirmaAplicada(firmaAplicada_firmado);

			pkiDocumentoFirmantesRepository.save(docFirmantesPki.get());

		} catch (Exception e) {
			res.setMessage("No se pudo actualizar la tabla documentos firmantes");
			res.setStatus("fail");
			return false;
		}

		// Sección para agregar firma al documento pdf
		String fileName = documentFirmadosPath + "/" + payload.getHashDocumento() + "_"
				+ userInfo.getData().getIdUsuario() + ".pdf";

		firmaEscritorio(payload.getHashDocumento(), payload.getDocumento(), fileName);

		try {
			// sección para actualizar documento PKI
			PkiDocumento documentoPki = docFirmantesPki.get().getDocumento();

			documentoPki.setAlgoritmo(encryptionAlgorithm);
			//

			TabDocumentosAdjuntos documentoAdjuntoTab = serviceConsultaMethods
					.getTabDocumentoAdjuntoByHash(payload.getHashDocumento(), res);
			if (documentoAdjuntoTab == null) {
				return false;
			}
			List<TabDocumentosAdjuntos> listaDocAdjuntosTab = serviceConsultaMethods
					.getTabDocumentosAdjuntos(documentoAdjuntoTab.getIdDocument());
			List<TabDocsFirmantes> listaFirmantesTab = serviceConsultaMethods
					.getTabDocsFirmantes(documentoAdjuntoTab.getIdDocument().getId());

			for (TabDocumentosAdjuntos docAdjuntoTab : listaDocAdjuntosTab) {
				boolean archivoCompleted = true;
				for (TabDocsFirmantes firmanteTab : listaFirmantesTab) {

					Optional<PkiDocumentoFirmantes> docUsuFirmante = pkiDocumentoFirmantesRepository
							.findByHashDocumentoAndIdNumEmpleadoAndIdFirmaAplicada(docAdjuntoTab.getDocumentoHash(),
									firmanteTab.getIntEmpleado(), firmaAplicada_firmado);
					if (!docUsuFirmante.isPresent()) {
						archivoCompleted = false;
					}
				}
				if (archivoCompleted) {
					documentoPki.setTerminado(1);
				}
			}

			pkiDocumentoRepository.save(documentoPki);

		} catch (Exception e) {
			res.setMessage("No se pudo actualizar la información en la tabla documento PKI");
			res.setStatus("fail");
			return false;
		}

		// Validamos si podemos dar por terminado el dcumento (No el archivo adjunto,
		// más bien el tab documento)
		// Obtenemos los elementos necesarios de la sección de la BD tablero para
		// posterior
		TabDocumentosAdjuntos documentoAdjuntoTab = serviceConsultaMethods
				.getTabDocumentoAdjuntoByHash(payload.getHashDocumento(), res);
		if (documentoAdjuntoTab == null) {
			return false;
		}
		List<TabDocumentosAdjuntos> documentosAdjuntosTabList = serviceConsultaMethods
				.getTabDocumentosAdjuntos(documentoAdjuntoTab.getIdDocument());

		boolean allDocsFirmados = true;
		PkiDocumento pkiDoc = null;
		for (TabDocumentosAdjuntos docAdjunto : documentosAdjuntosTabList) {
			pkiDoc = serviceConsultaMethods.getDocumentoPKIByHash(docAdjunto.getDocumentoHash(), res);
			if (pkiDoc.getTerminado() == 0) {
				allDocsFirmados = false;
			}
		}

		if (allDocsFirmados) {
			TabCatEtapaDocumento etapaDoc_terminado = serviceConsultaMethods
					.findEtapaDocumentoEnum(EnumTabCatEtapaDocumento.TERMINADO.getOpcion(), res);
			if (etapaDoc_terminado == null) {
				return false;
			}

			TabDocumentoWorkflow workflowStored_Terminado = serviceAlmacenarMethods.storeWorkFlow(
					documentoAdjuntoTab.getIdDocument(), etapaDoc_terminado, userInfo.getData().getEmpleado(), res);
			if (workflowStored_Terminado == null) {
				return false;
			}
		}

		Optional<SegOrgLogSesion> sesionExist =segOrgLogSesionRepository.findById(Integer.parseInt(userInfo.getData().getIdSession()));
		if(sesionExist.isPresent()) {
			
			 long tiempoTerminacion = System.currentTimeMillis();
			//almacena el Log de la sesión finalizada
			sesionExist.get().setD_fecha_fin(new Date());
			sesionExist.get().setN_end_sesion(tiempoTerminacion);
			segOrgLogSesionRepository.save(sesionExist.get());
			
		}
		
		responde.setAlgortimo(encryptionAlgorithm);

		res.setData(responde);
		res.setStatus("Succes");
		res.setMessage("Documento firmado");
		return true;

	}


}
