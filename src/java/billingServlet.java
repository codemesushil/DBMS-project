/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class billingServlet extends HttpServlet {

  public int setflag(String doctorId,Connection con) throws SQLException
  {
      Statement smt=con.createStatement();
      String sql="select flag from doctors where d_id=" +doctorId;
      ResultSet rs=smt.executeQuery(sql);
      int flag=0;
      while(rs.next())
      {
          flag=Integer.parseInt(rs.getString("flag"));
      }
            flag=flag-1;
            String updateFlag="update doctors set flag='" +flag +"' where d_id=" +doctorId;
            Statement update=con.createStatement();
            update.executeUpdate(updateFlag);
            return flag;
  }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session=request.getSession();
            String treatments[]=request.getParameterValues("treatment");
            ArrayList selectedTreatments=new ArrayList();
            for(int i=0;i<treatments.length;i++)
            {
                selectedTreatments.add("'" +treatments[i] +"'");
            }
            
            Connection con=DemoConnection.getConnection();
            Statement smt1=con.createStatement();
            String sql1="select t_id from treatments where t_name in (" +selectedTreatments.toString().substring(1,selectedTreatments.toString().length()-1) +")";
            System.out.println(sql1);
            ResultSet rs1=smt1.executeQuery(sql1);
            //Statement smt[]=null;
            int i=0;
            double random=Math.random()*10000;
                String bill=Double.toString(random).substring(0,4);
                String billId="bill" +bill;
            while(rs1.next())
            {
                Statement smt=con.createStatement();
                int tid=rs1.getInt("t_id");
                String sql="insert into billing(bill_id,t_id,paid) values('" +billId +"'" +", " +tid +", " +0 +");";
                smt.executeUpdate(sql);
                i++;
            }
            
            
            String username=(String)session.getAttribute("username");
            String doctorId=username.substring(1);
             ArrayList patients=(ArrayList)session.getAttribute("plist");
           ArrayList patient=(ArrayList)patients.get(0);
           
           
           String sql2="update process set bill_id ='" +billId +"'where p_id=" +patient.get(0) +" and d_id=" +doctorId +" ;";
           Statement smt2=con.createStatement();
           smt2.executeUpdate(sql2);
           int updatedFlag=setflag(doctorId,con);
           String sql3="update process set diagnosed='1' where p_id=" +patient.get(0);
           Statement smt3=con.createStatement();
           smt3.executeUpdate(sql3);
           patients.remove(0);
           
           
           
           session.setAttribute("plist",patients);
            System.out.println(updatedFlag);
           if(updatedFlag>0)
           {
            RequestDispatcher rd=request.getRequestDispatcher("/doctor.jsp");
            rd.forward(request, response);
           }
           else
           {
               RequestDispatcher rd=request.getRequestDispatcher("DoctorNoPatient.html");
               rd.forward(request, response);
           }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
