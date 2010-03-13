/*
 * Created on Oct 6, 2004
 */
package com.wesleyware.daowiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;
import java.util.Vector;

/**
 * This is a generic DAO that will be inherited by each table specific DAO to contain custom code.
 * The data access methods should be overloaded to take the correct record type and given public access.
 *
 * @author Wesley Williams
 *
 */
public abstract class RecordDao
{
    private final static boolean DEBUG = false;
    
    /**
     * Constructs a dao
     */
    public RecordDao()
    {
        
    }
    
    /**
     *  Creates record associated with this dao.
     */
    protected abstract Record newRecord();
    
  /*  
    // This would require the right driver (jdbc3) and database cooperation,
    // so I'm not even going to use it. Maybe later
    
    protected String[] retrievePK(Record record)
    {
        Vector pkNames = new Vector();
        Field[] pkFields = record.primaryKeyFields();
        
        for (int i = 0; i < pkFields.length; i++)
        {
            Constraints pkConstraints = pkFields[i].getContraints();
            
            if (pkConstraints.isPrimaryKey() && record.getValue(pkFields[i]) == null)
                pkNames.addElement(pkFields[i].getName());
        }
        
        return (String[]) pkNames.toArray(new String[pkNames.size()]);
    }
    
    protected boolean retrieveNonPK(Record record)
    {
        Field[]fields = record.allFields();
        
        for (int i = 0; i < fields.length; i++)
        {
            Constraints constraints = fields[i].getContraints();
            
            if (!constraints.isPrimaryKey() &&
            !record.exist(fields[i]))
                return true;
        }
        
        return false;
    }
    
    protected boolean createAndPopulate(Connection conn, Record record) throws SQLException
    {
        String[] pkReturnFields = retrievePK(record);
        boolean retrieveNonPK = retrieveNonPK(record);
        boolean success = false;
        String sql = createInsertSql(record);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = (pkReturnFields.length>0)?conn.prepareStatement(sql, pkReturnFields):conn.prepareStatement(sql);
        try
        {
            int p = 1;
            for (Iterator fields = record.keyIterator(); fields.hasNext(); p++)
            {
                Field field = (Field) fields.next();
                
                if (record.getValue(field) == null)
                {
                    stmt.setNull(p, field.getSqlType());
                }
                else
                {
                    stmt.setObject(p, record.getValue(field), field.getSqlType());
                }
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            success = (stmt.executeUpdate() == 1);
            
            if(pkReturnFields.length>0)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                {
                    Field[] pkFields = record.primaryKeyFields();
                    
                    for (int i = 0; i < pkFields.length; i++)
                    {
                        Constraints pkConstraints = pkFields[i].getContraints();
                        
                        if (pkConstraints.isPrimaryKey() && record.getValue(pkFields[i]) == null)
                            record.setValue(pkFields[i], rs.getString(pkFields[i].getName()));
                    }
                }
            }
        }
        finally
        {
            stmt.close();
        }
        
        if (retrieveNonPK)
            success = load(conn, record);
        
        return success;
    }
    
   */
    
      /**
     * Checks if record exist. Primary Keys are used a criteria.
     *
     * @param conn	an active DB connection
     * @param record	the record to be checked
     * @return		indicator of if the record exist
     * @throws SQLException
     */
    protected boolean exist(Connection conn, Record record) throws SQLException
    {
        boolean doesExist = false;
        String sql = createExistSql(record);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            Field[] pkFields = record.primaryKeyFields();
            for (int i = 0; i < pkFields.length; i++)
            {
                stmt.setObject(i + 1, record.getValue(pkFields[i]), pkFields[i].getSqlType());
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            ResultSet rs = stmt.executeQuery();
            try
            {
                if (rs.next())
                {
                    doesExist = true;
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stmt.close();
        }
        
        return doesExist;
    }
    
    /**
     * Saves the record calling either or create or update depending on what exist(conn,record) returns
     *
     * @param conn	an active DB connection
     * @param record	the record to be saved
     * @return		count of how many were saved
     * @throws SQLException
     */
    protected int save(Connection conn, Record record) throws SQLException
    {
        int updateCount = 0;

        if(exist(conn,record))
            updateCount = update(conn,record);
        else
            updateCount = create(conn,record)?1:0;
        
        return updateCount;
    }
    
    /**
     * Creates a new DB record
     *
     * @param conn	an active DB connection
     * @param record	the record to be created
     * @return		true if the record was created
     * @throws SQLException
     */
    protected boolean create(Connection conn, Record record) throws SQLException
    {
        boolean success = false;
        String sql = createInsertSql(record);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            int p = 1;
            for (Iterator fields = record.keyIterator(); fields.hasNext(); p++)
            {
                Field field = (Field) fields.next();
                
                if (record.getValue(field) == null)
                {
                    stmt.setNull(p, field.getSqlType());
                }
                else
                {
                    stmt.setObject(p, record.getValue(field), field.getSqlType());
                }
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            success = (stmt.executeUpdate() == 1);
        }
        finally
        {
            stmt.close();
        }
        
        return success;
    }
    
    /**
     * Updates a database record. Primary Keys are used as criteria.
     *
     * @param conn	an active DB connection
     * @param record	the record to update
     * @return		the number of records updated. should only be one.
     * @throws SQLException
     */
    protected int update(Connection conn, Record record) throws SQLException
    {
        int updatedRecords = 0;
        String sql = createUpdateSql(record);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            int p = 1;
            for (Iterator fields = record.keyIterator(); fields.hasNext();)
            {
                Field field = (Field) fields.next();
                
                if (!field.getContraints().isPrimaryKey())
                {
                    if (record.getValue(field) == null)
                    {
                        stmt.setNull(p, field.getSqlType());
                    }
                    else
                    {
                        stmt.setObject(p, record.getValue(field), field.getSqlType());
                    }
                    p++;
                }
            }
            
            Field[] pkFields = record.primaryKeyFields();
            for (int i = 0; i < pkFields.length; i++, p++)
            {
                stmt.setObject(p, record.getValue(pkFields[i]), pkFields[i].getSqlType());
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            updatedRecords = stmt.executeUpdate();
        }
        finally
        {
            stmt.close();
        }
        
        return updatedRecords;
    }
    
    /**
     * Deletes a database record. Primary Keys are used a criteria.
     *
     * @param conn	an active DB connection
     * @param record	the record to delete
     * @return		the number of records deleted. should only be one.
     * @throws SQLException
     */
    protected int delete(Connection conn, Record record) throws SQLException
    {
        int deletedRecords = 0;
        String sql = createDeleteSql(record);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            Field[] pkFields = record.primaryKeyFields();
            for (int i = 0; i < pkFields.length; i++)
            {
                stmt.setObject(i + 1, record.getValue(pkFields[i]), pkFields[i].getSqlType());
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            deletedRecords = stmt.executeUpdate();
        }
        finally
        {
            stmt.close();
        }
        
        return deletedRecords;
    }
    
    /**
     * Changes the primary key of a record
     *
     * @param conn		an active DB connection
     * @param oldRecord		record with old primary key values set
     * @param newRecord		record with new primary key values set
     * @return			the number of records updates. should only be one.
     */
    protected int updatePK(Connection conn, Record oldRecord, Record newRecord) throws SQLException
    {
        int updatedRecords = 0;
        String sql = createUpdatePkSql(oldRecord, newRecord);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            Field[] pkFields = oldRecord.primaryKeyFields();
            
            int p = 1;
            for (int i = 0; i < pkFields.length; i++, p++)
            {
                stmt.setObject(p, newRecord.getValue(pkFields[i]), pkFields[i].getSqlType());
            }
            
            for (int i = 0; i < pkFields.length; i++, p++)
            {
                stmt.setObject(p, oldRecord.getValue(pkFields[i]), pkFields[i].getSqlType());
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            updatedRecords = stmt.executeUpdate();
        }
        finally
        {
            stmt.close();
        }
        
        return updatedRecords;
        
    }
    
    /**
     * Fully loads the record with the given primary key.
     * If more than one record is returned the first one is loaded
     *
     * @param conn	an action DB connection
     * @param record	record with primary key values set
     * @return		true if the record was found
     * @throws SQLException
     */
    protected boolean load(Connection conn, Record record) throws SQLException
    {
        boolean success = false;
        String sql = createLoadSql(record);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            Field[] pkFields = record.primaryKeyFields();
            for (int i = 0; i < pkFields.length; i++)
            {
                stmt.setObject(i + 1, record.getValue(pkFields[i]), pkFields[i].getSqlType());
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            ResultSet rs = stmt.executeQuery();
            try
            {
                if (rs.next())
                {
                    Field[] fields = record.allFields();
                    
                    for (int i = 0; i < fields.length; i++)
                    {
                        record.setValue(fields[i], rs.getObject(fields[i].getName()));
                    }
                    
                    success = true;
                }
                
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stmt.close();
        }
        
        return success;
    }
    
    /**
     * Loads all the records in this table
     *
     * @param conn  an active DB connection
     * @return	a list of records
     * @throws SQLException
     */
    public List loadAll(Connection conn) throws SQLException
    {
        Record record = newRecord();
        
        Vector records = new Vector();
        
        String sql = createLoadAllSql(record);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            ResultSet rs = stmt.executeQuery();
            try
            {
                Field[] fields = record.allFields();
                while (rs.next())
                {
                    Record tempRecord = newRecord();
                    
                    for (int i = 0; i < fields.length; i++)
                    {
                        tempRecord.setValue(fields[i], rs.getObject(fields[i].getName()));
                    }
                    
                    records.add(tempRecord);
                }
                
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stmt.close();
        }
        
        return records;
    }
    
    /**
     * Searches for records matching the given record
     *
     * @param conn		an active DB connection
     * @param searchRecord	the record to be matched
     * @param exact		indicator of if the matches must be exact.
     * @return			list of matching records
     * @throws SQLException
     */
    protected List search(Connection conn, Record searchRecord, boolean exact) throws SQLException
    {
        Vector records = new Vector();
        String sql = createSearchSql(searchRecord, exact);
        
        if (DEBUG)
            System.out.println(sql);
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        try
        {
            int p = 1;
            for (Iterator fields = searchRecord.keyIterator(); fields.hasNext();)
            {
                Field field = (Field) fields.next();
                
                if (searchRecord.getValue(field) == null)
                    continue;
                else if (!exact && field.getJavaClass() == String.class)
                    stmt.setObject(p, "%" + searchRecord.getValue(field) + "%", field.getSqlType());
                else
                    stmt.setObject(p, searchRecord.getValue(field), field.getSqlType());
                p++;
            }
            
            if (DEBUG)
                System.out.println(stmt.toString());
            
            ResultSet rs = stmt.executeQuery();
            try
            {
                Field[] allFields = searchRecord.allFields();
                while (rs.next())
                {
                    Record record = newRecord();
                    
                    for (int i = 0; i < allFields.length; i++)
                    {
                        record.setValue(allFields[i], rs.getObject(allFields[i].getName()));
                    }
                    
                    records.add(record);
                }
                
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stmt.close();
        }
        
        return records;
    }
    
    /**
     * Creates SQL for creating the record in a prepared statement
     *
     * @param record	the record to create
     * @return 		the SQL for creating the record
     */
    protected String createInsertSql(Record record)
    {
        
        StringBuffer bufSQL = new StringBuffer(record.dataCount() * 15 + 30);
        bufSQL.append("INSERT INTO ").append(record.recordName()).append("(");
        
        for (Iterator fields = record.keyIterator(); fields.hasNext();)
        {
            bufSQL.append(((Field) fields.next()).getName());
            
            if (fields.hasNext())
                bufSQL.append(", ");
        }
        
        bufSQL.append(") VALUES(");
        
        for (int i = 0; i < record.dataCount(); i++)
        {
            if (i > 0)
                bufSQL.append(",");
            
            bufSQL.append("?");
        }
        
        bufSQL.append(")");
        
        return bufSQL.toString();
    }
    
    /**
     * Creates SQL for updating the record in a prepared statement. Only the set fields are updated.
     *
     * @param record	the record to create
     * @return 		the SQL for updating the record
     */
    protected String createUpdateSql(Record record)
    {
        
        Field[] pkFields = record.primaryKeyFields();
        
        StringBuffer bufSQL = new StringBuffer(record.dataCount() * 15 + pkFields.length * 15 + 30);
        bufSQL.append("UPDATE ").append(record.recordName()).append(" SET ");
        
        boolean hasOneField = false;
        for (Iterator fields = record.keyIterator(); fields.hasNext();)
        {
            Field field = (Field) fields.next();
            
            if (!field.getContraints().isPrimaryKey())
            {
                if (hasOneField)
                    bufSQL.append(", ");
                
                bufSQL.append(field.getName()).append(" = ?");
                
                hasOneField = true;
            }
        }
        
        bufSQL.append(" WHERE ");
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(" AND ");
            
            bufSQL.append(pkFields[i].getName()).append(" = ?");
        }
        
        return bufSQL.toString();
    }
    
    /**
     * Creates SQL for deleting the record in a prepared statement with the primary key as the criteria.
     *
     * @param record	the record to delete
     * @return 		the SQL for deleting the record
     */
    protected String createDeleteSql(Record record)
    {
        Field[] pkFields = record.primaryKeyFields();
        
        StringBuffer bufSQL = new StringBuffer(pkFields.length * 15 + 30);
        bufSQL.append("DELETE FROM ").append(record.recordName()).append(" WHERE ");
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(" AND ");
            
            bufSQL.append(pkFields[i].getName()).append(" = ?");
        }
        
        return bufSQL.toString();
    }
    
    /**
     * Creates SQL for updating a records primary key in a prepared statement.
     *
     * @param record	the record to update
     * @return 		the SQL for updating the record
     */
    protected String createUpdatePkSql(Record oldRecord, Record newRecord)
    {
        
        Field[] pkFields = oldRecord.primaryKeyFields();
        
        StringBuffer bufSQL = new StringBuffer(pkFields.length * 30 + 30);
        bufSQL.append("UPDATE ").append(oldRecord.recordName()).append(" SET ");
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(", ");
            
            bufSQL.append(pkFields[i].getName()).append(" = ?");
            
        }
        
        bufSQL.append(" WHERE ");
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(" AND ");
            
            bufSQL.append(pkFields[i].getName()).append(" = ?");
        }
        
        return bufSQL.toString();
    }
    
    /**
     * Creates SQL for selecting certain fields in the record by the primary key.
     *
     * @param record	set fields in the record are the select list
     * @return 		the SQL for selecting the fields from the DB
     */
    protected String createExistSql(Record record)
    {
        Field[] pkFields = record.primaryKeyFields();
        
        StringBuffer bufSQL = new StringBuffer(record.dataCount() * 15 + pkFields.length * 15 + 30);
        bufSQL.append("SELECT ");
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(", ");
     
            bufSQL.append(pkFields[i].getName());
        }
        
        bufSQL.append(" FROM ").append(record.recordName()).append(" WHERE ");
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(" AND ");
            
            bufSQL.append(pkFields[i].getName()).append(" = ?");
        }
        
        return bufSQL.toString();
    }
    
    /**
     * Creates SQL for completely loading a record by the primary key
     *
     * @param record	the record to load
     * @return 		the SQL for loading the record
     */
    protected String createLoadSql(Record record)
    {
        Field[] fields = record.allFields();
        Field[] pkFields = record.primaryKeyFields();
        
        StringBuffer bufSQL = new StringBuffer(fields.length * 15 + pkFields.length * 15 + 30);
        bufSQL.append("SELECT ");
        
        for (int i = 0; i < fields.length; i++)
        {
            if (i > 0)
                bufSQL.append(", ");
            
            bufSQL.append(fields[i].getName());
        }
        
        bufSQL.append(" FROM ").append(record.recordName()).append(" WHERE ");
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(" AND ");
            
            bufSQL.append(pkFields[i].getName()).append(" = ?");
        }
        
        return bufSQL.toString();
    }
    
    /**
     * Creates SQL for loading all records in the table ordered by primary keys
     *
     * @param record	the record type to be loaded
     * @return 		the SQL for loading the records
     */
    protected String createLoadAllSql(Record record)
    {
        Field[] fields = record.allFields();
        Field[] pkFields = record.primaryKeyFields();
        
        StringBuffer bufSQL = new StringBuffer(fields.length * 15 + pkFields.length * 15 + 30);
        bufSQL.append("SELECT ");
        
        for (int i = 0; i < fields.length; i++)
        {
            if (i > 0)
                bufSQL.append(", ");
            
            bufSQL.append(fields[i].getName());
        }
        
        bufSQL.append(" FROM ").append(record.recordName());
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i == 0)
                bufSQL.append(" ORDER BY ");
            else
                bufSQL.append(", ");
            
            bufSQL.append(pkFields[i].getName());
        }
        
        return bufSQL.toString();
    }
    
    /**
     * Creates SQL for loading all records in the table matching given criteria ordered by primary keys
     *
     * @param record	the record that should be matched
     * @return 		the SQL for matching the record
     */
    protected String createSearchSql(Record record, boolean exact)
    {
        Field[] allFields = record.allFields();
        Field[] pkFields = record.primaryKeyFields();
        
        StringBuffer bufSQL = new StringBuffer(allFields.length * 15 + pkFields.length * 15 + 30);
        bufSQL.append("SELECT ");
        
        for (int i = 0; i < allFields.length; i++)
        {
            if (i > 0)
                bufSQL.append(", ");
            
            bufSQL.append(allFields[i].getName());
        }
        
        bufSQL.append(" FROM ").append(record.recordName()).append(" WHERE 1=1");
        
        for (Iterator searchFields = record.keyIterator(); searchFields.hasNext();)
        {
            Field field = (Field) searchFields.next();
            
            if (record.getValue(field) == null)
                bufSQL.append(" AND ").append(field.getName()).append(" IS NULL");
            else if (!exact && field.getJavaClass() == String.class)
                bufSQL.append(" AND ").append(field.getName()).append(" LIKE ?");
            else
                bufSQL.append(" AND ").append(field.getName()).append(" = ?");
        }
        
        for (int i = 0; i < pkFields.length; i++)
        {
            if (i == 0)
                bufSQL.append(" ORDER BY ");
            else
                bufSQL.append(", ");
            
            bufSQL.append(pkFields[i].getName());
        }
        
        return bufSQL.toString();
    }
    
}
