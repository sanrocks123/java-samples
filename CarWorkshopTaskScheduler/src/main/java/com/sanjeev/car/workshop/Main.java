
package com.sanjeev.car.workshop;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class Main {

    private static final XLogger log = XLoggerFactory.getXLogger(SchedulerRunnable.class);

    public static void main(String[] args) {
        log.entry("Main :: main");

        Executor executor = new Executor();
        executor.start();

        log.exit("Main :: main");
    }
}
