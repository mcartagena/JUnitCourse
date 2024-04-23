package com.mcartagena.powermock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SystemUnderTestTest {
    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void methodCallingAStaticMethod() {
        List<Integer> stats = List.of(1, 2, 3);
        when(dependency.retrieveAllStats()).thenReturn(stats);

        try (MockedStatic<UtilityClass> utilityClassMockedStatic = mockStatic(UtilityClass.class)) {
            utilityClassMockedStatic.when(() -> UtilityClass.staticMethod(6))
                    .thenReturn(150);

            assertEquals(150, systemUnderTest.methodCallingAStaticMethod());
            utilityClassMockedStatic.verify(() -> UtilityClass.staticMethod(6),
                    atLeast(1));
        }

    }

}