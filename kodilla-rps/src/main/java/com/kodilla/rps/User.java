package com.kodilla.rps;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String username;
    private final int amountOfRoundsToWin;
    private final List<Boolean> userWonRounds = new ArrayList<>();

    public User(String username, int amountOfRoundsToWin) {
        this.username = username;
        this.amountOfRoundsToWin = amountOfRoundsToWin;
    }

    public String getUsername() {
        return username;
    }

    public int getAmountOfRoundsToWin() {
        return amountOfRoundsToWin;
    }

    public void addWonRounds() {
        userWonRounds.add(true);
    }

    public int getAmountOfWonRounds() {
        return userWonRounds.size();
    }
}
