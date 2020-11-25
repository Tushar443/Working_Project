<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
    <%@ include file="cache_control.jsp" %>
<%@ include file="header.jsp" %>
<% 
	User user1 =(User)session.getAttribute("user");
	if(user1 !=null  && user1.getUserId() > 0 && user1.getUserName().equalsIgnoreCase("admin")){
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Fair Form</title>
</head>
<body>
	<spr:form action="train_fair_form.htm" method="post" commandName="user_add_fair">
		<table id="table-1"  align="center">
				<tr>
					<td>
					<spr:radiobutton value="gen" path="seatType" />General<br>
					<spr:radiobutton value="ac1" path="seatType" />AC1<br>
					<spr:radiobutton value="ac2" path="seatType" />AC2<br>
					<spr:radiobutton value="fc" path="seatType" />First Class<br>
					<spr:radiobutton value="slp" path="seatType" />Sleeper<br>
					</td>
				</tr>
		<tr>
		<td>
			<spr:input path="totalPrice"/>
		</td>
		</tr>
		<tr>
		<td>
		<input type="submit" value="Add">
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