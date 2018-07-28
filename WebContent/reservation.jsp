<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="dbPackage.MyDbConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
System.out.println(request.getSession().getAttribute("userEmail"));
if(request.getSession().getAttribute("userEmail") == null)
	response.sendRedirect("index.jsp");
%>

<html>
<head>
<script type="text/javascript" src="ajaxcalls.js"></script>

<link rel="stylesheet" href="/newproject/reservationStyling.css" type="text/css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Doctors Online</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="home.jsp">Home</a></li>
				<li class="active"><a href="#">Reservations</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	
	<% 
	Connection con = MyDbConnection.getConnection();
	Statement stmt = con.createStatement();
	String query = "SELECT Distinct `Dtime` FROM `doctors` WHERE Davailable = 1";
	String query2 = "SELECT Distinct `Dname` FROM `doctors` WHERE Davailable = 1";
	ResultSet rs = stmt.executeQuery(query);
	%>

	<section id="contact">
		<div class="section-content">
			<h1 class="section-header">
				Get <span class="content-header wow fadeIn " data-wow-delay="0.2s"
					data-wow-duration="2s">Reservation</span>
			</h1>
		</div>
		<div class="contact-section">
			<div class="container">
				<form method="post" action="/newproject/ReserveServlet">
					<div class="col-md-6 form-line">
						<div class="form-group">
							<label for="exampleInputUsername">Your Email</label> <input name="REmail"
								type="text" value="<%=request.getSession().getAttribute("userEmail")%>" class="form-control" id="" placeholder=" Enter Email">
						</div>
						
						<div class="form-group">
							<label for="telephone">Doctor Name</label> 
							<select name="my_doctor" onchange="javascript:populatetime(this.value)">
							<%
							ResultSet rs2 = stmt.executeQuery(query2);
							while(rs2.next()) {
							String name = rs2.getString("Dname");
							%>
								<option value="<%=name%>"> <%=name %> </option>
							<%
							}
							%>
							</select>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail">Time</label> 
							<div id="time_selection">
							<select name="my_time">
								<option>Please Select Doctor. </option>
							</select>
							</div>
						</div>
					</div>
						<div>
							<button type="submit" class="btn btn-default submit">
								<i class="fa fa-paper-plane" aria-hidden="true"></i> Reserve
							</button>
						</div>

					
				</form>
				</div>
			</div>
		
	</section>


</body>
</html>