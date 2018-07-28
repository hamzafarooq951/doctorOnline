<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dbPackage.MyDbConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>

<%
	String sql = "SELECT Dtime FROM doctors WHERE Dname='" + request.getParameter("q") + "' AND Davailable = 1";
	Connection con = MyDbConnection.getConnection();
	Statement stmt = con.createStatement();
	ResultSet rs;
	
	System.out.println(sql);
	
	rs = stmt.executeQuery(sql);
	
	PrintWriter pr = response.getWriter();
	pr.write("<select name=\"my_time\">");
	if(rs.isBeforeFirst()) {
		while(rs.next()){
			pr.append("<option value = \"" + rs.getString("Dtime") + "\" >" + rs.getString("Dtime") + "</option>");
		}
	} else {
		pr.append("<option>Doctor is busy today.</option>");
	}
	pr.write("</select>");
%>
