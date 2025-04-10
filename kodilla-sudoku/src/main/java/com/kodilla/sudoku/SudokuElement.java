package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuElement {

    public static int EMPTY = -1;
    private int value;
    private List<Integer> possibleValues = new ArrayList<>();

    public SudokuElement() {
        this.value = EMPTY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean addToPossibleValues(int value) {
        return possibleValues.add(value);
    }

    public void removeFromPossibleValues(int value) {
        possibleValues.remove(value);
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    @Override
    public String toString() {
        return value == EMPTY ? "." : String.valueOf(value);
    }
}
