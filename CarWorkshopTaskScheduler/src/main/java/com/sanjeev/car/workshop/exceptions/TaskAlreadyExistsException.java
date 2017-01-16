package com.sanjeev.car.workshop.exceptions;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class TaskAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TaskAlreadyExistsException(String paramString) {
        super(paramString);
    }

}
