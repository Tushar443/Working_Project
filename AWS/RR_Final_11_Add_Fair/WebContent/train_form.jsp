<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
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
<title>Train Management</title>
</head>
<body>
<h1> Add Trains</h1>
<spr:form action="train_add_form.htm" method="post" commandName="train">
	<table align="center">
		<tr>
		<td>Train No</td>
		</tr>
		<tr>
		<td>
			<spr:input path="trainNo"/>
		</td>
		</tr>
		<tr>
		<td>Train Name</td>
		</tr>
		<tr>
		<td>
			<spr:input path="trainName"/>
		</td>
		</tr>
		
		<tr>
		<td>Source</td>
		</tr>
		<tr>
		<td>
			<spr:input path="departs"/>
		</td>
		</tr>
		
		<tr>
		<td>Destination</td>
		</tr>
		<tr>
		<td>
			<spr:input path="reach"/>
		</td>
		</tr>
		
		<tr>
		<td>General</td>
		</tr>
		<tr>
		<td>
			<spr:input path="gen"/>
		</td>
		</tr>
		
		<tr>
		<td>A.C.1</td>
		</tr>
		<tr>
		<td>
			<spr:input path="ac1"/>
		</td>
		</tr>
		
		<tr>
		<td>A.C.2</td>
		</tr>
		<tr>
		<td>
			<spr:input path="ac2"/>
		</td>
		</tr>
		
		<tr>
		<td>FC</td>
		</tr>
		<tr>
		<td>
			<spr:input path="fc"/>
		</td>
		</tr>
		
		<tr>
		<td>Sleeper coach</td>
		</tr>
		<tr>
		<td>
			<spr:input path="slp"/>
		</td>
		</tr>
		
		<tr>
		<td>Date Of Travel</td>
		</tr>
		<tr>
		<td>
			<spr:input path="dateOfTravel"/>
		</td>
		</tr>
		<tr>
		<td><a href="home_admin.jsp">Back</a>
		<td>
		<input type="submit" value="Add Train">
		</td>
		</tr>
	</table>
	</spr:form>
</body>
</html>
<%
	}else
		response.sendRedirect("index.jsp");
%>