<%@page import="com.cdac.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<h1>
		Welcome <%= (session.getAttribute("user") !=null)?((User)session.getAttribute("user")).getUserName(): "!!!!!!!!!"%>
	</h1>
	<h1>
		<a href="logout.htm">Logout</a>
	</h1>
	</h1>
</body>
</html>
