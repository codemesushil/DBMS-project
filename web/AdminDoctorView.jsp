<%-- 
    Document   : AdminDoctorView
    Created on : 29 Sep, 2018, 7:02:36 PM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page import="src.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList doctors[]=new ArrayList[100];
Admin admin=new Admin();
doctors=admin.getDoctors();
int count=admin.countDoctors;

%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet"  href="style.css">
    <link rel="stylesheet"  href="homepage.css">
<script type="text/javascript" src="/FC6F1877-52B1-CB49-8F09-F221A84E5EC1/main.js" charset="UTF-8"></script></head>
<body style="background-image:url('AdminMenuBack.jpg') ">

	 <div class="nav">
           
            
             <a href="AdminMenu.jsp">Back</a>
            <a href="ContactSupport.html">Logout</a>
     </div>

  

    <div class="admincontainer" style="top:30%;">
     	   <div><h1>Doctors<h1></div>
     	   	<div>
     	   	<table>
     	   		<tr>
     	   			<th>Doctor ID</th>
     	   			<th>First Name</th>
     	   			<th>Last Name</th>
     	   			<th>Gender</th>
     	   			<th>Age</th>
     	   			<th>Contact Number</th>
     	   			<th>Email</th>
     	   			<th>Department</th>
     	   		</tr>
                        <%
                            for(int i=0;i<count;i++)
                            {
                                
                                %>
     	   		<tr>
                            <td><%=doctors[i].get(0)%></td>
     	   			<td><%=doctors[i].get(1)%></td>
     	   			<td><%=doctors[i].get(2)%></td>
     	   			<td><%=doctors[i].get(3)%></td>
     	   			<td><%=doctors[i].get(4)%></td>
     	   			<td><%=doctors[i].get(5)%></td>
     	   			<td><%=doctors[i].get(6)%></td>
     	   			<td><%=doctors[i].get(7)%></td>
     	   		</tr>
                        <%
                          }
                          %>
     	   	</table>
     	   </div>
     </div>

</body>
</html>
