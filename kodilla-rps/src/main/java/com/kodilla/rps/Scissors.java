package com.kodilla.rps;

public final class Scissors implements Shape{
    @Override
    public int compareShape(Shape opponent) {
        if (opponent instanceof Scissors) return 0;
        if (opponent instanceof Paper) return 1;
        return -1;
    }

    @Override
    public String getShapeName() {
        return "Scissors";
    }
}
