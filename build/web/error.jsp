<%-- 
    Document   : error
    Created on : 10 Oct, 2018, 10:30:16 PM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String error=request.getAttribute("error").toString();
    String source=request.getAttribute("source").toString();
    String link=request.getAttribute("link").toString();
    %>
<!DOCTYPE html>
<html>
<head>
	<title>Contact Support</title>
	<link rel="stylesheet" type="text/css" href="Form1.css">
	<link rel="stylesheet" type="text/css" href="homepage.css">
	<link rel="stylesheet" type="text/css" href="style.css">
	 <style> 
    .container
     {
	font-family: sans-serif;
	color: #FFF;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 400px;
    padding: 40px;
    background: rgba(29,92,62,.8);
    box-sizing: border-box;
    box-shadow: 0 15px 25px rgba(22,69,46,.5);
    border-radius: 10px;
     }

     textarea{
     	width: 100%;
     	border-radius: 3px;
     	border:none;
     	padding: 5px;
     }
	</style>
</head>

   
	<body>
		<div class="nav">
	
                    <a href="<%=source%>"><%=link%></a>
       </div>
		
		<div class="container"> 
                    <p style="align:centre;"><%=error%></p>
		</div>

</body>
</html>