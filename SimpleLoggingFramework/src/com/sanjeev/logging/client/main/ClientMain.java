// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.logging.client.main;

import com.sanjeev.logging.implementations.SimpleLoggerFactory;
import com.sanjeev.logging.interfaces.ISimpleLogger;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class ClientMain {
    ISimpleLogger log = SimpleLoggerFactory.getSimpleLogger(ClientMain.class);

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ClientMain client = new ClientMain();
        LoggerX x = new LoggerX();
        LoggerY y = new LoggerY();

        client.test();
        x.testX();
        y.testY();
    }

    public void test() {
        try {
            throw new Exception("This is throwable exception");
        } catch (Throwable e) {
            log.error(e);
        }
    }
}

class LoggerX {
    ISimpleLogger log = SimpleLoggerFactory.getSimpleLogger(LoggerX.class);

    public void testX() {
        log.debug("This is DEBUG");
    }
}

class LoggerY {
    ISimpleLogger log = SimpleLoggerFactory.getSimpleLogger(LoggerY.class);

    public void testY() {
        log.info("this is INFO");
    }
}
