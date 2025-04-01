package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.*;
import com.kodilla.checkers.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<BoardRow> rows = new ArrayList<>();
    private Player currentPlayer;
    private Player playerOne;
    private Player playerTwo;
    private FigureColor winner = null;
    private int whiteFigures = 0;
    private int blackFigures = 0;
    private final List<Figure> capturedWhiteFigures = new ArrayList<>();
    private final List<Figure> capturedBlackFigures = new ArrayList<>();

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

    public void moveFigure(Move move) {
        if (move == null) return;
        Figure figure = getFigure(move.getFromPoint());
        setFigure(move.getToPoint(), figure);
        setFigure(move.getFromPoint(), new None());
    }

    public Board init(Player playerOne, Player playerTwo) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Point p = new Point(x, y);
                if (shouldPlacePawn(x, y, FigureColor.BLACK)) {
                    setFigure(p, new Pawn(FigureColor.BLACK));
                    blackFigures++;
                } else if (shouldPlacePawn(x, y, FigureColor.WHITE)) {
                    setFigure(p, new Pawn(FigureColor.WHITE));
                    whiteFigures++;
                } else {
                    setFigure(p, new None());
                }
            }
        }
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = playerOne;
        return this;
    }

    private boolean shouldPlacePawn(int x, int y, FigureColor color) {
        if (color == FigureColor.BLACK && y < 3) {
            return (x + y) % 2 == 1;
        } else if (color == FigureColor.WHITE && y > 4) {
            return (x + y) % 2 == 1;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchToNextTurn() {
        currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
    }

    public boolean isLegalMove(Move move) {
        if (move == null) return false;

        Point from = move.getFromPoint();
        Point to = move.getToPoint();

        if (!isInBounds(from) || !isInBounds(to)) return false;
        if (isPlaceEmpty(from) || !isPlaceEmpty(to)) return false;
        if (isOpponentFigure(from)) return false;
        if (!isMoveDiagonal(move)) return false;

        if (getFigure(from) instanceof Pawn) {
            int maxDistance = isOpponentAdjacent(move) ? 2 : 1;
            return !isDifferenceInMoveBiggerThan(move, maxDistance);
        }

        return true;
    }

    public boolean isPlaceEmpty(Point point) {
        return getFigure(point) instanceof None;
    }

    public boolean isOpponentFigure(Point point) {
        Figure figure = getFigure(point);
        return !(figure instanceof None) && figure.getColor() != currentPlayer.getFigureColor();
    }

    public boolean isMoveDiagonal(Move move) {
        if (move == null) return false;
        Point from = move.getFromPoint();
        Point to = move.getToPoint();
        return Math.abs(to.x - from.x) == Math.abs(to.y - from.y);
    }

    public boolean isDifferenceInMoveBiggerThan(Move move, int maxDistance) {
        Point from = move.getFromPoint();
        Point to = move.getToPoint();
        return Math.abs(to.x - from.x) > maxDistance || Math.abs(to.y - from.y) > maxDistance;
    }

    public boolean isOpponentAdjacent(Move move) {
        Point from = move.getFromPoint();
        int[][] directions = { {1, 1}, {-1, 1}, {1, -1}, {-1, -1} };

        for (int[] d : directions) {
            int x = from.x + d[0];
            int y = from.y + d[1];
            if (isInBounds(new Point(x, y)) && isOpponentFigure(new Point(x, y))) {
                return true;
            }
        }
        return false;
    }

    public boolean showAndAttackOpponents(Move move) {
        Point from = move.getFromPoint();
        Point to = move.getToPoint();

        int stepX = Integer.compare(to.x, from.x);
        int stepY = Integer.compare(to.y, from.y);

        int captured = 0;
        for (int i = 1; i < Math.abs(to.x - from.x); i++) {
            Point p = new Point(from.x + i * stepX, from.y + i * stepY);
            if (isOpponentFigure(p)) {
                setFigure(p, new None());
                if (currentPlayer.getFigureColor() == FigureColor.WHITE) {
                    blackFigures--;
                    capturedBlackFigures.add(new Pawn(FigureColor.BLACK));
                } else {
                    whiteFigures--;
                    capturedWhiteFigures.add(new Pawn(FigureColor.WHITE));
                }
                captured++;
            }
        }

        return captured > 0;
    }

    public boolean setPawnToQueen(Point point, FigureColor color) {
        if (point == null || getFigure(point) instanceof Queen) return false;

        if ((color == FigureColor.BLACK && point.y == 7) ||
                (color == FigureColor.WHITE && point.y == 0)) {
            setFigure(point, new Queen(color));
            return true;
        }

        return false;
    }

    public FigureColor showWinner() {
        return winner;
    }

    public FigureColor checkWinner() {
        if (whiteFigures == 0) return FigureColor.BLACK;
        if (blackFigures == 0) return FigureColor.WHITE;
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(capturedWhiteFigures).append("\n");
        sb.append("   A  B  C  D  E  F  G  H  \n");
        sb.append("  |==|==|==|==|==|==|==|==|\n");
        for (int row = 0; row < 8; row++) {
            sb.append(rows.get(row).toString(row + 1));
        }
        sb.append("  |==|==|==|==|==|==|==|==|\n");
        sb.append("   A  B  C  D  E  F  G  H  \n");
        sb.append(capturedBlackFigures);
        return sb.toString();
    }

    private boolean isInBounds(Point point) {
        return point != null && point.x >= 0 && point.x < 8 && point.y >= 0 && point.y < 8;
    }
}
