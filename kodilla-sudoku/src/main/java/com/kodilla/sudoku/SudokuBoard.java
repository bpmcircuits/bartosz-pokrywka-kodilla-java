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

    public SudokuElement getCellAt(Point position) {
        return rows.get(position.y).getCols().get(position.x);
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
