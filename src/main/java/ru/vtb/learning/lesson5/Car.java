package ru.vtb.learning.lesson5;

public class Car implements Runnable {
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    // каим типом правильно пользоваться int или Integer и при каких условиях?
    public Car(Race race, int speed, int carNumber) {
        this.speed = speed;
        this.race = race;
        this.name = "Участник #" + carNumber;
    }

    @Override
    public void run() {
        try {
            Logger.Log(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            Logger.Log(this.name + " готов. Скорость: " + this.getSpeed());
            race.ready();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        race.finishCar(this);
    }
}
