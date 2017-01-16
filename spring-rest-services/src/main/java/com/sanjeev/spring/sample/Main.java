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
 * Date Created: Aug 8, 2015
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2015 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.spring.sample;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");
        MainBean mainBean = (MainBean) appContext.getBean("mainBean");

        System.out.println(mainBean.getName());
        appContext.registerShutdownHook();
    }

}
