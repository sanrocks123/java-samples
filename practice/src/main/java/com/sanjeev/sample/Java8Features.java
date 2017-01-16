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
 * Date Created: Sep 23, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

package com.sanjeev.sample;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class Java8Features {

    public static void main(String[] args) {
        Java8Features java8 = new Java8Features();
        //java8.functionalInterfaces();
        //java8.methodReferences();
        //java8.streams();
        java8.threads();
    }

    /**
     * 
     */
    private void threads() {
        Runnable t = new Thread() {

            @Override
            public void run() {
                System.out.println("Thread - " + Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);

        t3.start();
        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 
     */
    private void streams() {
        List<String> names = Arrays.asList("a", "a", "b", "c", "");
        List<String> filtered = names.stream().filter(a -> !a.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
        Objects.requireNonNull(null);
    }

    /**
     * 
     */
    private void methodReferences() {
        List<String> names = Arrays.asList("a", "a", "b", "c", "");
        names.forEach(System.out::println);
    }

    /**
     * 
     */
    private void functionalInterfaces() {
        MathOperation subtraction = (a, b) -> a - b;
        System.out.println(subtraction.operation(5, 2));

        SingleParam sayHello = message -> message;
        System.out.println(sayHello.sayHello("Sanjeev"));
    }

    @FunctionalInterface
    interface MathOperation {
        public int operation(int a, int b);
    }

    @FunctionalInterface
    interface SingleParam {
        public String sayHello(String str);

        default public String sayHellos(String str) {
            return "";
        }
    }
}
