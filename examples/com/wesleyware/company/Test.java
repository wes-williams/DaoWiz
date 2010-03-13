package com.wesleyware.company;

import com.wesleyware.daowiz.*;
import java.sql.*;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        // this doesn't show every possible usage
        // It should give the general idea though
        // look closer at the javadocs for other operations
        try
        {
            // Get a connection.
            // This will be hidden in a static method somewhere.
            Class.forName("org.postgresql.Driver");
            Connection conn =   DriverManager.getConnection("jdbc:postgresql://wes-redhat.mshome.net:5432/example", "postgres", "");
            try
            {
                // create and empty company
                Company company= new Company();
                
                // set all the data with not null constraints
                // give back the user some feedback if the data is bad.
                if(!company.setCompanyId("COMPANY1"))
                    System.out.println("Test1: COULDN'T SET COMPANY ID");
                if(!company.setCompanyName("Test Company 1"))
                    System.out.println("TEST1: COULDN'T SET COMPANY NAME");
                if(!company.setAddress("123 Test Cove"))
                    System.out.println("TEST1: COULDN'T SET ADDRESS");
                if(!company.setCity("Test City"))
                    System.out.println("TEST1: COULDN'T SET COMPANY NAME");
                if(!company.setState("TN"))
                    System.out.println("TEST1: COULDN'T SET STATE");
                if(!company.setZipcode("12344"))
                    System.out.println("TEST1: COULDN'T SET ZIPCODE");
                
                // create the company
                // give the user some feedback if unsuccessful
                if(!company.create(conn))
                    System.out.println("TEST1: COULDN'T CREATE COMPANY");
                
                // load the data to get the default data
                // give the user some feedback if unsuccessful or show the record if successful
                if(!company.load(conn))
                    System.out.println("TEST1: COULDN'T LOAD COMPANY");
                else
                {
                    System.out.println("--------------------------------");
                    company.debug(System.out);
                    System.out.println("--------------------------------");
                }
                
                // save record
                // this checks to see if it exist then calls create or update
                if(company.save(conn) != 1)
                    System.out.println("TEST1: SAVE DIDN'T WORK AS EXPECTED");
                
                // clear the record or create a new object
                company.clear();
                
                // reset the data to update
                if(!company.setCompanyId("COMPANY1"))
                    System.out.println("Test2: COULDN'T SET COMPANY ID");
                if(!company.setAddress("123 Test Street"))
                    System.out.println("TEST2: COULDN'T SET ADDRESS");
                
                // update the company
                // give the user some feedback if unsuccessful
                int updateCount = company.update(conn);
                
                if(updateCount == 0)
                    System.out.println("TEST2: COULDN'T UPDATE COMPANY");
                else if(updateCount > 1)
                    System.out.println("TEST2:MORE THAN ONE RECORD AFFECTED BY UPDATE");
                
                // create a new company
                Company company2 = new Company();
                
                if(!company2.setCompanyId("COMPANY2"))
                    System.out.println("Test3: COULDN'T SET COMPANY2 ID");
                
                // change the primary key
                updateCount = company.updatePK(conn,company2);
                
                if(updateCount == 0)
                    System.out.println("TEST3: COULDN'T UPDATE PK FOR COMPANY");
                else if(updateCount > 1)
                    System.out.println("TEST3:MORE THAN ONE RECORD AFFECTED BY PK UPDATE");
                
                // clear the record or create a new object
                company.clear();
                
                // set the data to search for
                if(!company.setAddress("Test"))
                    System.out.println("Test4: COULDN'T SET ADDRESS");
                
                // search for the anything matching record
                List companyResults = company.search(conn, false);
                System.out.println("Test4: MATCHING RESULT COUNT = " + companyResults.size());
                
                for(int i=0;i<companyResults.size();i++)
                {
                    System.out.println("--------------------------------");
                    ((Company)companyResults.get(i)).debug(System.out);
                    System.out.println("--------------------------------");
                }
                
                // search for the exact matches of record
                companyResults = company.search(conn, true);
                System.out.println("Test4: EXACT RESULT COUNT = " + companyResults.size());
                
                for(int i=0;i<companyResults.size();i++)
                {
                    System.out.println("--------------------------------");
                    ((Company)companyResults.get(i)).debug(System.out);
                    System.out.println("--------------------------------");
                }
                
                // create record with PK and check for validity
                try
                {
                    company2 = new Company("COMPANY2");
                    
                    // dellete the record and give feedback is unexpected results
                    company2.delete(conn);
                    if(updateCount == 0)
                        System.out.println("TEST5: COULDN'T DELETE COMPANY2");
                    else if(updateCount > 1)
                        System.out.println("TEST5:MORE THAN ONE RECORD DELETE");
                }
                catch(InvalidPkException ex)
                {
                    System.out.println(ex);
                }
            }
            finally
            {
                try
                {
                    conn.close(); // close the connection
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
