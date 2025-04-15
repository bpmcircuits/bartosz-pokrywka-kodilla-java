package com.kodilla.checkers;

import com.kodilla.checkers.figures.Figure;
import com.kodilla.checkers.figures.FigureColor;
import com.kodilla.checkers.figures.Pawn;
import com.kodilla.checkers.figures.Queen;
import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Move;
import com.kodilla.checkers.player.HumanPlayer;
import com.kodilla.checkers.player.Player;
import com.kodilla.checkers.ui.UserInterface;
import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersTestSuite {

    private static Player[] players;

    @BeforeEach
    void setUp() {
        players = new Player[]{new HumanPlayer("PlayerA", FigureColor.WHITE), new HumanPlayer("PlayerB", FigureColor.BLACK)};
    }

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
        void testSimpleDeepCopy() throws CloneNotSupportedException {
            //given
            Board board = new Board(players[0], players[1]);
            board.init();
            //when
            Board deepCopy = board.deepCopy();
            //then
            assertNotEquals(board, deepCopy);
        }

        @Test
        void testDeepCopy() throws CloneNotSupportedException {
            //given
            Board board = new Board(players[0], players[1]);
            board.init();
            //when
            Board deepCopy = board.deepCopy();
            board.moveFigure(UserInterface.takeMove("A6B5"));
            //then
            System.out.println(board);
            System.out.println(deepCopy);
            assertNotEquals(board, deepCopy);
        }
    }

    @DisplayName("Board Move Tests")
    @Nested
    class BoardMoveTests {

        @Test
        void testIllegalWhiteMoveDirection() {
            //given
            Board board = new Board(players[0], players[1]);
            board.setFigure(new Point(2, 4), new Pawn(FigureColor.WHITE));
            System.out.println(board);
            //when
            boolean actual = board.moveFigure(UserInterface.takeMove("C5B6"));
            System.out.println(board);
            //then
            assertFalse(actual);
        }

        @Test
        void testIllegalBlackMoveDirection() {
            //given
            Board board = new Board(players[0], players[1]);
            board.setFigure(new Point(2, 4), new Pawn(FigureColor.BLACK));
            board.switchToNextTurn();
            System.out.println(board);
            //when
            boolean actual = board.moveFigure(UserInterface.takeMove("C5D4"));
            System.out.println(board);
            //then
            assertFalse(actual);
        }

        @Test
        void testSetPawnToQueen() {
            Board board = new Board(players[0], players[1]);
            board.setFigure(new Point(2, 0), new Pawn(FigureColor.WHITE));
            System.out.println(board);

            boolean actual = board.setPawnToQueen(new Point(2, 0), FigureColor.WHITE);
            System.out.println(board);

            assertTrue(actual);
        }

        @Test
        void testPawnToQueenConversion() {
            //given
            Board board = new Board(players[0], players[1]);
            board.setFigure(new Point(2, 1), new Pawn(FigureColor.WHITE));
            System.out.println(board);
            //when
            board.moveFigure(UserInterface.takeMove("C2B1"));
            System.out.println(board);
            Figure actual = board.getFigure(new Point(1,0));
            //then
            assertEquals(new Queen(FigureColor.WHITE), actual);
        }

        @Test
        void testCaptureFigureWithSuccess() {
            //given
            Board board = new Board(players[0], players[1]);
            board.setFigure(new Point(2, 4), new Pawn(FigureColor.WHITE));
            board.setFigure(new Point(3, 3), new Pawn(FigureColor.BLACK));
            board.setWhiteFigures(1);
            Move move = new Move(new Point(2, 4), new Point(4, 2));
            System.out.println(board);
            //when
            boolean actual = board.moveFigure(move);
            //then
            System.out.println(board);
            assertTrue(actual);
        }

        @Test
        void testCaptureWithNoSuccess() {
            //given
            Board board = new Board(players[0],players[1]);
            board.setFigure(new Point(0, 6), new Pawn(FigureColor.WHITE));
            board.setFigure(new Point(3, 3), new Pawn(FigureColor.BLACK));
            Move move = new Move(new Point(0, 6), new Point(2, 4));
            System.out.println(board);

            //when
            boolean actual = board.moveFigure(move);

            //then
            System.out.println(board);
            assertFalse(actual);
        }

        @Test
        void testTwoCaptureWithSuccessAndNoExtraMove() {
            //given
            Board board = new Board(players[0],players[1]);
            board.setFigure(new Point(2, 3), new Pawn(FigureColor.BLACK));
            board.setFigure(new Point(2, 1), new Pawn(FigureColor.BLACK));
            board.setFigure(new Point(5, 5), new Pawn(FigureColor.BLACK));
            board.setBlackFigures(3);
            board.setFigure(new Point(1, 4), new Pawn(FigureColor.WHITE));
            board.setWhiteFigures(1);
            System.out.println(board);

            //when
            boolean actualMoveOne = board.moveFigure(UserInterface.takeMove("B5D3"));
            System.out.println(board);
            boolean actualMoveTwo = board.moveFigure(UserInterface.takeMove("D3B1"));
            System.out.println(board);
            boolean actualMoveThree = board.moveFigure(UserInterface.takeMove("B1C2"));
            System.out.println(board);

            //then
            assertTrue(actualMoveOne);
            assertTrue(actualMoveTwo);
            assertFalse(actualMoveThree);
        }

        @Test
        void testThreeCaptureWithSuccessAndNoExtraMove() {
            //given
            Board board = new Board(players[0],players[1]);
            board.setFigure(new Point(2, 5), new Pawn(FigureColor.BLACK));
            board.setFigure(new Point(2, 3), new Pawn(FigureColor.BLACK));
            board.setFigure(new Point(2, 1), new Pawn(FigureColor.BLACK));
            board.setFigure(new Point(5, 5), new Pawn(FigureColor.BLACK));
            board.setBlackFigures(3);
            board.setFigure(new Point(1, 6), new Pawn(FigureColor.WHITE));
            board.setWhiteFigures(1);
            System.out.println(board);

            //when
            boolean actualMoveOne = board.moveFigure(UserInterface.takeMove("B7D52"));
            System.out.println(board);
            boolean actualMoveTwo = board.moveFigure(UserInterface.takeMove("D5B3"));
            System.out.println(board);
            boolean actualMoveThree = board.moveFigure(UserInterface.takeMove("B3D1"));
            System.out.println(board);
            boolean actualMoveFour = board.moveFigure(UserInterface.takeMove("D1E2"));
            System.out.println(board);

            //then
            assertTrue(actualMoveOne);
            assertTrue(actualMoveTwo);
            assertTrue(actualMoveThree);
            assertFalse(actualMoveFour);
        }

        @Test
        void testFirstCaptureButSecondMoveWithDifferentPawn() {
            //given
            Board board = new Board(players[0],players[1]);
            board.setFigure(new Point(2, 3), new Pawn(FigureColor.BLACK));
            board.setFigure(new Point(2, 1), new Pawn(FigureColor.BLACK));
            board.setBlackFigures(2);
            board.setFigure(new Point(1, 4), new Pawn(FigureColor.WHITE));
            board.setFigure(new Point(5, 5), new Pawn(FigureColor.WHITE));
            board.setWhiteFigures(2);
            System.out.println(board);

            //when
            boolean actualMoveOne = board.moveFigure(UserInterface.takeMove("B5D3"));
            System.out.println(board);
            boolean actualMoveTwo = board.moveFigure(UserInterface.takeMove("F6G7"));
            System.out.println(board);

            //then
            assertTrue(actualMoveOne);
            assertFalse(actualMoveTwo);
        }
    }
}
