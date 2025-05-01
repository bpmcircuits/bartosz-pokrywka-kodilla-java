package com.kodilla.sudoku;

import java.awt.*;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class SudokuGame {

    public boolean resolveSudoku() {
        while (true) {
            MenuEnum.MainMenuOption mainMenuOption = UserInterface.mainMenuChoice();
            switch (mainMenuOption) {
                case NEW_GAME -> playSudoku();
                case EXIT -> {
                    return true;
                }
            }
        }
    }

    private void playSudoku() {
        int boardSize = UserInterface.chooseBoardSize();
        SudokuBoard board = new SudokuBoard(boardSize);
        SudokuSolver solver = new SudokuSolver(board, boardSize);
        while (true) {
            System.out.println(board);
            List<Integer> cordsAndNumbers = UserInterface.chooseNumbersOrSolve();
            if (cordsAndNumbers == null) {
                solver.solveFullPuzzle();
                System.out.println(board);
                return;
            } else {
                setNumbersToBoard(board, cordsAndNumbers);
            }
        }
    }

    private void setNumbersToBoard(SudokuBoard board, List<Integer> cordsAndNumbers) {

        if (!cordsAndNumbers.stream().allMatch(n -> n >= 0 && n <= board.getSudokuSize())) {
            UserInterface.wrongNumber();
            return;
        }

        List<List <Integer>> numberGroups = IntStream.range(0, cordsAndNumbers.size() / 3)
                .mapToObj(getGroupsOfThree(cordsAndNumbers))
                .toList();

        numberGroups.forEach(group -> {
            Point point = new Point(group.get(0) - 1, group.get(1) - 1);
            board.setNumber(point, group.get(2));
        });

    }

    private static IntFunction<List<Integer>> getGroupsOfThree(List<Integer> cordsAndNumbers) {
        return i -> cordsAndNumbers.subList(i * 3, i * 3 + 3);
    }

}
