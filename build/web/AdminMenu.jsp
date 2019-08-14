<%-- 
    Document   : AdminMenu
    Created on : 23 Sep, 2018, 12:57:51 PM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Administration</title>
	<link rel="stylesheet" type="text/css" href="Form1.css">
	<link rel="stylesheet" type="text/css" href="homepage.css">
</head>
<body style="background-image: url('AdminMenuBack.jpg');">
    <%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");
    request.setAttribute("source","AdminMenu");
    %>
	<div class="nav">
       	<div style="margin: 0; margin-left: 5px; font-size: 40px; float: left; color: #fff;"><a href="GaiaFront.html" style="margin: 0; padding: 0;">GAIA</a></div>
       	    <a href="Index.html">Logout</a>
        </div>
    
    <div class="empty-container" style="top: 30%;">
        <form action="FireServlet" method="post">
        <div style="display: block; padding: 50px;">
    	<div style="margin: auto;">
    	   <a href="DoctorAdmission.html">Hire Doctor</a><br>
    	</div>
    	<div style="margin: auto; margin: 30px 0 0 0; padding: 20px 10px 0px 3px;">   
            <input type="text" name="d_id">
        </div>
        <div style="margin: auto; padding: 10px;">
        	<input type="submit" value="Fire Doctor" name="Fire Doctor">
        </div>
        </div>
        </form>

       
    </div>

     <div class="empty-container1" style="float: right;">
        	<a href="AdminPatientView.jsp">Active Patients</a>
        	<a href="AdminDoctorView.jsp">Doctors</a>
        	<a href="AdminDepartmentView.jsp">Departments</a>
        </div>
   
</body>
</html>
