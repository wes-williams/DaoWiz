/*
 * Created on Oct 5, 2004
 *
 */
package com.wesleyware.daowiz;


/**
 * This is an immutable object representaion of DB constraints.  
 * It is used in the construction of a DB field object.
 * 
 * @author Wesley Williams
 * 
 */
public class Constraints
{
	private boolean notNull; // is not null?
	private boolean primaryKey; // is PK?
	private boolean foreignKey; // is FK?
	private boolean defaultValue; // has default?

	/**
	 * Constructs an immutable representation of DB field constraints. 
	 * 
	 * @param notNullIn		indicator of if the DB field is not null 
	 * @param primaryKeyIn		indicator of if the DB field is or is part of the primary key
	 * @param foreignKeyIn		indicator of if the DB field is or is part of a foreign key
	 * @param defaultValueIn	indicator of if the DB field has a default value
	 */
	public Constraints(boolean notNullIn, boolean primaryKeyIn, boolean foreignKeyIn, boolean defaultValueIn)
	{
		notNull = notNullIn;
		primaryKey = primaryKeyIn;
		foreignKey = foreignKeyIn;
		defaultValue = defaultValueIn;
	}

	/**
	 * Indicates of if the DB field allows null values  
         *
	 * @return	indicator of if the DB field is not null
	 */
	public boolean isNotNull()
	{
		return notNull;
	}

	/**
         * Indicates is the field is part of the primary key.
	 * 
	 * @return 	indicator of if the DB field is or is part of the primary key
	 */
	public boolean isPrimaryKey()
	{
		return primaryKey;
	}

	/**
	 * Indicates if the field is or is part of a foreign key/ 
         *
	 * @return 	indicator of if the DB field is or is part of a foreign key
	 */
	public boolean isForeignKey()
	{
		return foreignKey;
	}

	/**
	 * Indicates is the field has a default value 
         *
	 * @return	indicator of if the DB field has a default value
	 */
	public boolean hasDefaultValue()
	{
		return defaultValue;
	}

	/**
         * Indicates is this given object is equal to this field
	 * 
	 * @return indicator of if the given object has the same constraints
	 */
	public boolean equals(Object value)
	{
		if (value == null)
			return false;
		if (value == this)
			return true;

		if (value instanceof Constraints)
		{
			Constraints constraintValue = (Constraints) value;
			return notNull == constraintValue.isNotNull()
				&& primaryKey == constraintValue.isPrimaryKey()
				&& foreignKey == constraintValue.isForeignKey()
				&& defaultValue == constraintValue.hasDefaultValue();
		}

		return false;
	}

/*
	public static void main(String[] args)
	{
		Constraints test1 = new Constraints(true, true, true, true);
		Constraints test2 = new Constraints(true, true, true, true);

		if (!test1.equals(test2))
			System.out.println("Test 1 failed");
		else
			System.out.println("Test 1 succesful");

		if (test1.equals(null))
			System.out.println("test 2 failed");
		else
			System.out.println("Test 2 succesful");

		if (test1.equals(new String("Test")))
			System.out.println("test 3 failed");
		else
			System.out.println("Test 3 succesful");
	}
*/

}
