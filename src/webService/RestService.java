package webService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javabeans.ApiStatus;
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
	public UserLogin restLogin(@QueryParam("useremail") String restEmail, @QueryParam("password") String restPassword)
			throws SQLException {

		Connection con = dbPackage.MyDbConnection.getConnection();
		ResultSet rs;
		UserLogin loginObject = new UserLogin();
		String loginQuery = "select * from user WHERE `email` = '" + restEmail + "' AND `password` = '" + restPassword
				+ "'";
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(loginQuery);

		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				loginObject.setEmail(rs.getString("email"));
				loginObject.setPassword(rs.getString("password"));
				loginObject.setLoginStatus(true);
				loginObject.setToken(AuthenticationEndpoint.issueToken((rs.getString("email"))));
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

		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				reserveRow = new ReserveRow(rs.getString("Remail"), rs.getString("Rtime"), rs.getString("doctor"));
				list.add(reserveRow);
			}
			userReservations.setReservedDoctors(list);
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

		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				doctorRow = new DoctorRow(rs.getString("Dname"), rs.getString("Dtime"));
				list.add(doctorRow);
			}
			availableDoctors.setDoctorsList(list);
		}
		return availableDoctors;
	}
	
	@POST
	@Path("/reserve")
	@Produces(MediaType.APPLICATION_JSON)
	public ApiStatus reserveDoctor(@HeaderParam("Authorization") String token, @QueryParam("email") String email, @QueryParam("name") String name, @QueryParam("time") String time) throws Exception {
		
		System.out.println(token);
		if(AuthenticationEndpoint.authenticateToken(token, email)) {
		
		String query = "SELECT * FROM `doctors`";
		int a = 0;
		ResultSet rs = DbQuery.getDbData(query);
		while(rs.next()) {
			if(name.equalsIgnoreCase(rs.getString("Dname")) && time.matches(rs.getString("Dtime"))){
				if(rs.getBoolean("Davailable")) {
					query = "INSERT INTO `reservation`(`Remail`, `Rtime`, `doctor`) VALUES ('"+ email +"', '"+ time +"', '"+ name +"')";
					DbQuery.getDbDataUpdate(query);
					query = "UPDATE `doctors` SET `Davailable`='0' WHERE `Dname`='"+ name +"' AND `Dtime`='"+ time +"'";
					DbQuery.getDbDataUpdate(query);
					return new ApiStatus("Successfully reserved");
				} else {
					a = 1;
					return new ApiStatus("Doctor is already reserved");
				}
			} else {
				a = 2;
			}
		}
		if(a == 2) {	return new ApiStatus("Doctor is not available at that time"); }
		
		} else {	return new ApiStatus("Please login first"); }
		
		return null;
	}

	@DELETE
	@Path("/reservation")
	@Produces(MediaType.APPLICATION_JSON)
	public ApiStatus reserveDelete( @QueryParam("name") String name, @QueryParam("time") String time) throws SQLException {
		
		String query = "DELETE FROM `reservation` WHERE `Rtime` = '" + time + "' AND `doctor` = '"+ name +"'";
		if(DbQuery.getDbDataUpdate(query) == 1) {
			query = "UPDATE `doctors` SET `Davailable`='1' WHERE `Dname`='"+ name +"' AND `Dtime`='"+ time +"'";
			DbQuery.getDbDataUpdate(query);
			return new ApiStatus("Reservation has cancelled");
		} else {
			return new ApiStatus("No Reservation found");
		}
	}

	@GET
	@Path("/hello")
	public String getHello() {
		return "Welcome to Doctor Online";
	}
}