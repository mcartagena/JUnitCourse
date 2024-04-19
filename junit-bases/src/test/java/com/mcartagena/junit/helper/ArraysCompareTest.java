package com.mcartagena.junit.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class ArraysCompareTest {

    @Test
    public void testArraySort_RandomArray() {
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};
        //int[] expected = {1, 4, 3, 12};
        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers);
    }

    @Test
    public void testArraySort_NullArray() {
        int[] numbers = null;
        Exception exception = assertThrows(NullPointerException.class, () -> {
                    Arrays.sort(numbers);
                }
        );
        String expectedMessage = "Cannot read the array length because \"a\" is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Timeout(1)
    public void testSort_Performance() {
        int source[] = {12, 1, 3, 4};
        for (int i = 1; i <= 100000000; i++) {
            source[0] = i;
            Arrays.sort(source);
        }
    }

}
