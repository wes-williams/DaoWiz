/*
 * Created on Nov 4, 2004
 */

package com.wesleyware.daowiz.gui;

import java.io.Serializable;

/**
 * This is a table definition.
 * It contains only data/methods of interest to the GUI interface.
 * This object is serialized as part of the GUI save mechanism
 *
 * @author Wesley Williams
 *
 */
public class FieldBean implements Serializable, Cloneable
{
    private String name = "";
    private String variableName = "";
    private FieldTypeBean fieldType;
    private Integer size;
    private Integer scale;
    private boolean defaultValue;
    private boolean notNull;
    private boolean pk;
    private boolean fk;
    private String comments = "";
    
    /** Creates a new instance of FieldBean */
    public FieldBean()
    {
    }
    
    /**
     * Setter for field name
     *
     * @param   nameIn  name of field
     */
    public void setName(String nameIn)
    {
        name = nameIn==null?"":nameIn.trim();
    }
    
    /**
     *  Getter for field name
     *
     * @return  name of field  
     */
    public String getName()
    {
        return name;
    }
    
    /**
     *  Setter for java variable name
     *
     * @param   variableNameIn  java variable name
     */
    public void setVariableName(String variableNameIn)
    {
        variableName = variableNameIn == null?"":variableNameIn.trim();
    }
    
    /**
     *  Getter for java variable name
     *
     * @return  java variable name
     */
    public String getVariableName()
    {
        return variableName;
    }
    
    /**
     *  Setter for field type
     *
     * @param   fieldTypeIn field type object
     */
    public void setFieldType(FieldTypeBean fieldTypeIn)
    {
        fieldType = fieldTypeIn;
    }
    
    /**
     * Getter for field type
     *
     * @return  field type object
     */
    public FieldTypeBean getFieldType()
    {
        return fieldType;
    }
    
    /**
     * Setter for size
     *
     * @param   sizeIn  size of field
     */
    public void setSize(Integer sizeIn)
    {
        size = sizeIn;
    }
    
    /**
     * Getter for size
     *
     * @return  size of field
     */
    public Integer getSize()
    {
        return size;
    }
    
    /**
     *  Setter for scale
     *
     * @param   scale of field
     */
    public void setScale(Integer scaleIn)
    {
        scale = scaleIn;
    }
    
    /**
     *  Getter for scale
     *
     * @return scale of field
     */
    public Integer getScale()
    {
        return scale;
    }
    
    /**
     *  Setter for indication of default value
     *
     * @param   defaultValueIn  indicator of if default value exist
     */
    public void setDefaultValue(boolean defaultValueIn)
    {
        defaultValue = defaultValueIn;
    }
    
    /**
     *  Indicator of default value
     *
     * @return  indicator of if default value exist
     */
    public boolean hasDefaultValue()
    {
        return defaultValue;
    }
    
    /**
     *  Setter for indicator of not null
     *
     * @param   notNullIn   indicator of if field is not null
     */
    public void setNotNull(boolean notNullIn)
    {
        notNull = notNullIn;
    }
    
    /**
     *  indicator of not null
     *
     * @return indicator of if field is not null
     */
    public boolean isNotNull()
    {
        return notNull;
    }
    
    /**
     *  Setter for indicator of primary key
     *
     * @param   pkIn    indicator of if field is primary key
     */
    public void setPk(boolean pkIn)
    {
        pk = pkIn;
    }
    
    /**
     *  Indicator of primary key
     *
     * @return  indicator of if field is primary key
     */
    public boolean isPk()
    {
        return pk;
    }
    
    /**
     *  Setter for indicator of foreign key
     *
     * @param   fkIn    indicator of if field is forign key
     */
    public void setFk(boolean fkIn)
    {
        fk = fkIn;
    }
    
    /**
     *  indicator of foreign key
     *
     * return indicator of if field is foreign key
     */
    public boolean isFk()
    {
        return fk;
    }
    
    /**
     *  Setter for comments used in javadocs
     *
     * @param   commentsIn  comments about field
     */
    public void setComments(String commentsIn)
    {
        comments = commentsIn;
    }
    
    /**
     *  Getter for notes used in javadocs
     *
     * @param   comments about field
     */
    public String getComments()
    {
        return comments;
    }
    
    /**
     *  indicator of whether field is valid
     *
     * @return  indicator of if field is valid
     */
    public boolean isValid()
    {
        if(name == null || name.length()==0 ||
        variableName == null || variableName.length()==0 ||
        fieldType==null)
            return false;
        
        if( FieldTypes.hasScale(fieldType.getType()))
        {
            if(scale==null ) return false;
        } 
        
        if(FieldTypes.hasSize(fieldType.getType()))
        {
            if(size==null) return false;
        }
        
        return true;
    }


    
}
