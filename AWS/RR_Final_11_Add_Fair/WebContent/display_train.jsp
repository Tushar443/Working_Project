<%@page import="java.util.List"%>
<%@page import="com.cdac.dto.Train"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
   <%@ include file="cache_control.jsp" %>
<%@ include file="header.jsp" %>
<% 
	User user2 =(User)session.getAttribute("user");
	if(user2 !=null  && user2.getUserId() > 0){
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Trains</title>
</head>
<body>
<%
			List<Train> tlist = (List<Train>)request.getAttribute("train"); 
		for(Train tr : tlist){
%>
	<table align="center">
		
		<tr>
			<td>
				<%=tr.getTrainNo()%>
			</td>
			<td>
				<%= tr.getTrainName() %>
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
			<% if(tr.getGen()>0){ %>
			<td>
				<%=  tr.getGen()%>
			</td>
			<%
			}
			else{
				%>
				<td style="color: red">Full</td>
			<%
			}
			%>
			
			<% if(tr.getAc1()>0){ %>
			<td>
				<%=  tr.getAc1()%>
			</td>
			<%
			}
			else{
				%>
				<td style="color: red">Full</td>
			<%
			}
			%>
			<% if(tr.getAc2()>0){ %>
			<td>
				<%=  tr.getAc2()%>
			</td>
			<%
			}
			else{
				%>
				<td style="color: red">Full</td>
			<%
			}
			%>
			
			
			
			<% if(tr.getFc()>0){ %>
			<td>
				<%=  tr.getFc() %>
			</td>
			<%
			}
			else{
				%>
				<td style="color: red">Full</td>
			<%
			}
			%>
			<% if(tr.getSlp()>0){ %>
			<td>
				<%=  tr.getSlp()%>
			</td>
			<%
			}
			else{
				%>
				<td style="color: red">Full</td>
			<%
			}
			%>
			
			<td>
				
			</td>
			<td>
			<a href="book_train_form.htm?trainId=<%= tr.getTrainId()%>">Book</a>
		</td>
		</tr>
<%
	}
%>
		<tr>
		<td>
			<a href="search.htm">Back</a>
		</td>
		</tr>
	</table>
</body>
</html>
<%
	}else
		response.sendRedirect("index.jsp");
%>