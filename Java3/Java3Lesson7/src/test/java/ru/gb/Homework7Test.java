package ru.gb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Homework7Test {

    @Test
    public void method1() {
        Homework7.start(AllGood.class);
        Assertions.assertTrue(true);
    }

    @Test
    public void method2() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            Homework7.start(TwoBeforeSuite.class);
        });
        Assertions.assertEquals("java.lang.RuntimeException: " +
                "Класс содержит больше одного метода с аннотацией @BeforeSuite!", exception.getMessage());
    }

    @Test
    public void method3() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            Homework7.start(TwoAfterSuite.class);
        });
        Assertions.assertEquals("java.lang.RuntimeException: " +
                "Класс содержит больше одного метода с аннотацией @AfterSuite!", exception.getMessage());
    }

    @Test
    public void method4() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            Homework7.start(NoAfterSuite.class);
        });
        Assertions.assertEquals("java.lang.RuntimeException: " +
                "Класс должен содержать метод с аннотацией @AfterSuite!", exception.getMessage());
    }

    @Test
    public void method5() {
        Homework7.start(NoBeforeSuite.class);
        Assertions.assertTrue(true);
    }

    @Test
    public void method6() {
        Homework7.start(SamePriority.class);
        Assertions.assertTrue(true);
    }


}
