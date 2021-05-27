package ru.vtb.learning.lesson5;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            Logger.Log(c.getName() + " начал этап: " + description);
            Thread.sleep((int)((float)length / (float)c.getSpeed()  * 1000));
            Logger.Log(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
