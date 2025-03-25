package com.kodilla.checkers;

import com.kodilla.checkers.logic.GameLogic;

public class CheckersRunner {
    public static void main(String[] args) {
        GameLogic logic = new GameLogic();
        logic.run();
    }
}
