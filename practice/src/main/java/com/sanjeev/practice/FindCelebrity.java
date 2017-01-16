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
 * File Name:    Sample.java
 *
 * Date Created: Sep 1, 2016
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2016 Symantec Ltd. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.practice;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class FindCelebrity {

    boolean matrix[][] = { { false, false, true, false },
            { false, false, false, false },
            { false, false, true, false },
            { false, false, true, false } };

    /**
     * @param args
     */
    public static void main(String[] args) {
        FindCelebrity s = new FindCelebrity();
        // System.out.println(s.findMin(new int[] { 3, 4, 5, 2, 1 }));
        //System.out.println(s.findMax(new int[] { 3, 4, 5, 2, 1 }));

        //System.out.println(s.findCelebrity(4));

        // Calendar from = Calendar.getInstance(TimeZone.getTimeZone("PST"));
        //System.out.println(getAnagramIndices("abdcghbaabcdij", "bcda"));
        //System.out.println(getAnagramIndices("abcde", "e"));
    }

    public static List<Integer> getAnagramIndices(String haystack, String needle) {
        List<Integer> result = new ArrayList<>();
        String subset = null;
        for (int i = 0; i < haystack.length(); i++) {
            int flag = 0;
            if (i + needle.length() - 1 > haystack.length()) {
                if (Character.toString(haystack.charAt(i)).contains(needle)) {
                    result.add(i);
                }
                return result;
            }
            subset = haystack.substring(i, i + needle.length());
            for (char c : needle.toCharArray()) {
                if (subset.contains(Character.toString(c))) {
                    flag++;
                }
            }

            if (flag == needle.length()) {
                result.add(i);
            }
        }

        return result.isEmpty() ? null : result;

    }

    /**
     * @param i
     */
    private int findCelebrity(int n) {

        int a = 0;
        int b = n - 1;

        while (a < b) {
            System.out.println("a : " + a + "\nb: " + b);
            if (knows(a, b)) {
                System.out.println(MessageFormat.format("''{0}'' knows ''{1}''", new Object[] { a, b }));
                a++;
                System.out.println("Moving to next a : " + a);
            } else {
                System.out.println(MessageFormat.format("''{0}'' does not knows ''{1}''", new Object[] { a, b }));
                b--;
                System.out.println("Decrementing b :" + b);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if (i != a && (knows(a, i) || !knows(i, a)))
                return -1;
        }
        return a;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    private boolean knows(int a, int b) {
        return matrix[a][b];
    }

    /**
     * 
     */
    private void matchPattern() {
        Pattern pattern = Pattern.compile("(.)*\\d");
        Matcher matcher = pattern.matcher("abd9dsds");
        if (matcher.matches()) {
            System.out.println("true");
        } else
            System.out.println("False");
    }

    private int findMin(int a[]) {
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min)
                min = a[i];
        }
        return min;
    }

    private int findMax(int a[]) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max)
                max = a[i];
        }
        return max;
    }

    private int findSubArray(int a[], int sum) {
        return 1;
    }

}
