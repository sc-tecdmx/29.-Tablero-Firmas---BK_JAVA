package mx.gob.tecdmx.firmapki.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;

@Service
public class ServiceSecurity {

	public DTOResponseUserInfo getUserInfo(HttpServletRequest request) {

		System.out.println("Request URL: " + request.getRequestURL());
	    System.out.println("Request Method: " + request.getMethod());
	    System.out.println("Query String: " + request.getQueryString());
	    // Imprimir encabezados
	    Collections.list(request.getAttributeNames()).forEach(atributos ->
	        System.out.println(atributos + ": " + request.getAttribute(atributos).toString())
	    );
	    Collections.list(request.getHeaderNames()).forEach(headerName ->
        	System.out.println(headerName + ": " + request.getHeader(headerName))
    	);
	
		String userdata = request.getAttribute("userdata").toString();
		Gson gson = new Gson();
		DTOResponseUserInfo responseInfo = gson.fromJson(userdata, DTOResponseUserInfo.class);
        return responseInfo;
	}
}
