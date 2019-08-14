/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author KSHITIJ DESHPANDE
 */

import java.sql.Connection;
import java.sql.*;
import org.eclipse.jdt.internal.compiler.batch.Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KSHITIJ DESHPANDE
 */


public class DemoConnection {
    Connection con;
    public static Connection getConnection()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management","root","kad168");
        System.out.println("Conneccted");
        return con;
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
}
