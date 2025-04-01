package com.kodilla.checkers.player;

import com.kodilla.checkers.figures.FigureColor;
import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Move;

public interface Player {
    Move getMove(Board board);
    String getName();
    FigureColor getFigureColor();
}
