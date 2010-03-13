package com.wesleyware.client;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * represents relationship between clients and companies
 */
public abstract class CompanyClientBase extends Record implements Cloneable
{
   protected final static CompanyClientDao DAO =  new CompanyClientDao();

   protected final static String DB_TABLE_NAME = "company_clients";

   /**
    *reference to company
    */
   public final static VarCharField COMPANYID = new VarCharField("company_id",10,new Constraints(true, true, true, false));
   /**
    *reference to client
    */
   public final static VarCharField CLIENTID = new VarCharField("client_id",10,new Constraints(true, true, true, false));

   private final static Field[] ALL_DB_FIELDS = 
   {
      COMPANYID,CLIENTID
   };

   private final static Field[] PK_DB_FIELDS = 
   {
      COMPANYID,CLIENTID
   };

   /**
    * No argument constructor
    */
   public CompanyClientBase()
   {
   }

   /**
    * PK constructor
    * 
    * throws InvalidPkException
    */
   public CompanyClientBase(String companyId,String clientId) throws InvalidPkException
   {
      if(!setCompanyId(companyId) ||
      !setClientId(clientId) )
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
      if(field==COMPANYID) return companyIdChoices(conn);
      if(field==CLIENTID) return clientIdChoices(conn);
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
    * setter for reference to client
    *
    * @param   value ofclientId
    * @return  true if valid
    */
   public boolean setClientId(String clientId)
   {
      return set(CLIENTID,clientId);
   }

   /**
    * getter for reference to client
    *
    * @return   value ofclientId
    */
   public String getClientId()
   {
      return get(CLIENTID);
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
      CompanyClient clone = new CompanyClient();
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
