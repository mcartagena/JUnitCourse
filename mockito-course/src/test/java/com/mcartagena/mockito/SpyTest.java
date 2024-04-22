package com.mcartagena.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpyTest {
    @Test
    public void test() {
        List arrayListSpy = spy(ArrayList.class);
        // with spy the methods works as they are and
        // we can overide some of them
        assertEquals(0, arrayListSpy.size());
        arrayListSpy.add("Dummy");
        assertEquals(1, arrayListSpy.size());
        arrayListSpy.remove("Dummy");
        assertEquals(0, arrayListSpy.size());

        // overiding the size method
        when(arrayListSpy.size()).thenReturn(5);
        assertEquals(5, arrayListSpy.size());

        verify(arrayListSpy).add("Dummy");
        verify(arrayListSpy, never()).clear();
    }
}
