package ru.lesson2;

public class Lesson2 {

    public static void main(String[] args) {
        //пункт 1
        System.out.println(checkSum(1 , 20));
        //пункт 2
        printIsPositiveNumber(1);
        //пункт 3
        System.out.println(isPositiveNumber(-5));
        //пункт 4
        printLine("Hello GeekBrains", 3);
        //пункт 5
        System.out.println("Год високосный - " + isLeapYear(1500));
    }

    public static boolean checkSum(int a, int b){
        int c = a + b;

        if(c >= 10 && c <= 20){
            return true;
        } else {
            return false;
        }
    }

    public static void printIsPositiveNumber(int number){
        if(number >= 0){
            System.out.println(number + " - положительное число");
        } else {
            System.out.println(number + " - отрицательное число");
        }
    }

    public static boolean isPositiveNumber(int number){
        if(number >= 0){
            return false;
        } else {
            return true;
        }
    }

    public static void printLine(String message, int printCount){
        for (int i = 0; i < printCount; i++) {
            System.out.println(message);
        }
    }

    public static boolean isLeapYear(int year){
        if(year % 400 == 0){
            return true;
        } else if(year % 100 == 0){
            return false;
        } else if(year % 4 == 0){
            return true;
        } else {
            return false;
        }
    }
}