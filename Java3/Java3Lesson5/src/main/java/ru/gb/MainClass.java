package ru.gb;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Semaphore semaphore = new Semaphore(CARS_COUNT / 2);
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
        CountDownLatch cdlStart = new CountDownLatch(CARS_COUNT);
        CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
        final Lock winnerLock = new ReentrantLock();
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, cdlStart, cdlFinish, winnerLock);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        cdlStart.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cdlFinish.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}




