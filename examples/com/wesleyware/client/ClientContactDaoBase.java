package com.wesleyware.client;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.*;
import java.util.List;
/**
 * This is a base dao class the extension should always be used.
 * contacts for clients
 */
public abstract class ClientContactDaoBase extends RecordDao
{

   protected Record newRecord()
   {
      return new ClientContact();
   }

  /**
   * Checks if record exist. Primary Keys are used a criteria.
   * 
   * @param conn	  an active DB connection
   * @param record	  the record to be checked
   * @return	  indicator of if the record exist
   * @throws SQLException
   */
   public boolean exist(Connection conn, ClientContactBase record) throws SQLException
   {
   	return exist(conn, (Record) record);
   }

  /**
   * Saves the record calling either or create or update depending on what exist(conn,record) returns
   * 
   * @param conn	  an active DB connection
   * @param record	  the record to be saved
   * @return	  count of how many were saved
   * @throws SQLException
   */
   public int save(Connection conn, ClientContactBase record) throws SQLException
   {
   	return save(conn, (Record) record);
   }

  /**
   * Creates a new DB record
   * 
   * @param conn	  an active DB connection
   * @param record	  the record to be created
   * @return	  true if the record was created
   * @throws SQLException
   */
   public boolean create(Connection conn, ClientContactBase record) throws SQLException
   {
   	return create(conn, (Record) record);
   }

   /**
    * Updates a database record. Primary Keys are used as criteria.
    * 
    * @param conn       an active DB connection
    * @param record     the record to update
    * @return           the number of records updated. should only be one.
    * @throws SQLException
    */
   public int update(Connection conn, ClientContactBase record) throws SQLException
   {
   	return update(conn, (Record) record);
   }

   /**
    * Deletes a database record. Primary Keys are used a criteria.
    * 
    * @param conn	  an active DB connection
    * @param record   the record to delete
    * @return	  the number of records deleted. should only be one.
    * @throws SQLException
    */
   public int delete(Connection conn, ClientContactBase record) throws SQLException
   {
   	return delete(conn, (Record) record);
   }

   /**
    * Changes the primary key of a record
    * 
    * @param conn		an active DB connection
    * @param oldRecord	record with old primary key values set
    * @param newRecord	record with new primary key values set
    * @return		the number of records updates. should only be one.
    */
   public int updatePK(Connection conn, ClientContactBase oldRecord, ClientContact newRecord) throws SQLException
   {
   	return updatePK(conn, (Record) oldRecord, (Record) newRecord);
   }

   /**
    * Fully loads the record with the given primary key.
    *
    * @param conn       an action DB connection
    * @param record     record with primary key values set
    * @return           true if the record was found
    * @throws SQLException
    */
   public boolean load(Connection conn, ClientContactBase record) throws SQLException
   {
   	  return load(conn, (Record) record);
   }

   /**
    * Searches for records matching the given record
    * 
    * @param conn		an active DB connection
    * @param searchRecord	the record to be matched
    * @param exact		indicator of if the matches must be exact.
    * @return		list of matching records
    * @throws SQLException
    */
   public List search(Connection conn, ClientContactBase searchRecord, boolean exact) throws SQLException
   {
      return search(conn, (Record) searchRecord, exact);
   }
}
