package com.mcartagena.junit.helper;

import org.junit.jupiter.api.*;

public class QuickBeforeAfterTest {

    @BeforeAll
    public static void beforeClass(){
        System.out.println("Before Class");
    }
    @BeforeEach
    public void setup(){
        System.out.println("Before Test");
    }

    @Test
    public void test1(){
        System.out.println("test1 executed");
    }

    @Test
    public void test2(){
        System.out.println("test2 executed");
    }
    @AfterEach
    public void teardown(){
        System.out.println("After test");
    }

    @AfterAll
    public static void afterClass(){
        System.out.println("After Class");
    }
}
