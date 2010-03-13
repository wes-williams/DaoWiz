/*
 * Created on Oct 7, 2004
 *
*/
package com.wesleyware.daowiz.fieldtypes;

import com.wesleyware.daowiz.*;

/**
 * This is an immutable object representaion of a VarChar field. It is used in the 
 * construction of a DB record object.
 * 
 * @author Wesley Williams
 */
public class VarCharField extends Field
{
	private int size;

	/**
	 * Constructs an immutable representation of a DB field
	 * 
	 * @param	nameIn			name of the field
	 * @param	sizeIn			length of the field
	 * @param	constraintsIn	constraints on the field
	 */
	public VarCharField(String nameIn, int sizeIn, Constraints constraintsIn)
	{
		super(nameIn, constraintsIn);
		size = sizeIn;
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

		if (value == null)
			return true;

		return (((String) value).length() <= size);
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

	/**
         * The max length of the field
         *
	 * @return	the length of the field
	 */
	public int getSize()
	{
		return size;
	}

/*
	public static void main(String[] args)
	{
		VarCharField test1 = new VarCharField("mytest", 1, new Constraints(true, true, true, true));
		VarCharField test2 = new VarCharField("mytest", 1, new Constraints(true, true, true, true));
		VarCharField test3 = new VarCharField("mytest2", 2, new Constraints(false, false, false, false));

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

		if (!test1.isValid("test"))
			System.out.println("Test 5 successful");
		else
			System.out.println("Test 5 failed");

		if (!test1.isValid("test"))
			System.out.println("Test 6 successful");
		else
			System.out.println("Test 6 failed");

		if (test1.isValid("t"))
			System.out.println("Test 7 successful");
		else
			System.out.println("Test 7 failed");

		if (!test1.isValid(new Long(3)))
			System.out.println("Test 8 successful");
		else
			System.out.println("Test 8 failed");
	}
*/
}