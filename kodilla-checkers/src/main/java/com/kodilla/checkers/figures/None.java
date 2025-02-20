package com.kodilla.checkers.figures;

import com.kodilla.checkers.logic.FigureColor;

public class None implements Figure {

    @Override
    public FigureColor getColor() {
        return FigureColor.NONE;
    }

    @Override
    public String toString() {
        return "  ";
    }
}
