<%@page import="com.cdac.dto.Train"%>
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
<title>Booking Details</title>
</head>
<body>
<%
	User user = (User)request.getAttribute("user");
	Train train = (Train) request.getAttribute("train");
		if(train !=null){
%>
<table align="center">
		<tr>
			<td>
				<%= train.getTrainName() %>
			</td>
			<td>
				<%=train.getTrainNo()%>
			</td>
			
			<td>
				<%=  train.getDateOfTravel()%>
			</td>
			<td>
				<%= train.getDeparts() %>
			</td>
			<td>
				<%=train.getReach()%>
			</td>
			
			<td>
				<%= user.getNoOfPassengers() %>
			</td>
			<td>
				<%=user.getSeatType()%>
			</td>
				<td>
				<%=  user.getTotalPrice()%>
				<% if(user.getTotalPrice()>0){ %>
				<td>Paid</td>
			<%
			}
			%>
			</td>
		<td>
			<a href="home.jsp">Back</a>
		</td>
	</table>
</body>
</html>
<%
		}
		else{
			%>
			<tr><td style="color: red"><h1>No Booking</h1></td></tr>
			<a href="home.jsp">Back</a>
	<%	
	}
%>	

<%
	}else
		response.sendRedirect("index.jsp");
%>