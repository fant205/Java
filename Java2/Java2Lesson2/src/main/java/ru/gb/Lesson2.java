package ru.gb;

/**
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * <p>
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
 * должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
 * <p>
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException,
 * и вывести результат расчета.
 */
public class Lesson2 {

    public static void main(String[] args) {

        String[][] array = new String[][]{{"1", "1", "1", "1"}, {"2", "2", "2", "2"}, {"3", "3", "3", "3"}, {"4", "4", "4", "4"}};
        int result = 0;
        try {
            result = summArray(array);
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static int summArray(String[][] array) throws MyArrayDataException, MyArraySizeException {
        if (array == null || array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException();
        }

        int result = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                String s = array[i][j];

                //способ №1
                try {
                    result = result + Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("В ячейке [%s][%s] содержатся символы или текст: %s", i, j, s));
                }

                //способ № 2
                if (!s.matches("\\d+")) {
                    throw new MyArrayDataException(String.format("В ячейке [%s][%s] содержатся символы или текст: %s", i, j, s));
                }

            }
        }

        return result;
    }
}


