package ru.vtb.learning.lesson5;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Race {
    private ArrayList<Car> cars;
    private Track track;

    private static CountDownLatch cdlStart;
    private static CountDownLatch cdlEnd;

    private final Object mon = new Object();
    private Car firstCar;

    public Race(Integer carCount, Track track) {
        this.cars = new ArrayList<>(carCount);
        this.track = track;
        cdlStart = new CountDownLatch(carCount);
        cdlEnd = new CountDownLatch(carCount);
    }

    public void add(Car car){
        if (cars.contains(car)) {
            return;
        }

        new Thread(car).start();
    }


    public void start(){
        try {
            cdlStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finishCar(Car car){
        // стоит использовать такие конструкции?
        if(firstCar == null) {
            synchronized (mon) {
                if (firstCar == null){
                    firstCar = car;
                    Logger.Log("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Победитель гонки "+ car.getName() +"!!!");

                }
            }
        }

        cdlEnd.countDown();
    }

    public void end(){
        try {
            cdlEnd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Stage> getStages() {
        return track.getStages();
    }

    public void ready() {
        // можно проверить, что машина ещё не была готова, чтобы не щёлкнуть счётчик дважды
        // но из условий задачи мы не понимаем, где зона ответственности класса "гонка".
        cdlStart.countDown();
        try {
            cdlStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
