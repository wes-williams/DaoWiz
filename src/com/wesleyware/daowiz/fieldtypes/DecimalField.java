/*
 * Created on Oct 7, 2004
 *
 */
package com.wesleyware.daowiz.fieldtypes;

import com.wesleyware.daowiz.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This is an immutable object representaion of a Decimal field. It is used in the 
 * construction of a DB record object.
 * 
 * @author Wesley Williams
 */
public class DecimalField extends Field
{
        private int size;
	private int scale;

	/**
	 * Constructs an immutable representation of a DB field
	 * 
	 * @param	nameIn			name of the field
	 * @param	scaleIn 		scale of the field
	 * @param	constraintsIn	constraints on the field
	 */
	public DecimalField(String nameIn, int sizeIn, int scaleIn, Constraints constraintsIn)
	{
		super(nameIn, constraintsIn);
                size = sizeIn;
		scale = scaleIn;
	}

	/**
         * Indicates if value is valid for this field according the constraints. 
         *
	 * @param 	value	the value to check
	 * @return	indicator if the given value is of the correct type and follows the constraints
	 */
	public boolean isValid(Object value)
	{
		if (value == null || value instanceof BigDecimal)
			return isValid((BigDecimal) value);

		return false;
	}

	/**
          * Indicates if value is valid for this field according the constraints. 
         *
	 * @param 	value	the value to check
	 * @return	indicator if the given value follows the constraints
	 */
	public boolean isValid(BigDecimal value)
	{
		if (this.getContraints().isNotNull() && value == null)
			return false;

		if (value == null)
			return true;

 		BigDecimal scaledValue = value.setScale(scale, BigDecimal.ROUND_DOWN);

		if(scaledValue.compareTo(value) != 0)
                    return false;
             
                if(scaledValue.setScale(0,BigDecimal.ROUND_DOWN).unscaledValue().compareTo(BigInteger.valueOf(10).pow(size-scale)) >= 0)
                    return false;
              
                return true;
	}

	/**
	 * Returns SQL type of this field
         * The returned value cooresponds to those defined in java.sql.Types
         *
	 * @return	the java class cooresponding to this field (DECIMAL)
	 */
	public int getSqlType()
	{
		return java.sql.Types.DECIMAL;
	}

	/**
	 * Returns the java class cooresponding to the SQL type
         *
	 * @return	the java class cooresponding to this field (BigDecimal)
	 */
	public Class getJavaClass()
	{
		return BigDecimal.class;
	}

	/**
	 * Returns the max scaled allows by this field 
         *
	 * @return	scale of the field
	 */
	public int getScale()
	{
		return scale;
	}
        
        /**
	 * Returns the max size allowed by this field 
         *
	 * @return unscaled size of the field
	 */
	public int getSize()
	{
		return size;
	}

/*
	public static void main(String[] args)
	{
		DecimalField test1 = new DecimalField("mytest",5, 2, new Constraints(true, true, true, true));
		DecimalField test2 = new DecimalField("mytest",5, 2, new Constraints(true, true, true, true));
		DecimalField test3 = new DecimalField("mytest2",5, 3, new Constraints(false, false, false, false));

		if (test1.equals(test2))
			System.out.println("Test 1 successful");
		else
			System.out.println("Test 1 failed");

		if (!test1.equals(test3))
			System.out.println("Test 2 successful");
		else
			System.out.println("Test 2 failed");

		if (!test1.equals(new String("mytest")))
			System.out.println("Test 3 successful");
		else
			System.out.println("Test 3 failed");

		if (!test1.isValid(null))
			System.out.println("Test 4 successful");
		else
			System.out.println("Test 4 failed");

		if (test1.isValid(new BigDecimal("5.05")))
			System.out.println("Test 5 successful");
		else
			System.out.println("Test 5 failed");

		if (!test1.isValid("test"))
			System.out.println("Test 6 successful");
		else
			System.out.println("Test 6 failed");

		if (!test1.isValid(new BigDecimal("5.055")))
			System.out.println("Test 7 successful");
		else
			System.out.println("Test 7 failed");

		if (test1.isValid(new BigDecimal("5.05000")))
			System.out.println("Test 8 successful");
		else
			System.out.println("Test 8 failed");
                if (!test1.isValid(new BigDecimal("1000.01")))
			System.out.println("Test 9 successful");
		else
			System.out.println("Test 9 failed");
                
                 if (test1.isValid(new BigDecimal("100.99")))
			System.out.println("Test 10 successful");
		else
			System.out.println("Test 10 failed");
	}
	*/
}
