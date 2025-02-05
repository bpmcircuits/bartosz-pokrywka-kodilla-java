package com.kodilla.testing.shape;

public class Square implements Shape {

    private double sideA, sideB;

    public Square(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public String getShapeName() {
        return "Square";
    }

    @Override
    public double getField() {
        return sideA*sideB;
    }
}
