/*
 * Test.java
 *
 * Created on November 17, 2004, 9:24 PM
 */

package com.wesleyware.company;


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
                        Company company= new Company("COMP" + i);
                        
                        if(!company.setCompanyName("Test Company " + i))
                            System.out.println("TEST: COULDN'T SET COMPANY NAME");
                        if(!company.setAddress(i +" Test Cove"))
                            System.out.println("TEST: COULDN'T SET ADDRESS");
                        if(!company.setCity("Test City " + i))
                            System.out.println("TEST: COULDN'T SET COMPANY NAME");
                        if(!company.setState("TN"))
                            System.out.println("TEST: COULDN'T SET STATE");
                        if(!company.setZipcode("1234"  + i))
                            System.out.println("TEST: COULDN'T SET ZIPCODE");
                        
                        if(!company.create(conn))
                            System.out.println("TEST1: COULDN'T CREATE company");
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
