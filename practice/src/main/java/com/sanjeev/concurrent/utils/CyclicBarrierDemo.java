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
 * File Name:    CyclicBarrierDemo.java
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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

class BarrierThread implements Runnable {

    private CyclicBarrier barrier;
    private String name;

    /**
     * 
     */
    public BarrierThread(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        name = Thread.currentThread().getName();
        try {
            int sleepTime = new Random().nextInt(10) * 1000;
            System.out.println(name + " is working for approx. " + sleepTime + " secs...");
            Thread.sleep(sleepTime);
            System.out.println(name + " is UP...");
            barrier.await();

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("All services are UP & Running... Lets play game...");
            }
        });
        new Thread(new BarrierThread(barrier)).start();
        new Thread(new BarrierThread(barrier)).start();
        new Thread(new BarrierThread(barrier)).start();
    }
}
