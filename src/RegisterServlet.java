

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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;
	private Statement stmt = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("registerUsername");
		String email = request.getParameter("registerEmail");
		String password = request.getParameter("registerPassword");
		String confirmPassword = request.getParameter("registerConfirmPassword");
		
		System.out.println(username + "  " + email + "  " + password + "  " + confirmPassword);
		
		con = MyDbConnection.getConnection();
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			stmt.executeUpdate("INSERT INTO `user`(username,email,password) VALUE ('"+ username +"','"+ email +"','"+ password +"')");
		} catch (SQLException e) {
			
			System.out.println("Can't update data to DB");
			e.printStackTrace();
		}
		
		response.sendRedirect("/newproject/index.jsp");
	}

}
