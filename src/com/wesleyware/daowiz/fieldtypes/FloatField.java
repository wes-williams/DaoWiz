/*
 * Created on Oct 7, 2004
 *
 */
package com.wesleyware.daowiz.fieldtypes;

import com.wesleyware.daowiz.*;

/**
 * This is an immutable object representaion of a Float field. It is used in the
 * construction of a DB record object.
 *
 * @author Wesley Williams
 */
public class FloatField extends Field
{
    /**
     * Constructs an immutable representation of a DB field
     *
     * @param	nameIn			name of the field
     * @param	constraintsIn	constraints on the field
     */
    public FloatField(String nameIn, Constraints constraintsIn)
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
        if (value == null || value instanceof Double)
            return isValid((Double) value);
        
        return false;
    }
    
    /**
     * Indicates if value is valid for this field according the constraints.
     *
     * @param 	value	the value to check
     * @return	indicator if the given value follows the constraints
     */
    public boolean isValid(Double value)
    {
        if (this.getContraints().isNotNull() && value == null)
            return false;
        
        return true;
    }
    
    /**
     * Indicates if value is valid for this field according the constraints.
     *
     * @param 	value	the value to check
     * @return	indicator if the given value follows the constraints
     */
    public boolean isValid(double value)
    {
        return isValid(new Double(value));
    }
    
    /**
     * Returns SQL type of this field
     * The returned value cooresponds to those defined in java.sql.Types
     *
     * @return	the java class cooresponding to this field (DOUBLE)
     */
    public int getSqlType()
    {
        return java.sql.Types.FLOAT;
    }
    
    /**
     * Returns the the java class cooresponding to the SQL type
     *
     * @return	the java class cooresponding to this field (Double)
     */
    public Class getJavaClass()
    {
        return Double.class;
    }
    
    /**
     * Returns the primitive type of this value.
     * If the given value is null the returned value is 0.
     *
     * @return  the primitive value or 0 if null
     */
    public static double getPrimitive(Double value)
    {
        return (value == null) ? 0 : value.doubleValue();
    }
    
        /*
         
        public static void main(String[] args)
        {
                FloatField test1 = new FloatField("mytest", new Constraints(true, true, true, true));
                FloatField test2 = new FloatField("mytest", new Constraints(true, true, true, true));
                FloatField test3 = new FloatField("mytest2", new Constraints(false, false, false, false));
         
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
         
                if (test1.isValid(new Double(6)))
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