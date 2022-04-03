package ru.lesson2;

public class Lesson2 {

    public static void main(String[] args) {
        System.out.println(checkSum(1 , 20));
    }

    public static boolean checkSum(int a, int b){
        int c = a + b;

        if(c >= 10 && c <= 20){
            return true;
        } else {
            return false;
        }
    }
}