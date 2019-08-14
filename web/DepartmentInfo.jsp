<%-- 
    Document   : DepartmentInfo
    Created on : 11 Oct, 2018, 1:55:57 AM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String dept_name=request.getAttribute("dept_name").toString();
    String dept_info=request.getAttribute("dept_info").toString();
    int count=Integer.parseInt(request.getAttribute("count").toString());
    ArrayList doctors[]=new ArrayList[15];
    doctors=(ArrayList[])request.getAttribute("dlist");
   
    
%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="Containers.css">
	<title></title>
</head>
<body>

	<div class="menu">
		<a href="">Contact Us</a>
		<a href="">For Management</a>
		<a href="">For Customers</a>
		
	</div>

	<div class="login-container">
		 <h3 style="color: #fff;"><%=dept_name%></h3>
         <p style="color: #fff;"><%=dept_info%></p>        
    </div>
         <%
             for(int i=0;i<count;i++)
             {
                 %>
    <div style="top:<%=60+40*i%>%;" class="login-container">
    	 <p style="text-align: center; color: #fff;">Name : Doctor <%=doctors[i].get(0)%> <%=doctors[i].get(1)%></p><br>
    	 <p style="text-align: center; color: #fff;">Age : <%=doctors[i].get(2)%> years</p><br>
         <p style="text-align: center; color: #fff;">Email : <%=doctors[i].get(3)%></p><br>
    </div>
                 <%
                     
                 }
             %>

</body>
</html>
