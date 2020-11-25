<%@page import="com.cdac.dto.Train"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ include file="cache_control.jsp" %>
       <%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr"%> 
       
<%@ include file="header.jsp" %>
<% 
	User user2 =(User)session.getAttribute("user");
	if(user2 !=null  && user2.getUserId() > 0 && user2.getUserName().equalsIgnoreCase("admin")){
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Train Price</title>
</head>
<body>
<spr:form action="update_DB.htm" method="post" commandName="classType">
		<table id="table-1"  align="center">
			<tbody>
				<tr>
					<td>
						<spr:input path="price"/>
					</td>
				</tr>
				
				<tr> 
					<td>
						<input type="submit" value="Next">
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