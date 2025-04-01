package com.kodilla.checkers;

import com.kodilla.checkers.figures.FigureColor;
import com.kodilla.checkers.figures.Pawn;
import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Move;
import com.kodilla.checkers.ui.UserInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersTestSuite {

    @Test
    void testCorrectNameToPointTranslation() {
        //given when
        Point actualPoint1 = UserInterface.findPoint("A1");
        Point actualPoint2 = UserInterface.findPoint("A8");
        Point actualPoint3 = UserInterface.findPoint("H1");
        Point actualPoint4 = UserInterface.findPoint("H8");
        //then
        Point expectedPoint1 = new Point(0, 0);
        Point expectedPoint2 = new Point(0, 7);
        Point expectedPoint3 = new Point(7, 0);
        Point expectedPoint4 = new Point(7, 7);
        assertEquals(expectedPoint1, actualPoint1);
        assertEquals(expectedPoint2, actualPoint2);
        assertEquals(expectedPoint3, actualPoint3);
        assertEquals(expectedPoint4, actualPoint4);
    }

    @Test
    void testCorrectMoveTranslation() {
        Move actualMove = UserInterface.takeMove("A1B2");
        Move expectedMove = new Move(new Point(0, 0), new Point(1, 1));
        assertEquals(expectedMove, actualMove);
    }

    @DisplayName("Board Tests")
    @Nested
    class BoardTests {
        @Test
        void testEmptyPlaceOnBoard() {
            //given
            Board board = new Board();
            board.init();
            //when
            boolean actual = board.isPlaceEmpty(new Point(0, 0));
            //then
            System.out.println(board);
            assertTrue(actual);
        }

        @Test
        void testNotEmptyPlaceOnBoard() {
            //given
            Board board = new Board();
            board.init();
            //when
            boolean actual = board.isPlaceEmpty(new Point(1, 0));
            //then
            System.out.println(board);
            assertFalse(actual);
        }
    }

    @DisplayName("Board Move Tests")
    @Nested
    class BoardMoveTests {
        @Test
        void testNonDiagonalMove() {
            //given
            Board board = new Board();
            board.init();
            //when
            Move moveX = new Move(new Point(0, 0), new Point(1, 0));
            Move moveY = new Move(new Point(0, 0), new Point(0, 1));
            boolean actualX = board.isMoveDiagonal(moveX);
            boolean actualY = board.isMoveDiagonal(moveY);
            //then
            System.out.println(board);
            assertFalse(actualX);
            assertFalse(actualY);
        }

        @Test
        void testDiagonalMove() {
            //given
            Board board = new Board();
            board.init();
            //when
            Move move = new Move(new Point(0, 0), new Point(1, 1));
            boolean actual = board.isMoveDiagonal(move);
            //then
            System.out.println(board);
            assertTrue(actual);
        }

        @Test
        void testFindAndAttackWithSuccess() {
            //given
            Board board = new Board();
            board.setFigure(new Point(0, 6), new Pawn(FigureColor.WHITE));
            board.setFigure(new Point(3, 3), new Pawn(FigureColor.BLACK));
            Move move = new Move(new Point(0, 6), new Point(4, 2));
            System.out.println(board);

            //when
            boolean actual = board.showAndAttackOpponents(move);
            board.move(move);

            //then
            System.out.println(board);
            assertTrue(actual);
        }

        @Test
        void testFindAndAttackWithNoSuccess() {
            //given
            Board board = new Board();
            board.setFigure(new Point(0, 6), new Pawn(FigureColor.WHITE));
            board.setFigure(new Point(3, 3), new Pawn(FigureColor.BLACK));
            Move move = new Move(new Point(0, 6), new Point(2, 4));
            System.out.println(board);

            //when
            boolean actual = board.showAndAttackOpponents(move);
            board.move(move);

            //then
            System.out.println(board);
            assertFalse(actual);
        }
    }
}
