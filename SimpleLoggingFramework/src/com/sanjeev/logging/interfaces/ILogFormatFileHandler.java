
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.interfaces;

import com.sanjeev.logging.entities.Message;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public interface ILogFormatFileHandler {

    /**
     * @param msg
     */
    public void handle(Message msg);

}
