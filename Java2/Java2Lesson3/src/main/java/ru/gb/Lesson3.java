package ru.gb;

import ru.gb.phonebook.PhoneBook;

import java.util.*;

public class Lesson3 {

    public static void main(String[] args) {
        System.out.println("Задание №1:");
        task1();
        System.out.println("\nЗадание №2:");
        task2();
    }

    /**
     * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
     * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
     * Посчитать сколько раз встречается каждое слово.
     */
    private static void task1() {
        String[] array = {"помидор", "огурец", "картошка", "яблоко", "груша", "огурец", "помидор", "арбуз", "картошка",
                "помидор", "груша", "яблоко", "помидор", "абрикос", "картошка", "арбуз", "помидор", "дыня", "груша", "яблоко"};

        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        for (String item : array) {
            set.add(item);

            if (map.containsKey(item)) {
                Integer count = map.get(item);
                map.put(item, ++count);
            } else {
                map.put(item, 1);
            }

        }
        System.out.println("Список уникальных слов: " + set.toString());
        System.out.println("Подсчет количества слов: " + map.toString());


    }

    /**
     * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
     * В этот телефонный справочник с помощью метода add() можно добавлять записи.
     * С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть
     * несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
     */
    private static void task2() {

        PhoneBook pb = new PhoneBook();

        pb.add("Иванов", "89998887755");
        pb.add("Петров", "89998887744");
        pb.add("Сидоров", "89998887733");
        pb.add("Иванов", "89998887722");
        pb.add("Фомичев", "89998887711");

        String name1 = "Иванов";
        List<String> result1 = pb.get(name1);
        System.out.printf("Номера телефонов по фамилии %s: %s\n", name1, result1);

        String name2 = "Петров";
        List<String> result2 = pb.get(name2);
        System.out.printf("Номера телефонов по фамилии %s: %s\n", name2, result2);

    }

}
