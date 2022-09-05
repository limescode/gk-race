package pl.limescode.race;

import pl.limescode.race.model.Car;
import pl.limescode.race.model.Race;
import pl.limescode.race.model.Road;
import pl.limescode.race.model.Tunnel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainApp {
    public static final int CARS_COUNT = 4;


    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        final Lock lock = new ReentrantLock();
        Race race = new Race(new Road(60), new Tunnel(lock), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CountDownLatch cdl = new CountDownLatch(CARS_COUNT * 3);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cdl);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cdl.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
