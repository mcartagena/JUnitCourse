package com.mcartagena.mockito.business;

import com.mcartagena.mockito.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoBusinessImplMockitoInjectMocksTest {

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testReretrieveTodosRelatedToSpring_usingAMock() {
        List<String> todos = List.of("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testReretrieveTodosRelatedToSpring_usingEmptyList() {
        List<String> todos = List.of();

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertEquals(0, filteredTodos.size());
    }

    @Test
    public void testReretrieveTodosRelatedToSpring_usingBDDMock() {
        // Given
        List<String> todos = List.of("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertThat(filteredTodos.size(), is(2));

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDDMockAndVerify() {
        // Given
        List<String> todos = List.of("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        verify(todoServiceMock).deleteTodo("Learn to Dance");
        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
        verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
        verify(todoServiceMock, atLeast(1)).deleteTodo("Learn to Dance");
        // verify(todoServiceMock).deleteTodo("Learn Spring");  // Error Wanted but not invoked
        verify(todoServiceMock, never()).deleteTodo("Learn Spring");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDDMockAndThenShould() {
        // Given
        List<String> todos = List.of("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should().deleteTodo("Learn to Dance");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDDMock_argumentCapture() {

        // Given
        List<String> todos = List.of("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDDMock_argumentCaptureMultipleTimes() {

        // Given
        List<String> todos = List.of("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));

    }

}