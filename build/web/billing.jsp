<%-- 
    Document   : billing
    Created on : 26 Sep, 2018, 2:22:25 PM
    Author     : KSHITIJ DESHPANDE
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String doctorLast=request.getAttribute("lastName").toString();
    String bid=request.getAttribute("b_id").toString();
    ArrayList patient=(ArrayList)request.getAttribute("patient");
    ArrayList treatments=(ArrayList)request.getAttribute("tlist");
    ArrayList tnames=new ArrayList();
    ArrayList tprice=new ArrayList();
    for(int i=0;i<treatments.size();i++)
    {
        ArrayList treatment=(ArrayList)treatments.get(i);
        String name=treatment.get(0).toString();
        int price=Integer.parseInt(treatment.get(1).toString());
        tnames.add(name);
        tprice.add(price);
    }
    int total=0;
    for(int i=0;i<tprice.size();i++)
    {
        total=total+Integer.parseInt(tprice.get(i).toString());
    }
    System.out.println(session.getAttribute("bill").toString());
    
    %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet"  href="style.css">
    <link rel="stylesheet"  href="homepage.css">
<script type="text/javascript" src="/FC6F1877-52B1-CB49-8F09-F221A84E5EC1/main.js" charset="UTF-8"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script></head>
<body style="background-image: url('BillingBack.jpg');">
	 <div class="nav">
            <div style="margin: 0; margin-left: 5px; font-size: 40px; float: left; color: #fff;"><a href="GaiaFront.html" style="margin: 0; padding: 0;">GAIA</a>
            </div>
            <a href="version.html">Version</a>
            <a href="ContactSupport.html">Contact Support</a>
     </div>

     

    <div class="billcontainer" style="height:535px;">
         <div>
             <div><h3><%=patient.get(2)%> <%=patient.get(0)%> <%=patient.get(1)%><h3></div>
                     <div><h5 style="float:left; margin-left:10px;"><%=bid%></h5>
                     </div>
                     </div>
     	   	<table>
     	   		<tr>
     	   			<th>Procedure</th>
     	   			<th>Amount</th>
     	   		</tr>
                        <%
                            for(int i=0;i<treatments.size();i++)
                            {
                            %>
     	   		<tr>
                            <td><%=tnames.get(i)%></td>
     	   			<td><%=tprice.get(i)%></td>
     	   		</tr>
                        <%
                            }%>
     	   		<tr style="background: #2E8B57">
     	   			<td>Total</td>
     	   			<td><%=total%></td>
     	   		</tr>
     	   	</table>
            <br><br>
            <div>
                <button type="button"  class="button" onclick="confirm()" style="margin: 10px 0 20px 25px" >Pay</button>
                <h5>Appointed to Dr <%=doctorLast%></h5></div>
                    
                <div id="Payment-Options" style="float: left; position: absolute;">
                
                
                    <form action="deleteServlet" method="post">
                    <div style="padding: 10px; transform: translate(5%,0);">
                            <div style="margin: auto;padding: 10px;">
                            <label style="color: #fff;">
                        <input type="radio" class="option-input radio" name="payment-method" value="Net Banking"/>
                            Net Banking
                            </label>
      
      
                            <label style="color: #fff;">
                        <input type="radio" class="option-input radio" name="payment-method" value="Credit Card"/>
                            Credit Card
                            </label>
                            
                        <label style="color: #fff;">
                        <input type="radio" class="option-input radio" name="payment-method" value="Debit Card"/>
                            Debit Card
                            </label>
                         
                        <label style="color: #fff;">
                        <input type="radio" class="option-input radio" name="payment-method" value="Cheques"/>
                            Cheque
                            </label>
                    </div>
                    <br><br>
                    <div><input type="submit"  class="button" id="submit"style="margin: 10px 0 20px 25px" >Confirm</button></div>
                </div>
                    </form>
     
     <script>
         document.getElementById("Payment-Options").style.display='none';
         document.getElementById("submit").style.display='none';
         function confirm()
         {
         document.getElementById("Payment-Options").style.display='block';
         document.getElementById("submit").style.display='block';
         }
     </script> 
</body>
</html>
