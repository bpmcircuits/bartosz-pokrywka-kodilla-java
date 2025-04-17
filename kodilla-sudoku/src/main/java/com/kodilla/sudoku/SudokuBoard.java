package com.kodilla.sudoku;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;

public class SudokuBoard {

    private final List<SudokuRow> rows = new ArrayList<>();
    private static final int SUDOKU_SIZE = 9;

    public SudokuBoard() {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            rows.add(new SudokuRow());
        }
    }

    public SudokuBoard initBoard() {
        return this;
    }

    public int getNumber(Point point) {
        if (point == null) return -1;
        return rows.get(point.y).getCols().get(point.x).getValue();
    }

    public void setNumber(Point point, int number) {
        if (point == null) return;
        rows.get(point.y).getCols().get(point.x).setValue(number);
    }

    public boolean sudokuAlgorithm() {
        boolean atLeastOneOperationPerformed = true;
        do {
            atLeastOneOperationPerformed = false;
            for (int col = 0; col < SUDOKU_SIZE; col++) {
                for (int row = 0; row < SUDOKU_SIZE; row++) {
                    if (getNumber(new Point(col, row)) != SudokuElement.EMPTY) {
                        continue;
                    }
                    List<Integer> possibleValues = rows.get(row).getCols().get(col).getPossibleValues();
                    possibleValues.removeAll(checkForRow(row));
                    possibleValues.removeAll(checkForColumn(col));
                    possibleValues.removeAll(checkForBlock(col, row));
                    System.out.println("Możliwe wartości dla pola " + col + " i " + row + " to " + possibleValues);
                    if (possibleValues.size() == 1) {
                        int valueToSet = possibleValues.getFirst();
                        Point point = new Point(col, row);

                        if (checkForMorePossibilitiesError(row, valueToSet, col)) return false;

                        setNumber(point, valueToSet);
                        atLeastOneOperationPerformed = true;
                    }
                    Integer uniqueValue = findUniqueValueForPoint(new Point(col, row));
                    if (uniqueValue != null) {
                        setNumber(new Point(col, row), uniqueValue);
                        atLeastOneOperationPerformed = true;
                    }
                }
            }

        } while (atLeastOneOperationPerformed);

        return checkForCompleteSudoku();
    }

    public Integer findUniqueValueForPoint(Point point) {
        SudokuElement current = rows.get(point.y).getCols().get(point.x);
        if (current.getValue() != SudokuElement.EMPTY) return null;

        List<Integer> possibleValues = current.getPossibleValues();

        for (int candidate : possibleValues) {
            boolean uniqueInRow = true;
            for (int x = 0; x < SUDOKU_SIZE; x++) {
                if (x == point.x) continue;
                SudokuElement other = rows.get(point.y).getCols().get(x);
                if (other.getValue() == SudokuElement.EMPTY && other.getPossibleValues().contains(candidate)) {
                    uniqueInRow = false;
                    break;
                }
            }
            if (uniqueInRow) return candidate;

            boolean uniqueInColumn = true;
            for (int y = 0; y < SUDOKU_SIZE; y++) {
                if (y == point.y) continue;
                SudokuElement other = rows.get(y).getCols().get(point.x);
                if (other.getValue() == SudokuElement.EMPTY && other.getPossibleValues().contains(candidate)) {
                    uniqueInColumn = false;
                    break;
                }
            }
            if (uniqueInColumn) return candidate;

            boolean uniqueInBlock = true;
            int startCol = (point.x / 3) * 3;
            int startRow = (point.y / 3) * 3;
            for (int dx = 0; dx < 3; dx++) {
                for (int dy = 0; dy < 3; dy++) {
                    int x = startCol + dx;
                    int y = startRow + dy;
                    if (x == point.x && y == point.y) continue;
                    SudokuElement other = rows.get(y).getCols().get(x);
                    if (other.getValue() == SudokuElement.EMPTY && other.getPossibleValues().contains(candidate)) {
                        uniqueInBlock = false;
                        break;
                    }
                }
                if (!uniqueInBlock) break;
            }
            if (uniqueInBlock) return candidate;
        }

        return null;
    }

    private boolean checkForMorePossibilitiesError(int row, int valueToSet, int col) {
        boolean existsInRow = checkForRow(row).contains(valueToSet);
        boolean existsInColumn = checkForColumn(col).contains(valueToSet);
        boolean existsInSquare = checkForBlock(col, row).contains(valueToSet);

        if (existsInRow || existsInColumn || existsInSquare) {
            System.err.println("KONFLIKT: Wartość " + valueToSet + " na pozycji (" + col + "," + row + ") już istnieje w:");
            if (existsInRow) System.err.println(" - wierszu " + row);
            if (existsInColumn) System.err.println(" - kolumnie " + col);
            if (existsInSquare) System.err.println(" - kwadracie 3x3");

            return true;
        }
        return false;
    }

    private boolean checkForCompleteSudoku() {
        for (int col = 0; col < SUDOKU_SIZE; col++) {
            for (int row = 0; row < SUDOKU_SIZE; row++) {
                if (getNumber(new Point(col, row)) == SudokuElement.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer> checkForRow(int rowIndex) {
        return collectNonEmptyValues(i -> new Point(i, rowIndex));
    }


    public List<Integer> checkForColumn(int colIndex) {
        return collectNonEmptyValues(i -> new Point(colIndex, i));
    }


    private List<Integer> collectNonEmptyValues(Function<Integer, Point> pointMapper) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            int number = getNumber(pointMapper.apply(i));
            if (number != SudokuElement.EMPTY) {
                values.add(number);
            }
        }
        return values;
    }


    public List<Integer> checkForBlock(int colIndex, int rowIndex) {
        List<Integer> values = new ArrayList<>();
        int startCol = (colIndex / 3) * 3;
        int startRow = (rowIndex / 3) * 3;
        for (int i = startCol; i < startCol + 3; i++) {
            for (int j = startRow; j < startRow + 3; j++) {
                int number = getNumber(new Point(i, j));
                if (number != SudokuElement.EMPTY) {
                    values.add(number);
                }
            }
        }
        return values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-------+-------+-------+\n");
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            sb.append(rows.get(i).toString());
            sb.append("\n");
            if ((i + 1) % 3 == 0)
                sb.append("+-------+-------+-------+\n");
        }
        return sb.toString();
    }


}
