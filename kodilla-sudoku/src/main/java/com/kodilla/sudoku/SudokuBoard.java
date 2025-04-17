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

    public int getNumber(Point point) {
        if (point == null) return -1;
        return rows.get(point.y).getCols().get(point.x).getValue();
    }

    public void setNumber(Point point, int number) {
        if (point == null) return;
        rows.get(point.y).getCols().get(point.x).setValue(number);
    }

    public boolean solveSudoku() {
        boolean progress = true;
        while (progress) {
            progress = false;
            for (int row = 0; row < SUDOKU_SIZE; row++) {
                for (int col = 0; col < SUDOKU_SIZE; col++) {
                    Point currentPosition = new Point(col, row);
                    if (getNumber(currentPosition) != SudokuElement.EMPTY) {
                        continue;
                    }

                    SudokuElement cell = getCellAt(currentPosition);
                    List<Integer> possibleValues = cell.getPossibleValues();
                    possibleValues.removeAll(getValuesInRow(row));
                    possibleValues.removeAll(getValuesInColumn(col));
                    possibleValues.removeAll(getValuesInBlock(col, row));

                    System.out.println("Possible values for cell at (" + col + "," + row + "): " + possibleValues);

                    if (possibleValues.size() == 1) {
                        int valueToSet = possibleValues.getFirst();
                        if (wouldCauseConflict(row, col, valueToSet)) {
                            return false;
                        }
                        setNumber(currentPosition, valueToSet);
                        progress = true;
                        continue;
                    }

                    Integer uniqueValue = findUniqueValueForCell(currentPosition);
                    if (uniqueValue != null) {
                        setNumber(currentPosition, uniqueValue);
                        progress = true;
                    }
                }
            }
        }
        return isSudokuComplete();
    }

    private SudokuElement getCellAt(Point position) {
        return rows.get(position.y).getCols().get(position.x);
    }

    public Integer findUniqueValueForCell(Point position) {
        SudokuElement current = getCellAt(position);
        if (current.getValue() != SudokuElement.EMPTY) {
            return null;
        }

        List<Integer> possibleValues = current.getPossibleValues();
        for (int candidate : possibleValues) {
            if (isUniqueInRegion(position, candidate, RegionType.ROW) ||
                    isUniqueInRegion(position, candidate, RegionType.COLUMN) ||
                    isUniqueInRegion(position, candidate, RegionType.BLOCK)) {
                return candidate;
            }
        }
        return null;
    }

    private enum RegionType { ROW, COLUMN, BLOCK }

    private boolean isUniqueInRegion(Point position, int candidate, RegionType regionType) {
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
                SudokuElement other = rows.get(y).getCols().get(x);
                if (other.getValue() == SudokuElement.EMPTY &&
                        other.getPossibleValues().contains(candidate)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean wouldCauseConflict(int row, int col, int value) {
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

    private boolean isSudokuComplete() {
        for (int row = 0; row < SUDOKU_SIZE; row++) {
            for (int col = 0; col < SUDOKU_SIZE; col++) {
                if (getNumber(new Point(col, row)) == SudokuElement.EMPTY) {
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
                int number = getNumber(new Point(i, j));
                if (number != SudokuElement.EMPTY) {
                    values.add(number);
                }
            }
        }
        return values;
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
