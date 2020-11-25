
<%@page import="com.cdac.dto.Train"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ include file="cache_control.jsp" %>
<%@ include file="header.jsp" %>

<% 
	User user1 =(User)session.getAttribute("user");
	if(user1 !=null  && user1.getUserId() > 0){
	Train tr = (Train)session.getAttribute("train"); 
		if(tr!=null){
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cancel Ticket</title>
</head>
<body>
		<table align="center">
		<tr>
			<td>
				<%=tr.getTrainNo()%>
			</td>
			
			<td>
				<%= tr.getTrainName() %>
			</td>
			
			<td>
				<%=  tr.getDateOfTravel()%>
			</td>
			<td>
				<%= tr.getDeparts() %>
			</td>
			<td>
				<%=tr.getReach()%>
			</td>
			
			<td>
				<%=  user1.getNoOfPassengers()%>
			</td>
			<td>
				<%= user1.getSeatType()%>
			</td>
			<td>
				<%=user1.getTotalPrice()%>
				<% if(user1.getTotalPrice()>0){ %>
				<td>Paid</td>
			<%
			}
			%>
			</td>
		<td>
			<a href="home.jsp">Back</a>
		</td>
		<td>
			<a href="cancel_train.htm">Cancel Booking</a>
		</td>
	</table>
	
	
</body>
</html>
<%}else{%>
	<h1 style="color: red">No Booking</h1>
	<a href="home.jsp">Back</a>
		
<%	
}
	}else
		response.sendRedirect("index.jsp");
%>