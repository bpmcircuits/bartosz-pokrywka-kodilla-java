package com.kodilla.rps;

public final class Paper implements Shape{
    @Override
    public int compareShape(Shape opponent) {
        if (opponent instanceof Paper) return 0;
        if (opponent instanceof Rock) return 1;
        return -1;
    }

    @Override
    public String getShapeName() {
        return "Paper";
    }
}
