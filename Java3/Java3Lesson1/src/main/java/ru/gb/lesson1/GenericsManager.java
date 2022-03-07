package ru.gb.lesson1;

import java.util.ArrayList;
import java.util.List;

public class GenericsManager<T> {

    public T changeItemPlace(T[] array, int i, int j) {
        T elem = array[i];
        array[i] = array[j];
        array[j] = elem;
        return (T) array;
    }

    public List<T> convertArrayToList(T[] array) {

        if (array == null) {
            throw new NullPointerException();
        }

        List<T> list = new ArrayList<>();
        for (T elem : array) {
            list.add(elem);
        }

        return list;
    }
}
