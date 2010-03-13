/*
 * Created on Nov 13, 2004
 */

package com.wesleyware.daowiz.gui;

import java.util.Vector;

/**
 * Contains all information regarding field types
 *
 * @author Wesley Williams
 *
 */
public class FieldTypes
{
    public final static int BIGINT = 1;
    public final static int BOOLEAN = 2;
    public final static int CHAR = 3;
    public final static int DATE = 4;
    public final static int DECIMAL = 5;
    public final static int DOUBLE = 6;
    public final static int FLOAT = 7;
    public final static int INT = 8;
    public final static int NUMERIC = 9;
    public final static int SMALLINT = 10;
    public final static int TEXT = 11;
    public final static int TIME = 12;
    public final static int TIMESTAMP =13;
    public final static int VARCHAR = 14;
    public final static int REAL = 15;
    
    /**
     *  Indicates is field type is a java primitive
     *
     * @param type  Type of field
     * @return  indicator of if type is a primitive
     */
    public static boolean isPrimitive(int type)
    {
        switch(type)
        {
            case BIGINT:
            case BOOLEAN:
            case DOUBLE:
            case FLOAT:
            case INT:
            case SMALLINT:
                return true;
        }
        
        return false;
    }
    
    
    /**
     *  Indicates if the field type requires a size
     *
     * @param type  Type of field
     * @return indicator of if size is available for this type
     */
    public static boolean hasSize(int type)
    {
        switch(type)
        {
            case CHAR:
            case VARCHAR:
            case NUMERIC:
            case DECIMAL:
                
                return true;
        }
        
        return false;
    }
    
    /**
     *  Indicator is the field type has scale
     *
     * @param type  Type of field
     * @return  indicator of if scale is avaliable for this type
     */
    public static boolean hasScale(int type)
    {
        switch(type)
        {
            case NUMERIC:
            case DECIMAL:
                return true;
        }
        return false;
    }
    
    /**
     *  List of field type beans
     *
     * @return list of field type beans
     */
    public static Vector getFieldTypeBeans()
    {
        Vector fieldTypes = new Vector();
        
        fieldTypes.add(new FieldTypeBean("BIGINT",BIGINT));
        fieldTypes.add(new FieldTypeBean("BOOLEAN",BOOLEAN));
        fieldTypes.add(new FieldTypeBean("CHAR",CHAR));
        fieldTypes.add(new FieldTypeBean("DATE",DATE));
        fieldTypes.add(new FieldTypeBean("DECIMAL",DECIMAL));
        fieldTypes.add(new FieldTypeBean("DOUBLE",DOUBLE));
        fieldTypes.add(new FieldTypeBean("FLOAT",FLOAT));
        fieldTypes.add(new FieldTypeBean("INT",INT));
        fieldTypes.add(new FieldTypeBean("NUMERIC",NUMERIC));
        fieldTypes.add(new FieldTypeBean("REAL",REAL));
        fieldTypes.add(new FieldTypeBean("SMALLINT",SMALLINT));
        fieldTypes.add(new FieldTypeBean("TEXT",TEXT));
        fieldTypes.add(new FieldTypeBean("TIME",TIME));
        fieldTypes.add(new FieldTypeBean("TIMESTAMP",TIMESTAMP));
        fieldTypes.add(new FieldTypeBean("VARCHAR",VARCHAR));
        
        return fieldTypes;
    }
    
}
