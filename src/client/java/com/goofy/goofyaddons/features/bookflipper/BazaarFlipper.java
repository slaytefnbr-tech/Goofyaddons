package com.goofy.goofyaddons.features.bookflipper;

import com.goofy.goofyaddons.utils.Schedular;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.goofy.goofyaddons.utils.Scoreboard;
import net.minecraft.client.Minecraft;

public class BazaarFlipper {
    private enum State {
        START,
        IDLE,
        FETCHING,
        BAZAAR_NAVIGATION
    }

    private Schedular schedular = new Schedular();
    private State state = State.IDLE;
    private State lastState = null;
    private List<FlipItem> flipItemsList = new ArrayList<>();
    private FlipCalculator flipCalculator = new FlipCalculator();
    private Scoreboard scoreboard = new Scoreboard();
    private final Queue<FlipItem> queue = new LinkedList<>();
    Minecraft minecraft = Minecraft.getInstance();

    public void onTick() {
        lastStateCheck();
        schedular.tick();

        switch (state) {
            case START -> {
                flipCalculator.Refresh();
            }

            case FETCHING -> {
                if (!flipItemsList.isEmpty()) processData();
                schedular.every(20, 10, () -> flipItemsList = flipCalculator.getFlipItemsList());
            }

            case BAZAAR_NAVIGATION -> {
                openBazaar(queue.peek().book().name());
                if (minecraft.screen.getTitle().toString().contains("Bazaar")) {
                }
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
            if (purse < flipItems.totalCost()) continue;
            queue.add(flipItems);
        }
        if (!queue.isEmpty()) {
            state = State.BAZAAR_NAVIGATION;
        } else {
            state = State.IDLE;
        }
    }

    private void openBazaar(String name) {
        minecraft.player.connection.sendCommand(name);
    }

}


