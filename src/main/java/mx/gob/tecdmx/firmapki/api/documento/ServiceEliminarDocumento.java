package mx.gob.tecdmx.firmapki.api.documento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentoWorkflowRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceEliminarDocumento {
	
	@Autowired
	TabDocumentosRepository tabDocumentoRepository;
	
	@Autowired
	TabDocumentoWorkflowRepository tabDocumentoWorkflowRepository;

	public DTOResponse eliminarDocumento(int idDocumento, DTOResponse res, DTOResponseUserInfo userInfo) {
		// busca el documento a eliminar
		Optional<TabDocumentos> documentExist = tabDocumentoRepository.findById(idDocumento);
		if (userInfo.getData().getIdEmpleado() == (documentExist.get().getIdNumEmpleadoCreador().getId())) {
			List<TabDocumentoWorkflow> docWorkflowList = tabDocumentoWorkflowRepository
					.findByIdDocumentOrderByWorkflowFecha(documentExist.get());
			// verifica la etapa del documento 00''||
			if (docWorkflowList.get(0).getIdEtapaDocumento().getDescetapa()
					.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion())) {
				if (documentExist.isPresent()) {
					documentExist.get().setVisible(false);
					tabDocumentoRepository.save(documentExist.get());
					res.setMessage("Se ha eliminado el documento");
					res.setStatus("Success");
					return res;
				}
				res.setMessage("No se pudo realizar la operación");
				res.setStatus("fail");
				return res;

			}

		}
		res.setMessage("No cuentas con permisos para realizar la operación");
		res.setStatus("fail");
		return res;

	}
}
