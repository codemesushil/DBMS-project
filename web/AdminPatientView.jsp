<%-- 
    Document   : AdminPatientView
    Created on : 27 Sep, 2018, 12:30:16 PM
    Author     : KSHITIJ DESHPANDE
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="src.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    int count=0;
    ArrayList patients[]=new ArrayList[50];
Admin admin=new Admin();
patients=admin.getPatients();
count=admin.countPatients;
    

  
    
%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet"  href="style.css">
    <link rel="stylesheet"  href="homepage.css">
<script type="text/javascript" src="/FC6F1877-52B1-CB49-8F09-F221A84E5EC1/main.js" charset="UTF-8"></script></head>
<body>

	 <div class="nav">

            
             <a href="AdminMenu.jsp">Back</a>
            <a href="Index.html">Logout</a>
     </div>

     <!--<div class="container1">
         <form action="SearchServlet" method="post">
             <input type="text" name="pid" placeholder="Search Patient(ID)" pattern="[0-9]{4}" title="Enter Valid Patient Id">
             <button type="submit" name="Search">Search<button>
     	</form>
     </div>-->

     <div class="admincontainer">
     	   <div><h1>Patients<h1></div>
     	   	<div>
     	   	<table>
     	   		<tr>
     	   			<th>Patient ID</th>
     	   			<th>First Name</th>
     	   			<th>Last Name</th>
     	   			<th>Gender</th>
     	   			<th>Age</th>
     	   			<th>Contact Number</th>
     	   			<th>Ailment</th>
     	   			<th>Blood Group</th>
     	   			<th>Insurance Number</th>
     	   			<th>Address</th>
     	   			<th>Diagnosed</th>
     	   		</tr>
                        <%
                            for(int i=0;i<count;i++)
                            {
                                %>
     	   		<tr>
                            <td><%=patients[i].get(0)%></td>
     	   			<td><%=patients[i].get(1)%></td>
     	   			<td><%=patients[i].get(2)%></td>
     	   			<td><%=patients[i].get(3)%></td>
     	   			<td><%=patients[i].get(4)%></td>
     	   			<td><%=patients[i].get(5)%></td>
     	   			<td><%=patients[i].get(6)%></td>
     	   			<td><%=patients[i].get(7)%></td>
     	   			<td><%=patients[i].get(8)%></td>
     	   			<td><%=patients[i].get(9)%></td>
     	   			<td><%=patients[i].get(10)%></td>
     	   		</tr>
                        <%
                            request.setAttribute("req","from_patint_view");
                          }
                          %>
     	   	</table>
     	   </div>
     </div>

</body>
</html>