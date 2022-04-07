package com.geekbrains.homework6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Homework6 {

//    2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
//    Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
//    идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
//    необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
//    Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
//
//
//    3. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной
//    четверки или единицы, то метод вернет false; Написать набор тестов для этого метода
//    (по 3-4 варианта входных данных).

    public static void main(String[] args) {

    }

    public static Integer[] method1(Integer[] array) {
        List<Integer> list = Arrays.asList(array);
        boolean contains = list.contains(4);
        if (!contains) {
            throw new RuntimeException("Массив должен содержать хотя бы одну цифру 4!");
        }

        Collections.reverse(list);
        List<Integer> result = new ArrayList<>();
        for (Integer a : list) {
            if (a == 4) {
                Collections.reverse(result);
                return result.toArray(new Integer[0]);
            } else {
                result.add(a);
            }
        }
        return null;
    }

    public static boolean method2(Integer[] array) {
        if (Arrays.asList(array).stream().distinct().count() == 2) {
            return true;
        }
        return false;
    }

}
