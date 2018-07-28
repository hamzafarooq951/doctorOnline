<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%request.getSession().invalidate();
request.getSession().removeAttribute("userEmail");
response.sendRedirect("index.jsp");%>
