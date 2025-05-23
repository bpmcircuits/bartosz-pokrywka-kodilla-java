package com.kodilla.sudoku;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGameTestSuite {

    private SudokuBoard sudokuBoard;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard(9);
        sudokuBoard.setNumber(new Point(1, 0), 2);
        sudokuBoard.setNumber(new Point(3, 0), 5);
        sudokuBoard.setNumber(new Point(5, 0), 1);
        sudokuBoard.setNumber(new Point(7, 0), 9);
        sudokuBoard.setNumber(new Point(0, 1), 8);
        sudokuBoard.setNumber(new Point(3, 1), 2);
        sudokuBoard.setNumber(new Point(5, 1), 3);
        sudokuBoard.setNumber(new Point(8, 1), 6);
        sudokuBoard.setNumber(new Point(1, 2), 3);
        sudokuBoard.setNumber(new Point(4, 2), 6);
        sudokuBoard.setNumber(new Point(7, 2), 7);
        sudokuBoard.setNumber(new Point(2, 3), 1);
        sudokuBoard.setNumber(new Point(6, 3), 6);
        sudokuBoard.setNumber(new Point(0, 4), 5);
        sudokuBoard.setNumber(new Point(1, 4), 4);
        sudokuBoard.setNumber(new Point(7, 4), 1);
        sudokuBoard.setNumber(new Point(8, 4), 9);
        sudokuBoard.setNumber(new Point(2, 5), 2);
        sudokuBoard.setNumber(new Point(6, 5), 7);
        sudokuBoard.setNumber(new Point(1, 6), 9);
        sudokuBoard.setNumber(new Point(4, 6), 3);
        sudokuBoard.setNumber(new Point(7, 6), 8);
        sudokuBoard.setNumber(new Point(0, 7), 2);
        sudokuBoard.setNumber(new Point(3, 7), 8);
        sudokuBoard.setNumber(new Point(5, 7), 4);
        sudokuBoard.setNumber(new Point(8, 7), 7);
        sudokuBoard.setNumber(new Point(1, 8), 1);
        sudokuBoard.setNumber(new Point(3, 8), 9);
        sudokuBoard.setNumber(new Point(5, 8), 7);
        sudokuBoard.setNumber(new Point(7, 8), 6);

    }
    @DisplayName("Tests for SudokuGame class")
    @Nested
    class testsSudokuGame {
        @Test
        void createSudokuGame() {
            //given when
            SudokuGame sudokuGame = new SudokuGame();
            //then
            assertNotNull(sudokuGame);
        }

        @Disabled
        @Test
        void testIfGameIsFinished() {
            //given
            SudokuGame sudokuGame = new SudokuGame();
            //when
            boolean actual = sudokuGame.resolveSudoku();
            //then
            assertTrue(actual);
        }
    }

    @DisplayName("Tests for SudokuRow class")
    @Nested
    class testsSudokuRow {

        @Test
        void createSudokuRow() {
            //given
            SudokuRow sudokuRow = new SudokuRow(9);
            //when then
            System.out.println(sudokuRow);
        }
    }

    @DisplayName("Tests for SudokuBoard class")
    @Nested
    class testsSudokuBoard {

        @Test
        void createSudokuBoard() {
            SudokuBoard board = new SudokuBoard(9);
            assertNotNull(board);
        }

        @Test
        void testRenderBoard() {
            //given
            SudokuBoard board = new SudokuBoard(9);
            //when then
            System.out.println(board);
        }

        @Test
        void testSetNumber() {
            //given
            SudokuBoard board = new SudokuBoard(9);
            board.setNumber(new Point(0, 0), 5);
            //when
            int actual = board.getNumber(new Point(0, 0));
            //then
            System.out.println(board);
            assertEquals(5, actual);

        }
    }

    @DisplayName("Tests for SudokuSolver class")
    @Nested
    class testsSudokuSolver {

        @Test
        void testCheckForRow() {
            //given
            SudokuBoard board = new SudokuBoard(9);
            SudokuSolver sudokuSolver = new SudokuSolver(board, 9);
            board.setNumber(new Point(1, 0), 2);
            board.setNumber(new Point(3, 0), 5);
            board.setNumber(new Point(6, 0), 7);
            board.setNumber(new Point(8, 0), 9);
            //when
            List<Integer> actual = sudokuSolver.getValuesInRow(0);
            //then
            System.out.println(board);
            assertEquals(List.of(2, 5, 7, 9), actual);

        }

        @Test
        void testCheckForColumn() {
            //given
            SudokuBoard board = new SudokuBoard(9);
            SudokuSolver sudokuSolver = new SudokuSolver(board, 9);
            board.setNumber(new Point(0, 2), 8);
            board.setNumber(new Point(0, 4), 6);
            board.setNumber(new Point(0, 6), 2);
            board.setNumber(new Point(0, 8), 1);
            //when
            List<Integer> actual = sudokuSolver.getValuesInColumn(0);
            //then
            System.out.println(board);
            assertEquals(List.of(8, 6, 2, 1), actual);
        }

        @Test
        void testCheckForBlock() {
            //given
            List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
            SudokuBoard board = new SudokuBoard(9);
            SudokuSolver sudokuSolver = new SudokuSolver(board, 9);
            board.setNumber(new Point(0, 0), 1);
            board.setNumber(new Point(1, 0), 2);
            board.setNumber(new Point(2, 0), 3);
            board.setNumber(new Point(0, 1), 4);
            board.setNumber(new Point(1, 1), 5);
            board.setNumber(new Point(2, 1), 6);
            board.setNumber(new Point(0, 2), 7);
            board.setNumber(new Point(1, 2), 8);
            board.setNumber(new Point(2, 2), 9);
            //when
            List<Integer> actual = sudokuSolver.getValuesInBlock(0, 0);
            List<Integer> actualTwo = sudokuSolver.getValuesInBlock(1, 1);
            List<Integer> actualThree = sudokuSolver.getValuesInBlock(2, 2);
            //then
            System.out.println(board);
            assertTrue(expected.containsAll(actual));
            assertTrue(expected.containsAll(actualTwo));
            assertTrue(expected.containsAll(actualThree));
        }

        @Test
        void testAlgorithm() {
            //given
            SudokuSolver sudokuSolver = new SudokuSolver(sudokuBoard, 9);
            //when then
            System.out.println(sudokuBoard);
            sudokuSolver.solveFullPuzzle();
            System.out.println(sudokuBoard);
        }
    }

    @DisplayName("Tests for UserInterface")
    @Nested
    class testsUserInterface {
        private final InputStream originalSystemIn = System.in;

        @AfterEach
        void tearDown() {
            System.setIn(originalSystemIn);
            UserInterface.resetScanner();
        }

        @Test
        void testChoiceOfNumbersWithoutSpacesAndComas() {
            //given
            String simulatedInput = "123456789\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);
            UserInterface.resetScanner();

            //when
            List<Integer> actual = UserInterface.chooseNumbersOrSolve();

            //then
            System.out.println(actual);
            assertEquals(List.of(1,2,3,4,5,6,7,8,9), actual);
        }

        @Test
        void testChoiceOfNumbersWithSpaces() {
            //given
            String simulatedInput = "1 2 3 4 56789\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);
            UserInterface.resetScanner();

            //when
            List<Integer> actual = UserInterface.chooseNumbersOrSolve();

            //then
            System.out.println(actual);
            assertEquals(List.of(1,2,3,4,5,6,7,8,9), actual);
        }

        @Test
        void testChoiceOfNumbersWithSpacesAndComas() {
            //given
            String simulatedInput = "1 ,2 3 4 5,678,9\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);
            UserInterface.resetScanner();

            //when
            List<Integer> actual = UserInterface.chooseNumbersOrSolve();

            //then
            System.out.println(actual);
            assertEquals(List.of(1,2,3,4,5,6,7,8,9), actual);
        }

        @Test
        void testFillAndSolve() {
            //given
            String simulatedInput = "115123157216241258265329338386418456493514548563591617652696726778844851869559587999\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);
            SudokuBoard board = new SudokuBoard(9);
            SudokuSolver sudokuSolver = new SudokuSolver(board, 9);
            SudokuGame sudokuGame = new SudokuGame();
            List<Integer> cordsAndNumbers = UserInterface.chooseNumbersOrSolve();
            //when
            assertNotNull(cordsAndNumbers);
            sudokuGame.setNumbersToBoard(board, cordsAndNumbers);
            System.out.println(board);
            //then
            boolean actual = sudokuSolver.solveFullPuzzle();
            System.out.println(board);
            assertTrue(actual);

        }
    }
}