<%@page import="java.util.List"%>
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
				<a href="train_delete.htm?trainId=<%= tr.getTrainId() %>">Delete</a>
			</td>
			<td>
				<a href="update_train_form.htm?trainId=<%= tr.getTrainId()%>">Update</a>
			</td>
			<td>
				<a href="passengers_list.htm?trainId=<%= tr.getTrainId()%>">Passengers List</a>
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