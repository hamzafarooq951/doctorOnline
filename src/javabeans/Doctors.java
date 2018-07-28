package javabeans;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Doctors {
	
	private ArrayList<DoctorRow> doctorsList = new ArrayList<DoctorRow>();
	
	public Doctors() {
		super();
	}
	
	public Doctors(ArrayList<DoctorRow> doctorsList) {
		super();
		this.doctorsList = doctorsList;
	}

	public ArrayList<DoctorRow> getDoctorsList() {
		return doctorsList;
	}

	public void setDoctorsList(ArrayList<DoctorRow> doctorsList) {
		this.doctorsList = doctorsList;
	}
}
