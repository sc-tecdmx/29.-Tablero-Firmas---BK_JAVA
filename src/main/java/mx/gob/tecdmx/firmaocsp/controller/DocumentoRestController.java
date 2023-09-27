package mx.gob.tecdmx.firmaocsp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.tecdmx.firmaocsp.dto.DocumentoDTO;
import mx.gob.tecdmx.firmaocsp.entity.Documento;
import mx.gob.tecdmx.firmaocsp.service.DocumentoService;

@RestController
@RequestMapping(path = "/api/documentos")
public class DocumentoRestController {

	@Autowired
	private DocumentoService documentoService;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/", produces = "application/json")
	public ResponseEntity<Documento> uploadDocumento(@RequestBody DocumentoDTO documento) {
		
		try {
			
		    Documento doc = documentoService.crearDocumento(documento);
		    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(doc);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(null);
	}
/*
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, path = "/usuario/{id}", produces = "application/json")
	public ResponseEntity<List<DocumentoReviewDTO>> getListDocsByIdUsuario(@PathVariable int id) {
		// Pageable paging = PageRequest.of(page, size);

		List<DocumentoReviewDTO> listDocs = documentoService.getListDocumentsByUsuario(id);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(listDocs);
	}
	
	
*/
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, path = "/{documentId}", produces = "application/json")
	public ResponseEntity<DocumentoDTO> getDocumentById(@PathVariable int documentId) throws IOException {
		try {
			
			DocumentoDTO doc = documentoService.getDocById(documentId);
		    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(doc);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(null);

	}
	
/*
	@GetMapping("/download/{documentId}")
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable int documentId) throws IOException {

		File docFirmado = documentoService.getDocFirmadoByDocumentoId(documentId);

		if (docFirmado != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + docFirmado.getName());

			InputStreamResource resource = new InputStreamResource(new FileInputStream(docFirmado));

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.contentLength(docFirmado.length()).body(resource);
		} else {
			// Handle case where documentId doesn't correspond to a file
			return ResponseEntity.notFound().build();
		}
	}
/*
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.DELETE, path = "/{documentId}", produces = "application/json")
	public ResponseEntity<PayLoadDTO> deleteDocumento(@PathVariable int documentId, @RequestHeader("llave1soloUso") String llave1soloUso, @RequestHeader("usuarioId") int usuarioId) {
		PayLoadDTO payload= documentoService.deleteDocumentoById(documentId, usuarioId, llave1soloUso);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(payload);
	}
*/	
	

}
