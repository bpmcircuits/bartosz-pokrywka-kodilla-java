package com.kodilla.testing.shape;

public class Triangle implements Shape {

    private double base, height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public String getShapeName() {
        return "Triangle";
    }

    @Override
    public double getField() {
        return (base*height)/2;
    }
}
