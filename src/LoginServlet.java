
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbPackage.MyDbConnection;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Statement stmt;
	private Connection con;
	private ResultSet rs;
	String query;
	private HttpSession session;
       
    /**
      * @see HttpServlet#HttpServlet()
      */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("inpEmail");
		String password = request.getParameter("inpPassword");
		String DbPassword = null, DbEmail = null;
		session = request.getSession();
		
		
		System.out.println(email + "  " + password);
		
		con = MyDbConnection.getConnection();
		query = "SELECT username, email, password FROM user WHERE email =" + "'" + email + "'";
		System.out.println(query);
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				DbEmail = rs.getString("email");
				DbPassword = rs.getString("password");
				System.out.println(DbEmail + "  " + DbPassword);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(email.equalsIgnoreCase(DbEmail) && password.equalsIgnoreCase(DbPassword) ) {
			
			session.setAttribute("userEmail", email);
			response.sendRedirect( "home.jsp" );
			System.out.println("Session varaiable:" + session.getAttribute("userEmail"));
			
		}else {
			response.sendRedirect( "index.jsp?loginStatus=failed" );
		}
	}
}
