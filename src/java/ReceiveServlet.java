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
public class ReceiveServlet extends HttpServlet {
    
    public String getDepartment(Connection con,String disease) throws SQLException
    {
        Statement smt=con.createStatement();
        String sql=null;
        String departmentId=null;
        switch(disease){
            case "Heart Attack":
                sql="select dept_id from departments where dept_name='Cardiology'";
                break;
            case "Cardiac Arrest":
                sql="select dept_id from departments where dept_name='Cardiology'";
                break;
            case "Trauma":
                sql="select dept_id from departments where dept_name='Out Patient Department'";
                break;
            case "Mental Incident":
                sql="select dept_id from departments where dept_name='Psychology'";
                break;
            case "Asthma":
                sql="select dept_id from departments where dept_name='ENT'";
                break;
            case "Respiratory Issue":
                sql="select dept_id from departments where dept_name='ENT'";
                break;
            case "Allergic Reaction":
                sql="select dept_id from departments where dept_name='out patient department'";
                break;
            case "Genral Purpose Checkup":
                sql="select dept_id from departments where dept_name='ENT'";
                break;
            case "Dental Checkup":
                sql="select dept_id from departments where dept_name='Dental'";
                break;
            case "Optical Checkup":
                sql="select dept_id from departments where dept_name='Optician'";
                break;
            case "Pediatric Checkup":
                sql="select dept_id from departments where dept_name='out patient department'";
                break;
            case "Chemotherapy":
                sql="select dept_id from departments where dept_name='Oncology'";
                break;
            case "Recovery Therapy":
                sql="select dept_id from departments where dept_name='Psychology'";
                break;
            case "Brain Surgery":
                sql="select dept_id from departments where dept_name='Neurology'";
                break;
            case "Skin Checkup":
                sql="select dept_id from departments where dept_name='Dermatology'";
                break;
            case "Cosmetic Corrective Surgery":
                sql="select dept_id from departments where dept_name='Dermatology'";
                break;
            case "Urologist":
                sql="select dept_id from departments where dept_name='Urology'";
                break;
            case "Proctologist":
                sql="select dept_id from departments where dept_name='Proctology'";
                break;
            case "Physiotherapy":
                sql="select dept_id from departments where dept_name='Out Patient Department'";
                break;
            case "Blood Report":
                sql="select dept_id from departments where dept_name='Pathologist'";
                break;
 
        }
        
        ResultSet rs=smt.executeQuery(sql);
        while(rs.next())
        {
            departmentId=rs.getString("dept_id");
        }
        return departmentId;
    }
    
    public int getDoctor(Connection con,String dept_id) throws SQLException
    {
        Statement smt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql1="select d_id,flag from doctors where department=" +"'" +dept_id +"';";
        int i=0;
        ResultSet rs = smt1.executeQuery(sql1);
        while(i<=2)
        {
        while(rs.next())
        {
            if(Integer.parseInt(rs.getString("flag"))==i)
            {
                String updateFlag=Integer.toString(i+1);
                System.out.println(updateFlag);
                String sql2="Update doctors set flag=" +"'" +updateFlag +"'" +"where d_id=" +rs.getInt("d_id") +";";
                System.out.println(sql2);
                Statement smt2=con.createStatement();
                smt2.execute(sql2);
                return rs.getInt("d_id");  
            }
        }
        i++;
        rs.beforeFirst();
        }
        return -1;
    }
    
    public int getpid(Connection con) throws SQLException
    {
        Statement smt=con.createStatement();
        String sql="select p_id from patients";
        int pid=-1;
        ResultSet rs=smt.executeQuery(sql);
        while(rs.next())
        {
            pid=rs.getInt("p_id");
        }
        return pid;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con=DemoConnection.getConnection();
        
        try (PrintWriter out = response.getWriter()) {
          String firstName=request.getParameter("firstname");
          out.println(firstName);
			String lastName=request.getParameter("lastname");
			String mobileNumber=request.getParameter("mobile");
			String emergencyContact=request.getParameter("emobile");
			String gender=request.getParameter("gender");
			String need=request.getParameter("p_need");
			String address=request.getParameter("address");
                        String disease;
			if(need.equals("emergency"))
			{
				disease=request.getParameter("emergency");	
			}
			else
			{
				disease=request.getParameter("Appointment");
			}
                        String bloodGroup=request.getParameter("b_group");
                        int height=Integer.parseInt(request.getParameter("height"));
                        int weight=Integer.parseInt(request.getParameter("weight"));
                        String dateOfBirth=request.getParameter("dateofbirth");
                                        
                        String insuranceNumber=request.getParameter("insuranceno");
                        String sql="insert into patients (first_name,last_name,mobile_number,emergency_contact,gender,address,disease,b_group,height,weight,dob,insNumber) values(" +"'" +firstName  +"'" +"," +"'" +lastName +"'" +"," +"'" +mobileNumber +"'" +"," +"'" +emergencyContact +"'" +"," +"'" +gender +"'" +"," +"'" +address +"'" +"," +"'" +disease +"'" +"," +"'" +bloodGroup +"'" +"," +"'" +height +"'" +"," +"'" +weight +"'" +"," +"'" +dateOfBirth +"'" +"," +"'" +insuranceNumber +"'" +");";
                        java.sql.PreparedStatement smt=con.prepareStatement(sql);
			smt.execute();
                        
                        String dept_id=getDepartment(con, disease);
                        int doctor_id=getDoctor(con, dept_id);
                        int pid=getpid(con);

                        Statement smt1=con.createStatement();
                        
                        String sql1="insert into process(p_id,d_id,diagnosed,discharged) values(" +pid +","  +doctor_id +",'0'" +"," +0 +");";
                        smt1.execute(sql1);
                        
                        
                        request.setAttribute("error","Patient Amitted");
                        request.setAttribute("source","Index.html");
                        request.setAttribute("link","OK");
                        
                        RequestDispatcher rd =request.getRequestDispatcher("/error.jsp");
                        rd.forward(request, response);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
