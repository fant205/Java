package ru.gb;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;


public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private final CyclicBarrier cb;
    private final CountDownLatch cdlStart;
    private final CountDownLatch cdlFinish;
    private final Lock winnerLock;

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdlStart, CountDownLatch cdlFinish, Lock winnerLock) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.cdlStart = cdlStart;
        this.cdlFinish = cdlFinish;
        this.winnerLock = winnerLock;

    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cdlStart.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        boolean lock = winnerLock.tryLock();
        if(lock){
            System.out.printf("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Победитель %s\n", this.name);
        }
        cdlFinish.countDown();
    }
}