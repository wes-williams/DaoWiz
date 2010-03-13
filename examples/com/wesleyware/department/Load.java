/*
 * Test.java
 *
 * Created on November 17, 2004, 9:24 PM
 */

package com.wesleyware.department;


import com.wesleyware.daowiz.*;
import java.sql.*;

/**
 *
 * @author  wes
 */
public class Load
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection conn =   DriverManager.getConnection("jdbc:postgresql://wes-redhat.mshome.net:5432/example", "postgres", "");
            try
            {
                
                 for(int i=1;i<=50;i++)
                {
                  try
                    {
                        Department dept = new Department("DEPT" + i,"COMP" + i);
                        
                        if(!dept.setName("Department " + i))
                            System.out.println("TEST1: COULDN'T SET NAME");
                        
                         if(!dept.setDescription("This is a description " + i))
                            System.out.println("TEST1: COULDN'T SET DESCRIPTION");
                         
                        if(!dept.create(conn))
                            System.out.println("TEST1: COULDN'T CREATE DEPT");
                    }
                    catch(InvalidPkException ex)
                    {
                        System.out.println("TEST1: INVALID PK");
                    }
                 }
                
            }
            finally
            {
                try
                {
                    conn.close();
                }
                catch (Exception ex)
                {}
            }
            
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}
