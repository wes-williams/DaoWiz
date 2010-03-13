package com.wesleyware.department;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Department of  company
 */
public abstract class DepartmentBase extends Record implements Cloneable
{
   protected final static DepartmentDao DAO =  new DepartmentDao();

   protected final static String DB_TABLE_NAME = "departments";

   /**
    *department abbreviation
    */
   public final static VarCharField DEPARTMENTID = new VarCharField("department_id",10,new Constraints(true, true, false, false));
   /**
    *referenced company
    */
   public final static VarCharField COMPANYID = new VarCharField("company_id",10,new Constraints(true, true, true, false));
   /**
    *department name
    */
   public final static VarCharField NAME = new VarCharField("name",50,new Constraints(true, false, false, false));
   /**
    *description of department
    */
   public final static TextField DESCRIPTION = new TextField("description",new Constraints(true, false, false, false));
   /**
    *creation date of record
    */
   public final static TimeStampField CREATIONDATE = new TimeStampField("creation_date",new Constraints(true, false, false, true));
   /**
    *modeified date of record
    */
   public final static TimeStampField MODIFIEDDATE = new TimeStampField("modified_date",new Constraints(true, false, false, true));

   private final static Field[] ALL_DB_FIELDS = 
   {
      DEPARTMENTID,COMPANYID,NAME,DESCRIPTION,
      CREATIONDATE,MODIFIEDDATE
   };

   private final static Field[] PK_DB_FIELDS = 
   {
      DEPARTMENTID,COMPANYID
   };

   /**
    * No argument constructor
    */
   public DepartmentBase()
   {
   }

   /**
    * PK constructor
    * 
    * throws InvalidPkException
    */
   public DepartmentBase(String departmentId,String companyId) throws InvalidPkException
   {
      if(!setDepartmentId(departmentId) ||
      !setCompanyId(companyId) )
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
      return null;
   }

   /**
    * setter for department abbreviation
    *
    * @param   value ofdepartmentId
    * @return  true if valid
    */
   public boolean setDepartmentId(String departmentId)
   {
      return set(DEPARTMENTID,departmentId);
   }

   /**
    * getter for department abbreviation
    *
    * @return   value ofdepartmentId
    */
   public String getDepartmentId()
   {
      return get(DEPARTMENTID);
   }

   /**
    * setter for referenced company
    *
    * @param   value ofcompanyId
    * @return  true if valid
    */
   public boolean setCompanyId(String companyId)
   {
      return set(COMPANYID,companyId);
   }

   /**
    * getter for referenced company
    *
    * @return   value ofcompanyId
    */
   public String getCompanyId()
   {
      return get(COMPANYID);
   }

   /**
    * setter for department name
    *
    * @param   value ofname
    * @return  true if valid
    */
   public boolean setName(String name)
   {
      return set(NAME,name);
   }

   /**
    * getter for department name
    *
    * @return   value ofname
    */
   public String getName()
   {
      return get(NAME);
   }

   /**
    * setter for description of department
    *
    * @param   value ofdescription
    * @return  true if valid
    */
   public boolean setDescription(String description)
   {
      return set(DESCRIPTION,description);
   }

   /**
    * getter for description of department
    *
    * @return   value ofdescription
    */
   public String getDescription()
   {
      return get(DESCRIPTION);
   }

   /**
    * setter for creation date of record
    *
    * @param   value ofcreationDate
    * @return  true if valid
    */
   public boolean setCreationDate(java.util.Date creationDate)
   {
      return set(CREATIONDATE,creationDate);
   }

   /**
    * getter for creation date of record
    *
    * @return   value ofcreationDate
    */
   public java.sql.Timestamp getCreationDate()
   {
      return get(CREATIONDATE);
   }

   /**
    * setter for modeified date of record
    *
    * @param   value ofmodifiedDate
    * @return  true if valid
    */
   public boolean setModifiedDate(java.util.Date modifiedDate)
   {
      return set(MODIFIEDDATE,modifiedDate);
   }

   /**
    * getter for modeified date of record
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
      Department clone = new Department();
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
