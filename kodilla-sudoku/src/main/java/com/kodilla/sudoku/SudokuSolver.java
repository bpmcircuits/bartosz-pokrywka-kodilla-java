package com.kodilla.sudoku;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SudokuSolver {

    private SudokuBoard board;
    private final int SUDOKU_SIZE;

    public SudokuSolver(SudokuBoard board, int SUDOKU_SIZE) {
        this.board = board;
        this.SUDOKU_SIZE = SUDOKU_SIZE;
    }

    public boolean solve() {
        boolean progress = true;
        while (progress) {
            progress = false;
            for (int row = 0; row < SUDOKU_SIZE; row++) {
                for (int col = 0; col < SUDOKU_SIZE; col++) {
                    Point currentPosition = new Point(col, row);
                    if (isElementEmpty(currentPosition)) continue;

                    List<Integer> possibleValues = removeRepeatingValues(currentPosition, row, col);

                    System.out.println("Possible values for cell at (" + col + "," + row + "): " + possibleValues);

                    if (possibleValues.size() == 1) {
                        int valueToSet = possibleValues.getFirst();
                        if (wouldCauseConflict(row, col, valueToSet)) return false;
                        board.setNumber(currentPosition, valueToSet);
                        progress = true;
                        continue;
                    }

                    Integer uniqueValue = findUniqueValueForCell(currentPosition);
                    if (uniqueValue != null) {
                        board.setNumber(currentPosition, uniqueValue);
                        progress = true;
                    }
                }
            }
        }
        return isSudokuComplete();
    }

    public List<Integer> removeRepeatingValues(Point currentPosition, int row, int col) {
        SudokuElement cell = board.getCellAt(currentPosition);
        List<Integer> possibleValues = cell.getPossibleValues();
        possibleValues.removeAll(getValuesInRow(row));
        possibleValues.removeAll(getValuesInColumn(col));
        possibleValues.removeAll(getValuesInBlock(col, row));
        return possibleValues;
    }

    public boolean isElementEmpty(Point currentPosition) {
        return board.getNumber(currentPosition) != SudokuElement.EMPTY;
    }


    public Integer findUniqueValueForCell(Point position) {
        SudokuElement current = board.getCellAt(position);
        if (current.getValue() != SudokuElement.EMPTY) {
            return null;
        }

        List<Integer> possibleValues = current.getPossibleValues();
        for (int candidate : possibleValues) {
            if (isUniqueInRegion(position, candidate, SudokuSolver.RegionType.ROW) ||
                    isUniqueInRegion(position, candidate, SudokuSolver.RegionType.COLUMN) ||
                    isUniqueInRegion(position, candidate, SudokuSolver.RegionType.BLOCK)) {
                return candidate;
            }
        }
        return null;
    }

    public enum RegionType { ROW, COLUMN, BLOCK }

    public boolean isUniqueInRegion(Point position, int candidate, SudokuSolver.RegionType regionType) {
        int startX, startY, endX, endY;

        switch (regionType) {
            case ROW:
                startX = 0;
                endX = SUDOKU_SIZE;
                startY = position.y;
                endY = position.y + 1;
                break;
            case COLUMN:
                startX = position.x;
                endX = position.x + 1;
                startY = 0;
                endY = SUDOKU_SIZE;
                break;
            case BLOCK:
                startX = (position.x / 3) * 3;
                endX = startX + 3;
                startY = (position.y / 3) * 3;
                endY = startY + 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown region type");
        }

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                if (x == position.x && y == position.y) {
                    continue;
                }
                SudokuElement other = board.getCellAt(new Point(x, y));
                if (other.getValue() == SudokuElement.EMPTY &&
                        other.getPossibleValues().contains(candidate)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean wouldCauseConflict(int row, int col, int value) {
        boolean existsInRow = getValuesInRow(row).contains(value);
        boolean existsInColumn = getValuesInColumn(col).contains(value);
        boolean existsInBlock = getValuesInBlock(col, row).contains(value);

        if (existsInRow || existsInColumn || existsInBlock) {
            System.err.println("CONFLICT: Value " + value + " at position (" + col + "," + row + ") already exists in:");
            if (existsInRow) System.err.println(" - row " + row);
            if (existsInColumn) System.err.println(" - column " + col);
            if (existsInBlock) System.err.println(" - 3x3 block");
            return true;
        }
        return false;
    }

    public boolean isSudokuComplete() {
        for (int row = 0; row < SUDOKU_SIZE; row++) {
            for (int col = 0; col < SUDOKU_SIZE; col++) {
                if (board.getNumber(new Point(col, row)) == SudokuElement.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Integer> getValuesInRow(int rowIndex) {
        return collectNonEmptyValues(i -> new Point(i, rowIndex));
    }

    public List<Integer> getValuesInColumn(int colIndex) {
        return collectNonEmptyValues(i -> new Point(colIndex, i));
    }

    public List<Integer> getValuesInBlock(int colIndex, int rowIndex) {
        List<Integer> values = new ArrayList<>();
        int startCol = (colIndex / 3) * 3;
        int startRow = (rowIndex / 3) * 3;
        for (int i = startCol; i < startCol + 3; i++) {
            for (int j = startRow; j < startRow + 3; j++) {
                int number = board.getNumber(new Point(i, j));
                if (number != SudokuElement.EMPTY) {
                    values.add(number);
                }
            }
        }
        return values;
    }

    public List<Integer> collectNonEmptyValues(Function<Integer, Point> pointMapper) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            int number = board.getNumber(pointMapper.apply(i));
            if (number != SudokuElement.EMPTY) {
                values.add(number);
            }
        }
        return values;
    }
}
