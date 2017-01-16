
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.file.handlers;

import java.io.IOException;
import java.util.Properties;

import com.sanjeev.logging.entities.Message;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class SpaceDelimitedFileHandler extends AbstractFileHandler {

    /**
     * @param prop
     * @throws IOException
     */

    public SpaceDelimitedFileHandler(Properties prop) {
        super(prop);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sanjeev.logging.file.handlers.AbstractFileHandler#
     * customFileHandlerHook()
     */
    @Override
    protected String formatMessage(Message msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(msg.getDateTime()).append(" ");
        sb.append(msg.getLogLevel()).append(" ");
        sb.append(msg.getClassName()).append(" ");
        sb.append(msg.getMessage()).append("\n");
        return sb.toString();
    }

}
