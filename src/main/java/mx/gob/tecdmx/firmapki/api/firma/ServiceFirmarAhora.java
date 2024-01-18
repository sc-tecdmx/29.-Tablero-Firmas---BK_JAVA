package mx.gob.tecdmx.firmapki.api.firma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.api.documento.DTOConfiguracion;
import mx.gob.tecdmx.firmapki.api.documento.DTODocAdjunto;
import mx.gob.tecdmx.firmapki.api.documento.DTOFirmanteDestinatario;
import mx.gob.tecdmx.firmapki.api.documento.PayloadAltaDocumento;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.entity.pki.PkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumento;
import mx.gob.tecdmx.firmapki.entity.pki.PkiDocumentoFirmantes;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDestinoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatEtapaDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstDest;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatInstFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatPrioridad;
import mx.gob.tecdmx.firmapki.entity.tab.TabCatTipoDocumento;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocConfig;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocDestinatarios;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocsFirmantes;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentoWorkflow;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentos;
import mx.gob.tecdmx.firmapki.entity.tab.TabDocumentosAdjuntos;
import mx.gob.tecdmx.firmapki.entity.tab.TabExpedientes;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatFirmaAplicadaRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiCatTipoFirmaRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDestinoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatEtapaDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatInstDestRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatInstFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatPrioridadRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabCatTipoDocumentoRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocConfigRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocDestinatariosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocsFirmantesRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosAdjuntosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabDocumentosRepository;
import mx.gob.tecdmx.firmapki.repository.tab.TabExpedientesRepository;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatFirmaAplicada;
import mx.gob.tecdmx.firmapki.utils.enums.EnumPkiCatTipoFirma;
import mx.gob.tecdmx.firmapki.utils.enums.EnumTabCatEtapaDocumento;

@Service
public class ServiceFirmarAhora {

	@Autowired
	TabExpedientesRepository tabExpedientesRepository;

	@Autowired
	TabCatDocConfigRepository tabCatDocConfigRepository;

	@Autowired
	TabDocConfigRepository tabConfigDocumentoRepository;

	@Autowired
	TabCatTipoDocumentoRepository tabCatTipoDocumentoRepository;

	@Autowired
	TabCatDestinoDocumentoRepository tabCatDestinoDocumentoRepository;

	@Autowired
	TabCatPrioridadRepository tabCatPrioridadRepository;

	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;

	@Autowired
	TabCatInstFirmantesRepository tabCatInstFirmantesRepository;

	@Autowired
	TabCatInstDestRepository tabCatInstDestRepository;

	@Autowired
	TabCatEtapaDocumentoRepository tabCatEtapaDocumentoRepository;

	@Autowired
	PkiCatTipoFirmaRepository pkiCatTipoFirmaRepository;

	@Autowired
	PkiCatFirmaAplicadaRepository pkiCatFirmaAplicadaRepository;

	@Autowired
	TabDocumentosRepository tabDocumentoRepository;

	@Autowired
	TabDocumentosAdjuntosRepository tabDocumentosAdjuntosRepository;

	@Autowired
	TabDocsFirmantesRepository tabDocsFirmantesRepository;

	@Autowired
	TabDocDestinatariosRepository tabDocDestinatariosRepository;

	@Autowired
	ServiceFirmar serviceFirmar;

	public TabCatEtapaDocumento findEtapaDocumento(String etapa, DTOResponse res) {
		Optional<TabCatEtapaDocumento> etapaDoc = tabCatEtapaDocumentoRepository.findByDescetapa(etapa);
		if (!etapaDoc.isPresent()) {
			res.setMessage("Dentro del catálogo para el workflow no se encontró la etapa " + etapa);
			res.setStatus("fail");
			return null;
		}
		return etapaDoc.get();
	}

	public PkiCatTipoFirma findTipoFirma(String tipofirma, DTOResponse res) {
		Optional<PkiCatTipoFirma> tipoFirmaCat = pkiCatTipoFirmaRepository.findByDescTipoFirma(tipofirma);
		if (!tipoFirmaCat.isPresent()) {
			res.setMessage("No se encontró el tipo de firma: " + tipofirma);
			res.setStatus("fail");
			return null;
		}
		return tipoFirmaCat.get();
	}

	public TabExpedientes findNumExpediente(String numExpediente, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (numExpediente != null) {
			Optional<TabExpedientes> numExpedienteOpt = tabExpedientesRepository.findByNumExpediente(numExpediente);
			if (numExpedienteOpt.isPresent()) {
				documentoAlta.setExpediente(numExpedienteOpt.get());
				return numExpedienteOpt.get();
			}
		}
		res.setMessage("No se encontró el número de expediente " + numExpediente);
		res.setStatus("fail");
		return null;
	}

	public TabCatTipoDocumento findTipoDocumento(Integer tipoDoc, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (tipoDoc > 0) {
			// Optional<TabCatTipoDocumento> tipoDocOpt =
			// tabCatTipoDocumentoRepository.findByDescTipoDocumento(tipoDoc);
			Optional<TabCatTipoDocumento> tipoDocOpt = tabCatTipoDocumentoRepository.findById(tipoDoc);

			if (tipoDocOpt.isPresent()) {
				documentoAlta.setTipoDoc(tipoDocOpt.get());
				return tipoDocOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de documento " + tipoDoc);
		res.setStatus("fail");
		return null;
	}

	public TabCatDestinoDocumento findTipoDestino(String tipoDest, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (tipoDest != null) {
			Optional<TabCatDestinoDocumento> tipoDestOpt = tabCatDestinoDocumentoRepository
					.findByDescDestinoDocumento(tipoDest);
			if (tipoDestOpt.isPresent()) {
				documentoAlta.setDestinoDoc(tipoDestOpt.get());
				return tipoDestOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de destino " + tipoDest);
		res.setStatus("fail");
		return null;
	}

	public TabCatPrioridad findTipoPrioridad(String tipoPrioridad, DAOAltaDocumento documentoAlta, DTOResponse res) {
		if (tipoPrioridad != null) {
			Optional<TabCatPrioridad> tipoPrioridadOpt = tabCatPrioridadRepository.findByDescPrioridad(tipoPrioridad);
			if (tipoPrioridadOpt.isPresent()) {
				documentoAlta.setPrioridad(tipoPrioridadOpt.get());
				return tipoPrioridadOpt.get();
			}
		}
		res.setMessage("No se encontró el tipo de prioridad " + tipoPrioridad);
		res.setStatus("fail");
		return null;
	}

	public InstEmpleado findEmpleado(int idEmpleado, DTOResponse res) {
		Optional<InstEmpleado> empleadoFirmante = instEmpleadoRepository.findById(idEmpleado);
		if (empleadoFirmante.isPresent()) {
			return empleadoFirmante.get();
		}
		res.setMessage("No se encontró el empleado con número de identificación: " + idEmpleado);
		res.setStatus("fail");
		return null;
	}

	public TabCatInstFirmantes findInstruccionFirmante(String instruccion, DTOResponse res) {
		Optional<TabCatInstFirmantes> instruccionFirmante = tabCatInstFirmantesRepository
				.findByDescInstrFirmante(instruccion);
		if (instruccionFirmante.isPresent()) {
			return instruccionFirmante.get();
		}
		res.setMessage("No se encontró la instrucción del firmante:" + instruccion);
		res.setStatus("fail");
		return null;
	}

	public TabCatInstDest findInstruccionDestinatario(String instruccion, DTOResponse res) {
		Optional<TabCatInstDest> instruccionDestinatario = tabCatInstDestRepository.findByDescInsDest(instruccion);
		if (instruccionDestinatario.isPresent()) {
			return instruccionDestinatario.get();
		}
		res.setMessage("No se encontró la instrucción del destinatario: " + instruccion);
		res.setStatus("fail");
		return null;
	}

	public PkiCatFirmaAplicada findFirmaAplicada(String tipoFirmaAplicada, DTOResponse res) {
		Optional<PkiCatFirmaAplicada> firmaAplicada = pkiCatFirmaAplicadaRepository
				.findByDescFirmaAplicada(tipoFirmaAplicada);// No se obtiene del payload, ya que este es un proceso de
															// firmado, las opciones se deben controlar aquí
		if (!firmaAplicada.isPresent()) {
			res.setMessage("No se encontró el tipo de firma aplicada " + tipoFirmaAplicada);
			res.setStatus("fail");
			return null;
		}
		return firmaAplicada.get();
	}

	public boolean validateCatalogos(String numExpedienteInp, Integer tipoDocumentoInp, String tipoDestinoInp,
			String tipoPrioridadInp, DAOAltaDocumento documentoAlta, DTOResponse res) {

		TabExpedientes numExpediente = findNumExpediente(numExpedienteInp, documentoAlta, res);
		TabCatTipoDocumento tipoDOc = findTipoDocumento(tipoDocumentoInp, documentoAlta, res);
		TabCatDestinoDocumento tipoDestino = findTipoDestino(tipoDestinoInp, documentoAlta, res);
		TabCatPrioridad tipoPrioridad = findTipoPrioridad(tipoPrioridadInp, documentoAlta, res);
		if (numExpediente == null || tipoDOc == null || tipoDestino == null || tipoPrioridad == null) {
			return false;
		}
		return true;
	}

	public boolean validateCatalogosEscritorio(String tipoPrioridadInp, DAOAltaDocumento documentoAlta,
			DTOResponse res) {
		TabCatPrioridad tipoPrioridad = findTipoPrioridad(tipoPrioridadInp, documentoAlta, res);
		if (tipoPrioridad == null) {
			return false;
		}
		return true;
	}

	public boolean validateFirmantesExist(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res) {
		// Validamos que los datos de los firmantes existan
		DAOFirmanteDestinatario firmante = null;
		for (DTOFirmanteDestinatario firmantes : payload.getFirmantes()) {
			firmante = new DAOFirmanteDestinatario();

			firmante.setSecuencia(firmantes.getSecuencia());

			InstEmpleado empleadoFirmante = findEmpleado(firmantes.getIdEmpleado(), res);
			if (empleadoFirmante == null) {
				return false;
			}
			firmante.setEmpleado(empleadoFirmante);

			TabCatInstFirmantes instruccionFirmante = findInstruccionFirmante(firmantes.getInstruccion(), res);
			if (instruccionFirmante == null) {
				return false;
			}
			firmante.setInstruccionFirmante(instruccionFirmante);

			documentoAlta.getFirmantes().add(firmante);
		}
		return true;
	}

	public boolean validateDestinatariosExist(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res) {
		// Validamos que los datos de los firmantes existan
		DAOFirmanteDestinatario destinatario = null;
		for (DTOFirmanteDestinatario destinatarios : payload.getDestinatarios()) {
			destinatario = new DAOFirmanteDestinatario();

			destinatario.setSecuencia(destinatarios.getSecuencia());

			InstEmpleado empleadoFirmante = findEmpleado(destinatarios.getIdEmpleado(), res);
			if (empleadoFirmante == null) {
				return false;
			}
			destinatario.setEmpleado(empleadoFirmante);

			TabCatInstDest instruccionDest = findInstruccionDestinatario(destinatarios.getInstruccion(), res);
			if (instruccionDest == null) {
				return false;
			}
			destinatario.setInstruccionDest(instruccionDest);

			documentoAlta.getDestinatarios().add(destinatario);
		}
		return true;
	}

	public boolean validateOrdenAndSecuenciaFirmantesModoCaptura(PayloadAltaDocumento payload,
			DAOAltaDocumento documentoAlta, DTOResponseUserInfo userInfo, DTOResponse res) {
		if (payload.isEnOrden()) {
			// Ordenamos la lista conforme a la secuencia dada por el usuario
			Collections.sort(payload.getFirmantes(), (o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));
			Collections.sort(documentoAlta.getFirmantes(),
					(o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

			// Verificamos que la numeración de la secuencia no este corrupta (que se salte
			// números)
			int secuencia = 1;
			for (DTOFirmanteDestinatario firmante : payload.getFirmantes()) {
				if (firmante.getSecuencia() != secuencia) {
					res.setMessage("La secuencia en los firmantes se salta la numeración");
					res.setStatus("fail");
					return false;
				}
				secuencia++;
			}
			return true;
		}
		return true;
	}

	public boolean validateOrdenAndSecuenciaFirmantesFirmarAhora(PayloadAltaDocumento payload,
			DAOAltaDocumento documentoAlta, DTOResponseUserInfo userInfo, DTOResponse res) {
		if (payload.isEnOrden()) {
			// Ordenamos la lista conforme a la secuencia dada por el usuario
			Collections.sort(payload.getFirmantes(), (o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));
			Collections.sort(documentoAlta.getFirmantes(),
					(o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

			// Verificamos que la numeración de la secuencia no este corrupta (que se salte
			// números)
			int secuencia = 1;
			for (DTOFirmanteDestinatario firmante : payload.getFirmantes()) {
				if (firmante.getSecuencia() != secuencia) {
					res.setMessage("La secuencia en los firmantes se salta la numeración");
					res.setStatus("fail");
					return false;
				}
				secuencia++;
			}

			DTOFirmanteDestinatario firmante1 = payload.getFirmantes().get(0);
			// Como la opción es Firmar ahora, debemos verificar que el que va a firmar
			// ahora sea el primero en la lista y si no, entonces lo ponemos como primero
			if (firmante1.getIdEmpleado() != userInfo.getData().getEmpleado().getId()) {
				res.setMessage(
						"Para permitir la opción firmar ahora, debes estar en la lista de firmantes y tener como número 1 en el orden de secuencia de firma");
				res.setStatus("fail");
				return false;
			}
			return true;
		} else {
			boolean isInList = false;
			for (DTOFirmanteDestinatario firmante : payload.getFirmantes()) {
				if (firmante.getIdEmpleado() == userInfo.getData().getIdEmpleado()) {
					isInList = true;
				}
			}
			if (!isInList) {
				res.setMessage("Para poder firmar debes agregarte como usuario firmante");
				res.setStatus("fail");
				return false;
			}
			return true;
		}
	}

	public boolean validateArchivoNuevoInDataBase(List<DTODocAdjunto> documentosAdjuntos, DTOResponse res) {
		int pos = 1;
		for (DTODocAdjunto docAdjuntoPayload : documentosAdjuntos) {
			boolean archivoExist = serviceFirmar.documentNotExistInDataBase(docAdjuntoPayload.getDocBase64(), pos, res);
			if (!archivoExist) {
				return false;
			}
			pos++;
		}
		return true;
	}

	public boolean validarArchivosUnicos(List<DTODocAdjunto> documentosAdjuntos, DTOResponse res) {
		Set<String> documentosUnicos = new HashSet<>();

		for (DTODocAdjunto documento : documentosAdjuntos) {
			if (!documentosUnicos.add(documento.getDocBase64())) {
				res.setMessage("No se puede cargar dos veces el mismo archivo");
				res.setStatus("fail");
				return false; // Se encontró un documento repetido
			}
		}
		return true; // Todos los documentos son únicos
	}

	public boolean validateDataModoCaptura(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponseUserInfo userInfo, DTOResponse res) {
		// Validamos que los datos de catálogos existan
		boolean catalogosValid = validateCatalogos(payload.getNumExpediente(), payload.getTipoDocumento(),
				payload.getTipoDestino(), payload.getTipoPrioridad(), documentoAlta, res);
		if (!catalogosValid) {
			return false;
		}

		if (payload.getFirmantes() != null) {

			boolean firmantesExist = validateFirmantesExist(payload, documentoAlta, res);
			if (!firmantesExist) {
				return false;
			}

			boolean ordenAndSecuenciaValid = validateOrdenAndSecuenciaFirmantesModoCaptura(payload, documentoAlta,
					userInfo, res);
			if (!ordenAndSecuenciaValid) {
				return false;
			}
		}

		if (payload.getDestinatarios() != null) {
			boolean destinatariosExist = validateDestinatariosExist(payload, documentoAlta, res);
			if (!destinatariosExist) {
				return false;
			}
		}

		if (payload.getDocumentosAdjuntos() != null) {
			boolean archivosUnicos = validarArchivosUnicos(payload.getDocumentosAdjuntos(), res);
			if (!archivosUnicos) {
				return false;
			}

			boolean isNuevoArchivoInDB = validateArchivoNuevoInDataBase(payload.getDocumentosAdjuntos(), res);
			if (!isNuevoArchivoInDB) {
				return false;
			}
		}

		return true;

	}

	public boolean validateDataFirmarAhoraEscritorio(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponseUserInfo userInfo, DTOResponse res) {

		boolean catalogosValid = validateCatalogosEscritorio(payload.getTipoPrioridad(), documentoAlta, res);
		if (!catalogosValid) {
			return false;
		}

		// Validamo que el que firma se haya agregado a la lista de firmantes
		if (payload.getFirmantes().size() < 1) {
			res.setMessage(
					"La lista de frirmantes está vacía y para poder firmar debes agregarte como usuario firmante");
			res.setStatus("fail");
			return false;
		}

		payload.getFirmantes().get(0).setIdEmpleado(userInfo.getData().getIdEmpleado());
		payload.getFirmantes().get(0).setSecuencia(0);

		boolean firmantesExist = validateFirmantesExist(payload, documentoAlta, res);
		if (!firmantesExist) {
			return false;
		}

		boolean archivosUnicos = validarArchivosUnicos(payload.getDocumentosAdjuntos(), res);
		if (!archivosUnicos) {
			return false;
		}

		boolean isNuevoArchivoInDB = validateArchivoNuevoInDataBase(payload.getDocumentosAdjuntos(), res);
		if (!isNuevoArchivoInDB) {
			return false;
		}

		return true;
	}

	public boolean validateDataFirmarAhora(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponseUserInfo userInfo, DTOResponse res) {
		// Validamos que los datos de catálogos existan
		boolean catalogosValid = validateCatalogos(payload.getNumExpediente(), payload.getTipoDocumento(),
				payload.getTipoDestino(), payload.getTipoPrioridad(), documentoAlta, res);
		if (!catalogosValid) {
			return false;
		}
		// Validamo que el que firma se haya agregado a la lista de firmantes
		if (payload.getFirmantes().size() < 1) {
			res.setMessage(
					"La lista de frirmantes está vacía y para poder firmar debes agregarte como usuario firmante");
			res.setStatus("fail");
			return false;
		}

		boolean firmantesExist = validateFirmantesExist(payload, documentoAlta, res);
		if (!firmantesExist) {
			return false;
		}

		boolean destinatariosExist = validateDestinatariosExist(payload, documentoAlta, res);
		if (!destinatariosExist) {
			return false;
		}

		boolean ordenAndSecuenciaValid = validateOrdenAndSecuenciaFirmantesFirmarAhora(payload, documentoAlta, userInfo,
				res);
		if (!ordenAndSecuenciaValid) {
			return false;
		}

		boolean archivosUnicos = validarArchivosUnicos(payload.getDocumentosAdjuntos(), res);
		if (!archivosUnicos) {
			return false;
		}

		boolean isNuevoArchivoInDB = validateArchivoNuevoInDataBase(payload.getDocumentosAdjuntos(), res);
		if (!isNuevoArchivoInDB) {
			return false;
		}

		return true;

	}

	public boolean storeDocumentosAdjuntosDesktop(List<DTODocAdjunto> documentosAdjuntos, TabDocumentos documentoStored,
			DTOResponse res, DTOResponseUserInfo userInfo) {
		Integer numDocumento = 1;
		for (DTODocAdjunto docAdjuntoPayload : documentosAdjuntos) {
			TabDocumentosAdjuntos docAdjuntoStored = serviceFirmar.storeDocumento("escritorio", documentoStored,
					docAdjuntoPayload.getDocBase64(), docAdjuntoPayload.getFileType(), numDocumento, res);
			if (docAdjuntoStored == null) {
				return false;
			}
			numDocumento++;
		}
		return true;
	}

	public boolean storeDocumentosAdjuntos(List<DTODocAdjunto> documentosAdjuntos, TabDocumentos documentoStored,
			DTOResponse res, DTOResponseUserInfo userInfo) {
		Integer numDocumento = 1;
		for (DTODocAdjunto docAdjuntoPayload : documentosAdjuntos) {
			TabDocumentosAdjuntos docAdjuntoStored = serviceFirmar.storeDocumento(documentoStored,
					docAdjuntoPayload.getDocBase64(), docAdjuntoPayload.getFileType(), numDocumento, res);
			if (docAdjuntoStored == null) {
				return false;
			}
			numDocumento++;
		}
		return true;
	}

	public boolean storePkiDocumento(List<TabDocumentosAdjuntos> documentosAdjuntos, String status, boolean isEnOrden,
			InstEmpleado empleadoCreador, InstEmpleado empleadoEnvio, Date fechaCreacion, Date fechaEnvio,
			DTOResponse res) {

		for (TabDocumentosAdjuntos docAdjunto : documentosAdjuntos) {

			PkiDocumento pkiDocStored = serviceFirmar.createPKIDocumento(docAdjunto.getDocumentoBase64(),
					empleadoCreador, empleadoEnvio, fechaEnvio, fechaCreacion, status, false, isEnOrden, res);

			if (pkiDocStored == null) {
				return false;
			}
		}
		return true;
	}

	public boolean storePkiDocumentoFirmantes(List<TabDocumentosAdjuntos> documentosAdjuntos,
			TabDocumentos documentoStored, PkiCatTipoFirma tipoFirma, List<TabDocsFirmantes> firmantes,
			DTOResponse res) {
		for (TabDocumentosAdjuntos docAdjunto : documentosAdjuntos) {
			for (TabDocsFirmantes currentFirmante : firmantes) {
				Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(currentFirmante.getIdNumEmpleado());
				SegOrgUsuarios usuario = empleado.get().getIdUsuario();
				int idUsuario = usuario.getnIdUsuario();
				if (!serviceFirmar.firmanteExistInPKIDocumentoFirmantes(docAdjunto.getDocumentoHash(),
						empleado.get().getIdUsuario())) {
					PkiDocumentoFirmantes documentoFirmantesStored = serviceFirmar.createPKIDocumentoFirmantes(
							docAdjunto.getDocumentoHash(), empleado.get().getIdUsuario(), empleado.get(),
							currentFirmante.getSecuencia(), documentoStored.getFechaLimiteFirma(), tipoFirma, res);

					if (documentoFirmantesStored == null) {
						return false;
					}
				}
			}

		}
		return true;
	}

	public boolean storePkiDocumentoFirmantes(List<TabDocumentosAdjuntos> documentosAdjuntos,
			TabDocumentos documentoStored, PkiCatTipoFirma tipoFirma, TabDocsFirmantes currentFirmante,
			DTOResponse res) {
		for (TabDocumentosAdjuntos docAdjunto : documentosAdjuntos) {

			Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(currentFirmante.getIdNumEmpleado());

			PkiDocumentoFirmantes documentoFirmantesStored = serviceFirmar.createPKIDocumentoFirmantes(
					docAdjunto.getDocumentoHash(), empleado.get().getIdUsuario(), empleado.get(),
					currentFirmante.getSecuencia(), documentoStored.getFechaLimiteFirma(), tipoFirma, res);

			if (documentoFirmantesStored == null) {
				return false;
			}
		}
		return true;
	}

	public boolean storeFirmantes(TabDocumentos documentoStored, DAOAltaDocumento documentoAlta, DTOResponse res) {

		Collections.sort(documentoAlta.getFirmantes(),
				(o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

		// Se almacenan los firmantes
		for (DAOFirmanteDestinatario firmante : documentoAlta.getFirmantes()) {
			TabDocsFirmantes firmanteStored = serviceFirmar.storeFirmante(documentoStored, firmante.getEmpleado(),
					firmante.getInstruccionFirmante(), firmante.getSecuencia(), res);
			if (firmanteStored == null) {
				return false;
			}
		}
		return true;
	}

	public boolean editedFirmantes(TabDocumentos documentoStored, DAOAltaDocumento documentoAlta, DTOResponse res) {

		List<TabDocsFirmantes> firmantesExistentes = tabDocsFirmantesRepository
				.findByIdDocumento(documentoStored.getId());
		for (TabDocsFirmantes firmante : firmantesExistentes) {
			tabDocsFirmantesRepository.delete(firmante);
		}

		Collections.sort(documentoAlta.getFirmantes(),
				(o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

		// Se almacenan los firmantes
		for (DAOFirmanteDestinatario firmante : documentoAlta.getFirmantes()) {
			TabDocsFirmantes firmanteStored = serviceFirmar.storeFirmante(documentoStored, firmante.getEmpleado(),
					firmante.getInstruccionFirmante(), firmante.getSecuencia(), res);
			if (firmanteStored == null) {
				return false;
			}
		}
		return true;
	}

	public boolean storeDestinatarios(TabDocumentos documentoStored, DAOAltaDocumento documentoAlta, DTOResponse res) {

		for (DAOFirmanteDestinatario destinatario : documentoAlta.getDestinatarios()) {

			TabDocDestinatarios destinatarioStored = serviceFirmar.storeDestinatario(documentoStored,
					destinatario.getEmpleado(), destinatario.getInstruccionDest(), res);
			if (destinatarioStored == null) {
				return false;
			}
		}
		return true;
	}

	public boolean editedDestinatarios(TabDocumentos documentoStored, DAOAltaDocumento documentoAlta, DTOResponse res) {

		List<TabDocDestinatarios> destinatariosExistentes = tabDocDestinatariosRepository
				.findByIdDocumento(documentoStored.getId());
		for (TabDocDestinatarios destinatario : destinatariosExistentes) {
			tabDocDestinatariosRepository.delete(destinatario);
		}

		for (DAOFirmanteDestinatario destinatario : documentoAlta.getDestinatarios()) {

			TabDocDestinatarios destinatarioStored = serviceFirmar.storeDestinatario(documentoStored,
					destinatario.getEmpleado(), destinatario.getInstruccionDest(), res);
			if (destinatarioStored == null) {
				return false;
			}
		}
		return true;
	}

	public boolean altaDocumentoModoCaptura(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res, DTOResponseUserInfo userInfo) {

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		boolean dataValid = validateDataModoCaptura(payload, documentoAlta, userInfo, res);
		if (!dataValid) {
			return false;
		}

		TabDocumentos documentoStored = serviceFirmar.storeTabDocumento(documentoAlta.getDestinoDoc(),
				documentoAlta.getTipoDoc(), documentoAlta.getPrioridad(), documentoAlta.getFolioEspecial(),
				documentoAlta.getExpediente(), documentoAlta.getAsunto(), documentoAlta.getNotas(),
				documentoAlta.getContenido(), documentoAlta.getFechaLimiteFirma(), documentoAlta.isEnOrden(), userInfo,
				res);

		if (documentoStored == null) {
			return false;
		}

		TabDocConfig configStored = serviceFirmar.storeTabDocConfig(payload.getConfiguraciones(), documentoStored, res);
		if (configStored == null) {
			return false;
		}

		boolean isDocAdjuntosStored = storeDocumentosAdjuntos(payload.getDocumentosAdjuntos(), documentoStored, res,
				userInfo);
		if (!isDocAdjuntosStored) {
			return false;
		}

		boolean isfirmantesStored = storeFirmantes(documentoStored, documentoAlta, res);
		if (!isfirmantesStored) {
			return false;
		}

		boolean isDestinatariosStored = storeDestinatarios(documentoStored, documentoAlta, res);
		if (!isDestinatariosStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_creado = findEtapaDocumento(EnumTabCatEtapaDocumento.CREADO.getOpcion(), res);
		if (etapaDoc_creado == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Creado = serviceFirmar.storeWorkFlow(documentoStored, etapaDoc_creado,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Creado == null) {
			return false;
		}

		res.setMessage("Se ha guardado el documento satisfactoriamente en modo captura");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return true;
	}

	public TabDocsFirmantes getCurrentFirmanteInList(List<TabDocsFirmantes> firmantes, InstEmpleado empleado,
			DTOResponse res) {
		if (firmantes == null) {
			res.setMessage("La lista de firmantes no puede estar vacía");
			res.setStatus("fail");
			return null;
		}

		TabDocsFirmantes currentFirmante = null;
		for (TabDocsFirmantes firmante : firmantes) {
			if (firmante.getIdNumEmpleado() == empleado.getId()) {
				currentFirmante = firmante;
				break;
			}
		}
		if (currentFirmante == null) {
			res.setMessage("El firmante a firmar no se encuentra dentro de la lista de firmantes");
			res.setStatus("fail");
			return null;
		}
		return currentFirmante;
	}

	public boolean puedeFirmarEnEtapaCreado(TabDocumentos documentoStored, List<TabDocsFirmantes> firmantes,
			DTOResponseUserInfo userInfo, DTOResponse res) {
		// validamos si el usuario que quiere firmar es el mismo quecreó el documento
		if (documentoStored.getIdUsuarioCreador() == userInfo.getData().getUser()) {
			// validamos si el documento s econfiguró para firmar en orden
			if (documentoStored.getEnOrden() == 1) {

				Collections.sort(firmantes, (o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));
				Optional<TabDocsFirmantes> resultado = firmantes.stream()
						.filter(firmante -> firmante.getIntEmpleado() == userInfo.getData().getEmpleado()).findFirst();
				if (resultado.get().getSecuencia() == 1) {
					return true;
				} else {
					res.setMessage(
							"Para poder firmar debes tener el número de secuencia 1, ya que el documento se connfiguró para firmar en orden");
					res.setStatus("fail");
					return false;
				}
			} else {
				return true;
			}

		} else {
			res.setMessage("El documento en etapa " + EnumTabCatEtapaDocumento.CREADO.getOpcion()
					+ " aún no puede ser firmado por otra persona distinta al creador");
			res.setStatus("fail");
			return false;
		}

	}

	public boolean firmar(TabDocumentos documentoStored, List<TabDocumentosAdjuntos> documentosAdjuntos,
			List<TabDocsFirmantes> firmantes, TabDocsFirmantes currentFirmante, DTOResponseUserInfo userInfo,
			DTOResponse res) {

		boolean archivosPkiDocumentoStored = storePkiDocumento(documentosAdjuntos,
				EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), documentoStored.getEnOrden() == 1 ? true : false,
				userInfo.getData().getEmpleado(), null, new Date(), null, res);
		if (!archivosPkiDocumentoStored) {
			return false;
		}

		String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
				: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
		PkiCatTipoFirma tipoFirma = findTipoFirma(tipofirma, res);
		if (tipoFirma == null) {
			return false;
		}

		boolean documentoFirmantesStored = storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored, tipoFirma,
				currentFirmante, res);
		if (!documentoFirmantesStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_enfirma = findEtapaDocumento(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), res);
		if (etapaDoc_enfirma == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Enfirma = serviceFirmar.storeWorkFlow(documentoStored, etapaDoc_enfirma,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Enfirma == null) {
			return false;
		}
		return true;
	}

	public boolean validateAllowedToEnviarDocumento(TabDocumentos documentoStored, DTOResponseUserInfo userInfo,
			DTOResponse res) {

		if (documentoStored.getIdUsuarioCreador() != userInfo.getData().getUser()) {
			res.setMessage("No puedes enviar el documento porque no eres el creador");
			res.setStatus("fail");
			return false;
		}

		String etapaSequence = serviceFirmar.getSequenceWorkflow(documentoStored, res);
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

	public boolean alguienYaFirmo(TabDocumentos documentoStored, DTOResponse res) {
		String etapaSequence = serviceFirmar.getSequenceWorkflow(documentoStored, res);
		if (etapaSequence == null) {
			return false;
		}
		if (etapaSequence.contains(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())) {
			return true;
		}
		return false;
	}

	public boolean enviarDocumento(int idTabDocumento, DTOResponseUserInfo userInfo, DTOResponse res) {

		TabDocumentos documentoStored = serviceFirmar.findTabDocumento(idTabDocumento, res);
		if (documentoStored == null) {
			return false;
		}

		boolean isAllowedToSend = validateAllowedToEnviarDocumento(documentoStored, userInfo, res);
		if (!isAllowedToSend) {
			return false;
		}

		List<TabDocsFirmantes> firmantes = serviceFirmar.getTabDocsFirmantes(documentoStored.getId());
		List<TabDocumentosAdjuntos> documentosAdjuntos = serviceFirmar.getTabDocumentosAdjuntos(documentoStored);

		boolean alguienHaFirmado = alguienYaFirmo(documentoStored, res);
		if (!alguienHaFirmado & res.getStatus() != null) {
			return false;
		}

		Collections.sort(firmantes, (o1, o2) -> Integer.compare(o1.getSecuencia(), o2.getSecuencia()));

		if (documentoStored.getEnOrden() == 1) {
			// Si ya lo ha firmado el firmante creador del documento
			if (alguienHaFirmado) {

				boolean updatedPkiDocumento = serviceFirmar.updateDataEnviadoInPkiDocumentosAdjuntos(documentosAdjuntos,
						new Date(), userInfo.getData().getEmpleado(), res);
				if (!updatedPkiDocumento) {
					return false;
				}

				PkiCatFirmaAplicada firmaAplicada = findFirmaAplicada(EnumPkiCatFirmaAplicada.FIRMADO.getOpcion(), res);
				if (firmaAplicada == null) {
					return false;
				}

				TabDocsFirmantes currentFirmante = null;
				for (TabDocsFirmantes firmante : firmantes) {
					boolean haFirmado = serviceFirmar.validateFirmanteHaFirmado2(
							documentosAdjuntos.get(0).getDocumentoHash(), firmante.getIntEmpleado(), firmaAplicada);
					if (!haFirmado) {
						currentFirmante = firmante;
					}
				}
				if (currentFirmante == null) {
					res.setMessage("ya no restan firmantes por firmar");
					res.setStatus("fail");
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, currentFirmante, res);
				if (!documentoFirmantesStored) {
					return false;
				}

				TabCatEtapaDocumento etapaDoc_enviado = findEtapaDocumento(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceFirmar.storeWorkFlow(documentoStored,
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

				// storePkiDocumento(List<TabDocumentosAdjuntos> documentosAdjuntos, boolean
				// isEnOrden, InstEmpleado empleadoCreador,
				// InstEmpleado empleadoEnvio, Date fechaCreacion, Date fechaEnvio, DTOResponse
				// res,
				// DTOResponseUserInfo userInfo)

				boolean archivosPkiDocumentoStored = storePkiDocumento(documentosAdjuntos,
						EnumTabCatEtapaDocumento.ENVIADO.getOpcion(), documentoStored.getEnOrden() == 1 ? true : false,
						userInfo.getData().getEmpleado(), userInfo.getData().getEmpleado(), new Date(), new Date(),
						res);
				if (!archivosPkiDocumentoStored) {
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, currentFirmante, res);
				if (!documentoFirmantesStored) {
					return false;
				}

				TabCatEtapaDocumento etapaDoc_enviado = findEtapaDocumento(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceFirmar.storeWorkFlow(documentoStored,
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
				boolean updatedPkiDocumento = serviceFirmar.updateDataEnviadoInPkiDocumentosAdjuntos(documentosAdjuntos,
						new Date(), userInfo.getData().getEmpleado(), res);
				if (!updatedPkiDocumento) {
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, firmantes, res);
				if (!documentoFirmantesStored) {
					return false;
				}

				TabCatEtapaDocumento etapaDoc_enviado = findEtapaDocumento(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceFirmar.storeWorkFlow(documentoStored,
						etapaDoc_enviado, userInfo.getData().getEmpleado(), res);
				if (workflowStored_Enviado == null) {
					return false;
				}
				res.setMessage("El documento se ha enviado satisfactoriamente");
				res.setStatus("Success");
				return true;
			} else {
				boolean archivosPkiDocumentoStored = storePkiDocumento(documentosAdjuntos,
						EnumTabCatEtapaDocumento.ENVIADO.getOpcion(), documentoStored.getEnOrden() == 1 ? true : false,
						userInfo.getData().getEmpleado(), userInfo.getData().getEmpleado(), new Date(), new Date(),
						res);
				if (!archivosPkiDocumentoStored) {
					return false;
				}

				String tipofirma = documentosAdjuntos.size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
						: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
				PkiCatTipoFirma tipoFirma = findTipoFirma(tipofirma, res);
				if (tipoFirma == null) {
					return false;
				}

				boolean documentoFirmantesStored = storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored,
						tipoFirma, firmantes, res);
				if (!documentoFirmantesStored) {
					return false;
				}

				TabCatEtapaDocumento etapaDoc_enviado = findEtapaDocumento(EnumTabCatEtapaDocumento.ENVIADO.getOpcion(),
						res);
				if (etapaDoc_enviado == null) {
					return false;
				}

				TabDocumentoWorkflow workflowStored_Enviado = serviceFirmar.storeWorkFlow(documentoStored,
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

	public boolean gotoFirmar(int idTabDocumento, DTOResponseUserInfo userInfo, DTOResponse res) {

		List<TabDocsFirmantes> firmantes = serviceFirmar.getTabDocsFirmantes(idTabDocumento);

		TabDocsFirmantes currentFirmante = getCurrentFirmanteInList(firmantes, userInfo.getData().getEmpleado(), res);
		if (currentFirmante == null) {
			return false;
		}

		TabDocumentos documentoStored = serviceFirmar.findTabDocumento(idTabDocumento, res);
		if (documentoStored == null) {
			return false;
		}

		String etapaSequence = serviceFirmar.getSequenceWorkflow(documentoStored, res);
		if (etapaSequence == null) {
			return false;
		}

		List<TabDocumentosAdjuntos> documentosAdjuntos = serviceFirmar.getTabDocumentosAdjuntos(documentoStored);

		if (etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion()) || etapaSequence.equals(
				EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-" + EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())) {
			boolean puedeFirmarEnEtapaCreado = puedeFirmarEnEtapaCreado(documentoStored, firmantes, userInfo, res);
			if (!puedeFirmarEnEtapaCreado) {
				return false;
			}

			boolean firmado = firmar(documentoStored, documentosAdjuntos, firmantes, currentFirmante, userInfo, res);
			if (!firmado) {
				return false;
			}

			res.setMessage("Se ha firmado el documento satisfactoriamente con número de folio: "
					+ documentoStored.getFolioDocumento());
			res.setStatus("Success");
			return true;
		} else if (etapaSequence.equals(
				EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-" + EnumTabCatEtapaDocumento.ENVIADO.getOpcion())
				|| etapaSequence.equals(
						EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-" + EnumTabCatEtapaDocumento.ENVIADO.getOpcion()
								+ "-" + EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())
				|| etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.ENVIADO.getOpcion())
				|| etapaSequence.equals(EnumTabCatEtapaDocumento.CREADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.ENVIADO.getOpcion() + "-"
						+ EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion())) {

			TabCatEtapaDocumento etapaDoc_enfirma = findEtapaDocumento(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(),
					res);
			if (etapaDoc_enfirma == null) {
				return false;
			}

			TabDocumentoWorkflow workflowStored_Enfirma = serviceFirmar.storeWorkFlow(documentoStored, etapaDoc_enfirma,
					userInfo.getData().getEmpleado(), res);
			if (workflowStored_Enfirma == null) {
				return false;
			}

		}
		res.setMessage("Se ha firmado el documento satisfactoriamente ");
		res.setStatus("Success");
		return true;

	}

	public boolean altaDocAndfirmarAhoraEscritorio(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res, DTOResponseUserInfo userInfo) {

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		boolean dataValid = validateDataFirmarAhoraEscritorio(payload, documentoAlta, userInfo, res);
		if (!dataValid) {
			return false;
		}

		TabDocumentos documentoStored = serviceFirmar.storeTabDocumento(documentoAlta.getDestinoDoc(),
				documentoAlta.getTipoDoc(), documentoAlta.getPrioridad(), documentoAlta.getFolioEspecial(),
				documentoAlta.getExpediente(), documentoAlta.getAsunto(), documentoAlta.getNotas(),
				documentoAlta.getContenido(), documentoAlta.getFechaLimiteFirma(), documentoAlta.isEnOrden(), userInfo,
				res);

		if (documentoStored == null) {
			return false;
		}

		boolean isDocAdjuntosStored = storeDocumentosAdjuntosDesktop(payload.getDocumentosAdjuntos(), documentoStored,
				res, userInfo);
		if (!isDocAdjuntosStored) {
			return false;
		}

		boolean isfirmantesStored = storeFirmantes(documentoStored, documentoAlta, res);
		if (!isfirmantesStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_creado = findEtapaDocumento(EnumTabCatEtapaDocumento.CREADO.getOpcion(), res);
		if (etapaDoc_creado == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Creado = serviceFirmar.storeWorkFlow(documentoStored, etapaDoc_creado,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Creado == null) {
			return false;
		}

		try {
			// Espera por 2000 milisegundos (2 segundos)
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Manejar la excepción en caso de que el sleep sea interrumpido
			e.printStackTrace();
		}

		// Se obtienen los datos previamente guardados de la BD
		List<TabDocumentosAdjuntos> documentosAdjuntos = serviceFirmar.getTabDocumentosAdjuntos(documentoStored);

		for (int doc = 0; doc < documentosAdjuntos.size(); doc++) {
			payload.getDocumentosAdjuntos().get(doc).setOriginalHash(documentosAdjuntos.get(doc).getDocumentoHash());
		}
		List<TabDocsFirmantes> firmantes = serviceFirmar.getTabDocsFirmantes(documentoStored.getId());
		TabDocsFirmantes currentFirmante = getCurrentFirmanteInList(firmantes, userInfo.getData().getEmpleado(), res);
		if (currentFirmante == null) {
			return false;
		}
		//

		boolean archivosPkiDocumentoStored = storePkiDocumento(documentosAdjuntos,
				EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), documentoAlta.isEnOrden(),
				userInfo.getData().getEmpleado(), null, new Date(), null, res);
		if (!archivosPkiDocumentoStored) {
			return false;
		}

		String tipofirma = payload.getDocumentosAdjuntos().size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
				: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
		PkiCatTipoFirma tipoFirma = findTipoFirma(tipofirma, res);
		if (tipoFirma == null) {
			return false;
		}

		boolean documentoFirmantesStored = storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored, tipoFirma,
				currentFirmante, res);
		if (!documentoFirmantesStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_enfirma = findEtapaDocumento(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), res);
		if (etapaDoc_enfirma == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Enfirma = serviceFirmar.storeWorkFlow(documentoStored, etapaDoc_enfirma,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Enfirma == null) {
			return false;
		}

		res.setMessage("Se ha guardado el documento satisfactoriamente en firma de Escritorio");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return true;
	}

	public boolean altaDocAndfirmarAhora(PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta, DTOResponse res,
			DTOResponseUserInfo userInfo) {

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		boolean dataValid = validateDataFirmarAhora(payload, documentoAlta, userInfo, res);
		if (!dataValid) {
			return false;
		}

		TabDocumentos documentoStored = serviceFirmar.storeTabDocumento(documentoAlta.getDestinoDoc(),
				documentoAlta.getTipoDoc(), documentoAlta.getPrioridad(), documentoAlta.getFolioEspecial(),
				documentoAlta.getExpediente(), documentoAlta.getAsunto(), documentoAlta.getNotas(),
				documentoAlta.getContenido(), documentoAlta.getFechaLimiteFirma(), documentoAlta.isEnOrden(), userInfo,
				res);

		if (documentoStored == null) {
			return false;
		}

		TabDocConfig configStored = serviceFirmar.storeTabDocConfig(payload.getConfiguraciones(), documentoStored, res);
		if (configStored == null) {
			return false;
		}

		boolean isDocAdjuntosStored = storeDocumentosAdjuntos(payload.getDocumentosAdjuntos(), documentoStored, res,
				userInfo);
		if (!isDocAdjuntosStored) {
			return false;
		}

		boolean isfirmantesStored = storeFirmantes(documentoStored, documentoAlta, res);
		if (!isfirmantesStored) {
			return false;
		}

		boolean isDestinatariosStored = storeDestinatarios(documentoStored, documentoAlta, res);
		if (!isDestinatariosStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_creado = findEtapaDocumento(EnumTabCatEtapaDocumento.CREADO.getOpcion(), res);
		if (etapaDoc_creado == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Creado = serviceFirmar.storeWorkFlow(documentoStored, etapaDoc_creado,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Creado == null) {
			return false;
		}

		try {
			// Espera por 2000 milisegundos (2 segundos)
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Manejar la excepción en caso de que el sleep sea interrumpido
			e.printStackTrace();
		}

		// Se obtienen los datos previamente guardados de la BD
		List<TabDocumentosAdjuntos> documentosAdjuntos = serviceFirmar.getTabDocumentosAdjuntos(documentoStored);
		List<TabDocsFirmantes> firmantes = serviceFirmar.getTabDocsFirmantes(documentoStored.getId());
		TabDocsFirmantes currentFirmante = getCurrentFirmanteInList(firmantes, userInfo.getData().getEmpleado(), res);
		if (currentFirmante == null) {
			return false;
		}
		//

		boolean archivosPkiDocumentoStored = storePkiDocumento(documentosAdjuntos,
				EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), documentoAlta.isEnOrden(),
				userInfo.getData().getEmpleado(), null, new Date(), null, res);
		if (!archivosPkiDocumentoStored) {
			return false;
		}

		String tipofirma = payload.getDocumentosAdjuntos().size() > 1 ? EnumPkiCatTipoFirma.MULTIPLE.getOpcion()
				: EnumPkiCatTipoFirma.SIMPLE.getOpcion();
		PkiCatTipoFirma tipoFirma = findTipoFirma(tipofirma, res);
		if (tipoFirma == null) {
			return false;
		}

		boolean documentoFirmantesStored = storePkiDocumentoFirmantes(documentosAdjuntos, documentoStored, tipoFirma,
				currentFirmante, res);
		if (!documentoFirmantesStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_enfirma = findEtapaDocumento(EnumTabCatEtapaDocumento.EN_FIRMA.getOpcion(), res);
		if (etapaDoc_enfirma == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Enfirma = serviceFirmar.storeWorkFlow(documentoStored, etapaDoc_enfirma,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Enfirma == null) {
			return false;
		}

		res.setMessage("Se ha guardado el documento satisfactoriamente en modo firmar ahora");
		res.setStatus("Success");
		payload.setFolio(documentoStored.getFolioDocumento());
		res.setData(payload);

		return true;
	}

	public boolean validateCatalogEditDocument(List<DTOConfiguracion> configuraciones,
			List<TabCatDocConfig> listConfig) {
		for (DTOConfiguracion configDTO : configuraciones) {
			Optional<TabCatDocConfig> config = tabCatDocConfigRepository.findByAtributo(configDTO.getAtributo());
			if (config.isPresent()) {
				listConfig.add(config.get());
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean editarDocumento(int idDocumento, PayloadAltaDocumento payload, DAOAltaDocumento documentoAlta,
			DTOResponse res, DTOResponseUserInfo userInfo) {

		// busca el documento a editar
		Optional<TabDocumentos> documentExist = tabDocumentoRepository.findById(idDocumento);

		if (!documentExist.isPresent()) {
			res.setMessage("El documento que deseas actualizar no existe");
			res.setStatus("Fail");
			res.setData(payload);
			return false;
		}

		List<TabCatDocConfig> listConfig = new ArrayList<TabCatDocConfig>();
		boolean validCatalog = validateCatalogEditDocument(payload.getConfiguraciones(), listConfig);

		if (!validCatalog) {
			res.setMessage("los elementos del catálogo de configuración no existen");
			res.setStatus("Fail");
			res.setData(payload);
			return false;
		}

		List<TabDocConfig> tabDocConfList = tabConfigDocumentoRepository.findByIdDocumento(idDocumento);
		for (TabDocConfig tabDocConf : tabDocConfList) {
			tabConfigDocumentoRepository.delete(tabDocConf);
		}

		TabDocConfig docConfig = null;
		for (TabCatDocConfig configCatalogo : listConfig) {
			docConfig = new TabDocConfig();
			docConfig.setIdDocumento(idDocumento);
			docConfig.setIdDocConfig(configCatalogo.getId());
			tabConfigDocumentoRepository.save(docConfig);
		}

		// Le asignamos los datos básicos
		documentoAlta = new DAOAltaDocumento(payload.getFolioEspecial(), payload.getAsunto(), payload.getNotas(),
				payload.getContenido(), payload.getFechaLimiteFirma(), payload.isEnOrden());

		// Elimina primero los docs adjuntos existentes
		List<TabDocumentosAdjuntos> docsExistentes = tabDocumentosAdjuntosRepository.findByIdDocument(documentExist.get());
		for (TabDocumentosAdjuntos docAdjunto : docsExistentes) {
			tabDocumentosAdjuntosRepository.delete(docAdjunto);
		}

		// valida los datos que sean correctos
		boolean dataValid = validateDataModoCaptura(payload, documentoAlta, userInfo, res);
		if (!dataValid) {
			return false;
		}
		// almacena en tab los nuevos datos del documento
		TabDocumentos documentoEdited = serviceFirmar.editTabDocumento(documentExist.get(),
				documentoAlta.getDestinoDoc(), documentoAlta.getTipoDoc(), documentoAlta.getPrioridad(),
				documentoAlta.getFolioEspecial(), documentoAlta.getExpediente(), documentoAlta.getAsunto(),
				documentoAlta.getNotas(), documentoAlta.getContenido(), documentoAlta.getFechaLimiteFirma(),
				documentoAlta.isEnOrden(), userInfo, res);

		if (documentoEdited == null) {
			return false;
		}

		boolean isDocAdjuntosStored = storeDocumentosAdjuntos(payload.getDocumentosAdjuntos(), documentoEdited, res,
				userInfo);
		if (!isDocAdjuntosStored) {
			return false;
		}

		boolean isfirmantesStored = editedFirmantes(documentoEdited, documentoAlta, res);
		if (!isfirmantesStored) {
			return false;
		}

		boolean isDestinatariosStored = editedDestinatarios(documentoEdited, documentoAlta, res);
		if (!isDestinatariosStored) {
			return false;
		}

		TabCatEtapaDocumento etapaDoc_creado = findEtapaDocumento(EnumTabCatEtapaDocumento.CREADO.getOpcion(), res);
		if (etapaDoc_creado == null) {
			return false;
		}

		TabDocumentoWorkflow workflowStored_Creado = serviceFirmar.storeWorkFlow(documentoEdited, etapaDoc_creado,
				userInfo.getData().getEmpleado(), res);
		if (workflowStored_Creado == null) {
			return false;
		}

		res.setMessage("Se ha actualizado el documento satisfactoriamente");
		res.setStatus("Success");
		payload.setFolio(documentoEdited.getFolioDocumento());
		res.setData(payload);

		return true;
	}

}
