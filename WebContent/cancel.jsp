
<%@page import="dbPackage.MyDbConnection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
String doctor,time;
doctor = request.getParameter("doc");
time = request.getParameter("tim");


String deleteQuery = "DELETE FROM `reservation` WHERE `doctor` ='" + doctor + "' and `Rtime` = '" + time + "'";
String doctorUpdate = "UPDATE `doctors` SET `Davailable` = '1' WHERE `Dname` = '"+ doctor +"' and `Dtime` = '"+ time +"'";

Connection con = MyDbConnection.getConnection();
Statement stmt = con.createStatement();

stmt.executeUpdate(deleteQuery);
stmt.executeUpdate(doctorUpdate);
response.sendRedirect("home.jsp");
%>