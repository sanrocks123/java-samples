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
 * File Name:    Main.java
 *
 * Date Created: Jun 21, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.spring.boot.main;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
@ComponentScan(basePackages = "com.sanjeev.spring.boot.controller, com.sanjeev.spring.boot.beans")
@SpringBootApplication
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setBanner(new Banner() {
            @Override
            public void printBanner(Environment env, Class<?> classz, PrintStream ps) {
                ps.println("*******************************************************************************");
                ps.println("                                Welcome to SpringBoot Application                              ");
                ps.println("*******************************************************************************");
            }
        });
        app.run(args);
    }

}
