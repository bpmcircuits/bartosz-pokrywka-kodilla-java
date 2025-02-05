package com.kodilla.testing.shape;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("TDD: ShapeCollector Test Suite.")
public class ShapeCollectorTestSuite {

    @Nested
    @DisplayName("Tests for square shape.")
    class SquareTests {

        @Test
        void addShapeAndGetShape() {

            ShapeCollector shapeCollector = new ShapeCollector();
            Shape square = new Square(4,5);

            shapeCollector.addFigure(square);

            assertEquals(square, shapeCollector.getFigure(0));
        }

        @Test
        void deleteShape() {

            ShapeCollector shapeCollector = new ShapeCollector();
            Shape square = new Square(4,5);

            shapeCollector.addFigure(square);

            boolean result = shapeCollector.removeFigure(square);

            assertTrue(result);
            assertNull(shapeCollector.getFigure(0));
        }

        @Test
        void checkTheCorrectField() {

            Shape square = new Square(4,5);

            double expectedField = 20;

            assertEquals(expectedField, square.getField());
        }

    }

    @Nested
    @DisplayName("Tests for circle shape.")
    class CircleTests {

        @Test
        void addShapeAndGetShape() {

            ShapeCollector shapeCollector = new ShapeCollector();
            Shape circle = new Circle(4.5);

            shapeCollector.addFigure(circle);

            assertEquals(circle, shapeCollector.getFigure(0));
        }

        @Test
        void deleteShape() {

            ShapeCollector shapeCollector = new ShapeCollector();
            Shape circle = new Circle(4.5);

            shapeCollector.addFigure(circle);

            boolean result = shapeCollector.removeFigure(circle);

            assertTrue(result);
            assertNull(shapeCollector.getFigure(0));
        }

        @Test
        void checkTheCorrectField() {

            Shape circle = new Circle(4);

            double expectedField = 50.26;
            double delta = 0.01;

            assertEquals(expectedField, circle.getField(), delta);
        }

    }

    @Nested
    @DisplayName("Tests for triangle shape.")
    class TriangleTests {

        @Test
        void addShapeAndGetShape() {

            ShapeCollector shapeCollector = new ShapeCollector();
            Shape triangle = new Triangle(4,5);

            shapeCollector.addFigure(triangle);

            assertEquals(triangle, shapeCollector.getFigure(0));
        }

        @Test
        void deleteShape() {

            ShapeCollector shapeCollector = new ShapeCollector();
            Shape triangle = new Triangle(4,5);

            shapeCollector.addFigure(triangle);

            boolean result = shapeCollector.removeFigure(triangle);

            assertTrue(result);
            assertNull(shapeCollector.getFigure(0));
        }

        @Test
        void checkTheCorrectField() {

            Shape triangle = new Triangle(4,5);

            double expectedField = 10;

            assertEquals(expectedField, triangle.getField());
        }
    }

    @Nested
    @DisplayName("Tests for all shapes.")
    class ShapeTests {

        @Test
        void checkCorrectShapeNamesOrder() {

            ShapeCollector shapeCollector = new ShapeCollector();

            shapeCollector.addFigure(new Square(5,4));
            shapeCollector.addFigure(new Circle(4.5));
            shapeCollector.addFigure(new Triangle(3,4));

            String shapeNames = "Square Circle Triangle";

            assertEquals(shapeNames, shapeCollector.showFigures());

        }
    }
}
