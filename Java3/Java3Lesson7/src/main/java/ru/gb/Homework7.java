package ru.gb;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gb.annotation.AfterSuite;
import ru.gb.annotation.BeforeSuite;
import ru.gb.annotation.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Homework7 {

    private static final Logger LOGGER = LogManager.getLogger(Homework7.class);

//    1. Создать класс, который может выполнять «тесты», в качестве тестов выступают классы
//    с наборами методов с аннотациями @Test.

//    Для этого у него должен быть статический метод start(),
//    которому в качестве параметра передается или объект типа Class, или имя класса.

//    Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется,
//    далее запущены методы с аннотациями @Test,
//    а по завершению всех тестов – метод с аннотацией @AfterSuite.

//    К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет
//    выбираться порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения.

//    Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре, иначе
//    необходимо бросить RuntimeException при запуске «тестирования».


    public static void start(Class cl) {
        try {
            //validation
            Method[] declaredMethods = cl.getDeclaredMethods();
            if (declaredMethods == null || declaredMethods.length == 0) {
                throw new RuntimeException("Класс не содержит методов!");
            }

            List<Method> list = Arrays.asList(declaredMethods);

            List<Method> collectBefore = list.stream().filter(a -> a.getAnnotation(BeforeSuite.class) != null).collect(Collectors.toList());
            if (collectBefore.size() > 1) {
                throw new RuntimeException("Класс содержит больше одного метода с аннотацией @BeforeSuite!");
            }
            Method before = null;
            if(collectBefore.size() > 0){
                before = collectBefore.get(0);
            }


            List<Method> collectAfter = list.stream().filter(a -> a.getAnnotation(AfterSuite.class) != null).collect(Collectors.toList());
            if (collectAfter.size() > 1) {
                throw new RuntimeException("Класс содержит больше одного метода с аннотацией @AfterSuite!");
            }

            if (collectAfter.size() == 0) {
                throw new RuntimeException("Класс должен содержать метод с аннотацией @AfterSuite!");
            }
            Method after = collectAfter.get(0);

            //processing
            List<Method> collectTest = list.stream()
                    .filter(a -> a.getAnnotation(Test.class) != null)
                    .sorted((Method m1, Method m2) ->
                            Integer.compare(m1.getAnnotation(Test.class).priority(), m2.getAnnotation(Test.class).priority()))
                    .collect(Collectors.toList());

            Object instance = cl.getDeclaredConstructor().newInstance();
            if(before != null){
                before.invoke(instance);
            }
            for (Method m : collectTest) {
                m.invoke(instance);
            }
            after.invoke(instance);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}