/*
 * Created on Nov 9, 2004
 */

package com.wesleyware.daowiz.gui;

import java.io.Serializable;
import java.util.Vector;

/**
 * Definition of table
 * Contains only data/methods of interest to the GUI interface
 * This object is serialized as part of the GUI save mechanism
 *
 * @author Wesley Williams
 *
 */
public class TableBean implements Serializable
{
    private String packageName = "";
    private String tableName = "";
    private String className = "";
    private String notes = "";
    private Vector fields;
    
    /** 
     * Creates a new instance of TableBean 
     */
    public TableBean()
    {
    }
    
    /** 
     * Setter for fields
     *
     * @param fieldsIn  should contain only FieldBean objects 
     */
    public void setFields(Vector fieldsIn)
    {
        fields = fieldsIn;
    }
    
    /**
     *  Getter for Fields in table
     *
     *  return the fields defined in this table
     */
    public Vector getFields()
    {
        return fields;
    }
   
    /**
     *  Setter for java package name
     *
     *  @param packageNameIn    name of java package
     */
    public void setPackageName(String packageNameIn)
    {
        packageName = packageNameIn==null?"":packageNameIn.trim();
    }
     
    /**
     *  Getter for java package name
     *
     *  @return name of java package
     */
    public String getPackageName()
    {
        return packageName;
    }
    
    /**
     *  Setter for db table name
     *
     *  @param tableIn  name of db table
     */
    public void setTableName(String tableNameIn)
    {
        tableName = tableNameIn==null?"":tableNameIn.trim();
    }
    
    /**
     *  Getter for db table name
     *
     *  @return  name of db table
     */
    public String getTableName()
    {
        return tableName;
    }
    
     /**
     *  Setter for java class name
     *
     *  @param classNameIn  name of java class
     */
    public void setClassName(String classNameIn)
    {
        className = classNameIn==null?"":classNameIn.trim();
    }
    
    /**
     *  Getter for java class name
     *
     *  @return  name of java class
     */
    public String getClassName()
    {
        return className;
    }
    
     /**
     *  Setter for notes included in javadocs
     *
     *  @param notesIn  notes included in javadocs
     */
    public void setNotes(String notesIn)
    {
        notes = notesIn==null?"":notesIn.trim();
    }
    
    /**
     *  Getter for notes included in javadocs
     *
     *  @return notes included in javadocs
     */
    public String getNotes()
    {
        return notes;
    }
    
    /**
     * validates if the data in this table table definition is valid
     * 
     * @return indicator of whether the definition is valid
     */
    public boolean isValid()
    {
        if(tableName.length()==0 && className.length()==0 || fields==null || fields.size()==0)
        {
            return false;
        }
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.get(i);
            
            if(!field.isValid())
                return false;
        }
        
        return true;
    }
  

}
