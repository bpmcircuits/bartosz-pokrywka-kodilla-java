package com.kodilla.checkers.figures;

import com.kodilla.checkers.logic.FigureColor;

public class Pawn implements Figure {

    private final FigureColor color;

    public Pawn(FigureColor color) {
        this.color = color;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return ((color == FigureColor.BLACK) ? "b" : "w") + "P";
    }
}
