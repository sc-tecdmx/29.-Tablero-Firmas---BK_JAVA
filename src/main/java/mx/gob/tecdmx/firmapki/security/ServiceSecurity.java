package mx.gob.tecdmx.firmapki.security;

import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;
import mx.gob.tecdmx.firmapki.entity.inst.InstEmpleado;
import mx.gob.tecdmx.firmapki.entity.seg.SegOrgUsuarios;
import mx.gob.tecdmx.firmapki.repository.inst.InstEmpleadoRepository;
import mx.gob.tecdmx.firmapki.repository.seg.SegOrgUsuariosRepository;
import mx.gob.tecdmx.firmapki.utils.DTOResponse;

@Service
public class ServiceSecurity {
	
	@Autowired
	InstEmpleadoRepository instEmpleadoRepository;
	
	@Autowired
	SegOrgUsuariosRepository segOrgUsuariosRepository;

	public DTOResponseUserInfo getUserInfo(HttpServletRequest request, DTOResponse res) {

//		System.out.println("Request URL: " + request.getRequestURL());
//	    System.out.println("Request Method: " + request.getMethod());
//	    System.out.println("Query String: " + request.getQueryString());
	    // Imprimir encabezados
//	    Collections.list(request.getAttributeNames()).forEach(atributos ->
//	        System.out.println(atributos + ": " + request.getAttribute(atributos).toString())
//	    );
//	    Collections.list(request.getHeaderNames()).forEach(headerName ->
//        	System.out.println(headerName + ": " + request.getHeader(headerName))
//    	);
	
		String userdata = request.getAttribute("userdata").toString();
		Gson gson = new Gson();
		DTOResponseUserInfo responseInfo = gson.fromJson(userdata, DTOResponseUserInfo.class);
		
		if(responseInfo.getData().getIdEmpleado()!=null) {
			Optional<InstEmpleado> empleado = instEmpleadoRepository.findById(responseInfo.getData().getIdEmpleado());
			if (!empleado.isPresent()) {
				res.setMessage("No se puede encontrar el empleado");
				res.setStatus("fail");
				return null;
			}
			responseInfo.getData().setEmpleado(empleado.get());
		}
		
		if(responseInfo.getData().getIdUsuario()!=null) {
			Optional<SegOrgUsuarios> usuario = segOrgUsuariosRepository.findById(responseInfo.getData().getIdUsuario());
			if (!usuario.isPresent()) {
				res.setMessage("No se puede encontrar el usuario");
				res.setStatus("fail");
				return null;
			}
			responseInfo.getData().setUser(usuario.get());
		}
		
        return responseInfo;
	}
}
