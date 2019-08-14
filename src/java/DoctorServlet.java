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
import javax.servlet.ServletContext;


/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class DoctorServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con=DemoConnection.getConnection();
        try (PrintWriter out = response.getWriter()) {
            System.out.println("From doctor servlet");
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("username");
            System.out.println(username);
            String doctorId=username.substring(1);
            String sql3="select last_name,flag from doctors where d_id=" +Integer.parseInt(doctorId);
            String sql="select p.p_id,p.first_name,p.last_name,p.gender,p.disease,p.b_group,p.height,p.weight,p.dob from patients p join process pr on p.p_id=pr.p_id where pr.d_id=" +doctorId +" and pr.diagnosed='0';";
            Statement smt=con.createStatement();
            Statement smt3=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            ResultSet rs3=smt3.executeQuery(sql3);
            String doctorName=null;
            String flag=null;
            while(rs3.next())
            {
                doctorName=rs3.getString("last_name");
                flag=rs3.getString("flag");
                
            }
            int flagValue=Integer.parseInt(flag);
            if(flagValue==0)
            {
                request.setAttribute("error","Sorry , there are no more Patients assigned to you\nwait for the Receptionist to assign a Patient");
                request.setAttribute("source","Index.html");
                request.setAttribute("link","Logout");
                RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            }
            
            ArrayList patients=new ArrayList();
          

            while(rs.next())
            {
                ArrayList patient=new ArrayList();
                patient.add(rs.getString("p_id"));
                patient.add(rs.getString("first_name"));
                patient.add(rs.getString("last_name"));
                patient.add(rs.getString("gender"));
                patient.add(rs.getString("disease"));
                patient.add(rs.getString("b_group"));
                patient.add(rs.getInt("height"));
                patient.add(rs.getInt("weight"));
                int age=2018-Integer.parseInt(rs.getString("dob").substring(0,4));
                patient.add(age);
                patient.add(doctorName);
                patients.add(patient);
             }
            
            Statement smt2=con.createStatement();
            String sql2="select t.t_name from treatments t join departments d on t.dept_id=d.dept_id join doctors dr on d.dept_id=dr.department where dr.d_id=" +doctorId;
            ResultSet rs2=smt2.executeQuery(sql2);
            ArrayList treatments=new ArrayList();
            while(rs2.next())
            {
                treatments.add(rs2.getString("t_name"));
            }

            
            
              session.setAttribute("plist",patients);
              session.setAttribute("tlist", treatments);
              
              
            RequestDispatcher rd=request.getRequestDispatcher("/doctor.jsp");
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
