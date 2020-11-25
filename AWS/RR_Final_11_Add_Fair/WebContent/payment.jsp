<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ include file="cache_control.jsp" %> 
                
<%@ include file="header.jsp" %>
<% 
	User user1 =(User)session.getAttribute("user");
	if(user1 !=null  && user1.getUserId() > 0){
		User bookUser=(User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
</head>
<body>
<div>
		<table align="center">
		<tr>
		<tr>
		<td>Total Amount</td>
		</tr>
		<tr>
		<td>
			<%= bookUser.getTotalPrice()%>
		</td>
		</tr>
		<tr>
		<td>No of Passengers</td>
		</tr>
		<tr>
		<td>
			<%= bookUser.getNoOfPassengers()%>
		</td>
		</tr>
		<tr>
		<td>Seat Type</td>
		</tr>
		<tr>
		<td>
			<%= bookUser.getSeatType()%>
		</td>
		</tr>
		<tr>
		<td><a href="payment.htm" >Pay</a></td>
		</tr>
		</table>
		</div>
		
</body>
</html>

<%
	}else
		response.sendRedirect("index.jsp");
%>