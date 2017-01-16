
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.go.euro.exceptions;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class BaseException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String message;

    /**
     * 
     */
    public BaseException(String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
