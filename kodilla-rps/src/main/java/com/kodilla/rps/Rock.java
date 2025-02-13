package com.kodilla.rps;

public final class Rock implements Shape{
    @Override
    public int compareShape(Shape opponent) {
        if (opponent instanceof Rock) return 0;
        if (opponent instanceof Scissors) return 1;
        return -1;
    }

    @Override
    public String getShapeName() {
        return "Rock";
    }
}
