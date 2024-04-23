package com.mcartagena.powermock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvokingPrivateMethodTest {
    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void privateMethodUnderTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> stats = List.of(1, 2, 3);
        when(dependency.retrieveAllStats()).thenReturn(stats);

        Method method = SystemUnderTest.class.getDeclaredMethod("privateMethodUnderTest");
        method.setAccessible(true);

        long result = (Long) method.invoke(systemUnderTest);

        assertEquals(6, result);
    }
}
