/*
 * Created on Nov 12, 2004
 */

package com.wesleyware.daowiz;

/**
 * Exception to be thrown when parameters used in record constructor are invalid.
 * 
 * @author Wesley Williams
 * 
 */
public class InvalidPkException extends Exception
{
    
    /**
     *  Constructs exception with default message
     */
    public InvalidPkException()
    {
        this("Invalid Primary Key");
    }
    
    /**
     *  Constructs exception with specified message
     *
     * @param   message    message to be included in the exception
     */
    public InvalidPkException(String message)
    {
        super("InvalidPkException: " + message);
    }
}
