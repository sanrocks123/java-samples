
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.implementations;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.sanjeev.logging.entities.LogLevels;
import com.sanjeev.logging.entities.Message;
import com.sanjeev.logging.interfaces.ILogFormatFileHandler;
import com.sanjeev.logging.interfaces.ISimpleLogger;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class SimpleLoggerImpl implements ISimpleLogger {

    private Properties prop;
    private String className;
    private ILogFormatFileHandler fileHandler;
    private Calendar cal = Calendar.getInstance();

    /**
     * 
     */
    public SimpleLoggerImpl(Class<?> classz) {
        try {
            prop = new Properties();
            prop.load(SimpleLoggerImpl.class.getResourceAsStream("/SimpleLogger.properties"));
            className = classz.getName();
            fileHandler = SimpleLoggerFactory.getFileHandler(prop);
        } catch (IOException ex) {
            System.out.println("WARNING! SimpleLogger.properties file is missing on classpath. Please add it.");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sanjeev.logging.interfaces.ISimpleLogger#info()
     */
    @Override
    public void info(Object message) {
        if (isEnabled(LogLevels.INFO)) {
            buildMessage(LogLevels.INFO, message);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sanjeev.logging.interfaces.ISimpleLogger#debug()
     */
    @Override
    public void debug(Object message) {
        if (isEnabled(LogLevels.DEBUG)) {
            buildMessage(LogLevels.DEBUG, message);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sanjeev.logging.interfaces.ISimpleLogger#error()
     */
    @Override
    public void error(Object message) {
        if (isEnabled(LogLevels.ERROR)) {
            buildMessage(LogLevels.ERROR, message);
        }
    }

    /**
     * @param logLevel
     * @param message
     */
    protected void buildMessage(LogLevels logLevel, Object message) {
        Message msg = new Message();
        msg.setDateTime(getFormattedDate(cal.getTime()));
        msg.setLogLevel(logLevel.getValue());
        msg.setClassName(className);
        if (message instanceof Throwable) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ((Throwable) message).printStackTrace(pw);
            message = sw.toString();
        }
        msg.setMessage(message);
        fileHandler.handle(msg);
    }

    /**
     * @param time
     * @return
     */
    protected String getFormattedDate(Date dateTime) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss','SSS").format(dateTime);
    }

    protected boolean isEnabled(LogLevels logLevel) {
        if (null == prop.getProperty("log.level")) {
            System.out.println("WARNING! - log.level property is missing. Please add it.");
            return Boolean.FALSE;
        }
        return logLevel.getValue().equalsIgnoreCase(prop.getProperty("log.level"));
    }

}
