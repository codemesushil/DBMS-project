/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class FireServlet extends HttpServlet {

    public ArrayList[] getDoctors(Connection con,ArrayList<Integer> patients,int doctorId) throws SQLException
    {
        Statement smt1=con.createStatement();
        String sql1="select dp.dept_id,dp.dept_name from departments dp join doctors dr on dp.dept_id=dr.department where dr.d_id=" +doctorId;
        ResultSet rs1 = smt1.executeQuery(sql1);
        String dept_id=null;
        while(rs1.next())
        {
            dept_id=rs1.getString("dept_id");
        }
        String sql2="select d_id from doctors where department=" +"'" +dept_id +"';";
        Statement smt2=con.createStatement();
        ResultSet rs2=smt2.executeQuery(sql2);
        ArrayList<Integer> doctorIds=new ArrayList<>();
        while(rs2.next())
        {
            doctorIds.add(rs2.getInt("d_id"));
        }
        
        String sql3="select flag from doctors where department='" +dept_id +"'";
        Statement smt3=con.createStatement();
        ResultSet rs3=smt3.executeQuery(sql3);
        ArrayList<Integer> flags=new ArrayList<>();
        
        while(rs3.next())
        {
            flags.add(Integer.parseInt(rs3.getString("flag")));
        }
        
        System.out.println(patients);
        
        ArrayList pairs[]=new ArrayList[3];
        int count=0;
        int bigCheckFlag=0;
        while(!patients.isEmpty()){
            int checkFlag=0;
        for(int i=0;i<doctorIds.size();i++)
        {
            if(doctorIds.get(i)!=doctorId)
            {
                if(flags.get(i)<=2)
                {
                    int pid=patients.get(count);
                    int did=doctorIds.get(i);
                    ArrayList pair=new ArrayList();
                    pair.add(pid);
                    pair.add(did);
                    System.out.println(pair);
                    pairs[count]=pair;
                    count++;
                    checkFlag=1;
                    flags.set(i,flags.get(i)+1);
                    break;
                }
            }
        }
        if(checkFlag==1)
        {
            patients.remove(0);
        }
        else
        {
            bigCheckFlag=1;
            break;
        }
        }
        if(bigCheckFlag==0)
        {
            return pairs;
        }
        System.out.println(pairs);
        return null;
    }
    
    public void updateFlag(Connection con,ArrayList pairs[],int count) throws SQLException
    {
        int i=0;
        System.out.println(count);
        while(i<count)
        {
            System.out.println(pairs[i]);
            Statement smt1=con.createStatement();
            String sql1="select flag from doctors where d_id=" +pairs[i].get(1);
            ResultSet rs=smt1.executeQuery(sql1);
            int updatedFlag=-1;
            while(rs.next())
            {
                updatedFlag=Integer.parseInt(rs.getString("flag"));
            }
            updatedFlag++;
            System.out.println(updatedFlag);
            String flag=Integer.toString(updatedFlag);
            String sql2="update doctors set flag='" +flag +"' where d_id="+pairs[i].get(1);
            Statement smt2=con.createStatement();
            smt2.executeUpdate(sql2);
            i++;
        }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection con=DemoConnection.getConnection();
            Statement smt=con.createStatement();         
            int doctorId=Integer.parseInt(request.getParameter("d_id"));
            String sql="select p_id from process where diagnosed='0' and d_id=" +doctorId;
            ResultSet rs =smt.executeQuery(sql);
            ArrayList<Integer> patient=new ArrayList<>();
            int count=0;
            while(rs.next())
            {
                patient.add(rs.getInt("p_id"));
                count++;
            }
            System.out.println(patient);
            ArrayList pairs[]=new ArrayList[3];
            pairs=getDoctors(con, patient, doctorId);
            if(pairs!=null)
            {
                System.out.println("hello1");
                System.out.println(count);
                int i=0;
                while(i<count)
                {
                    
                    int pid=Integer.parseInt(pairs[i].get(0).toString());
                    int did=Integer.parseInt(pairs[i].get(1).toString());
                    String sql1="update process set d_id=" +did +" where p_id=" +pid;
                    Statement smtUpdate=con.createStatement();
                    smtUpdate.executeUpdate(sql1);
                    i++;
                }
                System.out.println(count);
                updateFlag(con, pairs, count);
                System.out.println("hello2");
                String sql3="delete from doctors where d_id=" +doctorId;
                Statement smt3=con.createStatement();
                smt3.executeUpdate(sql3);
                
                RequestDispatcher rd=request.getRequestDispatcher("/AdminMenu.jsp");
                rd.forward(request, response);
            }
            else
            {
                Statement smt1=con.createStatement();
                String sql1="select dp.dept_id,dp.dept_name from departments dp join doctors dr on dp.dept_id=dr.department where dr.d_id=" +doctorId;
                ResultSet rs1 = smt1.executeQuery(sql1);
                String dept_name=null;
                while(rs1.next())
                {
                  dept_name=rs1.getString("dept_name");
                }
                request.setAttribute("dept_name",dept_name);
                RequestDispatcher rd=request.getRequestDispatcher("/nofiredoctor.jsp");
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
