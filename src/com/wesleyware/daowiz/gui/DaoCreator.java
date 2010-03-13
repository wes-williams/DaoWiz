/*
 * Created on Nov 13, 2004
 */

package com.wesleyware.daowiz.gui;

import java.util.Vector;

/**
 * Creates all dao code
 *
 * @author Wesley Williams
 *
 */
public class DaoCreator
{
    // checks to see if the table has a primary key
    private static boolean hasPK(TableBean table)
    {
        Vector fields = table.getFields();
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.get(i);
            
            if(field.isPk())
                return true;
        }
        
        return false;
    }
    
    // checks to see if the table has a foreign keys
    private static boolean hasFK(TableBean table)
    {
        Vector fields = table.getFields();
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.get(i);
            
            if(field.isFk())
                return true;
        }
        
        return false;
    }
    
    // returns the name to be used as the constant value for the field
    private static String getConstantName(FieldBean field)
    {
        return field.getVariableName().toUpperCase();
    }
    
    // returns the name to be used in the setters and getters
    private static String getMethodName(FieldBean field)
    {
        String variableName = field.getVariableName();
        String methodName = null;
        
        if(variableName.length()>0)
        {
            methodName = String.valueOf(variableName.charAt(0)).toUpperCase();
            if(variableName.length()>1)
            {
                methodName += variableName.substring(1);
            }
        }
        
        return methodName;
    }
    
    // returns the name of the data type to be used in setters
    private static String getSetterDataTypeName(FieldBean field)
    {
        FieldTypeBean fieldType = field.getFieldType();
        
        switch(fieldType.getType())
        {
            case FieldTypes.BIGINT:
                return "Long";
            case FieldTypes.BOOLEAN:
                return "Boolean";
            case FieldTypes.CHAR:
                return "String";
            case FieldTypes.DATE:
                return "java.util.Date";
            case FieldTypes.DECIMAL:
                return "java.math.BigDecimal";
            case FieldTypes.DOUBLE:
                return "Double";
            case FieldTypes.FLOAT:
                return "Double";
            case FieldTypes.REAL:
                return "Float";
            case FieldTypes.INT:
                return "Integer";
            case FieldTypes.NUMERIC:
                return "java.math.BigDecimal";
            case FieldTypes.SMALLINT:
                return "Short";
            case FieldTypes.TEXT:
                return "String";
            case FieldTypes.TIME:
                return "java.util.Date";
            case FieldTypes.TIMESTAMP:
                return "java.util.Date";
            case FieldTypes.VARCHAR:
                return "String";
                
        }
        
        return null;
    }
    
    // returns the name of the data type to be used in getters
    private static String getGetterDataTypeName(FieldBean field)
    {
        FieldTypeBean fieldType = field.getFieldType();
        
        switch(fieldType.getType())
        {
            case FieldTypes.BIGINT:
                return "Long";
            case FieldTypes.BOOLEAN:
                return "Boolean";
            case FieldTypes.CHAR:
                return "String";
            case FieldTypes.DATE:
                return "java.sql.Date";
            case FieldTypes.DECIMAL:
                return "java.math.BigDecimal";
            case FieldTypes.DOUBLE:
                return "Double";
            case FieldTypes.FLOAT:
                return "Double";
            case FieldTypes.REAL:
                return "Float";
            case FieldTypes.INT:
                return "Integer";
            case FieldTypes.NUMERIC:
                return "java.math.BigDecimal";
            case FieldTypes.SMALLINT:
                return "Short";
            case FieldTypes.TEXT:
                return "String";
            case FieldTypes.TIME:
                return "java.sql.Time";
            case FieldTypes.TIMESTAMP:
                return "java.sql.Timestamp";
            case FieldTypes.VARCHAR:
                return "String";
                
        }
        
        return null;
    }
    
    // returns the name of the primitive type for the field
    private static String getPrimitiveDataTypeName(FieldBean field)
    {
        FieldTypeBean fieldType = field.getFieldType();
        
        switch(fieldType.getType())
        {
            case FieldTypes.BIGINT:
                return "long";
            case FieldTypes.BOOLEAN:
                return "boolean";
            case FieldTypes.DOUBLE:
                return "double";
            case FieldTypes.FLOAT:
                return "double";
            case FieldTypes.REAL:
                return "float";
            case FieldTypes.INT:
                return "int";
            case FieldTypes.SMALLINT:
                return "short";
        }
        
        return null;
    }
    
    // prints the constraints of the field
    private static String printConstraints(FieldBean field)
    {
        StringBuffer buf = new StringBuffer(50);
        
        buf.append("new Constraints(").append(field.isNotNull()).append(", ");
        buf.append(field.isPk()).append(", ");
        buf.append(field.isFk()).append(", ");
        buf.append("").append(field.hasDefaultValue()).append(")");
        
        return buf.toString();
    }
    
    // prints the definition of the field
    private static String printFieldDefintion(FieldBean field)
    {
        FieldTypeBean fieldType = field.getFieldType();
        
        StringBuffer buf = new StringBuffer(300);
        
        buf.append("   /**\n");
        buf.append("    *").append(field.getComments()).append("\n");
        buf.append("    */\n");
        
        switch(fieldType.getType())
        {
            case FieldTypes.BIGINT:
                buf.append("   public final static BigIntField ").append(getConstantName(field));
                buf.append(" = new BigIntField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.BOOLEAN:
                buf.append("   public final static BooleanField ").append(getConstantName(field));
                buf.append(" = new BooleanField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.CHAR:
                buf.append("   public final static CharField ").append(getConstantName(field));
                buf.append(" = new CharField(\"").append(field.getName()).append("\",");
                buf.append(field.getSize()).append(",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.DATE:
                buf.append("   public final static DateField ").append(getConstantName(field));
                buf.append(" = new DateField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.DECIMAL:
                buf.append("   public final static DecimalField ").append(getConstantName(field));
                buf.append(" = new DecimalField(\"").append(field.getName()).append("\",");
                buf.append(field.getSize()).append(",").append(field.getScale()).append(",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.DOUBLE:
                buf.append("   public final static DoubleField ").append(getConstantName(field));
                buf.append(" = new DoubleField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.FLOAT:
                buf.append("   public final static FloatField ").append(getConstantName(field));
                buf.append(" = new FloatField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.REAL:
                buf.append("   public final static RealField ").append(getConstantName(field));
                buf.append(" = new RealField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.INT:
                buf.append("   public final static IntField ").append(getConstantName(field));
                buf.append(" = new IntField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.NUMERIC:
                buf.append("   public final static NumericField ").append(getConstantName(field));
                buf.append(" = new NumericField(\"").append(field.getName()).append("\",");
                buf.append(field.getSize()).append(",").append(field.getScale()).append(",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.SMALLINT:
                buf.append("   public final static SmallIntField ").append(getConstantName(field));
                buf.append(" = new SmallIntField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.TEXT:
                buf.append("   public final static TextField ").append(getConstantName(field));
                buf.append(" = new TextField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.TIME:
                buf.append("   public final static  TimeField ").append(getConstantName(field));
                buf.append(" = new TimeField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.TIMESTAMP:
                buf.append("   public final static TimeStampField ").append(getConstantName(field));
                buf.append(" = new TimeStampField(\"").append(field.getName()).append("\",");
                buf.append(printConstraints(field)).append(");\n");
                break;
            case FieldTypes.VARCHAR:
                buf.append("   public final static VarCharField ").append(getConstantName(field));
                buf.append(" = new VarCharField(\"").append(field.getName()).append("\",");
                buf.append(field.getSize()).append(",");
                buf.append(printConstraints(field)).append(");\n");
                break;
                
        }
        
        return buf.toString();
    }
    
    // prints methods for supporting fk list and references in base data object
    private static String printFkMethods(TableBean table)
    {
        StringBuffer buf = new StringBuffer(300);
        Vector fields = table.getFields();
        
        
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.elementAt(i);
            
            if(field.isFk())
            {
                String variableName = field.getVariableName();
                
                buf.append("\n   /**\n");
                buf.append("    * This method returns the possiblities for ").append(variableName).append(" given the current state of the object\n");
                buf.append("    *\n");
                buf.append("    * @param   conn   active db connection\n");
                buf.append("    * @return  possible values for ").append(variableName).append("\n");
                buf.append("    * @throws SQLException\n");
                buf.append("    */\n");
                buf.append("   public abstract List ").append(variableName).append("Choices(Connection conn) throws SQLException;\n");
                
            }
        }
        
        buf.append("\n   /**\n");
        buf.append("    * This method returns a list of values possible for a fk field given the state of the object\n");
        buf.append("    * It should use the DAO of the referenced table to generate this list.\n");
        buf.append("    * The fk constraint in each field indicates if anything should be returned by this method\n");
        buf.append("    *\n");
        buf.append("    * @param   conn   active db connection\n");
        buf.append("    * @param field     the field whose possible values will be returned\n");
        buf.append("    * @return list of possible data objects whose values are referenced by this field\n");
        buf.append("    * @throws SQLException\n");
        buf.append("    */\n");
        buf.append("   protected List choices(Connection conn, Field field) throws SQLException\n   {\n");
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.elementAt(i);
            if(field.isFk())
            {
                buf.append("      if(field==").append(getConstantName(field)).append(") return ").append(field.getVariableName()).append("Choices(conn);\n");
            }
        }
        buf.append("      return null;\n   }\n");
        
        
        if(!hasFK(table))
        {
            buf.append("\n   /**\n");
            buf.append("    * This method returns the record referenced by the given field\n");
            buf.append("    * The Data Object of the referenced class should be used to load itself\n");
            buf.append("    *\n");
            buf.append("    * @param conn		an active DB connection\n");
            buf.append("    * @param field     the field whose value references this record\n");
            buf.append("    * @return  the record referenced by this field\n");
            buf.append("    * @throws SQLException");
            buf.append("    */\n");
            buf.append("   protected Record reference(Connection conn, Field field) throws SQLException\n");
            buf.append("   {\n");
            buf.append("      return null;\n");
            buf.append("   }\n");
        }
        
        return buf.toString();
        
    }
    
    // prints methods for supporting fk list and references in extended data object
    private static String printDefaultFkMethods(TableBean table)
    {
        StringBuffer buf = new StringBuffer(300);
        Vector fields = table.getFields();
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.elementAt(i);
            
            if(field.isFk())
            {
                String variableName = field.getVariableName();
                
                buf.append("\n   /**\n");
                buf.append("    * This method returns the possiblities for ").append(variableName).append(" given the current state of the object\n");
                buf.append("    *\n");
                buf.append("    * @param   conn   active db connection\n");
                buf.append("    * @return  possible values for ").append(variableName).append("\n");
                buf.append("    * @throws SQLException\n");
                buf.append("    */\n");
                buf.append("   public List ").append(variableName).append("Choices(Connection conn) throws SQLException\n");
                buf.append("   {\n");
                buf.append("      // put your code here\n");
                buf.append("      return null;\n");
                buf.append("   }\n");
            }
        }
        
        buf.append("\n   /**\n");
        buf.append("    * This method returns the record referenced by the given field\n");
        buf.append("    * The Data Object of the referenced class should be used to load itself\n");
        buf.append("    *\n");
        buf.append("    * @param conn  an active DB connection\n");
        buf.append("    * @param field  the field whose value references this record\n");
        buf.append("    * @return  the record referenced by this field\n");
        buf.append("    * @throws SQLException\n");
        buf.append("    */\n");
        buf.append("   protected Record reference(Connection conn, Field field) throws SQLException\n");
        buf.append("   {\n");
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.elementAt(i);
            
            if(field.isFk())
            {
                String constantName = getConstantName(field);
                String variableName = field.getVariableName();
                
                buf.append("      if(field == ").append(constantName).append(")\n");
                buf.append("         return ").append(variableName).append("Reference(conn);\n");
                
            }
        }
        buf.append("      return null;\n");
        buf.append("   }\n");
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.elementAt(i);
            
            if(field.isFk())
            {
                String constantName = getConstantName(field);
                String variableName = field.getVariableName();
                
                buf.append("\n   /**\n");
                buf.append("    * This method returns the record referenced by ").append(variableName).append("\n");
                buf.append("    *\n");
                buf.append("    * @param conn  an active DB connection\n");
                buf.append("    * @return  the record referenced by ").append(variableName).append("\n");
                buf.append("    * @throws SQLException\n");
                buf.append("    */\n");
                buf.append("   public PUT_RECORD_TYPE_HERE ").append(variableName).append("Reference(Connection conn) throws SQLException\n");
                buf.append("   {\n");
                buf.append("      // put your code here\n");
                buf.append("      return null;\n");
                buf.append("   }\n");
                
            }
        }
        
        return buf.toString();
    }
    
    // prints the setters for the field
    private static String printSetter(FieldBean field)
    {
        String comments = field.getComments();
        String methodName = getMethodName(field);
        String variableName = field.getVariableName();
        String constantName = getConstantName(field);
        String setterDataTypeName = getSetterDataTypeName(field);
        
        StringBuffer buf = new StringBuffer(300);
        
        buf.append("\n   /**\n");
        buf.append("    * setter for ").append(comments).append("\n");
        buf.append("    *\n");
        buf.append("    * @param   value of").append(variableName).append("\n");
        buf.append("    * @return  true if valid\n");
        buf.append("    */\n");
        
        buf.append("   public boolean set").append(methodName);
        buf.append("(").append(setterDataTypeName).append(" ").append(variableName).append(")\n");
        buf.append("   {\n");
        buf.append("      return set(").append(constantName).append(",").append(variableName).append(");\n");
        buf.append("   }\n");
        
        String primitiveName = getPrimitiveDataTypeName(field);
        if(primitiveName != null)
        {
            buf.append("\n   /**\n");
            buf.append("    * primitive value setter for ").append(comments).append("\n");
            buf.append("    *\n");
            buf.append("    * @param   value of").append(variableName).append("\n");
            buf.append("    * @return  true if valid\n");
            buf.append("    */\n");
            
            buf.append("   public boolean set").append(methodName);
            buf.append("(").append(primitiveName).append(" ").append(variableName).append(")\n");
            buf.append("   {\n");
            buf.append("      return set(").append(getConstantName(field)).append(",").append(variableName).append(");\n");
            buf.append("   }\n");
            
        }
        
        return buf.toString();
    }
    
    // prints the getters for the field
    private static String printGetter(FieldBean field)
    {
        String comments = field.getComments();
        String methodName = getMethodName(field);
        String variableName = field.getVariableName();
        String constantName = getConstantName(field);
        String getterDataTypeName = getGetterDataTypeName(field);
        String primitiveDataTypeName = getPrimitiveDataTypeName(field);
        
        StringBuffer buf = new StringBuffer(300);
        
        buf.append("\n   /**\n");
        buf.append("    * getter for ").append(comments).append("\n");
        buf.append("    *\n");
        buf.append("    * @return   value of").append(variableName).append("\n");
        buf.append("    */\n");
        
        buf.append("   public ").append(getterDataTypeName).append(" get").append(methodName);
        
        if(FieldTypes.isPrimitive(field.getFieldType().getType()))
        {
            buf.append("Object");
        }
        
        buf.append("()\n");
        buf.append("   {\n");
        buf.append("      return get(").append(constantName).append(");\n");
        buf.append("   }\n");
        
        
        if(FieldTypes.isPrimitive(field.getFieldType().getType()))
        {
            buf.append("\n   /**\n");
            buf.append("    * primitive getter for ").append(comments).append("\n");
            buf.append("    *\n");
            buf.append("    * @return   value of").append(variableName).append("\n");
            buf.append("    */\n");
            
            buf.append("   public ").append(primitiveDataTypeName).append(" get").append(methodName).append("()\n");
            buf.append("   {\n");
            buf.append("      return getPrimitive(").append(constantName).append(");\n");
            buf.append("   }\n");
        }
        
        return buf.toString();
    }
    
    /**
     *  Creates the code for the base dao
     *
     * @param   table     the definition of the table
     * @return  the code for the base dao
     */
    public static String printBaseDao(TableBean table)
    {
        
        StringBuffer buf = new StringBuffer(3200);
        
        if(table.getPackageName().length()>0)
            buf.append("package ").append(table.getPackageName()).append(";\n\n");
        
        buf.append("import com.wesleyware.daowiz.fieldtypes.*;\n");
        buf.append("import com.wesleyware.daowiz.*;\n");
        buf.append("import java.sql.*;\n");
        buf.append("import java.util.List;");
        
        buf.append("\n/**\n");
        buf.append(" * This is a base dao class the extension should always be used.\n");
        buf.append(" * ").append(table.getNotes()).append("\n");
        buf.append(" */\n");
        buf.append("public abstract class ").append(table.getClassName()).append("DaoBase extends RecordDao\n{\n");
        
        
        buf.append("\n   protected Record newRecord()\n");
        buf.append("   {\n");
        buf.append("      return new ").append(table.getClassName()).append("();\n");
        buf.append("   }\n");
        
         buf.append("\n  /**\n");
        buf.append("   * Checks if record exist. Primary Keys are used a criteria.\n");
        buf.append("   * \n");
        buf.append("   * @param conn	  an active DB connection\n");
        buf.append("   * @param record	  the record to be checked\n");
        buf.append("   * @return	  indicator of if the record exist\n");
        buf.append("   * @throws SQLException\n");
        buf.append("   */\n");
        buf.append("   public boolean exist(Connection conn, ").append(table.getClassName()).append("Base record) throws SQLException\n");
        buf.append("   {\n");
        buf.append("   	return exist(conn, (Record) record);\n");
        buf.append("   }\n");
        
         buf.append("\n  /**\n");
        buf.append("   * Saves the record calling either or create or update depending on what exist(conn,record) returns\n");
        buf.append("   * \n");
        buf.append("   * @param conn	  an active DB connection\n");
        buf.append("   * @param record	  the record to be saved\n");
        buf.append("   * @return	  count of how many were saved\n");
        buf.append("   * @throws SQLException\n");
        buf.append("   */\n");
        buf.append("   public int save(Connection conn, ").append(table.getClassName()).append("Base record) throws SQLException\n");
        buf.append("   {\n");
        buf.append("   	return save(conn, (Record) record);\n");
        buf.append("   }\n");       
        
        buf.append("\n  /**\n");
        buf.append("   * Creates a new DB record\n");
        buf.append("   * \n");
        buf.append("   * @param conn	  an active DB connection\n");
        buf.append("   * @param record	  the record to be created\n");
        buf.append("   * @return	  true if the record was created\n");
        buf.append("   * @throws SQLException\n");
        buf.append("   */\n");
        buf.append("   public boolean create(Connection conn, ").append(table.getClassName()).append("Base record) throws SQLException\n");
        buf.append("   {\n");
        buf.append("   	return create(conn, (Record) record);\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    * Updates a database record. Primary Keys are used as criteria.\n");
        buf.append("    * \n");
        buf.append("    * @param conn       an active DB connection\n");
        buf.append("    * @param record     the record to update\n");
        buf.append("    * @return           the number of records updated. should only be one.\n");
        buf.append("    * @throws SQLException\n");
        buf.append("    */\n");
        buf.append("   public int update(Connection conn, ").append(table.getClassName()).append("Base record) throws SQLException\n");
        buf.append("   {\n");
        buf.append("   	return update(conn, (Record) record);\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    * Deletes a database record. Primary Keys are used a criteria.\n");
        buf.append("    * \n");
        buf.append("    * @param conn	  an active DB connection\n");
        buf.append("    * @param record   the record to delete\n");
        buf.append("    * @return	  the number of records deleted. should only be one.\n");
        buf.append("    * @throws SQLException\n");
        buf.append("    */\n");
        buf.append("   public int delete(Connection conn, ").append(table.getClassName()).append("Base record) throws SQLException\n");
        buf.append("   {\n");
        buf.append("   	return delete(conn, (Record) record);\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    * Changes the primary key of a record\n");
        buf.append("    * \n");
        buf.append("    * @param conn		an active DB connection\n");
        buf.append("    * @param oldRecord	record with old primary key values set\n");
        buf.append("    * @param newRecord	record with new primary key values set\n");
        buf.append("    * @return		the number of records updates. should only be one.\n");
        buf.append("    */\n");
        buf.append("   public int updatePK(Connection conn, ").append(table.getClassName()).append("Base oldRecord, ").append(table.getClassName()).append(" newRecord) throws SQLException\n");
        buf.append("   {\n");
        buf.append("   	return updatePK(conn, (Record) oldRecord, (Record) newRecord);\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    * Fully loads the record with the given primary key.\n");
        buf.append("    *\n");
        buf.append("    * @param conn       an action DB connection\n");
        buf.append("    * @param record     record with primary key values set\n");
        buf.append("    * @return           true if the record was found\n");
        buf.append("    * @throws SQLException\n");
        buf.append("    */\n");
        buf.append("   public boolean load(Connection conn, ").append(table.getClassName()).append("Base record) throws SQLException\n");
        buf.append("   {\n");
        buf.append("   	  return load(conn, (Record) record);\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    * Searches for records matching the given record\n");
        buf.append("    * \n");
        buf.append("    * @param conn		an active DB connection\n");
        buf.append("    * @param searchRecord	the record to be matched\n");
        buf.append("    * @param exact		indicator of if the matches must be exact.\n");
        buf.append("    * @return		list of matching records\n");
        buf.append("    * @throws SQLException\n");
        buf.append("    */\n");
        buf.append("   public List search(Connection conn, ").append(table.getClassName()).append("Base searchRecord, boolean exact) throws SQLException\n");
        buf.append("   {\n");
        buf.append("      return search(conn, (Record) searchRecord, exact);\n");
        buf.append("   }\n");
        
        buf.append("}\n"); // end class
        
        return buf.toString();
    }
    
    /**
     *  Creates the code for the dao extension
     *
     * @param   table     the definition of the table
     * @return  the code for the dao extension
     */
    public static String printExtensionDao(TableBean table)
    {
        StringBuffer buf = new StringBuffer(500);
        
        
        if(table.getPackageName().length()>0)
            buf.append("package ").append(table.getPackageName()).append(";\n\n");
        
        buf.append("import com.wesleyware.daowiz.fieldtypes.*;\n");
        buf.append("import com.wesleyware.daowiz.*;\n");
        
        buf.append("\n/**\n");
        buf.append(" * ").append(table.getNotes()).append("\n");
        buf.append(" */\n");
        buf.append("public class ").append(table.getClassName()).append("Dao extends ").append(table.getClassName()).append("DaoBase\n{\n");
        
        buf.append("}\n"); // end class
        
        
        return buf.toString();
    }
    
    /**
     *  Creates the code for the base data object
     *
     * @param   table     the definition of the table
     * @return  the code for the base data object
     */
    public static String printBaseRecord(TableBean table)
    {
        
        Vector fields = table.getFields();
        
        StringBuffer buf = new StringBuffer(1000+fields.size()*500);
        
        
        if(table.getPackageName().length()>0)
            buf.append("package ").append(table.getPackageName()).append(";\n\n");
        
        buf.append("import com.wesleyware.daowiz.fieldtypes.*;\n");
        buf.append("import com.wesleyware.daowiz.*;\n");
        buf.append("import java.sql.Connection;\n");
        buf.append("import java.sql.SQLException;\n");
        buf.append("import java.util.List;\n");
        
        buf.append("\n/**\n");
        buf.append(" * ").append(table.getNotes()).append("\n");
        buf.append(" */\n");
        buf.append("public abstract class ").append(table.getClassName()).append("Base extends Record implements Cloneable\n{\n");
        
        buf.append("   protected final static ").append(table.getClassName()).append("Dao DAO =  new ").append(table.getClassName()).append("Dao();\n\n");
        
        buf.append("   protected final static String DB_TABLE_NAME = \"").append(table.getTableName()).append("\";\n\n");
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.get(i);
            buf.append(printFieldDefintion(field));
        }
        
        buf.append("\n   private final static Field[] ALL_DB_FIELDS = \n   {\n      ");
        for(int i=0,c=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.get(i);
            if(c>0)
            {
                buf.append(",");
                if(c%4==0)
                {
                    buf.append("\n      ");
                }
            }
            buf.append(getConstantName(field));
            c++;
        }
        buf.append("\n   };\n");
        
        buf.append("\n   private final static Field[] PK_DB_FIELDS = \n   {\n      ");
        for(int i=0,c=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.get(i);
            if(field.isPk())
            {
                if(c>0)
                {
                    buf.append(",");
                    if(c%4==0)
                    {
                        buf.append("\n      ");
                    }
                }
                buf.append(getConstantName(field));
                c++;
            }
        }
        buf.append("\n   };\n");
        
        buf.append("\n   /**\n");
        buf.append("    * No argument constructor\n");
        buf.append("    */\n");
        buf.append("   public ").append(table.getClassName()).append("Base()\n");
        buf.append("   {\n");
        buf.append("   }\n");
        
        if(hasPK(table))
        {
            buf.append("\n   /**\n");
            buf.append("    * PK constructor\n");
            buf.append("    * \n");
            buf.append("    * throws InvalidPkException\n");
            buf.append("    */\n");
            buf.append("   public ").append(table.getClassName()).append("Base(");
            for(int i=0,c=0;i<fields.size();i++)
            {
                FieldBean field = (FieldBean) fields.get(i);
                if(field.isPk())
                {
                    if(c>0)buf.append(",");
                    buf.append(getSetterDataTypeName(field)).append(" ").append(field.getVariableName());
                    c++;
                }
            }
            buf.append(") throws InvalidPkException\n");
            buf.append("   {\n");
            buf.append("      if(");
            for(int i=0,c=0;i<fields.size();i++)
            {
                FieldBean field = (FieldBean) fields.get(i);
                if(field.isPk())
                {
                    if(c>0)
                        buf.append("||\n      ");
                    
                    buf.append("!set").append(getMethodName(field)).append("(").append(field.getVariableName()).append(") ");
                    c++;
                }
            }
            buf.append(")\n         throw new InvalidPkException();\n");
            buf.append("   }\n");
        }
        
        buf.append(printFkMethods(table));
        
        for(int i=0;i<fields.size();i++)
        {
            FieldBean field = (FieldBean) fields.get(i);
            buf.append(printSetter(field));
            buf.append(printGetter(field));
        }
        
        buf.append("\n   /**\n");
        buf.append("    *  Validates the record");
        buf.append("    *  @return table name\n");
        buf.append("    */\n");
        buf.append("   protected String recordName()\n");
        buf.append("   {\n");
        buf.append("      return DB_TABLE_NAME;\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    * Clones the record\n");
        buf.append("    */\n");
        buf.append("   public Object clone()\n");
        buf.append("   {\n");
        buf.append("      ").append(table.getClassName()).append(" clone = new ").append(table.getClassName()).append("();\n");
        buf.append("      copyTo(clone);\n");
        buf.append("      return clone;\n");
        buf.append("   }\n");
        
        
        buf.append("\n   /**\n");
        buf.append("    * Returns all fields for internal use \n");
        buf.append("    * @return all fields\n");
        buf.append("    */\n");
        buf.append("   protected Field[] allFields()\n");
        buf.append("   {\n");
        buf.append("	   return ALL_DB_FIELDS;\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    *  Returns all primary keys fields for internal use\n");
        buf.append("    *  @return primary key fields\n");
        buf.append("    */\n");
        buf.append("   protected Field[] primaryKeyFields()\n");
        buf.append("   {\n");
        buf.append("      return PK_DB_FIELDS;\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    *  Returns all primary keys fields for internal use\n");
        buf.append("    *  @return primary key fields\n");
        buf.append("    */\n");
        buf.append("   protected RecordDao dao()\n");
        buf.append("   {\n");
        buf.append("      return DAO;\n");
        buf.append("   }\n");
        
        buf.append("\n   /**\n");
        buf.append("    *  Shortcut to DAO method\n");
        buf.append("    *  @return all records \n");
        buf.append("    */\n");
        buf.append("   public static List loadAll(Connection conn) throws SQLException\n");
        buf.append("   {\n");
        buf.append("      return DAO.loadAll(conn);\n");
        buf.append("   }\n");
        
        buf.append("}\n"); // end class
        
        
        return buf.toString();
    }
    
    /**
     *  Creates the code for the data object extension
     *
     * @param   table     the definition of the table
     * @return  the code for the data object extension
     */
    public static String printExtensionRecord(TableBean table)
    {
        Vector fields = table.getFields();
        
        StringBuffer buf = new StringBuffer(400);
        
        
        if(table.getPackageName().length()>0)
            buf.append("package ").append(table.getPackageName()).append(";\n\n");
        
        buf.append("import com.wesleyware.daowiz.fieldtypes.*;\n");
        buf.append("import com.wesleyware.daowiz.*;\n");
        buf.append("import java.sql.Connection;\n");
        buf.append("import java.sql.SQLException;\n");
        buf.append("import java.util.List;\n");
        
        
        buf.append("\n/**\n");
        buf.append(" * ").append(table.getNotes()).append("\n");
        buf.append(" */\n");
        buf.append("public class ").append(table.getClassName()).append(" extends ").append(table.getClassName()).append("Base\n{\n");
        
        
        buf.append("\n   /**\n");
        buf.append("    * No argument constructor\n");
        buf.append("    */\n");
        buf.append("   public ").append(table.getClassName()).append("()\n");
        buf.append("   {\n");
        buf.append("   }\n");
        
        if(hasPK(table))
        {
            boolean hasPrimitives = false;
            
            buf.append("\n   /**\n");
            buf.append("    * PK Object constructor\n");
            buf.append("    * \n");
            buf.append("    * throws InvalidPkException\n");
            
            buf.append("    */\n");
            buf.append("   public ").append(table.getClassName()).append("(");
            for(int i=0,c=0;i<fields.size();i++)
            {
                FieldBean field = (FieldBean) fields.get(i);
                if(field.isPk())
                {
                    if(FieldTypes.isPrimitive(field.getFieldType().getType()))
                        hasPrimitives = true;
                    
                    if(c>0)
                        buf.append(",");
                    buf.append(getSetterDataTypeName(field)).append(" ").append(field.getVariableName());
                    c++;
                }
            }
            buf.append(")  throws InvalidPkException\n");
            buf.append("   {\n      super(");
            for(int i=0,c=0;i<fields.size();i++)
            {
                FieldBean field = (FieldBean) fields.get(i);
                if(field.isPk())
                {
                    if(c>0)
                        buf.append(",");
                    buf.append(field.getVariableName());
                    c++;
                }
            }
            buf.append(");\n   }\n");
            
            
            if(hasPrimitives)
            {
                buf.append("\n   /**\n");
                buf.append("    * PK Primitive constructor\n");
                buf.append("    * \n");
                buf.append("    * throws InvalidPkException\n");
                buf.append("    */\n");
                buf.append("   public ").append(table.getClassName()).append("(");
                for(int i=0,c=0;i<fields.size();i++)
                {
                    FieldBean field = (FieldBean) fields.get(i);
                    if(field.isPk())
                    {
                        if(c>0)
                            buf.append(",");
                        String dataTypeName = FieldTypes.isPrimitive(field.getFieldType().getType())?getPrimitiveDataTypeName(field):getSetterDataTypeName(field);
                        buf.append(dataTypeName).append(" ").append(field.getVariableName());
                        c++;
                    }
                }
                buf.append(")  throws InvalidPkException\n");
                buf.append("   {\n      this(");
                for(int i=0,c=0;i<fields.size();i++)
                {
                    FieldBean field = (FieldBean) fields.get(i);
                    if(field.isPk())
                    {
                        if(c>0)
                            buf.append(",");
                        
                        boolean isPrimitive = FieldTypes.isPrimitive(field.getFieldType().getType());
                        
                        if(isPrimitive)
                            buf.append(" new ").append(getSetterDataTypeName(field)).append("(");
                        
                        buf.append(field.getVariableName());
                        
                        if(isPrimitive)
                            buf.append(")");
                        c++;
                    }
                }
                buf.append(");\n   }\n");
            }
        }
        
        if(hasFK(table))
            buf.append(printDefaultFkMethods(table));
        
        buf.append("}\n"); // end class
        
        
        return buf.toString();
    }
}
