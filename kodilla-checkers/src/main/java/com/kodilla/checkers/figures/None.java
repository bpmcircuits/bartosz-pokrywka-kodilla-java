package com.kodilla.checkers.figures;

public class None implements Figure {

    @Override
    public FigureColor getColor() {
        return FigureColor.NONE;
    }

    @Override
    public String toString() {
        return "  ";
    }

    @Override
    public Figure clone() {
        return new None();
    }
}
