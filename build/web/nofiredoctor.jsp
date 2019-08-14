<%-- 
    Document   : nofiredoctor
    Created on : 30 Sep, 2018, 9:51:04 AM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String dept_name=request.getAttribute("dept_name").toString();
%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet"  href="style.css">
    <link rel="stylesheet"  href="homepage.css">
<script type="text/javascript" src="/FC6F1877-52B1-CB49-8F09-F221A84E5EC1/main.js" charset="UTF-8"></script></head>
<body style="background-image: url('AdminMenuBack.jpg'); background-attachment: fixed; background-size: cover;">
	 <div class="nav">
            <div style="margin: 0; margin-left: 5px; font-size: 40px; float: left; color: #fff;">GAIA
            </div>
            <a href="Index.html">Logout</a>
     </div>

     <div class="container1" style="margin: auto; top: 45%; left: 37%;">
     	<p>Cannot Fire Patient from <%=dept_name%>.There are not Enough doctors.</p>
     </div>
</body>
</html>