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
public class PatientServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection con=DemoConnection.getConnection();
            Statement smt=con.createStatement();
            String username=request.getParameter("username");
            String patientId=username.substring(1,username.length());
            int pid=Integer.parseInt(patientId);
            System.out.println(pid);
            String sql="select p.first_name,p.last_name,p.gender,dr.last_name as lname,dp.dept_name from patients p join process pr on p.p_id=pr.p_id join doctors dr on pr.d_id=dr.d_id join departments dp on dr.department=dp.dept_id where p.p_id=" +pid +";";
            
            ResultSet rs=smt.executeQuery(sql);
            ArrayList info=new ArrayList();
            while(rs.next())
            {
                info.add(pid);
                info.add(rs.getString("first_name"));
                info.add(rs.getString("last_name"));
                info.add(rs.getString("gender"));
                info.add(rs.getString("lname"));
                info.add(rs.getString("dept_name"));
                
            }
            System.out.println(info.toString());
            request.setAttribute("info",info);
            RequestDispatcher rd=request.getRequestDispatcher("/PatientInfoCard.jsp");
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
