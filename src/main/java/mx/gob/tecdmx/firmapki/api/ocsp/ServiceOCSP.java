package mx.gob.tecdmx.firmapki.api.ocsp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.tecdmx.firmapki.api.populate.CertCA;
import mx.gob.tecdmx.firmapki.api.populate.CertUser;
import mx.gob.tecdmx.firmapki.entity.pki.PkiTransaccion;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509AcAutorizadas;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Ocsp;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Registrados;
import mx.gob.tecdmx.firmapki.repository.pki.PkiTransaccionRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509AcAutorizadasRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509OcspRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509RegistradosRepository;
import mx.gob.tecdmx.firmapki.utils.CertificateUtils;
import mx.gob.tecdmx.firmapki.utils.dto.DTOResponse;

@Service
public class ServiceOCSP {

	@Autowired
	PkiX509AcAutorizadasRepository pkiX509AcAutorizadasRepository;

	@Autowired
	PkiX509OcspRepository pkiX509OcspRepository;

	@Autowired
	PkiTransaccionRepository pkiTransaccionRepository;

	@Autowired
	PkiX509RegistradosRepository pkiX509RegistradosRepository;

	@Value("${firma.document.ocsp.path}")
	private String evidenciasOcspPath;

	public DTOResponse validateOCSP(PayloadCertVigenciaOCSP payload, DTOResponse res) {

		InputStream inStream = new ByteArrayInputStream(payload.getCertificado());
		CertUser certUser = new CertUser(inStream);

		Optional<PkiX509Registrados> certRegistrado = pkiX509RegistradosRepository.findById(certUser.getSerialnumber());
		if (certRegistrado.isPresent()) {
			String emisorSerial = certRegistrado.get().getX509EmisorSerial().getId();
			System.out.println(emisorSerial);
			Optional<PkiX509AcAutorizadas> certIssuerData = pkiX509AcAutorizadasRepository.findById(emisorSerial);
			if (certIssuerData.isPresent()) {
				List<PkiX509AcAutorizadas> listCertOCSP = pkiX509AcAutorizadasRepository
						.findByX509EmisorSerialParentAndTipoCertificado(certRegistrado.get().getX509EmisorSerial(),
								"OCSP");
				Ocsp ocsp = null;
				if (listCertOCSP.size() > 0) {
					PkiX509Ocsp ocsResp = new PkiX509Ocsp();
					CertCA certIssuer = new CertCA(certIssuerData.get().getX509AcDerB64());
					CertCA certOCSP = new CertCA(listCertOCSP.get(0).getX509AcDerB64());

					ocsp = new Ocsp(certUser, certIssuer, certOCSP);

					ResponseBodyOCSP responseBody = new ResponseBodyOCSP();
					// responseBody.setFechaUTC(ocsp.getFechaUTC());
					responseBody.setNombreRespondedor(certOCSP.getNombreComunAutoridad());
					responseBody.setEmisorRespondedor(certIssuer.getNombreComunAutoridad());
					responseBody.setNumeroSerie(certUser.getSerialnumber());
					responseBody.setIndicador(ocsp.getIndicador());
					res.setData(responseBody);

				}
				res.setStatus("Success");
				res.setMessage(ocsp.getMessage() + " " + ocsp.getStatus());
				return res;
			}
		}
		res.setStatus("Fail");
		res.setMessage("No se pudo completar la solicitud");
		return res;

	}

	public boolean validateOCSP(PayloadOCSP payload, DTOResponse res) {
		CertificateUtils utils = new CertificateUtils();

		InputStream inStream = new ByteArrayInputStream(payload.getCertificado());
		CertUser certUser = new CertUser(inStream);

		Optional<PkiX509Registrados> certRegistrado = pkiX509RegistradosRepository.findById(certUser.getSerialnumber());
		if (certRegistrado.isPresent()) {
			String emisorSerial = certRegistrado.get().getX509EmisorSerial().getId();
			System.out.println(emisorSerial);
			Optional<PkiX509AcAutorizadas> certIssuerData = pkiX509AcAutorizadasRepository.findById(emisorSerial);
			if (certIssuerData.isPresent()) {
				List<PkiX509AcAutorizadas> listCertOCSP = pkiX509AcAutorizadasRepository
						.findByX509EmisorSerialParentAndTipoCertificado(certRegistrado.get().getX509EmisorSerial(),
								"OCSP");
				Ocsp ocsp = null;
				if (listCertOCSP.size() > 0) {
					PkiX509Ocsp ocsResp = new PkiX509Ocsp();
					CertCA certIssuer = new CertCA(certIssuerData.get().getX509AcDerB64());
					CertCA certOCSP = new CertCA(listCertOCSP.get(0).getX509AcDerB64());

					ocsp = new Ocsp(certUser, certIssuer, certOCSP);

					ocsResp.setX509SerialNumber(certRegistrado.get());
					ocsResp.setOcspResponseDerB64(ocsp.getBasicResponse());
					String filename = evidenciasOcspPath + "/" + utils.formatDate(ocsp.getFechaUTC()) + "_transaccion_"
							+ payload.getIdTransaccion() + "_ocsp_responseb64.txt";
					utils.storeBase64ToFile(ocsp.getBasicResponse(), filename);

					ocsResp.setOcspResponsePath(filename);
					ocsResp.setX509SerialResponder(certOCSP.getSerialnumber());
					ocsResp.setFechaResponse(ocsp.getFechaUTC());
					ocsResp.setOcspIndicador(ocsp.getIndicador());
					PkiX509Ocsp pkiX509OcspGuardado = null;

					Optional<PkiTransaccion> transaccion = pkiTransaccionRepository
							.findById(payload.getIdTransaccion());
					if (transaccion.isPresent()) {

						try {
							Optional<PkiX509Ocsp> lastOCSP = pkiX509OcspRepository.findTopByOrderByFechaResponseDesc();
							if (lastOCSP.isPresent()) {
								ocsResp.setIdOcspBlock(lastOCSP.get());
								pkiX509OcspGuardado = pkiX509OcspRepository.save(ocsResp);
							} else {
								pkiX509OcspGuardado = pkiX509OcspRepository.save(ocsResp);
								ocsResp.setIdOcspBlock(pkiX509OcspGuardado);
								pkiX509OcspGuardado = pkiX509OcspRepository.save(ocsResp);
							}

							try {
								transaccion.get().setIdOcsp(pkiX509OcspGuardado);
								pkiTransaccionRepository.save(transaccion.get());

								ResponseBodyOCSP responseBody = new ResponseBodyOCSP();
								responseBody.setFechaUTC(ocsp.getFechaUTC());
								responseBody.setNombreRespondedor(certOCSP.getNombreComunAutoridad());
								responseBody.setEmisorRespondedor(certIssuer.getNombreComunAutoridad());
								responseBody.setNumeroSerie(certUser.getSerialnumber());
								responseBody.setIndicador(ocsp.getIndicador());
								res.setData(responseBody);
								res.setStatus("Success");
								res.setMessage(ocsp.getMessage() + " " + ocsp.getStatus());
								return true;
							} catch (Exception e) {
								res.setStatus("fail");
								res.setMessage(
										"Ocurrió un error al intentar actualizar la información en la tabla de transaccion");
								return false;
							}

						} catch (Exception e) {
							res.setStatus("fail");
							res.setMessage("Ocurrió un error al intentar guardar el registro en la tabla de OCSP");
							return false;
						}
					} else {
						res.setData(null);
						res.setStatus("fail");
						res.setMessage(
								"Ocurrió un error al almacenar la información, no se encontró el identificador de la transacción");
						return false;
					}
				} else {
					res.setMessage(
							"No se encontró el certificado OCSP, el emitido por el certificado intermedio que corresponde a tu certificado personal. Contacta a tu administrador");
					res.setStatus("fail");
					return false;
				}
			} else {
				res.setMessage(
						"No se encontró el certificado intermedio, el emisor que corresponde a tu certificado personal. Contacta a tu administrador");
				res.setStatus("fail");
				return false;
			}
		} else {
			res.setMessage(
					"Error al intentar validar tu certificado por OCSP. Tu certificado personal no se encuentra registrado");
			res.setStatus("fail");
			return false;
		}
	}

}
