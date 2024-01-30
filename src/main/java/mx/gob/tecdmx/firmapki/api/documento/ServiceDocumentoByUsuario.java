package mx.gob.tecdmx.firmapki.api.documento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.entity.pki.HashDocumentoIdUsuarioIdTransaccionID;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoDestino;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.pki.PkiUsuariosCert;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.entity.tab.VistaTablero;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoDestinoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiDocumentoFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiUsuariosCertRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocDestinatariosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.VistaTableroRepository;
import mx.gob.tecdmx.firmapki.utils.dto.DTODocAdjunto;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;

@Service
public class ServiceDocumentoByUsuario {
	
	@Autowired
	VistaTableroRepository vistaTableroRepository;
	
	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;
	
	@Autowired
	TabDocDestinatariosRepository tabDocDestinatariosRepository;
	
	@Autowired
	TabDocumentosRepository tabDocumentosRepository;
	
	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;
	
	@Autowired
	PkiDocumentoFirmantesRepository pkiDocumentoFirmantesRepository;
	
	@Autowired
	PkiDocumentoDestinoRepository pkiDocumentoDestRepository;
	
	@Autowired
	PkiUsuariosCertRepository pkiUsuariosCertRepository;
	
	public DTOResponse getDocumentosByUsuario(DTOResponse res, DTOResponseUserInfo userInfo) {
		List<ResponseBodyDocsUsuario> listDocsUsuario = new ArrayList<ResponseBodyDocsUsuario>();
		ResponseBodyDocsUsuario docUsuario = null;
		boolean statusFirma = false;
		// Busca todos los documentos en los que el usuario activo se encuentre
		// involucrado
		List<VistaTablero> listDocsByUsuario = vistaTableroRepository.findByNumEmpleado(
				userInfo.getData().getIdEmpleado(), Sort.by(Sort.Direction.DESC, "creacionDocumentoFecha"));
		if (listDocsByUsuario.size() > 0) {
			// si tiene documentos recorre la lista obtenida
			for (VistaTablero docsUsuarioView : listDocsByUsuario) {
				docUsuario = new ResponseBodyDocsUsuario();
				docUsuario.setIdDocumento(docsUsuarioView.getIdDocumento());
				docUsuario.setFolioDocumento(docsUsuarioView.getFolioDocumento());
				docUsuario.setEtapa(docsUsuarioView.getEtapa());
				docUsuario.setPrioridad(docsUsuarioView.getPrioridad());
				docUsuario.setCreacionDocumentoFecha(docsUsuarioView.getCreacionDocumentoFecha());
				docUsuario.setAsunto(docsUsuarioView.getAsunto());
				docUsuario.setTipo(docsUsuarioView.getTipo());
				// obtienen la lista de los firmantes
				List<DTOEmpleado> firmantes = new ArrayList<DTOEmpleado>();
				DTOEmpleado empleado = null;
				List<TabDocsFirmantes> docFirmantes = tabDocsFirmantesRepository
						.findByIdDocumento(docsUsuarioView.getIdDocumento());
				for (TabDocsFirmantes docfirmante : docFirmantes) {
					empleado = new DTOEmpleado();
					empleado.setIdEmpleado(docfirmante.getIntEmpleado().getId());
					empleado.setNombre(docfirmante.getIntEmpleado().getNombre());
					empleado.setApellido1(docfirmante.getIntEmpleado().getApellido1());
					empleado.setApellido2(docfirmante.getIntEmpleado().getApellido2());
					firmantes.add(empleado);
				}
				docUsuario.setFirmantes(firmantes);
				// obtiene la lista de destinatarios
				List<DTOEmpleado> destinatarios = new ArrayList<DTOEmpleado>();
				DTOEmpleado empleadoDest = null;
				List<TabDocDestinatarios> docDestinatarios = tabDocDestinatariosRepository
						.findByIdDocumento(docsUsuarioView.getIdDocumento());
				for (TabDocDestinatarios docDestinatario : docDestinatarios) {
					empleadoDest = new DTOEmpleado();
					empleadoDest.setIdEmpleado(docDestinatario.getIdNumEmpleado());
					empleadoDest.setNombre(docDestinatario.getEmpleado().getNombre());
					empleadoDest.setApellido1(docDestinatario.getEmpleado().getApellido1());
					empleadoDest.setApellido2(docDestinatario.getEmpleado().getApellido2());
					destinatarios.add(empleadoDest);
				}
				docUsuario.setDestinatarios(destinatarios);
				// obtiene la listya de documentos adjuntos
				List<DTODocAdjunto> documentosAdjuntos = new ArrayList<DTODocAdjunto>();
				DTODocAdjunto docAdjuntoDto = null;
				Optional<TabDocumentos> documento = tabDocumentosRepository.findById(docsUsuarioView.getIdDocumento());
				if (documento.isPresent()) {
					List<TabDocumentosAdjuntos> docAdjuntos = tabDocumentosAdjuntosRepository
							.findByIdDocument(documento.get());
					for (TabDocumentosAdjuntos docAdjunto : docAdjuntos) {
						docAdjuntoDto = new DTODocAdjunto();
						docAdjuntoDto.setDocBase64(docAdjunto.getDocumentoBase64());
						docAdjuntoDto.setFilePath(docAdjunto.getDocumentoPath());
						docAdjuntoDto.setFileType(docAdjunto.getDocumentoFiletype());
						docAdjuntoDto.setOriginalHash(docAdjunto.getDocumentoHash());

						documentosAdjuntos.add(docAdjuntoDto);

						// aqui obtiene el estado de la firma , segun si es firmante o destinatario
						HashDocumentoIdUsuarioIdTransaccionID idDocumento = new HashDocumentoIdUsuarioIdTransaccionID();
						idDocumento.setHashDocumento(docAdjunto.getDocumentoHash());
						idDocumento.setIdUsuario(userInfo.getData().getIdUsuario());
						Optional<PkiDocumentoFirmantes> docUsuFirmante = pkiDocumentoFirmantesRepository
								.findById(idDocumento);
						Optional<PkiDocumentoDestino> docUsuDestinatario = pkiDocumentoDestRepository
								.findById(idDocumento);
						if (docUsuFirmante.isPresent()) {
							if (docUsuFirmante.get().getIdFirmaAplicada() != null) {
								statusFirma = true;
							}
						}
						if (docUsuDestinatario.isPresent()) {
							if (docUsuDestinatario.get().getIdFirmaAplicada() != null) {
								statusFirma = true;
							}
						}

					}
				}
				docUsuario.setStatusFirma(statusFirma);
				docUsuario.setDocumentosAdjuntos(documentosAdjuntos);

				listDocsUsuario.add(docUsuario);

			}
			res.setData(listDocsUsuario);
			res.setMessage("Se han encontrado resultados");
			res.setStatus("Success");
		} else {
			res.setData(listDocsUsuario);
			res.setMessage("No se han encontrado resultados");
			res.setStatus("Success");
		}
		return res;
	}
	
	public DTOResponse getUserSerial(DTOResponseUserInfo userInfo, DTOResponse res) {
		List<PkiUsuariosCert> pkiUsuariosCert = pkiUsuariosCertRepository.findByUsuario(userInfo.getData().getUser());
		if(pkiUsuariosCert.size()>0) {
			List<String> numSerieList = new ArrayList<String>();
			for(PkiUsuariosCert pkiUC : pkiUsuariosCert) {
				numSerieList.add(pkiUC.getX509SerialNumber());
			}
			res.setData(numSerieList);
			res.setMessage("Se ha obtenido el número de serie de manera satisfactoria");
			res.setStatus("Success");
			return res;
		}
		res.setMessage("No se ha podido obtener el usuario y su número de serie");
		res.setStatus("fail");
		return res;
	}


}
