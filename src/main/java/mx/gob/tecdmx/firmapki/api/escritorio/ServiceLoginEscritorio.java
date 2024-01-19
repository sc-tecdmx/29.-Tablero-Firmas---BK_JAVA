package mx.gob.tecdmx.firmapki.api.escritorio;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.gob.tecdmx.firmapki.api.populate.CertUser;
import mx.gob.tecdmx.firmapki.entity.pki.PkiUsuariosCert;
import mx.gob.tecdmx.firmapki.entity.pki.PkiX509Registrados;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;
import mx.gob.tecdmx.firmapki.repository.pki.PkiUsuariosCertRepository;
import mx.gob.tecdmx.firmapki.repository.pki.PkiX509RegistradosRepository;
import mx.gob.tecdmx.firmapki.utils.RestClient;

@Service
public class ServiceLoginEscritorio {

	@Value("${firma.url.security.login.escritorio}")
	private String loginEscritorioUrl;
	
	@Autowired
	PkiX509RegistradosRepository pkiX509RegistradosRepository;

	@Autowired
	PkiUsuariosCertRepository pkiUsuariosCertRepository;

	public DTORsponseEscritorio login(PayloadCertLoginEscritorio payload, DTORsponseEscritorio res) {
		// TODO Auto-generated method stub
		InputStream inStream = new ByteArrayInputStream(payload.getCertificado());
		CertUser certUser = new CertUser(inStream);
		SegOrgUsuarios usuario = null;
		RestClient restClient = new RestClient();
		DTOLoginEscritorio dtoLoginEscritorio = null;
		Gson gson = new Gson();

		Optional<PkiX509Registrados> certRegistrado = pkiX509RegistradosRepository.findById(certUser.getSerialnumber());
		if (!certRegistrado.isPresent()) {
			res.setStatus("fail");
			res.setMessage("El certificado no cuenta con permisos para realizar firma");
			return res;
		}
		Optional<PkiUsuariosCert> usuCert = pkiUsuariosCertRepository.findByX509SerialNumber(certUser.getSerialnumber());
		if (usuCert.isPresent()) {
			usuario = usuCert.get().getUsuario();
			dtoLoginEscritorio = new DTOLoginEscritorio();
			dtoLoginEscritorio.setIdEmpleado(null);
			dtoLoginEscritorio.setNombre(null);
			dtoLoginEscritorio.setEmail(usuario.getsEmail());
			dtoLoginEscritorio.setNoSerie(usuCert.get().getX509SerialNumber());
			
			String json = gson.toJson(dtoLoginEscritorio);
			
			
			String respPost = restClient.sendPostRequestForLoginEscritorio(loginEscritorioUrl, json);
			DTOResponseLogin miObjeto = gson.fromJson(respPost, DTOResponseLogin.class);
			
			res.setData(miObjeto.getToken());
			res.setMessage("Autenticación exitosa");
			res.setStatus("success");
			

		}else {
			res.setMessage("Datos incorrectos");
			res.setStatus("fail");
		}
		return res;

	}

}
