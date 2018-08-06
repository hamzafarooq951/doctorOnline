package javabeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class UserLogin {

	private String email, password,token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private Boolean loginStatus;
	
	public UserLogin() {
		
	}
	public UserLogin(String email, String password, Boolean loginStatus, String token) {
		super();
		this.email = email;
		this.password = password;
		this.loginStatus = loginStatus;
		this.token = token;
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
