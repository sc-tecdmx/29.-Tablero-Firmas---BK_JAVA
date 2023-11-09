/*
 * Copyright (c) 2009-2013 Rubrica.ec
 * 
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 */

package mx.gob.tecdmx.firmapki.api.ocsp;

import java.net.URL;

public class OcspTimeoutException extends Exception {

	private static final long serialVersionUID = 8593563176075864415L;

	private String url;

	public OcspTimeoutException(URL url) {
		super();
		this.url = url.toString();
	}

	public String getUrl() {
		return this.url;
	}
}
