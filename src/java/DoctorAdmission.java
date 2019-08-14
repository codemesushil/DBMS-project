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
/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class DoctorAdmission extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String firstName=request.getParameter("firstname");
            String lastName=request.getParameter("lastname");
            String mobile=request.getParameter("mobile");
            String email=request.getParameter("email");
            String gender=request.getParameter("gender");
            String dob=request.getParameter("dateofbirth");
            String department=request.getParameter("speciality");
            Connection con=DemoConnection.getConnection();
            Statement smt=con.createStatement();
            String deptSelect="select dept_id from departments where dept_name=" +"'" +department +"';";
            ResultSet rs=smt.executeQuery(deptSelect);
            while(rs.next())
            {
                department=rs.getString("dept_id");
            }
            System.out.println(department);
            String sql="insert into doctors(first_name,last_name,mobile_number,email,gender,dob,department,flag) values(" +"'" +firstName +"'" +"," +"'" +lastName +"'" +"," +"'" +mobile +"'" +"," +"'" +email +"'" +"," +"'" +gender +"'" +"," +"'" +dob +"'" +"," +"'" +department +"'" +"," +"'" +'0' +"'" +");";
            PreparedStatement stmt=con.prepareStatement(sql);
	    stmt.execute();
            
            RequestDispatcher rd=request.getRequestDispatcher("/AdminMenu.jsp");
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
