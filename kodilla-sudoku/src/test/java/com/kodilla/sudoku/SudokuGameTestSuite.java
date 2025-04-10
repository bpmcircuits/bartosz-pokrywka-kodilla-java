package com.kodilla.sudoku;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGameTestSuite {

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
            SudokuRow sudokuRow = new SudokuRow();
            //when then
            System.out.println(sudokuRow);
        }
    }

    @DisplayName("Tests for SudokuBoard class")
    @Nested
    class testsSudokuBoard {

        @Test
        void createSudokuBoard() {
            SudokuBoard board = new SudokuBoard().initBoard();
            assertNotNull(board);
        }

        @Test
        void testRenderBoard() {
            //given
            SudokuBoard board = new SudokuBoard().initBoard();
            //when then
            System.out.println(board);
        }

        @Test
        void testSetNumber() {
            //given
            SudokuBoard board = new SudokuBoard().initBoard();
            board.setNumber(new Point(0, 0), 5);
            //when
            int actual = board.getNumber(new Point(0, 0));
            //then
            System.out.println(board);
            assertEquals(5, actual);

        }
    }
}