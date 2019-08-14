<%-- 
    Document   : doctor
    Created on : 23 Sep, 2018, 12:53:33 PM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%
           ArrayList patients=(ArrayList)session.getAttribute("plist");
           ArrayList patient=(ArrayList)patients.get(0);
           
           ArrayList treatments=(ArrayList)session.getAttribute("tlist");
           ArrayList tnames=new ArrayList();
           for(int i=0;i<treatments.size();i++)
           {
               String name=treatments.get(i).toString();
               tnames.add(name);
           }
        %>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="homepage.css">
	<link rel="stylesheet" type="text/css" href="input.css">
</head>
<body style="background-image: url('DoctorLoginBack.jpg');">
	<div class="nav" style="margin-bottom: 50px;">
       	<div style="margin: 0; margin-left: 5px; font-size: 40px; float: left; color: #fff;"><a href="GaiaFront.html" style="margin: 0; padding: 0;">GAIA</a></div>
         
            <a href="Index.html">Logout</a>
        </div>
    
        <div style="margin:20px;  width: 100%; position: absolute; text-align: center; top:3%; color: #fff;">
            <h1>Welcome, Dr. <%=patient.get(9)%></h1>
       </div>
   

    <div style="display: inline-block;">
	<div class="information-container">
		  
        <div style="width: 70%;margin: 30px auto; padding: 20px; text-align: center; color: #fff;">
        	<h2>Patient Details</h2>
        </div>

        <div style="width: 70%;margin: 30px auto; padding: 20px; text-align: center; color: #fff;">
        	<div style="float: left;" ><%=patient.get(0)%></div>
        	<div style="float: right"><%=patient.get(2)%> <%=patient.get(1)%></div>
        </div>

        <div style="width: 70%;margin: 30px auto; padding: 20px; text-align: center; color: #fff;">
        	<div style="float: left;">Gender :<%=patient.get(3)%></div>
        	<div style="float: left; margin-left: 120px;">Blood Group :<%=patient.get(5)%></div>
        </div>

        <div style="width: 70%;margin: 30px auto; padding: 20px; text-align: center; color: #fff;">
        	<div style="float: left;"><%=patient.get(7)%> kg</div>
        	<div style="float: left; margin-left: 190px;"><%=patient.get(6)%> cm</div>
                <div style="float: left; margin-left: 190px;"><%=patient.get(8)%> years</div>
                
        </div>
        <div style="float: right; margin: 50px;">
                    <button type="button" onclick="tselect()">Diagnose</button>  
        </div>        
    </div>


        <div class="information-container" id="treatmentMenu" style="float: right; width: 20%; left: 92%; padding: 10px;">
           <h2 style="text-align: center; color: #fff;">Treatments</h2>

        <div style="display: block; padding: 10px; top: 50%;">
        <form action="billingServlet" method="post">
            <%
                for(int i=0;i<tnames.size();i++)
                {
               %>
            <label class="container" ><%=tnames.get(i)%>
                <input type="checkbox" name="treatment" value="<%=tnames.get(i)%>">
  				<span class="checkmark"></span>
			</label>
            <%
                }
                %>


        	<div style="margin-left: 90px; margin-top: 20px;">
                    <input type="submit" name="Submit" value="Submit">
        	</div>
        </form>
        </div>
        </div>
    </div>
  <script>
    document.getElementById('treatmentMenu').style.display='none';
    
    function tselect()
    {
        document.getElementById('treatmentMenu').style.display='block';
    }
    
</script>
</body>

</html>