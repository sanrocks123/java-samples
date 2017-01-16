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
 * File Name:    HelloWordImpl.java
 *
 * Date Created: Jun 21, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.spring.boot.beans;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

@Component
public class HelloWordImpl {

    public String healthCheck() {
        try {
            JSONObject result = new JSONObject();
            result.put("HostName", InetAddress.getLocalHost().getHostName());
            result.put("Host IP", InetAddress.getLocalHost().getHostAddress());
            return result.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "HostName Not found";
    }

}
