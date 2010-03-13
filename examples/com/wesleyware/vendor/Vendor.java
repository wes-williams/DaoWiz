package com.wesleyware.vendor;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.wesleyware.employee.Employee;

/**
 * Vendors for entire company
 */
public class Vendor extends VendorBase
{
    
    /**
     * No argument constructor
     */
    public Vendor()
    {
    }
    
    /**
     * PK Object constructor
     *
     * throws InvalidPkException
     */
    public Vendor(String vendorId)  throws InvalidPkException
    {
        super(vendorId);
    }
    
    /**
     * This method returns the possiblities for assignedEmployeeId given the current state of the object
     *
     * @param   conn   active db connection
     * @return  possible values for assignedEmployeeId
     * @throws SQLException
     */
    public List assignedEmployeeIdChoices(Connection conn) throws SQLException
    {
        // put your code here
        return Employee.loadAll(conn);
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
        if(field == ASSIGNEDEMPLOYEEID)
            return assignedEmployeeIdReference(conn);
        return null;
    }
    
    /**
     * This method returns the record referenced by assignedEmployeeId
     *
     * @param conn  an active DB connection
     * @return  the record referenced by assignedEmployeeId
     * @throws SQLException
     */
    public Employee assignedEmployeeIdReference(Connection conn) throws SQLException
    {
        // put your code here
        Employee employee = new Employee();
        
        if(!employee.setEmployeeId(getAssignedEmployeeIdObject()))
            return null;
        if(!employee.load(conn))
            return null;
        
        return employee;
    }
}
