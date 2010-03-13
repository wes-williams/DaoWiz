package com.wesleyware.vendor;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * represents relationship between vendors and companies
 */
public abstract class CompanyVendorBase extends Record implements Cloneable
{
   protected final static CompanyVendorDao DAO =  new CompanyVendorDao();

   protected final static String DB_TABLE_NAME = "company_vendors";

   /**
    *reference to company
    */
   public final static VarCharField COMPANYID = new VarCharField("company_id",10,new Constraints(true, true, true, false));
   /**
    *reference to vendor
    */
   public final static VarCharField VENDORID = new VarCharField("vendor_id",10,new Constraints(true, true, true, false));

   private final static Field[] ALL_DB_FIELDS = 
   {
      COMPANYID,VENDORID
   };

   private final static Field[] PK_DB_FIELDS = 
   {
      COMPANYID,VENDORID
   };

   /**
    * No argument constructor
    */
   public CompanyVendorBase()
   {
   }

   /**
    * PK constructor
    * 
    * throws InvalidPkException
    */
   public CompanyVendorBase(String companyId,String vendorId) throws InvalidPkException
   {
      if(!setCompanyId(companyId) ||
      !setVendorId(vendorId) )
         throw new InvalidPkException();
   }

   /**
    * This method returns the possiblities for companyId given the current state of the object
    *
    * @param   conn   active db connection
    * @return  possible values for companyId
    * @throws SQLException
    */
   public abstract List companyIdChoices(Connection conn) throws SQLException;

   /**
    * This method returns the possiblities for vendorId given the current state of the object
    *
    * @param   conn   active db connection
    * @return  possible values for vendorId
    * @throws SQLException
    */
   public abstract List vendorIdChoices(Connection conn) throws SQLException;

   /**
    * This method returns a list of values possible for a fk field given the state of the object
    * It should use the DAO of the referenced table to generate this list.
    * The fk constraint in each field indicates if anything should be returned by this method
    *
    * @param   conn   active db connection
    * @param field     the field whose possible values will be returned
    * @return list of possible data objects whose values are referenced by this field
    * @throws SQLException
    */
   protected List choices(Connection conn, Field field) throws SQLException
   {
      if(field==COMPANYID) return companyIdChoices(conn);
      if(field==VENDORID) return vendorIdChoices(conn);
      return null;
   }

   /**
    * setter for reference to company
    *
    * @param   value ofcompanyId
    * @return  true if valid
    */
   public boolean setCompanyId(String companyId)
   {
      return set(COMPANYID,companyId);
   }

   /**
    * getter for reference to company
    *
    * @return   value ofcompanyId
    */
   public String getCompanyId()
   {
      return get(COMPANYID);
   }

   /**
    * setter for reference to vendor
    *
    * @param   value ofvendorId
    * @return  true if valid
    */
   public boolean setVendorId(String vendorId)
   {
      return set(VENDORID,vendorId);
   }

   /**
    * getter for reference to vendor
    *
    * @return   value ofvendorId
    */
   public String getVendorId()
   {
      return get(VENDORID);
   }

   /**
    *  Validates the record    *  @return table name
    */
   protected String recordName()
   {
      return DB_TABLE_NAME;
   }

   /**
    * Clones the record
    */
   public Object clone()
   {
      CompanyVendor clone = new CompanyVendor();
      copyTo(clone);
      return clone;
   }

   /**
    * Returns all fields for internal use 
    * @return all fields
    */
   protected Field[] allFields()
   {
	   return ALL_DB_FIELDS;
   }

   /**
    *  Returns all primary keys fields for internal use
    *  @return primary key fields
    */
   protected Field[] primaryKeyFields()
   {
      return PK_DB_FIELDS;
   }

   /**
    *  Returns all primary keys fields for internal use
    *  @return primary key fields
    */
   protected RecordDao dao()
   {
      return DAO;
   }

   /**
    *  Shortcut to DAO method
    *  @return all records 
    */
   public static List loadAll(Connection conn) throws SQLException
   {
      return DAO.loadAll(conn);
   }
}
