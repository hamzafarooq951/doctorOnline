package webService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbQuery {
	
	 public static ResultSet getDbData(String query) {
		 
		 ResultSet rs;
		 Connection con = dbPackage.MyDbConnection.getConnection();
		 
			try {		
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	 }
	 
	 public static int getDbDataUpdate(String query) {
		 
		 Connection con = dbPackage.MyDbConnection.getConnection();
			try {		
				Statement stmt = con.createStatement();
				return stmt.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
	 }
}
