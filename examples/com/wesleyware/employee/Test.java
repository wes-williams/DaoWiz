package com.wesleyware.employee;

import java.sql.*;
import java.util.List;
import com.wesleyware.company.*;
import com.wesleyware.department.*;

public class Test
{
    public static void main(String[] args)
    {
        // all the operations work the same as they did in company
        // there are some other operations available that are realted to FK
        try
        {
            // Get a connection.
            // This will be hidden in a static method somewhere.
            Class.forName("org.postgresql.Driver");
            Connection conn =   DriverManager.getConnection("jdbc:postgresql://wes-redhat.mshome.net:5432/example", "postgres", "");
            try
            {
                // create a new employee
                Employee employee = new Employee();
                
                if(!employee.setEmployeeType("F"))
                    System.out.println("TEST1: COULDN'T SET TYPE");
                
                //find all the choices for company
                List companyChoices = employee.companyIdChoices(conn);
                
                // choose a company
                if(companyChoices.size()>0)
                {
                    Company company = (Company) companyChoices.get(0);
                    if(!employee.setCompanyId(company.getCompanyId()))
                        System.out.println("TEST1: COULDN'T SET COMPANY");
                }
                else
                    System.out.println("TEST1: COULDN'T SET COMPANY");
                
                //find all the choices for department
                List deptChoices = employee.departmentIdChoices(conn);
                
                // choose a department
                if(deptChoices.size()>0)
                {
                    Department dept = (Department) deptChoices.get(0);
                    if(!employee.setDepartmentId(dept.getDepartmentId()))
                        System.out.println("TEST1: COULDN'T SET DERT");
                }
                else
                    System.out.println("TEST1: COULDN'T SET DEPT");
                
                // set all the other not null data
                if(!employee.setFirstName("fname"))
                    System.out.println("TEST1: COULDN'T SET FNAME");
                if(!employee.setLastName("lname" ))
                    System.out.println("TEST1: COULDN'T SET LNAME");
                if(!employee.setBirthDate(new java.util.Date()))
                    System.out.println("TEST1: COULDN'T SET DOB");
                if(!employee.setStartDate(new java.util.Date()))
                    System.out.println("TEST1: COULDN'T SET START DATE");
                if(!employee.setStatus("A"))
                    System.out.println("TEST1: COULDN'T SET START STATUS");
                
                // create the employee
                if(!employee.create(conn))
                    System.out.println("TEST1: COULDN'T CREATE EMPLOYEE");
                
                // find all employee matches
                List employeeMatches = employee.search(conn,true);
                
                if(employeeMatches.size() == 0)
                    System.out.println("TEST1: COULDN'T FIND EMPLOYEE");
                else
                {
                    // This table has an autonumber as PK
                    // last one is the employee we just added.
                    employee = (Employee) employeeMatches.get(employeeMatches.size()-1);
                    
                    System.out.println("--------------------------------");
                    employee.debug(System.out);
                    System.out.println("--------------------------------");
                }
                
                // get the department referenced by the employee
                Department dept = employee.departmentIdReference(conn);
                
                if(dept!=null)
                {
                    System.out.println("--------------------------------");
                    dept.debug(System.out);
                    System.out.println("--------------------------------");
                    
                    // get the company referenced by the department
                    // it will match the one referenced by the employee
                    Company company = dept.companyIdReference(conn);
                    
                    if(company!=null)
                    {
                        System.out.println("--------------------------------");
                        company.debug(System.out);
                        System.out.println("--------------------------------");
                    }
                }
                
                // get the company referenced by the employee
                // it will match the cone referenced by the department
                Company company = employee.companyIdReference(conn);
                
                if(company!=null)
                {
                    System.out.println("--------------------------------");
                    company.debug(System.out);
                    System.out.println("--------------------------------");
                }
                
                // delete the employee
                if(employee.delete(conn) == 0)
                    System.out.println("TEST1: COULDN'T DELETE EMPLOYEE");
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
