<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="dbPackage.MyDbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/newproject/homeStyling.css" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%System.out.println(request.getSession().getAttribute("userEmail"));
if(request.getSession().getAttribute("userEmail") == null)
	response.sendRedirect("index.jsp");
Connection con = MyDbConnection.getConnection();
Statement stat = con.createStatement();
String query = "select * from reservation";
ResultSet rs = stat.executeQuery(query);
%>
</head>
<body>

	<nav id="navi" class="navbar navbar-default navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Doctors Online</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="home.jsp">Home</a></li>
				<li><a href="reservation.jsp">Reservation</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span>
						Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<h2>Your Reservations</h2>
		<p>You reserved following doctors:</p>
		<%
		if(rs.isBeforeFirst()){
		%>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Your Email</th>
					<th>Time</th>
					<th>Doctor Name</th>
					<th>Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
			while(rs.next()) {
		String email = rs.getString("Remail");
		String time = rs.getString("Rtime");
		String doctor = rs.getString("doctor");
		System.out.println(email + "  " + time + "  " + doctor);
		
		%>
				<tr>
					<td><%=email  %></td>
					<td><%=time %></td>
					<td><%=doctor  %>  </td>
					<td>
					<a  href="cancel.jsp?doc=<%= doctor %>&tim=<%= time %>">Cancel</a>
					</td>
				</tr>
		<%
			}
		} else {
		%>
		<h3> You Have No reservations</h3>
		<%
		}
		%>	
			</tbody>
		</table>
	</div>
</body>
</html>