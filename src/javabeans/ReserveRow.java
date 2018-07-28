package javabeans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hamza
 *
 */

@XmlRootElement 
public class ReserveRow {
	private String email, time, doctor;
	
	public ReserveRow() {
		
	}

	public ReserveRow(String email, String time, String password) {
		super();
		this.email = email;
		this.time = time;
		this.doctor = password;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPassword() {
		return doctor;
	}

	public void setPassword(String password) {
		this.doctor = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
