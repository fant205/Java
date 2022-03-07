package ru.gb.lesson1;

import ru.gb.lesson1.task3.Apple;
import ru.gb.lesson1.task3.Box;
import ru.gb.lesson1.task3.Orange;

import java.util.Arrays;
import java.util.List;

public class Java3Lesson1 {

    public static void main(String[] args) {

//        1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
        task1();

//        2. Написать метод, который преобразует массив в ArrayList;
        task2();

        //3. Большая задача
        task3();

    }

    private static void task1() {
        String[] array = {"a", "b", "c"};
        GenericsManager<String> manager = new GenericsManager<>();
        manager.changeItemPlace(array, 0, 2);
        System.out.println(Arrays.toString(array));
    }

    private static void task2() {
        String[] array = {"a", "b", "c"};
        GenericsManager<String> manager = new GenericsManager<>();
        List<String> result = manager.convertArrayToList(array);
        System.out.println(result.toString());

    }

    private static void task3() {

//        3. Большая задача:
//        a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
//        b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну
//        коробку нельзя сложить и яблоки, и апельсины;
//        c. Для хранения фруктов внутри коробки можете использовать ArrayList;

        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        Box<Orange> orangeBox1 = new Box<>();
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());

//      d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
//      (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
        System.out.println("Вес коробки яблок: " + appleBox.getWeight());
        System.out.println("Вес коробки апельсин: " + orangeBox1.getWeight());

//      e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут
//      в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем
//      сравнивать с коробками с апельсинами);
        System.out.println("Результат сравнения коробок: " + appleBox.compare(orangeBox1));


//      f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку
//      фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
//      а в другую перекидываются объекты, которые были в этой коробке;
        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());

        System.out.println("Количество апельсинов в 1 коробке: " + orangeBox1.size());
        System.out.println("Количество апельсинов в 2 коробке: " + orangeBox2.size());
        orangeBox1.push(orangeBox2);
        System.out.println("Количество апельсинов в 1 коробке после перекладывания: " + orangeBox1.size());
        System.out.println("Количество апельсинов в 2 коробке после перекладывания: " + orangeBox2.size());


//      g. Не забываем про метод добавления фрукта в коробку.

    }
}