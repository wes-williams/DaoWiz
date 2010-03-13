/*
 * Created on Oct 7, 2004
 *
*/
package com.wesleyware.daowiz.fieldtypes;

import com.wesleyware.daowiz.*;

/**
 * This is an immutable object representaion of a Text field. It is used in the 
 * construction of a DB record object.
 * 
 * @author Wesley Williams
 */
public class TextField extends Field
{
	/**
	 * Constructs an immutable representation of a DB field
	 * 
	 * @param	nameIn			name of the field
	 * @param	constraintsIn	constraints on the field
	 */
	public TextField(String nameIn, Constraints constraintsIn)
	{
		super(nameIn, constraintsIn);
	}

	/**
         * Indicates if value is valid for this field according the constraints.
         *
	 * @param 	value	the value to check
	 * @return	indicator if the given value is of the correct type and follows the constraints
	 */
	public boolean isValid(Object value)
	{
		if (value == null || value instanceof String)
			return isValid((String) value);

		return false;
	}

	/**
         * Indicates if value is valid for this field according the constraints.
	 * 
	 * @param 	value	the value to check
	 * @return	indicator if the given value follows the constraints
	 */
	public boolean isValid(String value)
	{
		if (this.getContraints().isNotNull() && value == null)
			return false;

		return true;
	}

	/**
         * Returns SQL type of this field
         * The returned value cooresponds to those defined in java.sql.Types
         *
	 * @return 	the corresponding value defined in java.sql.Types (VARCHAR)
	 */
	public int getSqlType()
	{
		return java.sql.Types.VARCHAR;
	}

	/**
         * Returns the the java class cooresponding to the SQL type 
	 * 
	 * @return	the java class cooresponding to this field (String)
	 */
	public Class getJavaClass()
	{
		return String.class;
	}

/*
	public static void main(String[] args)
	{
		TextField test1 = new TextField("mytest", new Constraints(true, true, true, true));
		TextField test2 = new TextField("mytest", new Constraints(true, true, true, true));
		TextField test3 = new TextField("mytest2", new Constraints(false, false, false, false));

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

		if (!test1.isValid(new Boolean(true)))
			System.out.println("Test 5 successful");
		else
			System.out.println("Test 5 failed");
		if (test1.isValid("test"))
			System.out.println("Test 6 successful");
		else
			System.out.println("Test 6 failed");
	}
*/
	
}