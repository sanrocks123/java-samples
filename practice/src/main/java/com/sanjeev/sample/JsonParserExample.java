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
 * File Name:    JsonParserExample.java
 *
 * Date Created: Oct 18, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.sample;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class JsonParserExample {

    /**
     * @param args
     * @throws IOException
     * @throws JSONException
     */
    public static void main(String[] args) throws JSONException, IOException {

        InputStream is = JsonParserExample.class.getResourceAsStream("/events-groups.json");
        JSONObject groups = new JSONObject(IOUtils.toString(is));

        //        formatMessage(groups, "ConsoleLogin");

    }

    /**
     * @param groups
     * @param string
     */
    private static void formatMessage(JSONObject groups, String eventTypeCode) {
        groups.getJSONArray("groups").forEach(i -> {
            try {
                JSONObject group = (JSONObject) i;
                group.getJSONArray("typeCodes").forEach(typeCode -> {
                    try {
                        if (((String) typeCode).equalsIgnoreCase(eventTypeCode)) {
                            System.out.println(group.getString("prefix"));
                        }
                    } catch (JSONException ex) {
                    }
                });
            } catch (JSONException ex) {

            }
        });
    }

}
