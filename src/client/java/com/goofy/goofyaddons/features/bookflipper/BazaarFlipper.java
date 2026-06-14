package com.goofy.goofyaddons.features.bookflipper;

import com.goofy.goofyaddons.utils.Schedular;

import java.util.ArrayList;
import java.util.List;
import com.goofy.goofyaddons.features.bookflipper.FlipCalculator;
import com.goofy.goofyaddons.utils.Scoreboard;

public class BazaarFlipper {
    private enum State {
        IDLE,
        FETCHING
    }

    private Schedular schedular = new Schedular();
    private State state = State.IDLE;
    private State lastState = null;
    private final List<FlipItem> flipItemsList = new ArrayList<>();
    private FlipCalculator flipCalculator = new FlipCalculator();
    private Scoreboard scoreboard = new Scoreboard();


    public void onTick() {
        lastStateCheck();
        schedular.tick();

        switch (state) {

            case FETCHING -> {
                if (!flipItemsList.isEmpty())
                schedular.every(20, 10, () -> flipCalculator.getFlipItemsList());
            }
        }

    }



    private void lastStateCheck() {
        if (state != lastState) {
            schedular.reset();
            lastState = state;
        }
    }

    private void processData() {
        double purse = scoreboard.getPurse();

        for (FlipItem flipItems : flipItemsList) {

        }
    }

}


