
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

@XmlRootElement(name = "entry")
@XmlType(propOrder = { "dateTime", "logLevel", "className", "message" })
public class Message implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String dateTime;
    private String logLevel;
    private String className;
    private Object message;

    /**
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime
     *            the dateTime to set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the logLevel
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * @param logLevel
     *            the logLevel to set
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className
     *            the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the message
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(Object message) {
        this.message = message;
    }

}
