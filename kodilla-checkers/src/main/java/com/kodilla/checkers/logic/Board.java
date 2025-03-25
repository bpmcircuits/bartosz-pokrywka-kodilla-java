package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<BoardRow> rows = new ArrayList<>();
    private FigureColor currentPlayer;
    private FigureColor winner;
    private int whiteFigures;
    private int blackFigures;

    public Board() {
        for (int row = 0; row < 8; row++)
            rows.add(new BoardRow());
    }

    public Figure getFigure(Point point) {
        if (point == null) return null;
        return rows.get(point.y).getCols().get(point.x);
    }

    public void setFigure(Point point, Figure figure) {
        if (figure == null) return;
        rows.get(point.y).getCols().set(point.x, figure);
    }

    public void move(Move move) {
        if (move == null) return;
        Figure figure = getFigure(move.getFromPoint());
        setFigure(move.getToPoint(), figure);
        setFigure(move.getFromPoint(), new None());
    }

    @Override
    public String toString() {
        String s = "   A  B  C  D  E  F  G  H  \n";
        s += "  |==|==|==|==|==|==|==|==|\n";
        for (int row = 0; row < 8; row++) {
            s += rows.get(row).toString(row + 1);
        }
        s += "  |==|==|==|==|==|==|==|==|\n";
        s += "   A  B  C  D  E  F  G  H  \n";
        return s;
    }

    public void init() {
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if (row < 3) {
                    if (col % 2 == 0 && row % 2 != 0) setFigure(new Point(col, row), new Pawn(FigureColor.BLACK));
                    if (col % 2 != 0 && row % 2 == 0) setFigure(new Point(col, row), new Pawn(FigureColor.BLACK));
                }

                if (row > 4) {
                    if (col % 2 != 0 && row % 2 == 0) setFigure(new Point(col, row), new Pawn(FigureColor.WHITE));
                    if (col % 2 == 0 && row % 2 != 0) setFigure(new Point(col, row), new Pawn(FigureColor.WHITE));
                }
            }
        }
        currentPlayer = FigureColor.WHITE;
        whiteFigures = 12;
        blackFigures = 12;
    }

    public void switchToNextTurn() {
        currentPlayer = currentPlayer == FigureColor.WHITE ? FigureColor.BLACK : FigureColor.WHITE;
    }

    public FigureColor getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isLegalMove(Move move) {
        if (move == null) return false;
        if (isPlaceEmpty(move.getFromPoint()) || !isPlaceEmpty(move.getToPoint())) return false;
        if (isOpponentFigure(move.getFromPoint())) return false;
        if (!isMoveDiagonal(move)) return false;
        if ((getFigure(move.getFromPoint()) instanceof Pawn)) {
            int difference = isOpponentAdjacent(move) ? 2 : 1;
            if (isDifferenceInMoveBiggerThan(move, difference)) return false;
        }

        return true;
    }

    public boolean isPlaceEmpty(Point point) {
        return getFigure(point) instanceof None;
    }

    public boolean isOpponentFigure(Point point) {
        return getFigure(point).getColor() != currentPlayer && getFigure(point).getClass() != None.class;
    }

    public boolean isMoveDiagonal(Move move) {
        if (move == null) return false;
        return Math.abs(move.getToPoint().x - move.getFromPoint().x) == Math.abs(move.getToPoint().y - move.getFromPoint().y);
    }

    public boolean isDifferenceInMoveBiggerThan(Move move, int difference) {
        if (move == null) return false;
        return Math.abs(move.getFromPoint().x - move.getToPoint().x) > difference
                || Math.abs(move.getFromPoint().y - move.getToPoint().y) > difference;
    }

    public boolean isOpponentAdjacent(Move move) {
        if (move == null) return false;
        int fromX = move.getFromPoint().x;
        int fromY = move.getFromPoint().y;
        int[][] directions = { {1, 1}, {-1, 1}, {1, -1}, {-1, -1} };

        for (int[] d : directions) {
            int newX = fromX + d[0];
            int newY = fromY + d[1];
            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                if (isOpponentFigure(new Point(newX, newY))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findAndAttackOpponents(Move move) {
        if (move == null) return false;
        int attackedFigures = 0;
        int stepX = (move.getToPoint().x > move.getFromPoint().x) ? 1 : -1;
        int stepY = (move.getToPoint().y > move.getFromPoint().y) ? 1 : -1;

        for (int i = 1; i < Math.abs(move.getToPoint().x - move.getFromPoint().x); i++) {
            Point point = new Point(move.getFromPoint().x + i * stepX, move.getFromPoint().y + i * stepY);
            if (isOpponentFigure(point)) {
                setFigure(point, new None());
                attackedFigures++;
                if (currentPlayer == FigureColor.WHITE) blackFigures--;
                if (currentPlayer == FigureColor.BLACK) whiteFigures--;
            }
        }

        return attackedFigures > 0;
    }

    public boolean setPawnToQueen(Point point, FigureColor color) {
        if (point == null) return false;
        if (getFigure(point) instanceof Queen) return false;
        if (color == FigureColor.BLACK) {
            if (point.y == 7) {
                setFigure(point, new Queen(FigureColor.BLACK));
                return true;
            }
        } else if (color == FigureColor.WHITE) {
            if (point.y == 0) {
                setFigure(point, new Queen(FigureColor.WHITE));
                return true;
            }
        }
        return false;
    }

    public FigureColor getWinner() {
        return winner;
    }

    public FigureColor checkWinner() {
        if (whiteFigures == 0) return FigureColor.BLACK;
        else if (blackFigures == 0) return FigureColor.WHITE;
        else return null;
    }
}
