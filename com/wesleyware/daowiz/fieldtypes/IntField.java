/*
 * Created on Oct 7, 2004
 *
 */
package com.wesleyware.daowiz.fieldtypes;

import com.wesleyware.daowiz.*;

/**
 * This is an immutable object representaion of an Integer field. It is used in the
 * construction of a DB record object.
 *
 * @author Wesley Williams
 */
public class IntField extends Field
{
    /**
     * Constructs an immutable representation of a DB field
     *
     * @param	nameIn			name of the field
     * @param	constraintsIn	constraints on the field
     */
    public IntField(String nameIn, Constraints constraintsIn)
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
        if (value == null || value instanceof Integer)
            return isValid((Integer) value);
        
        return false;
    }
    
    /**
     * Indicates if value is valid for this field according the constraints.
     *
     * @param 	value	the value to check
     * @return	indicator if the given value follows the constraints
     */
    public boolean isValid(Integer value)
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
    public boolean isValid(int value)
    {
        return isValid(new Integer(value));
    }
    
    /**
     * Returns SQL type of this field
     * The returned value cooresponds to those defined in java.sql.Types
     *
     * @return	the java class cooresponding to this field (INTEGER)
     */
    public int getSqlType()
    {
        return java.sql.Types.INTEGER;
    }
    
    /**
     * Returns the the java class cooresponding to the SQL type
     *
     * @return	the java class cooresponding to this field (Integer)
     */
    public Class getJavaClass()
    {
        return Integer.class;
    }
    
    /**
     * Returns the primitive type of this value.
     * If the given value is null the returned value is 0.
     *
     * @return  the primitive value or 0 if null
     */
    public static int getPrimitive(Integer value)
    {
        return (value == null) ? 0 : value.intValue();
    }
    
/*
        public static void main(String[] args)
        {
                IntField test1 = new IntField("mytest", new Constraints(true, true, true, true));
                IntField test2 = new IntField("mytest", new Constraints(true, true, true, true));
                IntField test3 = new IntField("mytest2", new Constraints(false, false, false, false));
 
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
 
                if (test1.isValid(new Integer(6)))
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