package poly.entity;

import java.util.Date;

public class USERS {
	private String Id;
	private String Password;
	private String Fullname;
	private Date Birthday;
	private Boolean Gender;
	private String Moble;
	private String Email;
	private Boolean Role;
	
	public USERS() {
	}

	public USERS(String id, String password, String fullname, Date birthday, Boolean gender, String moble, String email,Boolean role) {
		Id = id;
		Password = password;
		Fullname = fullname;
		Birthday = birthday;
		Gender = gender;
		Moble = moble;
		Email = email;
		Role = role;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public Boolean getGender() {
		return Gender;
	}

	public void setGender(Boolean gender) {
		Gender = gender;
	}

	public String getMoble() {
		return Moble;
	}

	public void setMoble(String moble) {
		Moble = moble;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Boolean getRole() {
		return Role;
	}

	public void setRole(Boolean role) {
		Role = role;
	}
}
