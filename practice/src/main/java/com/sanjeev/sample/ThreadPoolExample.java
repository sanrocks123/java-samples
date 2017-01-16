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
 * File Name:    ThreadPoolExample.java
 *
 * Date Created: Oct 6, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
class MyExecutor {

    BlockingQueue<Runnable> taskQueue = null;
    List<PooledThread> pooledThreads;
    private boolean isStopped = false;

    public MyExecutor(int noOfThreads, int noOfTasks) {
        taskQueue = new ArrayBlockingQueue<Runnable>(noOfTasks);
        pooledThreads = new ArrayList<>();
        for (int i = 0; i < noOfThreads; i++) {
            pooledThreads.add(new PooledThread(taskQueue));
        }

        for (PooledThread thread : pooledThreads)
            thread.start();

        System.out.println("Thread Pool created with pool size : " + noOfThreads);
    }

    public synchronized void execute(Runnable runnable) {

        if (isStopped) {
            throw new IllegalStateException("Thread pool stopped");
        }
        try {
            this.taskQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        System.out.println("Stop requested...");
        this.isStopped = true;
        System.out.println("Currrent TAsk szie : " + this.taskQueue.size());
        while (this.taskQueue.size() != 0) {
            try {
                this.wait();
                for (PooledThread thread : pooledThreads) {
                    thread.doStop();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class PooledThread extends Thread {

    private boolean isStopped = false;
    private BlockingQueue<Runnable> taskQueue;

    /**
     * @param taskQueue
     */
    public PooledThread(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isStopped) {
            System.out.println("Current Task Queue Size : " + taskQueue.size());
            System.out.println(Thread.currentThread().getName() + " is waiting for task assignment... ");
            try {
                Runnable task = this.taskQueue.take();
                System.out.println(Thread.currentThread().getName() + " is processing your task...Please wait...");
                task.run();
                synchronized (taskQueue) {
                    if (taskQueue.size() == 0)
                        taskQueue.notifyAll();
                }
                System.out.println("task complete!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName() + " is stopped ");
    }

    /**
     * 
     */
    public void doStop() {
        this.isStopped = true;
        System.out.println(Thread.currentThread().getName() + " is asked to stop...");
        //this.interrupt();
    }

}

class MyTask implements Runnable {

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

public class ThreadPoolExample {

    /**
     * @param args
     */
    public static void main(String[] args) {

        MyExecutor exec = new MyExecutor(2, 10);
        exec.execute(new Thread(new MyTask()));
        exec.execute(new Thread(new MyTask()));
        exec.execute(new Thread(new MyTask()));
        exec.execute(new Thread(new MyTask()));
        exec.stop();

        exec.execute(new Thread(new MyTask()));

        //deadLockDemo();

        System.out.println(Thread.currentThread().getName() + " done!");
    }

    /**
     * 
     */
    private static void deadLockDemo() {
        Object r1 = new Object();
        Object r2 = new Object();

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " acquiring lock on r1...");
                synchronized (r1) {
                    System.out.println(Thread.currentThread().getName() + " Success, lock acquired on r1");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " Waiting to acquiring lock on r2...");
                    synchronized (r2) {
                        System.out.println("Success, lock acquired on r2");
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " acquiring lock on r2...");
                synchronized (r2) {
                    System.out.println(Thread.currentThread().getName() + " Success, lock acquired on r2");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " waiting to acquiring lock on r1...");
                    synchronized (r1) {
                        System.out.println("Success, lock acquired on r1");
                    }
                }
            }
        }.start();
    }
}
