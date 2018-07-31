package javabeans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hamza
 *
 */

@XmlRootElement 
public class ReserveRow {
	private String email, time, doctorName;
	
	public ReserveRow() {
		
	}

	public ReserveRow(String email, String time, String doctorName) {
		super();
		this.email = email;
		this.time = time;
		this.doctorName = doctorName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
