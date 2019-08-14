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
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class indexServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con=DemoConnection.getConnection();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           int flag=0;
           Statement stmt=con.createStatement();
           String sql="select d_id from doctors";
           ResultSet rs = stmt.executeQuery(sql);
          String username=request.getParameter("username");
          String password=request.getParameter("password");
          HttpSession session=request.getSession();
          if(username.equals("admin") && password.equals("password"))
          {
              flag=1;
               session.setAttribute("username",username);
              RequestDispatcher  rd1=request.getRequestDispatcher("AdminMenu.jsp");
              rd1.forward(request,response);
          }
          if(username.equals("reception") && password.equals("pass"))
          {
              flag=1;
              session.setAttribute("username", username);
              RequestDispatcher rd= request.getRequestDispatcher("ReceptionMenu.html");
              rd.forward(request, response);
          }
          while(rs.next())
          {
              if(username.equals("d" + Integer.toString(rs.getInt("d_id"))) && password.equals("p" + Integer.toString(rs.getInt("d_id"))))
              {
                  flag=1;
                  session.setAttribute("username",username);
                  RequestDispatcher rd2=request.getRequestDispatcher("DoctorServlet");
                  rd2.forward(request, response);
              }
          }
          if(flag==0)
          {
          RequestDispatcher rd3=request.getRequestDispatcher("Index.html");
          rd3.forward(request, response);
          }
          
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

  
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
