/*
 * Created on Nov 4, 2004
 */

package com.wesleyware.daowiz.gui;

import javax.swing.table.TableModel;
import java.util.Vector;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.EventListenerList;

/**
 * Table model for fields in GUI
 *
 * @author Wesley Williams
 *
 */
public class FieldTableModel implements TableModel
{
    
    private Vector data = new Vector();
    protected EventListenerList listenerList = new EventListenerList();
    
    public final static int FIELD_NAME = 0;
    public final static int VARIABLE_NAME = 1;
    public final static int FIELD_TYPE = 2;
    public final static int SIZE = 3;
    public final static int SCALE = 4;
    public final static int IS_NOT_NULL = 5;
    public final static int HAS_DEFAULT_VALUE = 6;
    public final static int IS_PK = 7;
    public final static int IS_FK = 8;
    public final static int COMMENTS = 9;
    
    /** 
     * Creates a new instance of FieldTableModel 
     */
    public FieldTableModel()
    {
        
    }
    
    /**
     * Returns number of columns
     *
     * @return number of columns
     */
    public int getColumnCount()
    {
        return 10;
    }
    
    /**
     * Returns heading of column
     *
     * @param column  column in model
     * @return heading of given column
     */
    public String getColumnName(int column)
    {
        switch(column)
        {
            case FIELD_NAME:return "Field Name";
            case VARIABLE_NAME:return "Variable Name";
            case SIZE: return "Size";
            case SCALE: return "Scale";
            case FIELD_TYPE:return "Type";
            case IS_NOT_NULL:return "Not Null";
            case HAS_DEFAULT_VALUE:return "Default";
            case IS_PK:return "PK";
            case IS_FK:return "FK";
            case COMMENTS:return "Comments";
        }
        
        return "";
    }
    
    /**
     * Returns class of column
     *
     * @param column  column in model
     * @return class of given column
     */
    public Class getColumnClass(int column)
    {
        switch(column)
        {
            case FIELD_NAME:
            case VARIABLE_NAME:
            case COMMENTS:
                return String.class;
            case FIELD_TYPE:
                return FieldTypeBean.class;
            case SIZE:
            case SCALE:
                return Integer.class;
            case IS_NOT_NULL:
            case HAS_DEFAULT_VALUE:
            case IS_PK:
            case IS_FK:
                return Boolean.class;
        }
        
        return Object.class;
    }
    
    /**
     *  Indicates if cell is editable
     *
     * @param   row     row in model
     * @param   column  column in model
     * @return indicator of if field can be edited
     */
    public boolean isCellEditable(int row,int column)
    {
        
        FieldBean field = (FieldBean) data.get(row);
        FieldTypeBean fieldType = field.getFieldType();
        switch(column)
        {
            case IS_NOT_NULL:
                 return !field.isPk();
            case SCALE:
                return (fieldType!=null && fieldType.hasScale() );
            case SIZE:
                return (fieldType!=null && fieldType.hasSize() );
        }
        
        
        return true;
    }
    
    /**
     * Sets value in certain field
     *
     * @param   value   value to be set
     * @param   row     row in model
     * @param   column  column in model
    */
    public void setValueAt(Object value, int row, int column)
    {
        FieldBean field = (FieldBean) data.get(row);
        Object oldValue = getValueAt(row,column);
        
        if( oldValue == value)
        {
            return;
        }
        else if(oldValue!=null)
        {
            if(oldValue.equals(value))return;
        }
        else if(value!=null && value.equals(oldValue))
        {
            return;
        }
        
        switch(column)
        {
            case FIELD_NAME:
                field.setName((String)value);
                break;
            case VARIABLE_NAME:
                field.setVariableName((String)value);
                break;
            case COMMENTS:
                field.setComments((String)value);
                break;
            case SIZE:
                
                if(value != null && ((Integer)value).intValue()<=0)
                {
                    return;
                }
                
                field.setSize((Integer)value);
                break;
            case SCALE:
                
                if(value != null && ((Integer)value).intValue()<0)
                {
                    return;
                }
                 
                field.setScale((Integer)value);
                break;
            case FIELD_TYPE:
                field.setFieldType((FieldTypeBean)value);
                break;
            case IS_NOT_NULL:
                
                field.setNotNull(value==null?false:((Boolean)value).booleanValue());
                break;
            case HAS_DEFAULT_VALUE:
                field.setDefaultValue(value==null?false:((Boolean)value).booleanValue());
                break;
            case IS_PK:
                field.setPk(value==null?false:((Boolean)value).booleanValue());
                break;
            case IS_FK:
                field.setFk(value==null?false:((Boolean)value).booleanValue());
                break;
        }
        
        fireTableChanged(new TableModelEvent(this,row,row,column));
        
        switch(column)
        {
            case FIELD_TYPE:
                adjustBasedOnFieldType(field,field.getFieldType(),row);
                break;
            case FIELD_NAME:
                if(value != null && field.getVariableName().length()==0)
                {
                    field.setVariableName((String)value);
                    fireTableChanged(new TableModelEvent(this,row,row,VARIABLE_NAME));
                }
                break;
            case IS_PK:
                if(field.isPk() && !field.isNotNull())
                {
                    field.setNotNull(true);
                    fireTableChanged(new TableModelEvent(this,row,row,IS_NOT_NULL));
                }
                break;
                
        }
    }
    
    // adjust other values based on the field type chosen
     private void adjustBasedOnFieldType(FieldBean field,FieldTypeBean fieldType, int row)
    {
        
        if(fieldType == null )
        {
            if(field.getScale() != null)
            {
                field.setScale(null);
                fireTableChanged(new TableModelEvent(this,row,row,SCALE));
            }
            
            if(field.getSize() != null)
            {
                field.setSize(null);
                fireTableChanged(new TableModelEvent(this,row,row,SIZE));
            }
        }
        else
        {
            if(!fieldType.hasScale())
            {
                field.setScale(null);
                fireTableChanged(new TableModelEvent(this,row,row,SCALE));
            }
            
            if(!fieldType.hasSize())
            {
                field.setSize(null);
                fireTableChanged(new TableModelEvent(this,row,row,SIZE));
            }
            
        }
    }
    
     /**
      * Getter for certain field value
      *
      * @param   row     row in model
      * @param   column  column in model
      *
      * @return value in field at row and column
      */
    public Object getValueAt( int row, int column)
    {
        FieldBean field = (FieldBean) data.get(row);
        
        switch(column)
        {
            case FIELD_NAME:
                return field.getName();
            case VARIABLE_NAME:
                return field.getVariableName();
            case COMMENTS:
                return field.getComments();
            case FIELD_TYPE:
                return field.getFieldType() ;
            case SIZE:
                return field.getSize();
            case SCALE:
                return field.getScale();
            case IS_NOT_NULL:
                return new Boolean(field.isNotNull());
            case HAS_DEFAULT_VALUE:
                return new Boolean(field.hasDefaultValue());
            case IS_PK:
                return new Boolean(field.isPk());
            case IS_FK:
                return new Boolean(field.isFk());
        }
        
        return null;
    }
    
    /**
     * Clears model and adds new rows
     *
     * @param beans expects FieldBean type
     */
    public void setFields(Vector beans)
    {
        if(data.size()>0)
        {
            data.removeAllElements();
        }
        
        if(beans != null && beans.size()>0)
        {
            for(int i=0;i<beans.size();i++)
            {
                addField((FieldBean)beans.elementAt(i));
            }
        }
        
        this.fireTableChanged(new TableModelEvent(this));
    }
    
    /**
     *  Adds a row
     *
     * @param   bean    object representation of field
     */
    public void addField(FieldBean bean)
    {
        if(bean==null)
            bean = new FieldBean();
        
        data.add(bean);
        
        int lastRow = data.size()-1;
        
        fireTableChanged(new TableModelEvent(this,lastRow ,lastRow,TableModelEvent.ALL_COLUMNS,TableModelEvent.INSERT));
    }
    
    /**
     *  Deletes a row
     *
     * @param   row to delete
     */
    public void deleteField(int row)
    {
        data.remove(row);
        fireTableChanged(new TableModelEvent(this,row ,row, TableModelEvent.ALL_COLUMNS,TableModelEvent.DELETE));
    }
    
    /**
     *  Adds table model listener
     *
     *  @param  l   listener to add
     */
    public void addTableModelListener(TableModelListener l)
    {
        listenerList.add(TableModelListener.class, l);
    }
    
    /**
     *  Removes table model listener
     *
     *  @param  l   listener to remove
     */
    public void removeTableModelListener(TableModelListener l)
    {
        listenerList.remove(TableModelListener.class,l);
    }
    
    /**
     *  Fires table model events
     *
     * @param   e   event to fire
     */
    protected void fireTableChanged(TableModelEvent e)
    {
        Object[] listeners = listenerList.getListenerList();
        
        for (int i = listeners.length-2; i>=0; i-=2)
        {
            if (listeners[i]==TableModelListener.class)
            {
                if (e == null)
                    e = new TableModelEvent(this);
                
                ((TableModelListener)listeners[i+1]).tableChanged(e);
            }
        }
        
    }
    
    /**
     *  Gets row count
     *
     *  @return    row count
     */
    public int getRowCount()
    {
        return data.size();
    }
    
    /**
     *  Gets reference to field values
     *  This value is not cloned. 
     *
     * @return  list of object representation of table
     */
    public Vector getFields()
    {
        return data;
    }
}
