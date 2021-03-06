package javabeans;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class UserReserves {
	private ArrayList<ReserveRow> ReservationsList = new ArrayList<ReserveRow>();
	
	public UserReserves() {
		
	}

	public UserReserves(ArrayList<ReserveRow> reservationsList) {
		super();
		this.ReservationsList = reservationsList;
	}

	public ArrayList<ReserveRow> getReservedDoctors() {
		return ReservationsList;
	}

	public void setReservedDoctors(ArrayList<ReserveRow> reservationsList) {
		this.ReservationsList = reservationsList;
	}
}
