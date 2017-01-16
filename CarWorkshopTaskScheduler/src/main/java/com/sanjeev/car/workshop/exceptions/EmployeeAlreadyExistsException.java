package com.sanjeev.car.workshop.exceptions;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class EmployeeAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmployeeAlreadyExistsException(String paramString) {
        super(paramString);
    }

}
