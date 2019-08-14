<%-- 
    Document   : PatientInfoCard
    Created on : 12 Oct, 2018, 8:57:47 PM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList info = (ArrayList)request.getAttribute("info");
    String gender=info.get(3).toString();
    if(gender.equals("Male"))
    {
        gender="Mr.";
    }
    else
    {
        gender="Ms.";
    }
    %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="Containers.css">
	<title></title>
</head>
<body>


	<div class="menu">
        <a href="Homepage.html">Logout</a>
	</div>




	 <div class="login-container">
            <h2>Welcome <%=gender%> <%=info.get(1).toString()%> <%=info.get(2).toString()%></h2>
            <br>
            <h2>Your ID is <strong>P<%=Integer.parseInt(info.get(0).toString())%></strong></h2>
            <br>
            <p style="color: #fff; text-align: center;">Your appointment has been confirmed with doctor <strong><%=info.get(4).toString()%> </strong>of department <%=info.get(5).toString()%> <br>at <strong>9:15 Tomorrow</strong></p>
        </div>

</body>
</html>