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
 * File Name:    CountDownLatch.java
 *
 * Date Created: Aug 3, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.concurrent.utils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

class CountDownThread implements Runnable {
    private CountDownLatch latch;

    public CountDownThread(CountDownLatch latch) {
        this.latch = latch;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            int sleepTime = new Random().nextInt(10) * 1000;
            String thread = Thread.currentThread().getName();
            System.out.println(thread + " is working for approx. " + sleepTime + " secs...");
            Thread.sleep(sleepTime);
            System.out.println(thread + " is done with task...");
            latch.countDown();
            System.out.println("Count : " + latch.getCount());
            sleepTime = new Random().nextInt(7) * 1000;
            System.out.println(thread + " will take some more time " + sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CountDownLatchDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(3);

        new Thread(new CountDownThread(latch)).start();
        new Thread(new CountDownThread(latch)).start();
        new Thread(new CountDownThread(latch)).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " started working...");
    }

}
