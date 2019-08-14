/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author KSHITIJ DESHPANDE
 */
public class Admin {
    public int countPatients;
    public int countDoctors;
    
    public Admin()
    {
        this.countPatients=0;
        this.countDoctors=0;
    }
    
    public ArrayList[] getPatients()
    {
        Connection con=DemoConnection.getConnection();
        try{
        Statement smt=con.createStatement();
        String sql="select p.p_id,p.first_name,p.last_name,p.gender,p.dob,p.mobile_number,p.disease,p.b_group,p.insNumber,p.address,pr.diagnosed from patients p join process pr on p.p_id=pr.p_id";
        ResultSet rs=smt.executeQuery(sql);
        ArrayList patients[]=new ArrayList[50];
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
            this.countPatients++;
        }
        return patients;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;  
    }
    
    public ArrayList[] getDoctors()
    {
         Connection con=DemoConnection.getConnection();
         try
         {
             Statement smt=con.createStatement();
             String sql="select d.d_id,d.first_name,d.last_name,d.mobile_number,d.email,d.gender,d.dob,dp.dept_name from doctors d join departments dp on d.department=dp.dept_id order by d.d_id";
             ResultSet rs=smt.executeQuery(sql);
             ArrayList doctors[]=new ArrayList[100];
             int i=0;
             while(rs.next())
             {
                 ArrayList doctor=new ArrayList();
                 doctor.add(rs.getString("d_id"));
                 doctor.add(rs.getString("first_name"));
                 doctor.add(rs.getString("last_name"));
                 doctor.add(rs.getString("gender"));
                 int age=2018-Integer.parseInt(rs.getString("dob").substring(0,4));
                 doctor.add(age);
                 doctor.add(rs.getString("mobile_number"));
                 doctor.add(rs.getString("email"));
                 doctor.add(rs.getString("dept_name"));
                 doctors[i]=doctor;
                 i++;
                 this.countDoctors++;
             }
             return doctors;
         }
         catch(SQLException e)
         {
             e.printStackTrace();
         }
         return null;
    }
    
    public static void main(String args[])
    {
        Admin admin=new Admin();
        ArrayList patients[]=new ArrayList[50];
        patients=admin.getPatients();
        
    }
    
}
