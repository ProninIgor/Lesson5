package ru.vtb.learning.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Track {

    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Track(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }


}
