/** SYMANTEC: Copyright 02015 Symantec Corporation. All rights reserved.
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
 * File Name:    EmployeeServiceImpl.java
 *
 * Date Created: Aug 8, 2015
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2015 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.spring.services;

import org.springframework.stereotype.Service;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    /*
     * (non-Javadoc)
     * 
     * @see com.sanjeev.spring.services.IEmployeeService#getName()
     */

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Sanjeev Saxena is back";
    }

    public void initialize() throws Exception {
        System.out.println(" EmployeeServiceImpl initialize");
    }

    public void cleanUp() throws Exception {
        System.out.println("EmployeeServiceImpl destroyed");
    }

    // CONSTANTS ------------------------------------------------------

    // CLASS VARIABLES ------------------------------------------------

    // INSTANCE VARIABLES ---------------------------------------------

    // CONSTRUCTORS ---------------------------------------------------

    // PUBLIC METHODS -------------------------------------------------

    // PROTECTED METHODS ----------------------------------------------

    // PRIVATE METHODS ------------------------------------------------

    // ACCESSOR METHODS -----------------------------------------------

}
