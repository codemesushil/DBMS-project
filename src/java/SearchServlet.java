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
public class SearchServlet extends HttpServlet {
    
    public void searchPatient(Connection con,int pid,HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException
    {
        Statement smt=con.createStatement();
        String sql="select p.p_id,p.first_name,p.last_name,p.gender,p.dob,p.mobile_number,p.disease,p.b_group,p.insNumber,p.address,pr.diagnosed from patients p join process pr on p.p_id=pr.p_id where p.p_id=" +pid;
        ResultSet rs=smt.executeQuery(sql);
            ArrayList patients[]=new ArrayList[2];
        int i=0;
        while(rs.next())
        {
            ArrayList patient=new ArrayList();
            patient.add(rs.getInt("p_id"));
            patient.add(rs.getString("first_name"));
            patient.add(rs.getString("last_name"));
            patient.add(rs.getString("gender"));
            int age=2018-Integer.parseInt(rs.getString("dob").substring(0,4));
            patient.add(age);
            patient.add(rs.getString("mobile_number"));
            patient.add(rs.getString("disease"));
            patient.add(rs.getString("b_group"));
            patient.add(rs.getString("insNumber"));  
            patient.add(rs.getString("address"));
            if(rs.getString("diagnosed").equals("1"))
            {
                patient.add("YES");
            }
            else
            {
                patient.add("NO");
            }
            patients[i]=patient;
            i++;
        }
        request.setAttribute("source","search");
        request.setAttribute("plist",patients);
        request.setAttribute("count",i);
        RequestDispatcher rd =request.getRequestDispatcher("/AdminPatientView.jsp");
        rd.forward(request, response);
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection con=DemoConnection.getConnection();
            String source=request.getAttribute("req").toString();
            if(source.equals("from_patint_view"))
            {
                int pid=Integer.parseInt(request.getParameter("pid").toString());
                searchPatient(con,pid,request,response);
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
