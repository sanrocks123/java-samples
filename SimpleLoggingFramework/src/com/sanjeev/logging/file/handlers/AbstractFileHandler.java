
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.file.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.sanjeev.logging.entities.Message;
import com.sanjeev.logging.interfaces.ILogFormatFileHandler;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public abstract class AbstractFileHandler implements ILogFormatFileHandler {

    private static final Integer MAX_SIZE = 5000;
    private Properties prop;
    private String logFileName;
    private static volatile BlockingQueue<String> blockingQueue;

    public AbstractFileHandler(Properties prop) {
        this.prop = prop;
        logFileName = prop.getProperty("log.filename");

        if (null == logFileName) {
            System.out.println("WARNING! - log.file property is missing. Please add it.");
            logFileName = "defaultLogger.log";
        }

        Path filePath = Paths.get(logFileName);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Error! - " + e.getStackTrace());
            }
        }

    }

    private class AsyncLogConsumerThread implements Runnable {

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            Thread.currentThread().setName("AsyncLogConsumerThread");
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFileName, true)));) {
                String message = "";
                while (null != message) {
                    message = blockingQueue.poll(2, TimeUnit.SECONDS);
                    if (null == message) {
                        System.out.println("No more message found on Queue. So closing down 'AsyncLogConsumerThread' thread.");
                    } else {
                        out.println(message);
                        System.out.println(MessageFormat.format("Message consumed (''{0}'') : ''{1}''", new Object[] { Thread.currentThread().getName(), message }));
                    }
                }
            } catch (IOException e) {
                System.out.println("There was error in read/write of file.");
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                e.printStackTrace();
            }

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sanjeev.logging.interfaces.ILogFormatFileHandler#handle(com.sanjeev.
     * logging.entities.Message)
     */
    @Override
    public void handle(Message msg) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFileName, true)));) {
            if (isSyncLogging()) {
                out.println(formatMessage(msg));
            } else {
                doAsyncLogging(formatMessage(msg));
            }
        } catch (IOException ex) {
            System.out.println("Error! - " + ex.getStackTrace());
        }
    }

    /**
     * @param message
     */
    protected void doAsyncLogging(String message) {

        if (null == blockingQueue) {
            synchronized (Object.class) {
                if (null == blockingQueue) {
                    blockingQueue = new ArrayBlockingQueue<String>(MAX_SIZE);
                    new Thread(new AsyncLogConsumerThread()).start();
                }
            }
        }

        try {
            blockingQueue.put(message);
            System.out.println(MessageFormat.format("Message produced (''{0}'') : '{1}'", new Object[] { Thread.currentThread().getName(), message }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param out
     * @param msg
     * 
     */
    abstract protected String formatMessage(Message msg);

    /**
     * @return
     */
    protected boolean isSyncLogging() {
        String logType = prop.getProperty("log.type");
        if (null == logType) {
            System.out.println("WARNING! - log.type property is missing. please add it.");
            return true;
        }
        return "SYNC".equalsIgnoreCase(logType);
    }

}
