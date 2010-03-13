/*
 * Test.java
 *
 * Created on November 17, 2004, 9:24 PM
 */

package com.wesleyware.employee;


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
                    Employee employee = new Employee();
                    
                    if(!employee.setEmployeeType("F"))
                        System.out.println("TEST1: COULDN'T SET TYPE");
                    if(!employee.setCompanyId("COMP" + i))
                        System.out.println("TEST1: COULDN'T SET COMPANY");
                    if(!employee.setDepartmentId("DEPT" + i))
                        System.out.println("TEST1: COULDN'T SET DEPT");
                    if(!employee.setFirstName("fname" + i))
                        System.out.println("TEST1: COULDN'T SET FNAME");
                    if(!employee.setLastName("lname" + i))
                        System.out.println("TEST1: COULDN'T SET LNAME");
                    if(!employee.setBirthDate(new java.util.Date()))
                        System.out.println("TEST1: COULDN'T SET DOB");
                    if(!employee.setStartDate(new java.util.Date()))
                        System.out.println("TEST1: COULDN'T SET START DATE");
                    if(!employee.setStatus("A"))
                        System.out.println("TEST1: COULDN'T SET START STATUS");
                    
                    if(!employee.create(conn))
                        System.out.println("TEST1: COULDN'T CREATE EMPLOYEE");
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
