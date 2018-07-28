

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbPackage.MyDbConnection;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	String email = request.getParameter("REmail");
		String time = request.getParameter("my_time");
		String doctor = request.getParameter("my_doctor");
		String query= "INSERT INTO `reservation`(`Remail`, `Rtime`, `doctor`) VALUES ('" + email +"' ,'"+ time +"','"+ doctor +"')";
		String updateQuery = "UPDATE `doctors` SET `Davailable`=0 WHERE `Dname`='"+ doctor +"' AND `Dtime`='"+ time +"'";
		System.out.println(email + time + doctor);
		
		Connection con = MyDbConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.executeUpdate(updateQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("home.jsp");
		
	}

}
