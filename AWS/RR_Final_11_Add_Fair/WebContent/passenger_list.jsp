<%@page import="com.cdac.dto.Train"%>
<%@page import="com.cdac.dto.User"%>
<%@page import="java.util.List"%>
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
<title>Passenger List</title>
</head>
<body>
<table align="center">
		<%
			Train tr =(Train)request.getAttribute("train"); 
			List<User> plist = (List<User>)request.getAttribute("passengerList");
		%>
		<tr>
		<th> Train Details</th>
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
			</tr>
			<tr>
			<td>
				@@@@@@@
			</td>
			</tr>
		<%
		for(User ur : plist){
			if(ur!=null && ur.getUserId()>0){
		%>
			<tr>
				<th> User Details</th>
			<td>
				<%= ur.getUserName() %>
			</td>
			<td>
				<%= ur.getNoOfPassengers()%>
			</td>
			<td>
				<%=ur.getSeatType()%>
			</td>
			
			<td>
				<%=  ur.getTotalPrice()%>
			</td>
			<td>
				<%= ur.getUserId() %>
			</td>
			</tr>
		<%
			}else{
		%>
				<tr><td style="color: red">No Passengers</td></tr>
		<%	
			}
		}
		%>	
		<tr>
		<td>
			<a href="searchByAdmin.htm">Back</a>
		</td>
		</tr>
	</table>
</body>
</html>
<%
	}else{
		response.sendRedirect("index.jsp");
	}
%>