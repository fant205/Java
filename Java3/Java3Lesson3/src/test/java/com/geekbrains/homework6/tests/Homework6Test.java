package com.geekbrains.homework6.tests;

import com.geekbrains.homework6.Homework6;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Homework6Test {

    @Test
    public void method1Test1() {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        Integer[] result = Homework6.method1(array);
        Assertions.assertArrayEquals(new Integer[]{5, 6}, result);
    }

    @Test
    public void method1Test2() {
        Integer[] array = {1, 4, 4, 3, 4, 12, 14};
        Integer[] result = Homework6.method1(array);
        Assertions.assertArrayEquals(new Integer[]{12, 14}, result);
    }

    @Test
    public void method1Test3() {
        Integer[] array = {1, 2, 3, 4, 5, 4};
        Integer[] result = Homework6.method1(array);
        Assertions.assertArrayEquals(new Integer[]{}, result);
    }

    @Test
    public void method2Test1() {
        Integer[] array = {1, 1, 1};
        Assertions.assertEquals(false, Homework6.method2(array));
    }

    @Test
    public void method2Test2() {
        Integer[] array = {4, 4, 4};
        Assertions.assertEquals(false, Homework6.method2(array));
    }

    @Test
    public void method2Test3() {
        Integer[] array = {4, 2, 2, 1};
        Assertions.assertEquals(false, Homework6.method2(array));
    }

    @Test
    public void method2Test4() {
        Integer[] array = {1, 1, 4, 4};
        Assertions.assertEquals(true, Homework6.method2(array));
    }
}
