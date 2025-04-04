package com.kodilla.checkers.figures;

public class Queen implements Figure {

    private final FigureColor color;

    public Queen(FigureColor color) {
        this.color = color;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return ((color == FigureColor.BLACK) ? "b" : "w") + "Q";
    }
}
