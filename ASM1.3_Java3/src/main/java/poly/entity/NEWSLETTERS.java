package poly.entity;

public class NEWSLETTERS {
	private String Email;
	private Boolean Enabled;
	
	public NEWSLETTERS() {
	}

	public NEWSLETTERS(String email, Boolean enabled) {
		Email = email;
		Enabled = enabled;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Boolean getEnabled() {
		return Enabled;
	}

	public void setEnabled(Boolean enabled) {
		Enabled = enabled;
	}
	
	
	
}
