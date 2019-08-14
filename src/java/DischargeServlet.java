/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class DischargeServlet extends HttpServlet {

   

    
     public void deletefrompatient(Connection con,int pid) throws SQLException
    {
        Statement smt=con.createStatement();
        String sql="delete from patients where p_id='" +pid +"';";
        smt.executeUpdate(sql);
        
    }
     
     public void updateFlag(Connection con,int doctorId) throws SQLException
     {
         Statement smt=con.createStatement();
         String sql="select flag from doctors where d_id=" +doctorId;
         ResultSet rs=smt.executeQuery(sql);
         String flag=null;
         while(rs.next())
         {
             flag=rs.getString("flag");
         }
         System.out.println(flag);
         int updatedFlag=Integer.parseInt(flag);
         updatedFlag=updatedFlag-1;
         System.out.println(updatedFlag);
         flag=Integer.toString(updatedFlag);
         String sql2="update doctors set flag='" +flag +"' where d_id='" +doctorId +"';";
         System.out.println(sql2);
         Statement smt2=con.createStatement();
         smt2.executeUpdate(sql2);
     }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          Connection con=DemoConnection.getConnection();
                  Statement smt=con.createStatement();
                  
            HttpSession session=request.getSession();
            String patientId=request.getParameter("pid");
            int pid=Integer.parseInt(patientId);
          String sql1="select d_id,bill_id from process where p_id=" +pid;
          ResultSet rs1=smt.executeQuery(sql1);
          String doctorId=null;
          String billId=null;
          while(rs1.next())
          {
              doctorId=rs1.getString("d_id");
              billId=rs1.getString("bill_id");
          }
          
            if(doctorId==null)
            {
                request.setAttribute("error","sorry Cannot Find patient with given Id");
                request.setAttribute("source","Index.html");
                request.setAttribute("link","Back");
                RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            }
          int dId=Integer.parseInt(doctorId);
        
          String sql2="select t.t_name,t.price from treatments t join billing b on b.t_id=t.t_id where b.bill_id=" +"'" +billId +"';";
          Statement smt2=con.createStatement();
          ResultSet rs2=smt.executeQuery(sql2);
          ArrayList treatments=new ArrayList();
          
          while(rs2.next())
          {
              ArrayList treatment=new ArrayList();
              treatment.add(rs2.getString("t_name"));
              treatment.add(rs2.getInt("price"));
              treatments.add(treatment);
          }
          
          String sql3="select first_name,last_name,gender from patients where p_id=" +pid;
          String sql4="select last_name from doctors where d_id=" +dId;
          
          Statement smt3=con.createStatement();
          ResultSet rs3=smt3.executeQuery(sql3);
          String firstName=null;
          String lastName=null;
          String gender=null;
          
          ArrayList patient=new ArrayList();
          while(rs3.next())
          {
              firstName=rs3.getString("first_name");
              lastName=rs3.getString("last_name");
              gender=rs3.getString("gender");
          }
          
          patient.add(firstName);
          patient.add(lastName);
          if(gender.equals("Male"))
          {
            patient.add("Mr.");
          }
          else
          {
              patient.add("Ms.");
          }
          
          Statement smt4=con.createStatement();
          ResultSet rs4=smt4.executeQuery(sql4);
          String doctorLast=null;
          while(rs4.next())
          {
              doctorLast=rs4.getString("last_name");
          }
          
           
            //deletefrompatient(con, pid);
            //updateFlag(con, dId);
          
          request.setAttribute("tlist", treatments);
          request.setAttribute("lastName",doctorLast);
          request.setAttribute("patient",patient);
          request.setAttribute("b_id",billId);
          
           session.setAttribute("bill",billId);
            RequestDispatcher rd =request.getRequestDispatcher("/billing.jsp");
            rd.forward(request, response);
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
