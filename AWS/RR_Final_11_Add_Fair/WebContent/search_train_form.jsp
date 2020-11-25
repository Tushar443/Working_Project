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
<title>Search Train</title>
</head>
<body>
	<h1>Search Train</h1>
<spr:form action="search_train_form.htm" method="post" commandName="train">
	<table align="center">
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
		<td>Date of Travel</td>
		</tr>
		<tr>
		<td>
			<spr:input path="dateOfTravel"/>
		</td>
		</tr>
		
		<tr>
		<td><a href="home.jsp">Back</a>
		<td>
		<input type="submit" value="Search">
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