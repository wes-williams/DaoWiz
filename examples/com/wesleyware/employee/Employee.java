package com.wesleyware.employee;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.wesleyware.department.Department;
import com.wesleyware.company.Company;

/**
 * Employees of company
 */
public class Employee extends EmployeeBase
{
    
    /**
     * No argument constructor
     */
    public Employee()
    {
    }
    
    /**
     * PK Object constructor
     *
     * throws InvalidPkException
     */
    public Employee(Integer employeeId)  throws InvalidPkException
    {
        super(employeeId);
    }
    
    /**
     * PK Primitive constructor
     *
     * throws InvalidPkException
     */
    public Employee(int employeeId)  throws InvalidPkException
    {
        this( new Integer(employeeId));
    }
    
    /**
     * This method returns the possiblities for companyId given the current state of the object
     *
     * @param   conn   active db connection
     * @return  possible values for companyId
     * @throws SQLException
     */
    public List companyIdChoices(Connection conn) throws SQLException
    {
        // put your code here
        return Company.loadAll(conn);
    }
    
    /**
     * This method returns the possiblities for departmentId given the current state of the object
     *
     * @param   conn   active db connection
     * @return  possible values for departmentId
     * @throws SQLException
     */
    public List departmentIdChoices(Connection conn) throws SQLException
    {
        // put your code here
        return Department.loadAll(conn);
    }
    
    /**
     * This method returns the record referenced by the given field
     * The Data Object of the referenced class should be used to load itself
     *
     * @param conn  an active DB connection
     * @param field  the field whose value references this record
     * @return  the record referenced by this field
     * @throws SQLException
     */
    protected Record reference(Connection conn, Field field) throws SQLException
    {
        if(field == COMPANYID)
            return companyIdReference(conn);
        if(field == DEPARTMENTID)
            return departmentIdReference(conn);
        return null;
    }
    
    /**
     * This method returns the record referenced by companyId
     *
     * @param conn  an active DB connection
     * @return  the record referenced by companyId
     * @throws SQLException
     */
    public Company companyIdReference(Connection conn) throws SQLException
    {
        // put your code here
        Company company = new Company();
        
        if(!company.setCompanyId(getCompanyId()))
            return null;
        if(!company.load(conn))
            return null;
        
        return company;
    }
    
    /**
     * This method returns the record referenced by departmentId
     *
     * @param conn  an active DB connection
     * @return  the record referenced by departmentId
     * @throws SQLException
     */
    public Department departmentIdReference(Connection conn) throws SQLException
    {
        // put your code here
        Department department = new Department();
        
        if(!department.setCompanyId(getCompanyId()))
            return null;
        
        if(!department.setDepartmentId(getDepartmentId()))
            return null;
        
        if(!department.load(conn))
            return null;
       
        return department;
    }
}
