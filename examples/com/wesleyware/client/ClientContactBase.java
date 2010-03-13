package com.wesleyware.client;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * contacts for clients
 */
public abstract class ClientContactBase extends Record implements Cloneable
{
   protected final static ClientContactDao DAO =  new ClientContactDao();

   protected final static String DB_TABLE_NAME = "client_contacts";

   /**
    *auto generated id for client contact
    */
   public final static IntField CLIENTCONTACTID = new IntField("client_contact_id",new Constraints(true, true, false, true));
   /**
    *referenced client
    */
   public final static VarCharField CLIENTID = new VarCharField("client_id",10,new Constraints(true, false, true, false));
   /**
    *department
    */
   public final static VarCharField DEPARTMENT = new VarCharField("department",30,new Constraints(true, false, false, false));
   /**
    *first name
    */
   public final static VarCharField FIRSTNAME = new VarCharField("first_name",30,new Constraints(true, false, false, false));
   /**
    *last name
    */
   public final static VarCharField LASTNAME = new VarCharField("last_name",30,new Constraints(true, false, false, false));
   /**
    *email address
    */
   public final static VarCharField EMAIL = new VarCharField("email",60,new Constraints(false, false, false, false));
   /**
    *phone number
    */
   public final static VarCharField PHONE = new VarCharField("phone",12,new Constraints(false, false, false, false));
   /**
    *phone extension
    */
   public final static VarCharField PHONEEXTENSION = new VarCharField("phone_ext",4,new Constraints(false, false, false, false));
   /**
    *date of birth
    */
   public final static DateField BIRTHDATE = new DateField("birth_date",new Constraints(false, false, false, false));
   /**
    *client status (A = Active, I =Inactive, P = Prospective)
    */
   public final static CharField STATUS = new CharField("status",1,new Constraints(true, false, false, false));
   /**
    *creation date of record
    */
   public final static TimeStampField CREATIONDATE = new TimeStampField("creation_date",new Constraints(true, false, false, true));
   /**
    *last modified date of record
    */
   public final static TimeStampField MODIFIEDDATE = new TimeStampField("modified_date",new Constraints(true, false, false, true));

   private final static Field[] ALL_DB_FIELDS = 
   {
      CLIENTCONTACTID,CLIENTID,DEPARTMENT,FIRSTNAME,
      LASTNAME,EMAIL,PHONE,PHONEEXTENSION,
      BIRTHDATE,STATUS,CREATIONDATE,MODIFIEDDATE
   };

   private final static Field[] PK_DB_FIELDS = 
   {
      CLIENTCONTACTID
   };

   /**
    * No argument constructor
    */
   public ClientContactBase()
   {
   }

   /**
    * PK constructor
    * 
    * throws InvalidPkException
    */
   public ClientContactBase(Integer clientContactId) throws InvalidPkException
   {
      if(!setClientContactId(clientContactId) )
         throw new InvalidPkException();
   }

   /**
    * This method returns the possiblities for clientId given the current state of the object
    *
    * @param   conn   active db connection
    * @return  possible values for clientId
    * @throws SQLException
    */
   public abstract List clientIdChoices(Connection conn) throws SQLException;

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
      if(field==CLIENTID) return clientIdChoices(conn);
      return null;
   }

   /**
    * setter for auto generated id for client contact
    *
    * @param   value ofclientContactId
    * @return  true if valid
    */
   public boolean setClientContactId(Integer clientContactId)
   {
      return set(CLIENTCONTACTID,clientContactId);
   }

   /**
    * primitive value setter for auto generated id for client contact
    *
    * @param   value ofclientContactId
    * @return  true if valid
    */
   public boolean setClientContactId(int clientContactId)
   {
      return set(CLIENTCONTACTID,clientContactId);
   }

   /**
    * getter for auto generated id for client contact
    *
    * @return   value ofclientContactId
    */
   public Integer getClientContactIdObject()
   {
      return get(CLIENTCONTACTID);
   }

   /**
    * primitive getter for auto generated id for client contact
    *
    * @return   value ofclientContactId
    */
   public int getClientContactId()
   {
      return getPrimitive(CLIENTCONTACTID);
   }

   /**
    * setter for referenced client
    *
    * @param   value ofclientId
    * @return  true if valid
    */
   public boolean setClientId(String clientId)
   {
      return set(CLIENTID,clientId);
   }

   /**
    * getter for referenced client
    *
    * @return   value ofclientId
    */
   public String getClientId()
   {
      return get(CLIENTID);
   }

   /**
    * setter for department
    *
    * @param   value ofdepartment
    * @return  true if valid
    */
   public boolean setDepartment(String department)
   {
      return set(DEPARTMENT,department);
   }

   /**
    * getter for department
    *
    * @return   value ofdepartment
    */
   public String getDepartment()
   {
      return get(DEPARTMENT);
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
    * setter for email address
    *
    * @param   value ofemail
    * @return  true if valid
    */
   public boolean setEmail(String email)
   {
      return set(EMAIL,email);
   }

   /**
    * getter for email address
    *
    * @return   value ofemail
    */
   public String getEmail()
   {
      return get(EMAIL);
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
    * setter for client status (A = Active, I =Inactive, P = Prospective)
    *
    * @param   value ofstatus
    * @return  true if valid
    */
   public boolean setStatus(String status)
   {
      return set(STATUS,status);
   }

   /**
    * getter for client status (A = Active, I =Inactive, P = Prospective)
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
    * setter for last modified date of record
    *
    * @param   value ofmodifiedDate
    * @return  true if valid
    */
   public boolean setModifiedDate(java.util.Date modifiedDate)
   {
      return set(MODIFIEDDATE,modifiedDate);
   }

   /**
    * getter for last modified date of record
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
      ClientContact clone = new ClientContact();
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
