package com.kodilla.rps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Opponent {

    private final int difficulty;

    private final Shape[] shapes = { new Rock(), new Paper(), new Scissors() };
    private final Random random = new Random();
    private final List<Boolean> opponentWonRounds = new ArrayList<>();

    public Opponent(int difficulty) {
        this.difficulty = difficulty;
    }

    public Shape chooseRandomShape(Shape player) {

        switch (difficulty) {
            case 1 -> {
                int index = random.nextInt(shapes.length);
                return shapes[index];
            }
            case 2 -> {
                int randomNumber = random.nextInt(100);

                if (randomNumber < 50) {
                    return win(player);
                } else if (randomNumber < 75) {
                    return draw(player);
                } else {
                    return loose(player);
                }
            }
            case 3 -> {
                int randomNumber = random.nextInt(100);

                if (randomNumber < 70) {
                    return win(player);
                } else if (randomNumber < 85) {
                    return draw(player);
                } else {
                    return loose(player);
                }
            }
        }

        return shapes[0];

    }

    public void addWonRounds() {
        opponentWonRounds.add(true);
    }

    public int getAmountOfWonRounds() {
        return opponentWonRounds.size();
    }

    private static Shape win(Shape player) {
        if (player instanceof Rock) return new Paper();
        if (player instanceof Paper) return new Scissors();
        return new Rock(); // Jeśli gracz wybrał Nożyce, komputer wybiera Kamień
    }

    private static Shape draw(Shape player) {
        if (player instanceof Rock) return new Rock();
        if (player instanceof Paper) return new Paper();
        return new Scissors();
    }

    private static Shape loose(Shape player) {
        if (player instanceof Rock) return new Scissors();
        if (player instanceof Paper) return new Rock();
        return new Paper();
    }
}
