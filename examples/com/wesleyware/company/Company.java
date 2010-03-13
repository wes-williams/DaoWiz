package com.wesleyware.company;

import com.wesleyware.daowiz.fieldtypes.*;
import com.wesleyware.daowiz.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * These are companies which make up a larger company
 */
public class Company extends CompanyBase
{

   /**
    * No argument constructor
    */
   public Company()
   {
   }

   /**
    * PK Object constructor
    * 
    * throws InvalidPkException
    */
   public Company(String companyId)  throws InvalidPkException
   {
      super(companyId);
   }
}
