<%@page import="com.cdac.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="cache_control.jsp" %>
<%@ include file="header.jsp" %>
<% 
	User user1 =(User)session.getAttribute("user");
	if(user1 !=null  && user1.getUserId() > 0){
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome To Home  <%= (session.getAttribute("user") !=null)?((User)session.getAttribute("user")).getUserName(): "!!!!!!!!!"%></h1>

<h3>
<a href="search.htm">Search Trains</a>
</h3>

<h3>
<a href="cancel_seat.htm">Cancel Ticket</a>
</h3>

<h3>
<a href="booking_status.htm">Booking Status</a>
</h3>

<h3>
<a href="index.jsp">Back</a>
</h3>

</body>
</html>
<%
	}else
		response.sendRedirect("index.jsp");
%>