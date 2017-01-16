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
 * File Name:    RomanNumeralCoversion.java
 *
 * Date Created: Jun 28, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.equal.expert.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class RomanNumeralCoversionImpl implements IRomanNumeralConversion {

    private Map<Character, Integer> symbolValues;

    /**
     * 
     */
    public RomanNumeralCoversionImpl() {
        init();
    }

    /**
     * 
     */
    private void init() {
        symbolValues = new HashMap<>();
        symbolValues.put('I', 1);
        symbolValues.put('V', 5);
        symbolValues.put('X', 10);
    }

    /**
     * @param input
     * @return
     */
    @Override
    public Integer getDecimalValue(String input) {
        Integer result = 0;
        char[] charArray = input.toCharArray();
        Integer previousValue = null;
        Integer currentValue = null;

        // Solution 1
        for (Character c : charArray) {
            currentValue = symbolValues.get(c);
            if (null == previousValue) {
                result = result + currentValue;
            } else if (currentValue <= previousValue) {
                result = result + currentValue;
            } else if (currentValue >= previousValue) {
                result = (result - previousValue) + (currentValue - previousValue);
            }
            previousValue = currentValue;
        }

        // Solution 2
        for (int i = 0; i < charArray.length; i++) {
            currentValue = symbolValues.get(charArray[i]);
            if (i < charArray.length - 1) {
                if (currentValue < symbolValues.get(charArray[i + 1]))
                    currentValue = currentValue * -1;
            }
            result += currentValue;
        }

        // output
        return result;
    }

}
