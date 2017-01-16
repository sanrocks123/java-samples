package com.sanjeev.sample;

import static org.junit.Assert.assertEquals;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SampleTest {

    @Test
    public void findPairTest() {
        int a[] = { 2, 3, 1, 5, 4 };
        String expected = "45";
        String actual = findPair(a, 9);
        assertEquals(expected, actual);
    }

    @Test
    public void productArrayTest() {
        int[] arr = new int[] { 10, 3, 5, 6, 2 };
        int[] expected = new int[] { 180, 600, 360, 300, 900 };
        int[] actual = productArray(arr, arr.length);
        assertEquals(expected, actual);
    }

    /**
     * @param arr
     * @return
     */
    private int[] productArray(int[] arr, int n) {
        int[] left = new int[n];
        int[] right = new int[n];
        int[] prod = new int[n];

        left[0] = 1;
        right[n - 1] = 1;

        List<Integer> leftArr = new ArrayList<>();
        leftArr.add(left[0]);
        for (int i = 1; i < n; i++) {
            left[i] = arr[i - 1] * left[i - 1];
            leftArr.add(left[i]);
        }
        System.out.println("Left : " + leftArr);
        leftArr.removeAll(leftArr);
        leftArr.add(right[n - 1]);
        for (int j = n - 2; j >= 0; j--) {
            right[j] = arr[j + 1] * right[j + 1];
            leftArr.add(right[j]);
        }
        System.out.println("Right : " + leftArr);

        for (int k = 0; k < n; k++) {
            prod[k] = left[k] * right[k];
        }

        leftArr.removeAll(leftArr);
        System.out.println(Arrays.asList(prod).toString());
        return prod;
    }

    /**
     * @param a
     * @param i
     * @return
     */
    private String findPair(int[] a, int sum) {
        StringBuilder sb = new StringBuilder();
        int len = a.length - 1;
        for (int i = 0; i <= len; i++) {
            System.out.println("Pass : " + i);
            for (int j = i + 1; j <= len; j++) {
                System.out.println(MessageFormat.format("Comparing sum of ''{0}'' ''{1}''  with ''{2}''", new Object[] { a[i], a[j], sum }));
                if (sum == (a[i] + a[j])) {
                    sb.append(a[i]).append(a[j]);
                    System.out.println(MessageFormat.format("Pair found : ''{0}'' ''{1}''", new Object[] { a[i], a[j], sum }));
                    break;
                }
            }
        }
        return sb.toString();
    }
}
