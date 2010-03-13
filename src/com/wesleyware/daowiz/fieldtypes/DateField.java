/*
 * Created on Oct 7, 2004
 *
 */
package com.wesleyware.daowiz.fieldtypes;

import com.wesleyware.daowiz.*;

/**
 * This is an immutable object representaion of a Date field. It is used in the 
 * construction of a DB record object.
 * 
 * @author Wesley Williams
 */
public class DateField extends Field
{
	/**
	 * Constructs an immutable representation of a DB field
	 * 
	 * @param	nameIn			name of the field
	 * @param	constraintsIn	constraints on the field
	 */
	public DateField(String nameIn, Constraints constraintsIn)
	{
		super(nameIn, constraintsIn);
	}

	/**
         * Indicates if the value is valid acoording to the constraints.
         *
	 * @param 	value	the value to check
	 * @return	indicator if the given value is of the correct type and follows the constraints
	 */
	public boolean isValid(Object value)
	{
		if (value == null || value instanceof java.util.Date)
			return isValid((java.util.Date) value);

		return false;
	}

	/**
         * Indicates if the value is valid acoording to the constraints.
         *
	 * @param 	value	the value to check
	 * @return	indicator if the given value follows the constraints
	 */
	public boolean isValid(java.util.Date value)
	{
		if (this.getContraints().isNotNull() && value == null)
			return false;

		return true;
	}

	/**
         * Returns SQL type of this field
         * The returned value cooresponds to those defined in java.sql.Types
	 * 
	 * @return	the java class cooresponding to this field (DATE)
	 */
	public int getSqlType()
	{
		return java.sql.Types.DATE;
	}

	/**
	 * Returns the java class cooresponding to the SQL type 
         *
	 * @return	the java class cooresponding to this field (Date)
	 */
	public Class getJavaClass()
	{
		return java.sql.Date.class;
	}

/*	
	public static void main(String[] args)
	{
		DateField test1 = new DateField("mytest", new Constraints(true, true, true, true));
		DateField test2 = new DateField("mytest", new Constraints(true, true, true, true));
		DateField test3 = new DateField("mytest2", new Constraints(false, false, false, false));

		if (test1.equals(test2))
			System.out.println("Test 1 successfull");
		else
			System.out.println("Test 1 failed");

		if (!test1.equals(test3))
			System.out.println("Test 2 successfull");
		else
			System.out.println("Test 2 failed");

		if (!test1.equals(new String("mytest")))
			System.out.println("Test 3 successfull");
		else
			System.out.println("Test 3 failed");

		if (!test1.isValid(null))
			System.out.println("Test 4 successful");
		else
			System.out.println("Test 4 failed");

		if (test1.isValid(new Date(System.currentTimeMillis())))
			System.out.println("Test 5 successful");
		else
			System.out.println("Test 5 failed");
		if (!test1.isValid("test"))
			System.out.println("Test 6 successful");
		else
			System.out.println("Test 6 failed");
	}
	
	*/
}