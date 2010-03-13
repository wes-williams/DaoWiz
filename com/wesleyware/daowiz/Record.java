/*
 * Created on Oct 5, 2004
 *
 */
package com.wesleyware.daowiz;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.math.BigDecimal;
import com.wesleyware.daowiz.fieldtypes.*;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.Serializable;
import java.util.List;
import java.io.PrintStream;

/**
 * This is an object representation of a single DB record. This should be wrapped for
 * each table with fields as static members and sets and gets for each field.
 * Could be overridden to make most sets and gets public so that only fields would be needed
 * Prefered usage is wrapping with sets and gets, so it looks like a normal bean.
 *
 * @author Wesley Williams
 */
public abstract class Record implements Serializable
{
    //	data map for record
    // using linkedhashmap b/c it guarantees iterator order
    private Map data = Collections.synchronizedMap(new LinkedHashMap());
    
    /**
     *   Constructs a record
     */
    public Record()
    {
    }
  
    /**
     * Returns the dao associated with the record
     *
     * @return the dao associated with the record
     */
    protected abstract RecordDao dao();
    
    /**
     * Returns the name of the DB table that this object represents
     *
     * @return	name of the DB table that this object represents
     */
    protected abstract String recordName(); // table name
    
    /**
     * Returns all fields in the record.
     * This method should remain protected, and the returned value should not be mutated.
     * Intended for use with the dao to create SQL statements
     *
     * @return	all of the fields in this table.
     */
    protected abstract Field[] allFields(); // all fields
    
    /**
     * Returns all primary key fields in the record.
     * This method should remain protected, and the returned value should not be mutated.
     * Intended for use with the dao to create SQL statements
     *
     * @return	primary key fields for a record in this table
     */
    protected abstract Field[] primaryKeyFields(); // pk fields
    
    /**
     * This method returns a list of values possible for a fk field given the state of the object
     * It should use the DAO of the referenced table to generate this list.
     * The fk constraint in each field indicates if anything should be returned by this method
     *
     * @param conn		an active DB connection
     * @param field     the field whose possible values will be returned
     * @return list of possible data objects whose values are referenced by this field
     * @throws SQLException
     */
    protected abstract java.util.List choices(Connection conn, Field field) throws SQLException;
    
    /**
     * This method returns the record referenced by the given field
     * The Data Object of the referenced class should be used to load itself
     *
     * @param conn		an active DB connection
     * @param field     the field whose value references this record
     * @return  the record referenced by this field
     * @throws SQLException
     */
    protected abstract Record reference(Connection conn,Field field) throws SQLException;
    
    
      /**
     * Checks if record exist. 
     * Primary Keys are used as criteria.
     *
     * @param conn	an active DB connection
     * @return		indicator of if the record exist
     * @throws SQLException
     */
    public boolean exist(Connection conn) throws SQLException
    {
        return dao().exist(conn,this);
    }
    
    /**
     * Saves the record.
     * Will save by calling either or create or update depending on what exist(conn,record) returns
     *
     * @param conn	an active DB connection
     * @return		count of how many were saved
     * @throws SQLException
     */
    public int save(Connection conn) throws SQLException
    {
       return dao().save(conn,this); 
    }
    
    
    /**
     * Creates a new DB record
     *
     * @param conn	an active DB connection
     * @return		true if the record was created
     * @throws SQLException
     */
    public boolean create(Connection conn) throws SQLException
    {
        return dao().create(conn,this);
    }
    
    /**
     * Updates a database record. 
     * Primary Keys are used as criteria.
     *
     * @param conn	an active DB connection
     * @return		the number of records updated. should only be one.
     * @throws SQLException
     */
    public int update(Connection conn) throws SQLException
    {
        return dao().update(conn,this);
    }
    
    /**
     * Deletes a database record. 
     * Primary Keys are used a criteria.
     *
     * @param conn	an active DB connection
     * @return		the number of records deleted. should only be one.
     * @throws SQLException
     */
    public int delete(Connection conn) throws SQLException
    {
        return dao().delete(conn,this);
    }
    
    /**
     * Changes the primary key of a record
     *
     * @param conn		an active DB connection
     * @param newRecord		record with new primary key values set
     * @return			the number of records updates. should only be one.
     */
    public int updatePK(java.sql.Connection conn,Record newRecord) throws java.sql.SQLException
    {
        return dao().updatePK(conn, this, newRecord);
    }
    
    /**
     *  Fully loads the record with the given primary key.
     *
     * @param conn	an action DB connection
     * @return		true if the record was found
     * @throws SQLException
     */
    public boolean load(java.sql.Connection conn) throws java.sql.SQLException
    {
        return dao().load(conn,this);
    }
    
    /**
     * Searches for records matching the given record
     *
     * @param conn	an active DB connection
     * @param exact	indicator of if the matches must be exact.
     * @return		list of matching records
     * @throws SQLException
     */
    public List search(java.sql.Connection conn, boolean exact) throws java.sql.SQLException
    {
        return dao().search(conn,this,exact);
    }
    
    /**
     *  Removes a field and cooresponding value from record
     *
     * @return true if the field existed
     */
    protected boolean remove(Field field)
    {
        return (data.remove(field) != null);
    }
    
    /**
     * Checks to see if this value has been set.
     *
     * @param field
     * @return indicator of if it exist
     */
    protected boolean exist(Field field)
    {
        return data.containsKey(field);
    }
    
    /**
     * Clears out the record data
     */
    public void clear()
    {
        data.clear();
    }
    
    /**
     * This method should/must remain protected, and the returned value should not be mutated.
     * Intended for use with the dao to create SQL statements
     *
     * @return	iterator of the fields that have been set in the record
     */
    protected final Iterator keyIterator()
    {
        return data.keySet().iterator();
    }
    
    /**
     *  Helper method for cloning record
     *  Clones all mutable values
     *
     * @param dest	the clone
     */
    protected void copyTo(Record dest)
    {
        dest.clear();
        
        for (Iterator targetFields = data.keySet().iterator(); targetFields.hasNext();)
        {
            Field field = (Field) targetFields.next();
            Object value = data.get(field);
            
            if (value instanceof java.sql.Date)
                value = ((java.sql.Date) value).clone();
            else if (value instanceof java.sql.Time)
                value = ((java.sql.Time) value).clone();
            else if (value instanceof java.sql.Timestamp)
                value = ((java.sql.Timestamp) value).clone();
            
            dest.setValue(field, value);
        }
        
    }
    
    
    /**
     *  Prints out all names and values to the given output stream
     *
     * @param   where to write the output
     */
    public void debug(PrintStream out)
    {
        for(java.util.Iterator iter = keyIterator();iter.hasNext();)
        {
            Field field = (Field)iter.next();
            out.print(field.getName());
            out.print(" = ");
            out.println(getValue(field));
        }
    }
    
    /**
     *  Indicates if all values are valid based on constraints
     *
     * @return is valid record
     */
    public boolean isValid()
    {
        Field[] fields = allFields();
        
        for(int i=0;i<fields.length;i++)
        {
            if(!data.containsKey(fields[i]) && !fields[i].getContraints().hasDefaultValue())
                return false;
            
            if(!fields[i].isValid(data.get(fields[i])))
                return false;
        }
        
        return true;
    }
    
    /**
     * The number of fields set in the record
     *
     * @return	the number of fields set in the record
     */
    public int dataCount()
    {
        
        return data.size();
    }
    
    /**
     * Compares the data values to determine if record is an exact match
     *
     * @param recordIn   the value to compare
     * @return	indicator if the record values are equal
     */
    public boolean dataEquals(Record recordIn)
    {
        if (recordIn == null)
            return false;
        if (recordIn == this)
            return true;
        
        return data.equals(recordIn.data);
    }
    
    /**
     * Compares the pk values to determine if record is a match
     * If any pk fields are null the records do not match
     *
     * @param recordIn   the value to compare
     * @return	indicator if the record values are equal
     */
    public boolean equals(Object valueIn)
    {
        if (valueIn == null)
            return false;
        if (valueIn == this)
            return true;
        
        if (valueIn instanceof Record)
        {
            Record recordIn = (Record) valueIn;
            
            if (primaryKeyFields().length != recordIn.primaryKeyFields().length)
                return false;
            
            Field[] pkFields = primaryKeyFields();
            for (int i = 0; i < pkFields.length; i++)
            {
                Object thatValue = recordIn.getValue(pkFields[i]);
                Object thisValue = getValue(pkFields[i]);
                
                if (thisValue == null || thatValue == null)
                    return false;
                
                if (!thisValue.equals(thatValue))
                    return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * Sets the value without any validation.
     * This method should/must remain protected. No validation is done, so the data
     * given might not coorespond to the given field. The field can not be null.
     *
     * @param field		the DB field being set
     * @param value		the value being set
     * @throws NullPointerException
     */
    protected final void setValue(Field field, Object value) throws NullPointerException
    {
        if (field == null)
            throw new NullPointerException();
        
        data.put(field, value);
    }
    
    /**
     *	Set the value of a BigInt field
     *
     * @param field		the DB field being set
     * @param value		the object value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(BigIntField field, Long value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a BigInt field
     *
     * @param field		the DB field being set
     * @param value		the primitive value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(BigIntField field, long value)
    {
        
        if (field != null && field.isValid(value))
        {
            data.put(field, new Long(value));
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Char field
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(CharField field, String value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Date field
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(DateField field, java.util.Date value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, (value == null) ? null : new java.sql.Date(value.getTime()));
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Decimal field
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(DecimalField field, BigDecimal value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Real field
     *
     * @param field		the DB field being set
     * @param value		the object value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(RealField field, Float value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Float field
     *
     * @param field		the DB field being set
     * @param value		the primitive value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(RealField field, float value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, new Float(value));
            return true;
        }
        
        return false;
    }
    
    /**
     * Set the value of a Float field
     * The SQL float field  cooresponds to a java double.
     *
     * @param field		the DB field being set
     * @param value		the object value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(FloatField field, Double value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     * Set the value of a Float field 
     * The SQL float field  cooresponds to a java double.
     *
     * @param field		the DB field being set
     * @param value		the primitive value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(FloatField field, double value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, new Double(value));
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Double field
     *
     * @param field		the DB field being set
     * @param value		the object value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(DoubleField field, Double value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Double field
     *
     * @param field		the DB field being set
     * @param value		the primitive value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(DoubleField field, double value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, new Double(value));
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Int field
     *
     * @param field		the DB field being set
     * @param value		the object value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(IntField field, Integer value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Int field
     *
     * @param field		the DB field being set
     * @param value		the primitive value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(IntField field, int value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, new Integer(value));
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Numeric field
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(NumericField field, BigDecimal value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a SmallInt field
     *
     * @param field		the DB field being set
     * @param value		the object value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(SmallIntField field, Short value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a SmallInt field
     *
     * @param field		the DB field being set
     * @param value		the primitive value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(SmallIntField field, short value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, new Short(value));
            return true;
        }
        
        return false;
    }
    
    /**
     * Set the value of a Time field
     * The given value will not reference the value stored in the record.
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(TimeField field, java.util.Date value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, (value == null) ? null : new java.sql.Time(value.getTime()));
            return true;
        }
        
        return false;
    }
    
    /**
     * Set the value of a Timestamp field
     * The given value will not reference the value stored in the record.
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(TimeStampField field, java.util.Date value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, (value == null) ? null : new java.sql.Timestamp(value.getTime()));
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a VarChar field
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(VarCharField field, String value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Text field
     *
     * @param field		the DB field being set
     * @param value		the value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(TextField field, String value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Boolean field
     *
     * @param field		the DB field being set
     * @param value		the object value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(BooleanField field, Boolean value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, value);
            return true;
        }
        
        return false;
    }
    
    /**
     *	Set the value of a Boolean field
     *
     * @param field		the DB field being set
     * @param value		the primitive value to be set
     * @return			true if the data was valid and set
     */
    protected boolean set(BooleanField field, boolean value)
    {
        if (field != null && field.isValid(value))
        {
            data.put(field, new Boolean(value));
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the value of the given given field.
     * This method should/must remain protected. 
     * It does not clone the value.
     *
     * @param field	the field whose value should be retreived.
     * @return		uncloned value
     */
    protected final Object getValue(Field field)
    {
        return data.get(field);
    }
    
    /**
     *	Returns the object value of a BigInt field
     *
     * @param field	the field whose value should be returned
     * @return	the object value cooresponding to the given field
     */
    protected Long get(BigIntField field)
    {
        return (Long) data.get(field);
    }
    
    /**
     *	Returns the primitve value of a BigInt field
     *
     * @param field	the field whose value should be returned
     * @return	the primitie value cooresponding to the given field
     */
    protected long getPrimitive(BigIntField field)
    {
        return BigIntField.getPrimitive((Long)data.get(field));
    }
    
    /**
     *	Returns the value of a Char field
     *
     * @param field	the field whose value should be returned
     * @return	the value cooresponding to the given field
     */
    protected String get(CharField field)
    {
        return (String) data.get(field);
    }
    
    /**
     * Returns the value of a Date field.
     * This value is cloned.
     *
     * @param field	the field whose value should be returned
     * @return	the cloned value cooresponding to the given field
     */
    protected java.sql.Date get(DateField field)
    {
        java.sql.Date value = (java.sql.Date) data.get(field);
        
        return (value == null) ? null : (java.sql.Date) value.clone();
    }
    
    /**
     *	Returns the value of a BigDecimal field
     *
     * @param field	the field whose value should be returned
     * @return	the value cooresponding to the given field
     */
    protected BigDecimal get(DecimalField field)
    {
        return (BigDecimal) data.get(field);
    }
    
    /**
     *	Returns the object value of a Double field
     *
     * @param field	the field whose value should be returned
     * @return	the object value cooresponding to the given field
     */
    protected Double get(DoubleField field)
    {
        return (Double) data.get(field);
    }
    
    /**
     *	Returns the primitive value of a Double field
     *
     * @param field	the field whose value should be returned
     * @return	the primitive value cooresponding to the given field
     */
    protected double getPrimitive(DoubleField field)
    {
        return DoubleField.getPrimitive((Double)data.get(field));
    }
    
    /**
     * Returns the object value of a Float field 
     * The SQL float field  cooresponds to a java double.
     *
     * @param field	the field whose value should be returned
     * @return	the object value cooresponding to the given field
     */
    protected Double get(FloatField field)
    {
        return (Double) data.get(field);
    }
    
    /**
     * Returns the primitive value of a Float field.
     * The SQL float field  cooresponds to a java double.
     *
     * @param field	the field whose value should be returned
     * @return	the primitive value cooresponding to the given field
     */
    protected double getPrimitive(FloatField field)
    {
        return FloatField.getPrimitive((Double)data.get(field));
    }
    
    /**
     * Returns the object value of a Real field
     *
     * @param field	the field whose value should be returned
     * @return	the object value cooresponding to the given field
     */
    protected Float get(RealField field)
    {
        return (Float) data.get(field);
    }
    
    /**
     * Returns the primitive value of a Real field
     *
     * @param field	the field whose value should be returned
     * @return	the primitive value cooresponding to the given field
     */
    protected float getPrimitive(RealField field)
    {
        return RealField.getPrimitive((Float)data.get(field));
    }
    
    /**
     * Returns the object value of an Integer field
     *
     * @param field	the field whose value should be returned
     * @return	the object value cooresponding to the given field
     */
    protected Integer get(IntField field)
    {
        return (Integer) data.get(field);
    }
    
    /**
     * Returns the primitive value of an Integer field
     *
     * @param field	the field whose value should be returned
     * @return	the primitive value cooresponding to the given field
     */
    protected int getPrimitive(IntField field)
    {
        return IntField.getPrimitive((Integer)data.get(field));
    }
    
    /**
     * Returns the value of a Numeric field
     *
     * @param field	the field whose value should be returned
     * @return	the value cooresponding to the given field
     */
    protected BigDecimal get(NumericField field)
    {
        return (BigDecimal) data.get(field);
    }
    
    /**
     * Returns the object value of a Short field
     *
     * @param field	the field whose value should be returned
     * @return	the object value cooresponding to the given field
     */
    protected Short get(SmallIntField field)
    {
        return (Short) data.get(field);
    }
    
    /**
     * Returns the primitive value of a Short field
     *
     * @param field	the field whose value should be returned
     * @return	the primitive value cooresponding to the given field
     */
    protected short getPrimitive(SmallIntField field)
    {
        return SmallIntField.getPrimitive((Short)data.get(field));
    }
    
    /**
     * Returns the value of a Time field.
     * This value is cloned.
     *
     * @param field	the field whose value should be returned
     * @return	the cloned value cooresponding to the given field
     */
    protected java.sql.Time get(TimeField field)
    {
        java.sql.Time value = (java.sql.Time) data.get(field);
        
        return (value == null) ? null : (java.sql.Time) value.clone();
    }
    
    /**
     * Returns the value of a Timestamp field.
     * This value is cloned.
     *
     * @param field	the field whose value should be returned
     * @return	the cloned value cooresponding to the given field
     */
    protected java.sql.Timestamp get(TimeStampField field)
    {
        java.sql.Timestamp value = (java.sql.Timestamp) data.get(field);
        
        return (value == null) ? null : (java.sql.Timestamp) value.clone();
    }
    
    /**
     * Returns the value of a VarChar field
     *
     * @param field	the field whose value should be returned
     * @return	the value cooresponding to the given field
     */
    protected String get(VarCharField field)
    {
        return (String) data.get(field);
    }
    
    /**
     * Returns the value of a Text field
     *
     * @param field	the field whose value should be returned
     * @return	the value cooresponding to the given field
     */
    protected String get(TextField field)
    {
        return (String) data.get(field);
    }
    
    /**
     * Returns the object value of a Boolean field
     *
     * @param field	the field whose value should be returned
     * @return	the object value cooresponding to the given field
     */
    protected Boolean get(BooleanField field)
    {
        return (Boolean) data.get(field);
    }
    
    /**
     * Returns the primitive value of a Boolean field
     *
     * @param field	the field whose value should be returned
     * @return	the primitive value cooresponding to the given field
     */
    protected boolean getPrimitive(BooleanField field)
    {
        return BooleanField.getPrimitive((Boolean)data.get(field));
    }
    
}
