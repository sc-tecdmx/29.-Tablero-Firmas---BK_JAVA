package mx.gob.tecdmx.firmaocsp.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmaocsp.dto.AsignacionDTO;
import mx.gob.tecdmx.firmaocsp.dto.DocumentoDTO;
import mx.gob.tecdmx.firmaocsp.entity.Asignacion;
import mx.gob.tecdmx.firmaocsp.entity.CatAccion;
import mx.gob.tecdmx.firmaocsp.entity.CatArea;
import mx.gob.tecdmx.firmaocsp.entity.CatCargo;
import mx.gob.tecdmx.firmaocsp.entity.CatEstado;
import mx.gob.tecdmx.firmaocsp.entity.CatTipoAsociacion;
import mx.gob.tecdmx.firmaocsp.entity.CatTipoDocumento;
import mx.gob.tecdmx.firmaocsp.entity.CatTipoUso;
import mx.gob.tecdmx.firmaocsp.entity.Configuracion;
import mx.gob.tecdmx.firmaocsp.entity.Documento;
import mx.gob.tecdmx.firmaocsp.entity.Usuario;
import mx.gob.tecdmx.firmaocsp.repository.AsignacionRepository;
import mx.gob.tecdmx.firmaocsp.repository.CatAccionRepository;
import mx.gob.tecdmx.firmaocsp.repository.CatAreaRepository;
import mx.gob.tecdmx.firmaocsp.repository.CatCargoRepository;
import mx.gob.tecdmx.firmaocsp.repository.CatEstadoRepository;
import mx.gob.tecdmx.firmaocsp.repository.CatTipoAsociacionRepository;
import mx.gob.tecdmx.firmaocsp.repository.CatTipoDocumentoRepository;
import mx.gob.tecdmx.firmaocsp.repository.CatTipoUsoRepository;
import mx.gob.tecdmx.firmaocsp.repository.ConfiguracionRepository;
import mx.gob.tecdmx.firmaocsp.repository.DocumentoRepository;
import mx.gob.tecdmx.firmaocsp.repository.UsuarioRepository;

@Service
public class DocumentoService {

	@Autowired
	AsignacionRepository personaRepository;

	@Autowired
	DocumentoRepository documentoRepository;
	
	@Autowired
	CatAccionRepository catAccionRepository;

	@Autowired
	CatAreaRepository catAreaRepository;

	@Autowired
	CatCargoRepository catCargoRepository;

	@Autowired
	CatEstadoRepository catEstadoRepository;

	@Autowired
	CatTipoAsociacionRepository catTipoAsociacionRepository;

	@Autowired
	CatTipoDocumentoRepository catTipoDocumentoRepository;

	@Autowired
	CatTipoUsoRepository catTipoUsoRepository;

	@Autowired
	ConfiguracionRepository configuracionRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AsignacionRepository asignacionRepository;
	
	public CatAccion getCatalogAccionIdByCode(Optional<CatAccion> catalog) {
		if(catalog.isPresent()) {
			return catalog.get();
		}
		return null;
	}
	
	public CatArea getCatalogAreaIdByCode(Optional<CatArea> catalog) {
		if(catalog.isPresent()) {
			return catalog.get();
		}
		return null;
	}
	
	public CatCargo getCatalogCargoIdByCode(Optional<CatCargo> catalog) {
		if(catalog.isPresent()) {
			return catalog.get();
		}
		return null;
	}
	
	public CatEstado getCatalogEstadoIdByCode(Optional<CatEstado> catalog) {
		if(catalog.isPresent()) {
			return catalog.get();
		}
		return null;
	}
	
	public CatTipoAsociacion getCatalogTipoAsociacionIdByCode(Optional<CatTipoAsociacion> catalog) {
		if(catalog.isPresent()) {
			return catalog.get();
		}
		return null;
	}
	
	public CatTipoDocumento getCatalogTipoDocumentoIdByCode(Optional<CatTipoDocumento> catalog) {
		if(catalog.isPresent()) {
			return catalog.get();
		}
		return null;
	}
	
	public CatTipoUso getCatalogTipoUsoIdByCode(Optional<CatTipoUso> catalog) {
		if(catalog.isPresent()) {
			return catalog.get();
		}
		return null;
	}
	
	public Usuario getUsuarioIdByCorreo(Optional<Usuario> usuario) {
		if(usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}
	
	
	
	
	public Documento crearDocumento(DocumentoDTO docToSave) {
		Documento document = new Documento();
		document.setIdDocumento(docToSave.getIdDocumento());
		document.setFolio(docToSave.getFolio());
		document.setFolioDocumento(docToSave.getFolioDocumento());
		document.setNumeroExpediente(docToSave.getNumeroExpediente());
		document.setNombreExpediente(docToSave.getNombreExpediente());
		document.setFechaDocumento(docToSave.getFechaDocumento());
		document.setAsunto(docToSave.getAsunto());
		document.setContenido(docToSave.getContenido());
		document.setNota(docToSave.getNota());
		document.setDocumento(docToSave.getDocumento());
		document.setPorcentajeFirma(0);
		document.setCargo(docToSave.getCargo());
		
		CatTipoUso tipoUsoId = getCatalogTipoUsoIdByCode(catTipoUsoRepository.findByCode(docToSave.getTipoUsoCode()));
		CatArea areaDestinoId = getCatalogAreaIdByCode(catAreaRepository.findByCode(docToSave.getAreaDestinoCode()));
		CatArea areaCopiaId = getCatalogAreaIdByCode(catAreaRepository.findByCode(docToSave.getAreaCopiaCode()));
		CatTipoDocumento tipoDocumentoId = getCatalogTipoDocumentoIdByCode(catTipoDocumentoRepository.findByCode(docToSave.getTipoDocumentoCode()));
		Usuario destinatarioId = getUsuarioIdByCorreo(usuarioRepository.findByCorreo(docToSave.getDestinatario()));
		Usuario elaboroId = getUsuarioIdByCorreo(usuarioRepository.findByCorreo(docToSave.getElaboro()));
		CatEstado estadoDocumentoId = getCatalogEstadoIdByCode(catEstadoRepository.findByCode(docToSave.getEstadoDocumentoCode()));
		
		
		document.setIdTipouso(tipoUsoId);
		document.setIdAreadestino(areaDestinoId);
		document.setIdAreacopia(areaCopiaId);
		document.setIdTipodocumento(tipoDocumentoId);
		document.setIdDestinatario(destinatarioId);
		document.setIdElaboro(elaboroId);
		document.setIdEstadodocumento(estadoDocumentoId);
		
		document.setActivo(true);
		document.setUsuCreacion(1);
		document.setUsuModificacion(1);
		document.setFhCreacion(new Date());
		document.setFhModificacion(new Date());
		
		documentoRepository.save(document);
		
		Asignacion firmante = null;
		for(AsignacionDTO firmanteDTO: docToSave.getFirmantes()) {
			firmante = new Asignacion();
			firmante.setIdDocumento(document);
				Usuario firmanteId = getUsuarioIdByCorreo(usuarioRepository.findByCorreo(firmanteDTO.getCorreo()));		
				firmante.setIdUsuario(firmanteId);
				
				CatAccion tipoAccionId = getCatalogAccionIdByCode(catAccionRepository.findByCode(firmanteDTO.getAccionCode()));
				firmante.setIdAccion(tipoAccionId);
				
				CatEstado estadoAccionId = getCatalogEstadoIdByCode(catEstadoRepository.findByCode(firmanteDTO.getEstadoAccionCode()));
				firmante.setIdEstadoaccion(estadoAccionId);
				
				CatTipoAsociacion tipoAsociacionId = getCatalogTipoAsociacionIdByCode(catTipoAsociacionRepository.findByCode("FIRMANTE"));
				firmante.setIdTipoasociacion(tipoAsociacionId);
				
				firmante.setObligatorio(firmanteDTO.isObligatorio());
				
				asignacionRepository.save(firmante);
			
		}
		
		
		Asignacion destinatario = null;
		for(AsignacionDTO destinatarioDTO: docToSave.getFirmantes()) {
			destinatario = new Asignacion();
			destinatario.setIdDocumento(document);
			
			Usuario destinatarioArrId = getUsuarioIdByCorreo(usuarioRepository.findByCorreo(destinatarioDTO.getCorreo()));		
			destinatario.setIdUsuario(destinatarioArrId);
			
			CatAccion tipoAccionId = getCatalogAccionIdByCode(catAccionRepository.findByCode(destinatarioDTO.getAccionCode()));
			destinatario.setIdAccion(tipoAccionId);
			
			CatEstado estadoAccionId = getCatalogEstadoIdByCode(catEstadoRepository.findByCode(destinatarioDTO.getEstadoAccionCode()));
			destinatario.setIdEstadoaccion(estadoAccionId);
			
			CatTipoAsociacion tipoAsociacionId = getCatalogTipoAsociacionIdByCode(catTipoAsociacionRepository.findByCode("DESTINATARIO"));
			destinatario.setIdTipoasociacion(tipoAsociacionId);
			
			destinatario.setObligatorio(destinatarioDTO.isObligatorio());
			
			asignacionRepository.save(destinatario);
		}
		
		Configuracion config = new Configuracion();
		config.setOrdenFirma(docToSave.getConfiguracion().getOrdenFirma());
		config.setModoCaptura(docToSave.getConfiguracion().isModoCaptura());
		config.setGeneraNumeroOficio(docToSave.getConfiguracion().getGeneraNumeroOficio());
		config.setFechaLimite(docToSave.getConfiguracion().getFechaLimite());
		config.setDocumentoFirmar(docToSave.getConfiguracion().getDocumento());
		//guarda en tabla config
		configuracionRepository.save(config);

		return document;
		
	}
	
	
	public DocumentoDTO getDocById(long idDocumento) {
		DocumentoDTO documentoDto = new DocumentoDTO();
		Optional<Documento> document = documentoRepository.findById(idDocumento);
		if (document.isPresent()) {	
			documentoDto.setIdDocumento(document.get().getIdDocumento());
			documentoDto.setFolio(document.get().getFolio());
			documentoDto.setFolioDocumento(document.get().getFolioDocumento());
			documentoDto.setNumeroExpediente(document.get().getNumeroExpediente());
			documentoDto.setNombreExpediente(document.get().getNombreExpediente());
			documentoDto.setFechaDocumento(document.get().getFechaDocumento());
			documentoDto.setAsunto(document.get().getAsunto());
			documentoDto.setContenido(document.get().getContenido());
			documentoDto.setNota(document.get().getNota());
			documentoDto.setDocumento(document.get().getDocumento());
			documentoDto.setTipoUsoCode(document.get().getIdTipouso().getCode());
			documentoDto.setAreaDestinoCode(document.get().getIdAreadestino().getCode());
			documentoDto.setAreaCopiaCode(document.get().getIdAreacopia().getCode());
			documentoDto.setTipoDocumentoCode(document.get().getIdTipodocumento().getCode());
			documentoDto.setDestinatario(document.get().getIdDestinatario().getCorreo());
			documentoDto.setElaboro(document.get().getIdElaboro().getCorreo());
			documentoDto.setEstadoDocumentoCode(document.get().getIdEstadodocumento().getCode());
			
			return documentoDto;
		}
		return null;
	}
	
	
/*
	public File getDocFirmadoByDocumentoId(int idDocumento) {
		Optional<Firma> docFirmado = documentoFirmadoRepository.findById(idDocumento);
		if (docFirmado.isPresent()) {
			String docFirmadoBase64 = docFirmado.get().getArchivoFirmadoBase64();
			try {
				// Decodificar la cadena Base64 a bytes
				byte[] decodedBytes = java.util.Base64.getDecoder().decode(docFirmadoBase64);
				Random random = new Random();

				// Generar un número aleatorio de 5 dígitos
				int numeroAleatorio = random.nextInt(90000) + 10000;

				// Crear un archivo y escribir los bytes decodificados
				File outputFile = new File("docFirmado-" + numeroAleatorio + ".pdf");
				FileOutputStream outputStream = new FileOutputStream(outputFile);
				outputStream.write(decodedBytes);
				outputStream.close();

				return outputFile;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public DocumentoDTO getDocById(int idDocumento) {

		DocumentoDTO docDetail = null;

		Optional<Documento> document = documentoRepository.findById(idDocumento);
		if (document.isPresent()) {
			docDetail = new DocumentoDTO();
			docDetail.setIdDocumento(document.get().getId());
			String llave1SoloUso = document.get().getHashActualSha256()
					+ document.get().getHashAnteriorSha256();
			String llave1SoloUsoSha256 = firmaDocumentoService.generateSHA256(llave1SoloUso);
			docDetail.setLlave1soloUso(llave1SoloUsoSha256);
			
			List<Firma> docsFirmados = documentoFirmadoRepository.findByDocumentoIdOrderByTimeStampDesc(idDocumento);
			if (docsFirmados.size() > 0) {
				docDetail
						.setUrlDescarga("http://localhost:8080/api/documentos/download/" + docsFirmados.get(0).getId());
				docDetail.setDocumentoFirmadoBase64(docsFirmados.get(0).getArchivoFirmadoBase64());
				List<Integer> firmantes = new ArrayList<Integer>();
				for(int i=0;i<docsFirmados.size();i++) {
					firmantes.add(docsFirmados.get(i).getUsuarioId());
				}
				docDetail.setFirmantes(firmantes);
			}
			
			
			docDetail.setFhCreacion(document.get().getFhCreacion());
			docDetail.setFhModificacion(document.get().getFhModificacion());
			docDetail.setUsuCreacion(1);
			docDetail.setNombreDocumento(document.get().getNombre());
		}

		return docDetail;
	}

	public List<DocumentoReviewDTO> getListDocumentsByUsuario(int idUsuario) {

		List<DocumentoReviewDTO> docList = new ArrayList<DocumentoReviewDTO>();
		DocumentoReviewDTO docDTO = null;
		Optional<Documento> document = null;
		List<Integer> listDocsUnicos = new ArrayList<Integer>();
		
		List<Firma> documents = documentoFirmadoRepository.findByUsuarioId(idUsuario);
		for (int i = 0; i < documents.size(); i++) {
			if(!listDocsUnicos.contains(documents.get(i).getDocumentoId())) {
				listDocsUnicos.add(documents.get(i).getDocumentoId());
			}
		}
		List<Documento> docs = documentoRepository.findAllByUsuCreacion(idUsuario);
		for (int i = 0; i < docs.size(); i++) {
			if(!listDocsUnicos.contains(docs.get(i).getId())) {
				listDocsUnicos.add(docs.get(i).getId());
			}
		}
		List<Firma> docsFirmados = null;
		List<Integer> firmantes = null;
		for (int i = 0; i < listDocsUnicos.size(); i++) {
			document = documentoRepository.findById(listDocsUnicos.get(i));
			if (document.isPresent()) {
				docsFirmados = documentoFirmadoRepository.findByDocumentoIdOrderByTimeStampDesc(listDocsUnicos.get(i));
				firmantes = new ArrayList<Integer>();
				for(int j=0;j<docsFirmados.size();j++) {
					firmantes.add(docsFirmados.get(j).getUsuarioId());
				}
				
				docDTO = new DocumentoReviewDTO();
				docDTO.setIdDocumento(document.get().getId());
				// docDTO.setDocumentoFirmadoBase64(documents.get(i).getArchivoFirmadoBase64());
				docDTO.setFhCreacion(document.get().getFhCreacion());
				docDTO.setFhModificacion(document.get().getFhModificacion());
				docDTO.setUsuCreacion(document.get().getUsuCreacion());
				docDTO.setUsuModificacion(document.get().getUsuModificacion());
				docDTO.setNombreDocumento(document.get().getNombre());
				docDTO.setFirmantes(firmantes);
				docList.add(docDTO);
			}
		}
		return docList;
	}
	
	public boolean validateSha256(String llave, int idDocumento, int usuarioId) {
		Optional<Firma> docFirmados = documentoFirmadoRepository
				.findByDocumentoIdAndUsuarioId(idDocumento, usuarioId);
		if(docFirmados.isPresent()) {
			String llave1SoloUso = docFirmados.get().getHashActualSha256()+docFirmados.get().getHashAnteriorSha256();
			String llave1SoloUsoSha256 = firmaDocumentoService.generateSHA256(llave1SoloUso);
			if (llave.equals(llave1SoloUsoSha256)) {
				return true;
			}
		}
		return false;
	}
	
	public PayLoadDTO deleteDocumentoById(int idDocumento, int usuarioId, String llave) {
		Optional<Documento> document = documentoRepository.findById(idDocumento);
		PayLoadDTO payload = new PayLoadDTO();
		if(document.isPresent()) {
			if(document.get().getUsuCreacion()==usuarioId) {
				List<Firma> documentsFirmados = documentoFirmadoRepository.findByDocumentoIdOrderByTimeStampDesc(idDocumento);
				if(documentsFirmados.size()==1) {
					if(documentsFirmados.get(0).getUsuarioId() == usuarioId) {
						
						if(validateSha256(llave,idDocumento,usuarioId)) {
							payload.setMessage("El documento se ha eliminado exitósamente");
							payload.setData(getDocById(idDocumento));
							documentoRepository.deleteById(idDocumento);
							documentoFirmadoRepository.deleteById(documentsFirmados.get(0).getId());
						}else {
							payload.setMessage("La llave de un solo uso es incorrecta");
						}
					}else {
						payload.setMessage("No es posible borrar el documento porque alguien ya lo firmó");
					}
				}else if(documentsFirmados.size()==0){
					if(validateSha256(llave,idDocumento,usuarioId)) {
						payload.setMessage("El documento se ha eliminado exitósamente, nadie lo había firmado");
						payload.setData(getDocById(idDocumento));
						documentoRepository.deleteById(idDocumento);
					}else {
						payload.setMessage("La llave de un solo uso es incorrecta");
					}
					
				}else {
					payload.setMessage("No es posible borrar el documento porque está firmado por más usuarios");
				}
			}else {
				payload.setMessage("No es posible borrar el documento porque no lo creaste tú");
			}
		}else {
			payload.setMessage("No es posible borrar el documento porque no existe");
		}
		return payload;
	}

*/	

}
