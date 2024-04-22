package com.mcartagena.mockito;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {
    @Test
    public void test() {
        List<Integer> scores = List.of(99, 100, 101, 105);

        // asserts over List
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(99, 105));
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(190)));

        // asserts over String
        assertThat("", is(emptyString()));
        assertThat(null, is(emptyOrNullString()));

        // arrays
        Integer[] marks = {1, 2, 3};

        assertThat(marks, arrayWithSize(3));
        assertThat(marks, arrayContaining(1, 2, 3));
        assertThat(marks, arrayContainingInAnyOrder(3, 2, 1));

    }
}
