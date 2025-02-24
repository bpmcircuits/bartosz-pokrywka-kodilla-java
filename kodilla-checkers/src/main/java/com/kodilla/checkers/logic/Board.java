package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.Figure;
import com.kodilla.checkers.figures.FigureColor;
import com.kodilla.checkers.figures.None;
import com.kodilla.checkers.figures.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<BoardRow> rows = new ArrayList<>();

    public Board() {
        for (int row = 0; row < 8; row++)
            rows.add(new BoardRow());
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().set(col, figure);
    }

    public void move(Move move) {
        Figure figure = getFigure(move.getFromCol(), move.getFromRow());
        setFigure(move.getToCol(), move.getToRow(), figure);
        setFigure(move.getFromCol(), move.getFromRow(), new None());
    }

    @Override
    public String toString() {
        String s = "|==|==|==|==|==|==|==|==|\n";
        for (int row = 0; row < 8; row++) {
            s += rows.get(row).toString();
        }
        s += "|==|==|==|==|==|==|==|==|\n";
        return s;
    }

    public void init() {
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if (row < 3) {
                    if (col % 2 == 0 && row % 2 != 0) setFigure(col, row, new Pawn(FigureColor.BLACK));
                    if (col % 2 != 0 && row % 2 == 0) setFigure(col, row, new Pawn(FigureColor.BLACK));
                }

                if (row > 4) {
                    if (col % 2 != 0 && row % 2 == 0) setFigure(col, row, new Pawn(FigureColor.WHITE));
                    if (col % 2 == 0 && row % 2 != 0) setFigure(col, row, new Pawn(FigureColor.WHITE));
                }
            }
        }
    }
}
