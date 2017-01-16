
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.go.euro.exceptions;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class MissingArgumentException extends BaseException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param string
     */
    public MissingArgumentException(String message) {
        super(message);
    }

}
