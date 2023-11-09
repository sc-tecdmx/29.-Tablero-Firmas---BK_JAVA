package mx.gob.tecdmx.firmapki;

import com.google.gson.annotations.SerializedName;

public class UserInfoDTO {

	private String sub;
	@SerializedName("field_name_in_json")
	private boolean emailVerified;
	private String name;
	@SerializedName("preferred_username")
	private String preferredUsername;
	@SerializedName("given_name")
	private String givenName;
	@SerializedName("family_name")
	private String familyName;
	private String email;

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreferredUsername() {
		return preferredUsername;
	}

	public void setPreferredUsername(String preferredUsername) {
		this.preferredUsername = preferredUsername;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserInfoDTO{" +
				"sub='" + sub + '\'' +
				", emailVerified=" + emailVerified +
				", name='" + name + '\'' +
				", preferredUsername='" + preferredUsername + '\'' +
				", givenName='" + givenName + '\'' +
				", familyName='" + familyName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
