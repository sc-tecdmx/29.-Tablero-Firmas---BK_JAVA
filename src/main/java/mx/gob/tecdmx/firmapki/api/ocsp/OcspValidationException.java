package mx.gob.tecdmx.firmapki.api.ocsp;

import java.util.Date;

public class OcspValidationException extends Exception {

	private static final long serialVersionUID = 3850292634299899214L;

	private int revocationReason;
	private Date revocationTime;

	public OcspValidationException(int revocationReason, Date revocationTime) {
		super();
		this.revocationReason = revocationReason;
		this.revocationTime = revocationTime;
	}

	public int getRevocationReason() {
		return revocationReason;
	}

	public Date getRevocationTime() {
		return revocationTime;
	}
}