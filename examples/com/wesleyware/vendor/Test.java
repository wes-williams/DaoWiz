/*
 * Test.java
 *
 * Created on November 17, 2004, 9:24 PM
 */

package com.wesleyware.vendor;


import com.wesleyware.daowiz.*;
import java.sql.*;

/**
 *
 * @author  wes
 */
public class Test
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
                        Vendor vendor = new Vendor("VNDR" + i);
                        
                        if(!vendor.setVendorName("Test Client " + i))
                            System.out.println("TEST: COULDN'T SET COMPANY NAME");
                        if(!vendor.setAddress(i +" Test Cove"))
                            System.out.println("TEST: COULDN'T SET ADDRESS");
                        if(!vendor.setCity("Test City " + i))
                            System.out.println("TEST: COULDN'T SET COMPANY NAME");
                        if(!vendor.setState("TN"))
                            System.out.println("TEST: COULDN'T SET STATE");
                        if(!vendor.setZipcode("1234"  + i))
                            System.out.println("TEST: COULDN'T SET ZIPCODE");
                        if(!vendor.setStatus("P"))
                            System.out.println("TEST: COULDN'T SET STATUS");
                        if(!vendor.setAssignedEmployeeId(i))
                            System.out.println("TEST: COULDN'T SET EMPLOYEE");
                        if(!vendor.create(conn))
                            System.out.println("TEST: COULDN'T CREATE vendor");
                    }
                    catch(InvalidPkException ex)
                    {
                        System.out.println("TEST: INVALID PK");
                    }
                }
                
                for(int i=1;i<=50;i++)
                {
                    VendorContact vendorContact = new VendorContact();
                    if(!vendorContact.setVendorContactId(i))
                        System.out.println("TEST1: COULDN'T CREATE vendor contact id");
                    if(!vendorContact.setVendorId("VNDR" + i))
                        System.out.println("TEST1: COULDN'T CREATE vendor id");
                    if(!vendorContact.setFirstName("fname" + i))
                        System.out.println("TEST1: COULDN'T SET FNAME");
                    if(!vendorContact.setLastName("lname" + i))
                        System.out.println("TEST1: COULDN'T SET LNAME");
                    if(!vendorContact.setBirthDate(new java.util.Date()))
                        System.out.println("TEST1: COULDN'T SET DOB");
                    if(!vendorContact.setStatus("A"))
                        System.out.println("TEST1: COULDN'T SET START STATUS");
                    
                    if(!vendorContact.create(conn))
                        System.out.println("TEST1: COULDN'T CREATE vendor contact");
                }
                 
                for(int i=1;i<=50;i++)
                {
                    CompanyVendor companyVendor = new CompanyVendor();
                    if(!companyVendor.setVendorId("VNDR" +i))
                         System.out.println("TEST2: COULDN'T SET VENDOR ID");
                    if(!companyVendor.setCompanyId("COMP"+i))
                         System.out.println("TEST2: COULDN'T SET COMPANY ID");
                    
                    if(!companyVendor.create(conn))
                        System.out.println("TEST2: COULDN'T CREATE company vendor");
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
