package ru.gb.lesson1.task3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> list;

    public Box() {
        list = new ArrayList<>();
    }


    public float getWeight() {
        float result = 0;
        for (T elem : list) {
            result = elem.getWeight() + result;
        }
        return result;
    }

    public void add(T item) {

        if (item == null) {
            throw new NullPointerException();
        }

        list.add(item);
    }

    public boolean compare(Box<?> box) {
        if (box == null) {
            return false;
        }

        if (this.getWeight() == box.getWeight()) {
            return true;
        }

        return false;

    }

    public void push(Box box) {

        if (box == null) {
            throw new NullPointerException();
        }

        this.list.addAll(box.list);
        box.list.clear();

    }

    public int size(){
        return list.size();
    }

}
