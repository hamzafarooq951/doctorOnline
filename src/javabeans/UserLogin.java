package javabeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class UserLogin {

	private String email, password;
	private Boolean loginStatus;
	
	public UserLogin() {
		
	}
	public UserLogin(String email, String password, Boolean loginStatus) {
		super();
		this.email = email;
		this.password = password;
		this.loginStatus = loginStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	
}
