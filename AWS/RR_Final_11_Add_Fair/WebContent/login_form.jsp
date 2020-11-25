<%@page import="com.cdac.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr"%> 


<%  
	User user1 = (User)session.getAttribute("user");
	
	if(user1 !=null  && user1.getUserId() > 0){
		if(user1.getUserName().equalsIgnoreCase("admin")){
			response.sendRedirect("home_admin.jsp");
		}else{
			response.sendRedirect("home.jsp");	
		}
	}else{
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<spr:form action="login.htm" method="post" commandName="user">
	<table align="center">
		<tr>
		<td>User Name</td>
		</tr>
		<tr>
		<td>
			<spr:input path="userName"/>
			<!--  	<spr:errors path="userName"></spr:errors>-->
		</td>
		</tr>
		<tr>
		<td>Password</td>
		</tr>
		<tr>
		<td>
			<spr:password path="userPass"/>
			<!--  	<spr:errors path="userName"></spr:errors>-->
		</td>
		</tr>
		<tr>
		<td><a href="index.jsp">Back</a></td>
		
		<td>
			<a href="reg_form">Register</a>
		</td>
		<td>
		<input type="submit" value="LogIn">
		</td>
		</tr>
	</table>
	</spr:form>
</body>
</html>
<%
	}
%>