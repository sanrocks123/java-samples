
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.implementations;

import java.util.Properties;

import com.sanjeev.logging.file.handlers.SpaceDelimitedFileHandler;
import com.sanjeev.logging.file.handlers.XmlBasedFileHandler;
import com.sanjeev.logging.interfaces.ILogFormatFileHandler;
import com.sanjeev.logging.interfaces.ISimpleLogger;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class SimpleLoggerFactory {

    /**
     * @param class1
     * @return
     */
    public static ISimpleLogger getSimpleLogger(Class<?> classz) {
        return new SimpleLoggerImpl(classz);
    }

    /**
     * @param prop
     * @return
     */
    public static ILogFormatFileHandler getFileHandler(Properties prop) {

        String fileFormat = prop.getProperty("log.format");
        if (null == fileFormat) {
            System.out.println("WARNING! - log.format property is missing. Please add it.");
            fileFormat = "SPACES";
        }

        ILogFormatFileHandler fileHandler;
        switch (fileFormat) {
            case "SPACE":
                fileHandler = new SpaceDelimitedFileHandler(prop);
                break;
            case "XML":
                fileHandler = new XmlBasedFileHandler(prop);
                break;
            default:
                fileHandler = new SpaceDelimitedFileHandler(prop);
                break;
        }
        return fileHandler;
    }
}
