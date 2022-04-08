package com.geekbrains.homework4.task1;

public class Homework4Task1 {

    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {

        Homework4Task1 task1 = new Homework4Task1();
        Thread thread1 = new Thread(() -> {
            task1.printA();
        });
        Thread thread2 = new Thread(() -> {
            task1.printB();
        });
        Thread thread3 = new Thread(() -> {
            task1.printC();
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}