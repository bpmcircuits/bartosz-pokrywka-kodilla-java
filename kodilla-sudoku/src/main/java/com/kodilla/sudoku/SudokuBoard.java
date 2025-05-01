package com.kodilla.sudoku;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SudokuBoard {

    private final List<SudokuRow> rows = new ArrayList<>();
    private final int sudokuSize;

    public SudokuBoard(int sudokuSize) {
        this.sudokuSize = sudokuSize;
        for (int i = 0; i < sudokuSize; i++) {
            rows.add(new SudokuRow(sudokuSize));
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

    public int getSudokuSize() {
        return sudokuSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-------+-------+-------+\n");
        for (int i = 0; i < sudokuSize; i++) {
            sb.append(rows.get(i).toString());
            sb.append("\n");
            if ((i + 1) % 3 == 0)
                sb.append("+-------+-------+-------+\n");
        }
        return sb.toString();
    }


}
