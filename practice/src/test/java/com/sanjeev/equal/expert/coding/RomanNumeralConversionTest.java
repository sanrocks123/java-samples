package com.sanjeev.equal.expert.coding;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RomanNumeralConversionTest {

    private IRomanNumeralConversion conversion;
    private String input = null;
    private Integer actual = null;
    private Integer expected = null;

    @Before
    public void init() {
        conversion = new RomanNumeralCoversionImpl();
    }

    @Test
    public void testGetDecimalValue() {
        input = "II";
        actual = conversion.getDecimalValue(input);
        expected = 2;
        assertEquals(expected, actual);

        input = "III";
        expected = 3;
        actual = conversion.getDecimalValue(input);
        assertEquals(expected, actual);

        input = "V";
        expected = 5;
        actual = conversion.getDecimalValue(input);
        assertEquals(expected, actual);

    }

    @Test
    public void testSubractionScenario() {
        input = "IV";
        expected = 4;
        actual = conversion.getDecimalValue(input);
        assertEquals(expected, actual);

        input = "IX";
        expected = 9;
        actual = conversion.getDecimalValue(input);
        assertEquals(expected, actual);

        input = "XIV";
        expected = 14;
        actual = conversion.getDecimalValue(input);
        assertEquals(expected, actual);

    }

    @Test
    public void testAll() {
        String[] input = new String[] { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
        int[] expected = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] actual = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            actual[i] = conversion.getDecimalValue(input[i]);
        }
        for (int j = 0; j < input.length; j++) {
            assertEquals(expected[j], actual[j]);
        }
    }
}
