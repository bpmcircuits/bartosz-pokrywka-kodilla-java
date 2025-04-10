package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {

    private List<SudokuElement> cols = new ArrayList<>();
    private static final int COLUMN_SIZE = 9;

    public SudokuRow() {
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
        for (int col = 0; col < COLUMN_SIZE; col++) {
            sb.append(cols.get(col).toString()).append(" ");
            if ((col + 1) % 3 == 0) sb.append("| ");
        }
        return sb.toString();
    }

}
