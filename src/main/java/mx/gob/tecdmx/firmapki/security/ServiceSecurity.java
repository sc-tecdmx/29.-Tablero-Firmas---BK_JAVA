package mx.gob.tecdmx.firmapki.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.gob.tecdmx.firmapki.DTOResponseUserInfo;

@Service
public class ServiceSecurity {

	public DTOResponseUserInfo getUserInfo(HttpServletRequest request) {
		String userdata = request.getAttribute("userdata").toString();
		Gson gson = new Gson();
		DTOResponseUserInfo responseInfo = gson.fromJson(userdata, DTOResponseUserInfo.class);
        return responseInfo;
	}
}
