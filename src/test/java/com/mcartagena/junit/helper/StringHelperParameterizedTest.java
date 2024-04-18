package com.mcartagena.junit.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StringHelperParameterizedTest {

    StringHelper helper;

    @BeforeEach
    public void setup() {
        helper = new StringHelper();
    }

    @ParameterizedTest
    @MethodSource("stringsProvider")
    public void testTruncateAInFirst2Positions(String input, String expected) {
        assertEquals(expected, helper.truncateAInFirst2Positions(input));
    }

    static Stream<Arguments> stringsProvider() {
        return Stream.of(
                arguments("AACD", "XCD"),
                arguments("ACD", "CD")
        );
    }
}