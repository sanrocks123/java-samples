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
 * File Name:    GenericsExample.java
 *
 * Date Created: Mar 10, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class GenericsExample {

    class Node<T> {
        private T t;

        public T getNode() {
            return this.t;
        }

        public void setNode(T t) {
            this.t = t;
        }
    }

    // bounded type parameters
    public <T extends Number> T hello(T t) {
        return t;
    }

    // upper bounded wildcards
    public List<? extends Number> upperBoundWildCard(List<? extends Number> list) {
        return list;
    }

    // lower bounded wildcard
    public List<? super ArrayList<Number>> lowerBoundedWildCard(List<? super ArrayList<Number>> list) {
        return list;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        GenericsExample gen = new GenericsExample();

        Node<String> n = gen.new Node<String>();
        n.setNode("Helo");

        System.out.println(n.getNode());
        System.out.println(gen.hello(1));

        gen.upperBoundWildCard(new ArrayList<Integer>());

    }
}
