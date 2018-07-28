package javabeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DoctorRow {
	
	private String doctorName, doctorTime;
	
	public DoctorRow() {
		
	}

	public DoctorRow(String dname, String dtime) {
		super();
		this.doctorName = dname;
		this.doctorTime = dtime;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorTime() {
		return doctorTime;
	}

	public void setDoctorTime(String doctorTime) {
		this.doctorTime = doctorTime;
	}
	
	
}
