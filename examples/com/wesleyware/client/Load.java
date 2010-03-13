/*
 * Test.java
 *
 * Created on November 17, 2004, 9:04 PM
 */

package com.wesleyware.client;

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
                        Client client = new Client("CLT" + i);
           
                        if(!client.setClientName("Test Client " + i))
                            System.out.println("TEST: COULDN'T SET COMPANY NAME");
                        if(!client.setAddress(i +" Test Cove"))
                            System.out.println("TEST: COULDN'T SET ADDRESS");
                        if(!client.setCity("Test City " + i))
                            System.out.println("TEST: COULDN'T SET COMPANY NAME");
                        if(!client.setState("TN"))
                            System.out.println("TEST: COULDN'T SET STATE");
                        if(!client.setZipcode("1234"  + i))
                            System.out.println("TEST: COULDN'T SET ZIPCODE");
                        if(!client.setStatus("P"))
                            System.out.println("TEST: COULDN'T SET STATUS");
                        if(!client.setAssignedEmployeeId(i))
                         System.out.println("TEST: COULDN'T SET EMPLOYEE");
                        if(!client.create(conn))
                            System.out.println("TEST: COULDN'T CREATE company");
                    }
                    catch(InvalidPkException ex)
                    {
                        System.out.println("TEST: INVALID PK");
                    }
                }
                 
                for(int i=1;i<=50;i++)
                {
                    ClientContact clientContact = new ClientContact();
                    if(!clientContact.setClientContactId(i))
                        System.out.println("TEST1: COULDN'T CREATE vendor contact id");
                    if(!clientContact.setClientId("CLT" + i))
                        System.out.println("TEST1: COULDN'T CREATE vendor id");
                    if(!clientContact.setFirstName("fname" + i))
                        System.out.println("TEST1: COULDN'T SET FNAME");
                    if(!clientContact.setLastName("lname" + i))
                        System.out.println("TEST1: COULDN'T SET LNAME");
                    if(!clientContact.setBirthDate(new java.util.Date()))
                        System.out.println("TEST1: COULDN'T SET DOB");
                    if(!clientContact.setStatus("A"))
                        System.out.println("TEST1: COULDN'T SET START STATUS");
                    
                    if(!clientContact.create(conn))
                        System.out.println("TEST1: COULDN'T CREATE vendor contact");
                }
                
                for(int i=1;i<=50;i++)
                {
                    CompanyClient companyClient = new CompanyClient();
                    if(!companyClient.setClientId("CLT" +i))
                         System.out.println("TEST2: COULDN'T SET CLIENT ID");
                    if(!companyClient.setCompanyId("COMP"+i))
                         System.out.println("TEST2: COULDN'T SET COMPANY ID");
                    
                    if(!companyClient.create(conn))
                        System.out.println("TEST2: COULDN'T CREATE company client");
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
