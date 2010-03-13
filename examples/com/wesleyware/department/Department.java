package com.wesleyware.department;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.wesleyware.company.Company;

/**
 * Department of  company
 */
public class Department extends DepartmentBase
{
    
    /**
     * No argument constructor
     */
    public Department()
    {
    }
    
    /**
     * PK Object constructor
     *
     * throws InvalidPkException
     */
    public Department(String departmentId,String companyId)  throws InvalidPkException
    {
        super(departmentId,companyId);
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
}
