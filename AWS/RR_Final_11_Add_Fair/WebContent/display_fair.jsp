RR_Final_10_Booking_details2<%@page import="java.util.List"%>
<%@page import="com.cdac.dto.Train"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="cache_control.jsp" %>
<%@ include file="header.jsp" %>
<% 
	User user2 =(User)session.getAttribute("user");
	if(user2 !=null  && user2.getUserId() > 0 && user2.getUserName().equalsIgnoreCase("admin")){
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Trains</title>
</head>
<body>
<table align="center">
		<%
			List<Train> tlist = (List<Train>)request.getAttribute("tList"); 
		for(Train tr : tlist){
		%>
		<tr>
			<td>
				<%= tr.getTrainName() %>
			</td>
			<td>
				<%=tr.getTrainNo()%>
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
				<a href="train_fair.htm?trainId=<%= tr.getTrainId() %>">Update Fair</a>
			</td>
		<%
		}
		%>
		<td>
			<a href="home_admin.jsp">Back</a>
		</td>
	</table>
</body>
</html>
<%
	}else
		response.sendRedirect("index.jsp");
%>