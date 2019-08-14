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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class DepartmentServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String department=request.getParameter("button");
            Connection con=DemoConnection.getConnection();
            Statement smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql="select dp.dept_id,dp.dept_name,dp.description,dr.first_name,dr.last_name,dr.email,dr.dob from departments dp join doctors dr on dp.dept_id = dr.department where dp.dept_name=" +"'" +department +"';";
            ResultSet rs=smt.executeQuery(sql);
            ArrayList doctors[]=new ArrayList[15];
            int i=0;
            
            while(rs.next())
            {
                ArrayList doctor =new ArrayList();
                doctor.add(rs.getString("first_name"));
                doctor.add(rs.getString("last_name"));
                int age=2018-Integer.parseInt(rs.getString("dob").substring(0,4));
                doctor.add(age);
                doctor.add(rs.getString("email"));
                doctors[i]=doctor;
                i++;
            }
            rs.beforeFirst();
            rs.next();
            request.setAttribute("dept_name",rs.getString("dept_name"));
            request.setAttribute("dept_info",rs.getString("description"));
            request.setAttribute("count", i);
            request.setAttribute("dlist",doctors);
            RequestDispatcher rd= request.getRequestDispatcher("/DepartmentInfo.jsp");
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
