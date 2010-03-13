package com.wesleyware.company;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * These are companies which make up a larger company
 */
public abstract class CompanyBase extends Record implements Cloneable
{
   protected final static CompanyDao DAO =  new CompanyDao();

   protected final static String DB_TABLE_NAME = "companies";

   /**
    *abbreviation for company
    */
   public final static VarCharField COMPANYID = new VarCharField("company_id",10,new Constraints(true, true, false, false));
   /**
    *name of company
    */
   public final static VarCharField COMPANYNAME = new VarCharField("company_name",50,new Constraints(true, false, false, false));
   /**
    *street address
    */
   public final static VarCharField ADDRESS = new VarCharField("address",60,new Constraints(true, false, false, false));
   /**
    *additional address information
    */
   public final static VarCharField ADDRESS2 = new VarCharField("address_2",60,new Constraints(true, false, false, false));
   /**
    *city
    */
   public final static VarCharField CITY = new VarCharField("city",50,new Constraints(true, false, false, false));
   /**
    *state
    */
   public final static CharField STATE = new CharField("state",2,new Constraints(true, false, false, false));
   /**
    *zipcode
    */
   public final static VarCharField ZIPCODE = new VarCharField("zipcode",10,new Constraints(true, false, false, false));
   /**
    *phone number
    */
   public final static VarCharField PHONE = new VarCharField("phone",12,new Constraints(false, false, false, false));
   /**
    *record creation date
    */
   public final static TimeStampField CREATIONDATE = new TimeStampField("creation_date",new Constraints(true, false, false, true));
   /**
    *record modified date
    */
   public final static TimeStampField MODIFIEDDATE = new TimeStampField("modified_date",new Constraints(true, false, false, true));

   private final static Field[] ALL_DB_FIELDS = 
   {
      COMPANYID,COMPANYNAME,ADDRESS,ADDRESS2,
      CITY,STATE,ZIPCODE,PHONE,
      CREATIONDATE,MODIFIEDDATE
   };

   private final static Field[] PK_DB_FIELDS = 
   {
      COMPANYID
   };

   /**
    * No argument constructor
    */
   public CompanyBase()
   {
   }

   /**
    * PK constructor
    * 
    * throws InvalidPkException
    */
   public CompanyBase(String companyId) throws InvalidPkException
   {
      if(!setCompanyId(companyId) )
         throw new InvalidPkException();
   }

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
      return null;
   }

   /**
    * This method returns the record referenced by the given field
    * The Data Object of the referenced class should be used to load itself
    *
    * @param conn		an active DB connection
    * @param field     the field whose value references this record
    * @return  the record referenced by this field
    * @throws SQLException    */
   protected Record reference(Connection conn, Field field) throws SQLException
   {
      return null;
   }

   /**
    * setter for abbreviation for company
    *
    * @param   value ofcompanyId
    * @return  true if valid
    */
   public boolean setCompanyId(String companyId)
   {
      return set(COMPANYID,companyId);
   }

   /**
    * getter for abbreviation for company
    *
    * @return   value ofcompanyId
    */
   public String getCompanyId()
   {
      return get(COMPANYID);
   }

   /**
    * setter for name of company
    *
    * @param   value ofcompanyName
    * @return  true if valid
    */
   public boolean setCompanyName(String companyName)
   {
      return set(COMPANYNAME,companyName);
   }

   /**
    * getter for name of company
    *
    * @return   value ofcompanyName
    */
   public String getCompanyName()
   {
      return get(COMPANYNAME);
   }

   /**
    * setter for street address
    *
    * @param   value ofaddress
    * @return  true if valid
    */
   public boolean setAddress(String address)
   {
      return set(ADDRESS,address);
   }

   /**
    * getter for street address
    *
    * @return   value ofaddress
    */
   public String getAddress()
   {
      return get(ADDRESS);
   }

   /**
    * setter for additional address information
    *
    * @param   value ofaddress2
    * @return  true if valid
    */
   public boolean setAddress2(String address2)
   {
      return set(ADDRESS2,address2);
   }

   /**
    * getter for additional address information
    *
    * @return   value ofaddress2
    */
   public String getAddress2()
   {
      return get(ADDRESS2);
   }

   /**
    * setter for city
    *
    * @param   value ofcity
    * @return  true if valid
    */
   public boolean setCity(String city)
   {
      return set(CITY,city);
   }

   /**
    * getter for city
    *
    * @return   value ofcity
    */
   public String getCity()
   {
      return get(CITY);
   }

   /**
    * setter for state
    *
    * @param   value ofstate
    * @return  true if valid
    */
   public boolean setState(String state)
   {
      return set(STATE,state);
   }

   /**
    * getter for state
    *
    * @return   value ofstate
    */
   public String getState()
   {
      return get(STATE);
   }

   /**
    * setter for zipcode
    *
    * @param   value ofzipcode
    * @return  true if valid
    */
   public boolean setZipcode(String zipcode)
   {
      return set(ZIPCODE,zipcode);
   }

   /**
    * getter for zipcode
    *
    * @return   value ofzipcode
    */
   public String getZipcode()
   {
      return get(ZIPCODE);
   }

   /**
    * setter for phone number
    *
    * @param   value ofphone
    * @return  true if valid
    */
   public boolean setPhone(String phone)
   {
      return set(PHONE,phone);
   }

   /**
    * getter for phone number
    *
    * @return   value ofphone
    */
   public String getPhone()
   {
      return get(PHONE);
   }

   /**
    * setter for record creation date
    *
    * @param   value ofcreationDate
    * @return  true if valid
    */
   public boolean setCreationDate(java.util.Date creationDate)
   {
      return set(CREATIONDATE,creationDate);
   }

   /**
    * getter for record creation date
    *
    * @return   value ofcreationDate
    */
   public java.sql.Timestamp getCreationDate()
   {
      return get(CREATIONDATE);
   }

   /**
    * setter for record modified date
    *
    * @param   value ofmodifiedDate
    * @return  true if valid
    */
   public boolean setModifiedDate(java.util.Date modifiedDate)
   {
      return set(MODIFIEDDATE,modifiedDate);
   }

   /**
    * getter for record modified date
    *
    * @return   value ofmodifiedDate
    */
   public java.sql.Timestamp getModifiedDate()
   {
      return get(MODIFIEDDATE);
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
      Company clone = new Company();
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
