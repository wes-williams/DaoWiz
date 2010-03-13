/*
 * Created on Nov 5, 2004
 */

package com.wesleyware.daowiz.gui;

import java.io.Serializable;

/**
 * Immutable representaion of the db field type
 * Contains only data/methods of interest to the GUI interface
 * This object is serialized as part of the GUI save mechanism
 *
 * @author Wesley Williams
 *
 */
public class FieldTypeBean implements Serializable
{
    private String name;
    private int type;
    
    /** 
     * Creates a new instance of FieldType 
     * 
     * @param   nameIn  Name of field
     * @param   typeIn  Type of field
     */
    public FieldTypeBean(String nameIn, int typeIn)
    {
        name = nameIn;
        type = typeIn;
    }
    
    /**
     *  Name of the field
     *
     * @return name of field
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Type of field as defined in FieldTypes
     *
     * @return type of field
     */
    public int getType()
    {
        return type;
    }
    
    /**
     *  Indicates if field has has a size property
     *
     * @return indicator of if size exist
     */
    public boolean hasSize()
    {
       return FieldTypes.hasSize(type);
    }
    
    /**
     *  Indicates if field has has a scale property
     *
     * @return indicator of if scale exist
     */
    public boolean hasScale()
    {
      return FieldTypes.hasScale(type);
    }
       
    /**
     *  Returns the name of the field
     *
     * @return string representation as name of field
     */
    public String toString()
    {
        return name;
    }
}
