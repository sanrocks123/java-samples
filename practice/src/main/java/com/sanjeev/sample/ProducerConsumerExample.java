/** SYMANTEC: Copyright 2015 Symantec Corporation. All rights reserved.
 * THIS SOFTWARE CONTAINS CONFIDENTIAL INFORMATION AND TRADE SECRETS OF
 * SYMANTEC CORPORATION.USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED
 * WITHOUT THE PRIOR EXPRESS WRITTEN PERMISSION OF SYMANTEC CORPORATION.
 * The Licensed Software and Documentation are deemed to be commercial
 * computer software as defined in FAR 12.212 and subject to restricted
 * rights as defined in FAR Section 52.227-19 "Commercial Computer Software
 * - Restricted Rights" and DFARS 227.7202, "Rights in Commercial Computer
 * Software or Commercial Computer Software Documentation", as applicable,
 * and any successor regulations.  Any use, modification, reproduction
 * release, performance, display or disclosure of the Licensed Software
 * and Documentation by the U.S. Government shall be solely in accordance
 * with the terms of this Agreement.
 */
/********************************************************************
 * File Name:    HelloWorld.java
 *
 * Date Created: Aug 18, 2015
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2015 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.sample;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class ProducerConsumerExample {

    Integer count;
    Lock lock;
    Condition condition;

    ProducerConsumerExample() {
        count = 0;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    class Producer implements Runnable {

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {

            final long sleepTime = 3;
            while (true) {
                lock.lock();
                try {
                    if (count == 0) {
                        count++;
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Notified!");
                        condition.signal();
                    }

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {

            while (true) {
                lock.lock();
                try {
                    if (count == 0) {
                        try {
                            System.out.println("Waiting for producer...");
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("consumed count - " + count);
                    try {
                        System.out.println("ConsumerSleeping for few secs...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ProducerConsumerExample h = new ProducerConsumerExample();
        //Thread producer = new Thread(h.new Producer());
        //Thread consumer = new Thread(h.new Consumer());
        //producer.start();
        //consumer.start();

        ScheduledExecutorService execService = Executors.newScheduledThreadPool(2);

        execService.scheduleAtFixedRate(h.new Producer(), 1, 2, TimeUnit.SECONDS);
        execService.scheduleAtFixedRate(h.new Consumer(), 3, 7, TimeUnit.SECONDS);

        //  execService.shutdown();
    }

}
