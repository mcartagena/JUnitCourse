package com.mcartagena.mockito.business;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    public void letsMockListSizeMethod(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2, listMock.size());
        assertEquals(2, listMock.size());
        assertEquals(2, listMock.size());
    }
    @Test
    public void letsMockListSizeMethod_ReturnMultipleValues(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }
    @Test
    public void letsMockListGet(){
        List listMock = mock(List.class);
        // when(listMock.get(0)).thenReturn("Mocking get");
        // Argument matcher
        when(listMock.get(anyInt())).thenReturn("Mocking get");

        assertEquals("Mocking get", listMock.get(0));
        //assertEquals(null, listMock.get(1));  // mocking defaults null
    }
    @Test
    public void letsMockListGet_usingBDD(){
        // Given
        List<String> listMock = mock(List.class);
        given(listMock.get(anyInt())).willReturn("Mocking get");

        // When
        String firstElement = listMock.get(0);

        // Then
        assertThat(firstElement,is("Mocking get"));
    }
    @Test
    public void letsMockListGet_ThrowsAnException(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Test"));

        Exception exception = assertThrows(RuntimeException.class, ()-> {
                    listMock.get(0);
                });

        String expectedMessage = "Test";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
