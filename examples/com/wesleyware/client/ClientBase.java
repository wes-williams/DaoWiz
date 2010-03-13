package com.wesleyware.client;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clients of entire company
 */
public abstract class ClientBase extends Record implements Cloneable
{
   protected final static ClientDao DAO =  new ClientDao();

   protected final static String DB_TABLE_NAME = "clients";

   /**
    *client abbreviation
    */
   public final static VarCharField CLIENTID = new VarCharField("client_id",10,new Constraints(true, true, false, false));
   /**
    *client name
    */
   public final static VarCharField CLIENTNAME = new VarCharField("client_name",50,new Constraints(true, false, false, false));
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
    *phone
    */
   public final static VarCharField PHONE = new VarCharField("phone",12,new Constraints(false, false, false, false));
   /**
    *phone extension
    */
   public final static VarCharField PHONEEXTENSTION = new VarCharField("phone_ext",4,new Constraints(false, false, false, false));
   /**
    *status
    */
   public final static CharField STATUS = new CharField("status",1,new Constraints(true, false, false, false));
   /**
    *employee assigned to client relation
    */
   public final static IntField ASSIGNEDEMPLOYEEID = new IntField("assigned_employee_id",new Constraints(true, false, true, false));
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
      CLIENTID,CLIENTNAME,ADDRESS,ADDRESS2,
      CITY,STATE,ZIPCODE,PHONE,
      PHONEEXTENSTION,STATUS,ASSIGNEDEMPLOYEEID,CREATIONDATE,
      MODIFIEDDATE
   };

   private final static Field[] PK_DB_FIELDS = 
   {
      CLIENTID
   };

   /**
    * No argument constructor
    */
   public ClientBase()
   {
   }

   /**
    * PK constructor
    * 
    * throws InvalidPkException
    */
   public ClientBase(String clientId) throws InvalidPkException
   {
      if(!setClientId(clientId) )
         throw new InvalidPkException();
   }

   /**
    * This method returns the possiblities for assignedEmployeeId given the current state of the object
    *
    * @param   conn   active db connection
    * @return  possible values for assignedEmployeeId
    * @throws SQLException
    */
   public abstract List assignedEmployeeIdChoices(Connection conn) throws SQLException;

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
      if(field==ASSIGNEDEMPLOYEEID) return assignedEmployeeIdChoices(conn);
      return null;
   }

   /**
    * setter for client abbreviation
    *
    * @param   value ofclientId
    * @return  true if valid
    */
   public boolean setClientId(String clientId)
   {
      return set(CLIENTID,clientId);
   }

   /**
    * getter for client abbreviation
    *
    * @return   value ofclientId
    */
   public String getClientId()
   {
      return get(CLIENTID);
   }

   /**
    * setter for client name
    *
    * @param   value ofclientName
    * @return  true if valid
    */
   public boolean setClientName(String clientName)
   {
      return set(CLIENTNAME,clientName);
   }

   /**
    * getter for client name
    *
    * @return   value ofclientName
    */
   public String getClientName()
   {
      return get(CLIENTNAME);
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
    * @param   value ofphoneExtenstion
    * @return  true if valid
    */
   public boolean setPhoneExtenstion(String phoneExtenstion)
   {
      return set(PHONEEXTENSTION,phoneExtenstion);
   }

   /**
    * getter for phone extension
    *
    * @return   value ofphoneExtenstion
    */
   public String getPhoneExtenstion()
   {
      return get(PHONEEXTENSTION);
   }

   /**
    * setter for status
    *
    * @param   value ofstatus
    * @return  true if valid
    */
   public boolean setStatus(String status)
   {
      return set(STATUS,status);
   }

   /**
    * getter for status
    *
    * @return   value ofstatus
    */
   public String getStatus()
   {
      return get(STATUS);
   }

   /**
    * setter for employee assigned to client relation
    *
    * @param   value ofassignedEmployeeId
    * @return  true if valid
    */
   public boolean setAssignedEmployeeId(Integer assignedEmployeeId)
   {
      return set(ASSIGNEDEMPLOYEEID,assignedEmployeeId);
   }

   /**
    * primitive value setter for employee assigned to client relation
    *
    * @param   value ofassignedEmployeeId
    * @return  true if valid
    */
   public boolean setAssignedEmployeeId(int assignedEmployeeId)
   {
      return set(ASSIGNEDEMPLOYEEID,assignedEmployeeId);
   }

   /**
    * getter for employee assigned to client relation
    *
    * @return   value ofassignedEmployeeId
    */
   public Integer getAssignedEmployeeIdObject()
   {
      return get(ASSIGNEDEMPLOYEEID);
   }

   /**
    * primitive getter for employee assigned to client relation
    *
    * @return   value ofassignedEmployeeId
    */
   public int getAssignedEmployeeId()
   {
      return getPrimitive(ASSIGNEDEMPLOYEEID);
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
      Client clone = new Client();
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
