<%@page import="com.cdac.dto.Train"%>
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
<title>Other Details</title>
</head>
<body>
<spr:form action="book_train.htm" method="post" commandName="user">
		<table id="table-1"  align="center">
			<tbody>
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
					<spr:radiobutton path="noOfPassengers" value="1" />1<br>
					<spr:radiobutton path="noOfPassengers" value="2" />2<br>
					<spr:radiobutton path="noOfPassengers" value="3" />3<br>
					<spr:radiobutton path="noOfPassengers" value="4" />4<br>
					<spr:radiobutton path="noOfPassengers" value="5" />5<br>
					</td>
				</tr>
				<tr> 
					<td>
						<input type="submit" value="Payment">
					</td>
				</tr>
			</tbody>
		</table>
	</spr:form>
</body>
</html>
<%
	}else
		response.sendRedirect("index.jsp");
%>