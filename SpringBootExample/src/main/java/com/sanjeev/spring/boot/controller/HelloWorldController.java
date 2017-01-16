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
 * File Name:    HelloWorldController.java
 *
 * Date Created: Jun 21, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.spring.boot.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sanjeev.spring.boot.beans.HelloWordImpl;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWordImpl hImpl;

    @RequestMapping(value = "/")
    public ResponseEntity<String> helloWorld() {
        System.out.println("Getting health check...");
        return new ResponseEntity<String>(hImpl.healthCheck(), HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDetails(@RequestBody final String requestBody) {
        System.out.println("Calling /dcs-service/dcscloud/v1/login API...");
        ResponseEntity<String> response = new ResponseEntity<String>("", HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);

        RestTemplate client = new RestTemplate();
        response = client.exchange("http://10.211.64.248:8080/dcs-service/dcscloud/v1/login", HttpMethod.POST, requestEntity, String.class);
        System.out.println(response);

        return new ResponseEntity<String>(response.getBody(), response.getStatusCode());
    }
}
