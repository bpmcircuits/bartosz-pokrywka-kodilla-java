package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {

    private final List<SudokuElement> cols = new ArrayList<>();
    private final int columnSize;

    public SudokuRow(int columnSize) {
        this.columnSize = columnSize;
        for (int i = 0; i < 9; i++) {
            cols.add(new SudokuElement());
        }
    }

    public List<SudokuElement> getCols() {
        return cols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("| ");
        for (int col = 0; col < columnSize; col++) {
            sb.append(cols.get(col).toString()).append(" ");
            if ((col + 1) % 3 == 0) sb.append("| ");
        }
        return sb.toString();
    }

}
