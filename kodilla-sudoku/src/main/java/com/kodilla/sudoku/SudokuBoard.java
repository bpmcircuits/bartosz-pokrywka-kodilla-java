package com.kodilla.sudoku;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

    private final List<SudokuRow> rows = new ArrayList<>();
    private static final int ROW_SIZE = 9;

    public SudokuBoard() {
        for (int i = 0; i < ROW_SIZE; i++) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-------+-------+-------+\n");
        for (int i = 0; i < ROW_SIZE; i++) {
            sb.append(rows.get(i).toString());
            sb.append("\n");
            if ((i + 1) % 3 == 0)
                sb.append("+-------+-------+-------+\n");
        }
        return sb.toString();
    }
}
