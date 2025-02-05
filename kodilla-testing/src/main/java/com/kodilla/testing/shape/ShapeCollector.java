package com.kodilla.testing.shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeCollector {

    private List<Shape> shapes = new ArrayList<>();

    public void addFigure(Shape shape) {
        shapes.add(shape);
    }

    public boolean removeFigure(Shape shape) {
        if (shapes.contains(shape)) {
            shapes.remove(shape);
            return true;
        } else return false;
    }

    public Shape getFigure(int n) {
        return (shapes == null || shapes.isEmpty() || n < 0 || n >= shapes.size()) ? null : shapes.get(n);
    }

    public String showFigures() {
        StringBuilder result = new StringBuilder();
        shapes.forEach(shape -> result.append(shape.getShapeName()).append(" "));
        return result.toString().trim();

    }
}
