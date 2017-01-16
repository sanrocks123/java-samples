
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.entities;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public enum LogLevels {

    INFO("INFO"), DEBUG("DEBUG"), ERROR("ERROR");
    private String logLevel;

    LogLevels(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getValue() {
        return this.logLevel;
    }

}
