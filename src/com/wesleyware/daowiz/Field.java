/*
 * Created on Oct 5, 2004
 *  
 */
package com.wesleyware.daowiz;

/**
 * This is an immutable object representaion of a DB field. It is used in the 
 * construction of a DB record object.
 * 
 * @author Wesley Williams
 */
public abstract class Field
{
	private String name; // field name
	private Constraints constraints; // field constraints

	/**
	 * Constructs an immutable representation of a DB field
	 * 
	 * @param	nameIn			name of the DB field
	 * @param	constraintsIn	constraints on the DB field
	 */
	public Field(String nameIn, Constraints constraintsIn)
	{
		name = nameIn;
		constraints = constraintsIn;
	}

	/**
	 * Indicates if value is valid for this field. 
         *
	 * @param 	value	the value to check
	 * @return	indicator if the given value is of the correct type and follows the constraints
	 */
	public abstract boolean isValid(Object value); // is valid value?

	/**
         * Indicates the SQL type associated with this field as defined in java.sql.Types
         *
	 * @return 	the corresponding value defined in java.sql.Types
	 */
	public abstract int getSqlType(); // sql type for field

	/**
	 *  Indicates the java class associated with the SQL type 
         *
	 * @return	the java class cooresponding to this field
	 */
	public abstract Class getJavaClass(); // class for field

	/**
	 *  Returns the name of the field in the DB
         *
	 * @return	the name of the field in the DB
	 */
	public String getName()
	{
		return name;
	}

	/**
         * Returns the constraints defined on this field in the DB
	 * 
	 * @return	the constraints defined on this field in the DB
	 */
	public Constraints getContraints()
	{
		return constraints;
	}
	
	/**
	 * Indicates is given object is equal to this field.
         * Fields with matching names are considered equal
	 * 
	 * @param   valueIn  object to check for match
	 * @return  indicator if the given object matches this object
	 */
	public boolean equals(Object valueIn)
	{
		if(valueIn==null)return false;
		if(this == valueIn) return true;
		
		if(valueIn instanceof Field)
		{
			Field fieldValue = (Field) valueIn;
			return  name.equalsIgnoreCase(((Field)fieldValue).getName());
		}
		
		return false;
	}
	

}
