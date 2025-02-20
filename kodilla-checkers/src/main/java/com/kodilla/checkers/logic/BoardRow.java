package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.Figure;
import com.kodilla.checkers.figures.None;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {

    private final List<Figure> cols = new ArrayList<>();

    public BoardRow() {
        for (int col = 0; col < 8; col++)
            cols.add(new None());
    }

    public List<Figure> getCols() {
        return cols;
    }

    @Override
    public String toString() {
        String s = "|";
        for (int col = 0; col < 8; col++)
            s += cols.get(col) + "|";
        s += "\n";
        return s;
    }
}
