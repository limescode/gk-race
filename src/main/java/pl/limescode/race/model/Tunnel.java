package pl.limescode.race.model;

import java.util.concurrent.locks.Lock;

public class Tunnel extends Stage {

    private Lock lock;

    public Tunnel(Lock lock) {
        this.lock = lock;
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                lock.lock();
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                lock.unlock();
                c.getCdl().countDown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}