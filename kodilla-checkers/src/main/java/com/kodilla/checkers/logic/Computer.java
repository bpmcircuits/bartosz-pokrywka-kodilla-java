package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.*;
import com.kodilla.checkers.ui.MenuEnum;

import java.awt.*;
import java.util.Random;

public class Computer {

    private final FigureColor computerFigure;
    private final MenuEnum.ComputerLevelEnum computerLevel;
    private final Board board;
    private static final int MAX_DEPTH = 4;

    public Computer(Board board, FigureColor computerFigure, MenuEnum.ComputerLevelEnum computerLevel) {
        this.computerFigure = computerFigure;
        this.computerLevel = computerLevel;
        this.board = board;
    }

    public FigureColor getComputerFigure() {
        return computerFigure;
    }

    public String getComputerName() {
        return "ENIAC";
    }
}
