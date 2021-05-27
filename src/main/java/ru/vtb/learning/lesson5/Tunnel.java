package ru.vtb.learning.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore semaphore;
    public Tunnel(Integer carCount, Integer length) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(carCount / 2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                if (semaphore.availablePermits() == 0) {
                    Logger.Log(c.getName() + " готовится к этапу(ждет): " + description);
                }
                semaphore.acquire();
                Logger.Log(c.getName() + " начал этап: " + description);
                Thread.sleep((int)((float)length / (float)c.getSpeed()  * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                Logger.Log(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
