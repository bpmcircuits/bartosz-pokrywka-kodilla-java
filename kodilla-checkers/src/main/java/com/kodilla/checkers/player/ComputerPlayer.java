package com.kodilla.checkers.player;

import com.kodilla.checkers.figures.*;
import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Move;
import com.kodilla.checkers.ui.MenuEnum;

public class ComputerPlayer implements Player {

    private final FigureColor color;
    private final MenuEnum.ComputerLevelEnum computerLevel;
    private static final int MAX_DEPTH = 4;

    public ComputerPlayer(FigureColor color, MenuEnum.ComputerLevelEnum computerLevel) {
        this.color = color;
        this.computerLevel = computerLevel;

    }

    public FigureColor getFigureColor() {
        return color;
    }

    @Override
    public Move getMove(Board board) {
        return null;
    }

    public String getName() {
        return "ENIAC";
    }
}
