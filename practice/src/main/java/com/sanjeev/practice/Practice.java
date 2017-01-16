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
 * File Name:    Practice.java
 *
 * Date Created: Jan 13, 2017
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2017 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.practice;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

class A implements Runnable {

    private Object r1;
    private Object r2;

    public A(Object r1, Object r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to acquire lock on r1...");
        synchronized (r1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock on r1!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " trying to acquire lock on r2...");
            synchronized (r2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock on r2!");
            }

        }

    }

}

class B implements Runnable {

    private Object r1;
    private Object r2;

    public B(Object r1, Object r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to acquire lock on r2...");
        synchronized (r2) {
            System.out.println(Thread.currentThread().getName() + " acquired lock on r2!");
            System.out.println(Thread.currentThread().getName() + " trying to acquire lock on r1...");
            synchronized (r1) {
                System.out.println(Thread.currentThread().getName() + " acquired lock on r1!");
            }
        }
    }

}

public class Practice {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Object r1 = new Object();
        Object r2 = new Object();
        new Thread(new A(r1, r2)).start();
        new Thread(new B(r1, r2)).start();
    }
}
