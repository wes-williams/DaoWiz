package com.wesleyware.employee;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Employees of company
 */
public abstract class EmployeeBase extends Record implements Cloneable
{
   protected final static EmployeeDao DAO =  new EmployeeDao();

   protected final static String DB_TABLE_NAME = "employees";

   /**
    *auto generated employee id
    */
   public final static IntField EMPLOYEEID = new IntField("employee_id",new Constraints(true, true, false, false));
   /**
    *referenced company
    */
   public final static VarCharField COMPANYID = new VarCharField("company_id",10,new Constraints(true, false, true, false));
   /**
    *referenced department
    */
   public final static VarCharField DEPARTMENTID = new VarCharField("department_id",10,new Constraints(true, false, true, false));
   /**
    *first name
    */
   public final static VarCharField FIRSTNAME = new VarCharField("first_name",30,new Constraints(true, false, false, false));
   /**
    *last name
    */
   public final static VarCharField LASTNAME = new VarCharField("last_name",30,new Constraints(true, false, false, false));
   /**
    *email
    */
   public final static VarCharField EMAIL = new VarCharField("email",60,new Constraints(false, false, false, false));
   /**
    *phone
    */
   public final static VarCharField PHONE = new VarCharField("phone",12,new Constraints(false, false, false, false));
   /**
    *phone extension
    */
   public final static VarCharField PHONEEXTENSION = new VarCharField("phone_ext",4,new Constraints(false, false, false, false));
   /**
    *date of birth
    */
   public final static DateField BIRTHDATE = new DateField("birth_date",new Constraints(true, false, false, false));
   /**
    *start date of employment
    */
   public final static DateField STARTDATE = new DateField("start_date",new Constraints(true, false, false, false));
   /**
    *end date of employment
    */
   public final static DateField ENDDATE = new DateField("end_date",new Constraints(false, false, false, false));
   /**
    *employee type (F= Full Time, P = Part Time, C = Consultant, I = Intern)
    */
   public final static CharField EMPLOYEETYPE = new CharField("employee_type",1,new Constraints(true, false, false, false));
   /**
    *employee status (A = Active, I = Inactive)
    */
   public final static CharField STATUS = new CharField("status",1,new Constraints(true, false, false, false));
   /**
    *creation date of record
    */
   public final static TimeStampField CREATIONDATE = new TimeStampField("creation_date",new Constraints(true, false, false, true));
   /**
    *last modification date of record
    */
   public final static TimeStampField MODIFIEDDATE = new TimeStampField("modified_date",new Constraints(true, false, false, true));

   private final static Field[] ALL_DB_FIELDS = 
   {
      EMPLOYEEID,COMPANYID,DEPARTMENTID,FIRSTNAME,
      LASTNAME,EMAIL,PHONE,PHONEEXTENSION,
      BIRTHDATE,STARTDATE,ENDDATE,EMPLOYEETYPE,
      STATUS,CREATIONDATE,MODIFIEDDATE
   };

   private final static Field[] PK_DB_FIELDS = 
   {
      EMPLOYEEID
   };

   /**
    * No argument constructor
    */
   public EmployeeBase()
   {
   }

   /**
    * PK constructor
    * 
    * throws InvalidPkException
    */
   public EmployeeBase(Integer employeeId) throws InvalidPkException
   {
      if(!setEmployeeId(employeeId) )
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
    * This method returns the possiblities for departmentId given the current state of the object
    *
    * @param   conn   active db connection
    * @return  possible values for departmentId
    * @throws SQLException
    */
   public abstract List departmentIdChoices(Connection conn) throws SQLException;

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
      if(field==DEPARTMENTID) return departmentIdChoices(conn);
      return null;
   }

   /**
    * setter for auto generated employee id
    *
    * @param   value ofemployeeId
    * @return  true if valid
    */
   public boolean setEmployeeId(Integer employeeId)
   {
      return set(EMPLOYEEID,employeeId);
   }

   /**
    * primitive value setter for auto generated employee id
    *
    * @param   value ofemployeeId
    * @return  true if valid
    */
   public boolean setEmployeeId(int employeeId)
   {
      return set(EMPLOYEEID,employeeId);
   }

   /**
    * getter for auto generated employee id
    *
    * @return   value ofemployeeId
    */
   public Integer getEmployeeIdObject()
   {
      return get(EMPLOYEEID);
   }

   /**
    * primitive getter for auto generated employee id
    *
    * @return   value ofemployeeId
    */
   public int getEmployeeId()
   {
      return getPrimitive(EMPLOYEEID);
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
    * setter for referenced department
    *
    * @param   value ofdepartmentId
    * @return  true if valid
    */
   public boolean setDepartmentId(String departmentId)
   {
      return set(DEPARTMENTID,departmentId);
   }

   /**
    * getter for referenced department
    *
    * @return   value ofdepartmentId
    */
   public String getDepartmentId()
   {
      return get(DEPARTMENTID);
   }

   /**
    * setter for first name
    *
    * @param   value offirstName
    * @return  true if valid
    */
   public boolean setFirstName(String firstName)
   {
      return set(FIRSTNAME,firstName);
   }

   /**
    * getter for first name
    *
    * @return   value offirstName
    */
   public String getFirstName()
   {
      return get(FIRSTNAME);
   }

   /**
    * setter for last name
    *
    * @param   value oflastName
    * @return  true if valid
    */
   public boolean setLastName(String lastName)
   {
      return set(LASTNAME,lastName);
   }

   /**
    * getter for last name
    *
    * @return   value oflastName
    */
   public String getLastName()
   {
      return get(LASTNAME);
   }

   /**
    * setter for email
    *
    * @param   value ofemail
    * @return  true if valid
    */
   public boolean setEmail(String email)
   {
      return set(EMAIL,email);
   }

   /**
    * getter for email
    *
    * @return   value ofemail
    */
   public String getEmail()
   {
      return get(EMAIL);
   }

   /**
    * setter for phone
    *
    * @param   value ofphone
    * @return  true if valid
    */
   public boolean setPhone(String phone)
   {
      return set(PHONE,phone);
   }

   /**
    * getter for phone
    *
    * @return   value ofphone
    */
   public String getPhone()
   {
      return get(PHONE);
   }

   /**
    * setter for phone extension
    *
    * @param   value ofphoneExtension
    * @return  true if valid
    */
   public boolean setPhoneExtension(String phoneExtension)
   {
      return set(PHONEEXTENSION,phoneExtension);
   }

   /**
    * getter for phone extension
    *
    * @return   value ofphoneExtension
    */
   public String getPhoneExtension()
   {
      return get(PHONEEXTENSION);
   }

   /**
    * setter for date of birth
    *
    * @param   value ofbirthDate
    * @return  true if valid
    */
   public boolean setBirthDate(java.util.Date birthDate)
   {
      return set(BIRTHDATE,birthDate);
   }

   /**
    * getter for date of birth
    *
    * @return   value ofbirthDate
    */
   public java.sql.Date getBirthDate()
   {
      return get(BIRTHDATE);
   }

   /**
    * setter for start date of employment
    *
    * @param   value ofstartDate
    * @return  true if valid
    */
   public boolean setStartDate(java.util.Date startDate)
   {
      return set(STARTDATE,startDate);
   }

   /**
    * getter for start date of employment
    *
    * @return   value ofstartDate
    */
   public java.sql.Date getStartDate()
   {
      return get(STARTDATE);
   }

   /**
    * setter for end date of employment
    *
    * @param   value ofendDate
    * @return  true if valid
    */
   public boolean setEndDate(java.util.Date endDate)
   {
      return set(ENDDATE,endDate);
   }

   /**
    * getter for end date of employment
    *
    * @return   value ofendDate
    */
   public java.sql.Date getEndDate()
   {
      return get(ENDDATE);
   }

   /**
    * setter for employee type (F= Full Time, P = Part Time, C = Consultant, I = Intern)
    *
    * @param   value ofemployeeType
    * @return  true if valid
    */
   public boolean setEmployeeType(String employeeType)
   {
      return set(EMPLOYEETYPE,employeeType);
   }

   /**
    * getter for employee type (F= Full Time, P = Part Time, C = Consultant, I = Intern)
    *
    * @return   value ofemployeeType
    */
   public String getEmployeeType()
   {
      return get(EMPLOYEETYPE);
   }

   /**
    * setter for employee status (A = Active, I = Inactive)
    *
    * @param   value ofstatus
    * @return  true if valid
    */
   public boolean setStatus(String status)
   {
      return set(STATUS,status);
   }

   /**
    * getter for employee status (A = Active, I = Inactive)
    *
    * @return   value ofstatus
    */
   public String getStatus()
   {
      return get(STATUS);
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
    * setter for last modification date of record
    *
    * @param   value ofmodifiedDate
    * @return  true if valid
    */
   public boolean setModifiedDate(java.util.Date modifiedDate)
   {
      return set(MODIFIEDDATE,modifiedDate);
   }

   /**
    * getter for last modification date of record
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
      Employee clone = new Employee();
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
