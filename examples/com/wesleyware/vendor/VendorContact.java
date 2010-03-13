package com.wesleyware.vendor;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.wesleyware.vendor.Vendor;

/**
 * contacts for vendors
 */
public class VendorContact extends VendorContactBase
{
    
    /**
     * No argument constructor
     */
    public VendorContact()
    {
    }
    
    /**
     * PK Object constructor
     *
     * throws InvalidPkException
     */
    public VendorContact(Integer vendorContactId)  throws InvalidPkException
    {
        super(vendorContactId);
    }
    
    /**
     * PK Primitive constructor
     *
     * throws InvalidPkException
     */
    public VendorContact(int vendorContactId)  throws InvalidPkException
    {
        this( new Integer(vendorContactId));
    }
    
    /**
     * This method returns the possiblities for vendorId given the current state of the object
     *
     * @param   conn   active db connection
     * @return  possible values for vendorId
     * @throws SQLException
     */
    public List vendorIdChoices(Connection conn) throws SQLException
    {
        // put your code here
        return Vendor.loadAll(conn);
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
        if(field == VENDORID)
            return vendorIdReference(conn);
        return null;
    }
    
    /**
     * This method returns the record referenced by vendorId
     *
     * @param conn  an active DB connection
     * @return  the record referenced by vendorId
     * @throws SQLException
     */
    public Vendor vendorIdReference(Connection conn) throws SQLException
    {
        // put your code here
        Vendor vendor = new Vendor();
        vendor.setVendorId(getVendorId());
        vendor.load(conn);
        
        return vendor;
    }
}
