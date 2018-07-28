package webService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javabeans.DoctorRow;
import javabeans.Doctors;
import javabeans.ReserveRow;
import javabeans.UserLogin;
import javabeans.UserReserves;


@Path("/restservice")
public class RestService {

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public UserLogin restLogin(@QueryParam("useremail") String restEmail, @QueryParam("password") String restPassword) throws SQLException {
		
		Connection con = dbPackage.MyDbConnection.getConnection();
		ResultSet rs;
		UserLogin loginObject = new UserLogin();
		String loginQuery = "select * from user WHERE `email` = '" + restEmail + "' AND `password` = '" + restPassword +"'" ; 
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(loginQuery);
		
		if(rs.isBeforeFirst()) {
			while(rs.next()) {
				loginObject.setEmail(rs.getString("email"));
				loginObject.setPassword(rs.getString("password"));
				loginObject.setLoginStatus(true);
			}
			con.close();
			return loginObject;
		} else {
			loginObject.setEmail(null);
			loginObject.setPassword(null);
			loginObject.setLoginStatus(false);
			con.close();
			return loginObject;
		}	
	}
	
	
	@GET
	@Path("/reservations")
	@Produces(MediaType.APPLICATION_JSON)
	public UserReserves userReservavtions() throws SQLException {
		UserReserves userReservations = new UserReserves();
		ReserveRow reserveRow;
		ArrayList<ReserveRow> list = new ArrayList<ReserveRow>();
		Connection con = dbPackage.MyDbConnection.getConnection();
		ResultSet rs;
		Statement stmt = con.createStatement();
		String reserveQuery = "SELECT `Remail`, `Rtime`, `doctor` FROM `reservation`";
		rs = stmt.executeQuery(reserveQuery);
		
		if(rs.isBeforeFirst()) {
			while(rs.next()) {
				reserveRow = new ReserveRow(rs.getString("Remail"), rs.getString("Rtime"), rs.getString("doctor"));
				list.add(reserveRow);
			}
		userReservations.setReserveList(list);
		}
		return userReservations;
	}
	
	@GET
	@Path("/available")
	@Produces(MediaType.APPLICATION_JSON)
	public Doctors doctorAvailable() throws SQLException {
		Doctors availableDoctors = new Doctors();
		ArrayList<DoctorRow> list = new ArrayList<DoctorRow>();
		DoctorRow doctorRow;
		Connection con = dbPackage.MyDbConnection.getConnection();
		ResultSet rs;
		Statement stmt = con.createStatement();
		String availableQuery = "SELECT  `Dname`, `Dtime` FROM `doctors` WHERE `Davailable` = '1'";
		rs = stmt.executeQuery(availableQuery);
		
		if(rs.isBeforeFirst()) {
			while(rs.next()) {
				doctorRow = new DoctorRow(rs.getString("Dname"), rs.getString("Dtime"));
				list.add(doctorRow);
			}
			availableDoctors.setDoctorsList(list);
		}
		return availableDoctors;
		
	}
	
	@DELETE
	@Path("/reservation")
	public void reserveDelete(@QueryParam("id") String id) throws SQLException {
		Connection con = dbPackage.MyDbConnection.getConnection();
		Statement stmt = con.createStatement();
		String deleteQuery = "DELETE FROM `reservation` WHERE `Rid` = '" + id + "'";
		stmt.executeUpdate(deleteQuery);
	}
	
	
	@GET
	@Path("/hello")
	public String getHello() {
		return "Welcome to Doctor Online";
	}
}