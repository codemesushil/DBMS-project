/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class deleteServlet extends HttpServlet {

    public int setflag(int doctorId,Connection con) throws SQLException
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
          Connection con=DemoConnection.getConnection();
          String pmethod=request.getParameter("payment-method");
          System.out.println(session.getAttribute("bill").toString());
          String bid=session.getAttribute("bill").toString();
          Statement smt1=con.createStatement();
          Statement smt2=con.createStatement();
          String setPayment="update billing set payment_method='" +pmethod +"' where bill_id=" +"'" +bid +"';";
          String setStatus="update billing set paid=" +1 +" where bill_id=" +"'" +bid +"';";
          smt1.executeUpdate(setPayment);
          smt2.executeUpdate(setStatus);
          String sql="select p_id,d_id from process where bill_id='" +bid +"'";
          Statement smt3=con.createStatement();
          ResultSet rs=smt3.executeQuery(sql);
          int pid=0;
          int did=0;
          while(rs.next())
          {
              pid=rs.getInt("p_id");
              did=rs.getInt("p_id");
          }
          String updateProcess="update process set discharged=" +1 +" where bill_id='" +bid +"';";
          Statement smt4=con.createStatement();
          smt4.executeUpdate(updateProcess);
          setflag(did,con);
          String deletePatient="delete from patients where p_id=" +pid;
          Statement smt5=con.createStatement();
          smt5.executeUpdate(deletePatient);
          request.setAttribute("error","Payment Successfull\nPatient is Discharged");
          request.setAttribute("source","ReceptionMenu.html");
          request.setAttribute("link","OK");
            RequestDispatcher rd=request.getRequestDispatcher("error.jsp");
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
