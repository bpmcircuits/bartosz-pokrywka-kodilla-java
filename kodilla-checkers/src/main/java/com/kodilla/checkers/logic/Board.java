package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.*;
import com.kodilla.checkers.player.Player;
import com.kodilla.checkers.ui.UserInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {

    private List<BoardRow> rows = new ArrayList<>();
    private Player currentPlayer;
    private Player playerOne;
    private Player playerTwo;
    private FigureColor winner = null;
    private int whiteFigures = 0;
    private int blackFigures = 0;
    private List<Figure> capturedWhiteFigures = new ArrayList<>();
    private List<Figure> capturedBlackFigures = new ArrayList<>();

    public Board(Player playerOne, Player playerTwo) {
        for (int row = 0; row < 8; row++)
            rows.add(new BoardRow());
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = playerOne;
    }

    public Figure getFigure(Point point) {
        if (point == null) return null;
        return rows.get(point.y).getCols().get(point.x);
    }

    public void setFigure(Point point, Figure figure) {
        if (figure == null) return;
        rows.get(point.y).getCols().set(point.x, figure);
    }

    public boolean moveFigure(Move move) {
        if (move == null) return false;
        if (!isLegalMove(move)) {
            UserInterface.illegalMove();
            return false;
        }
        showAndAttackOpponents(move);
        Figure figure = getFigure(move.getFromPoint());
        setFigure(move.getToPoint(), figure);
        setFigure(move.getFromPoint(), new None());
        setPawnToQueen(move.getToPoint(), currentPlayer.getFigureColor());
        return true;
    }

    public Board init() {
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
        return this;
    }

    public Board deepCopy() throws CloneNotSupportedException {
        Board copy = new Board(playerOne, playerTwo);

        for (int y = 0; y < 8; y++) {
            BoardRow originalRow = this.rows.get(y);
            BoardRow copiedRow = new BoardRow();

            for (int x = 0; x < 8; x++) {
                Figure originalFigure = originalRow.getCols().get(x);
                copiedRow.getCols().set(x, originalFigure.clone());
            }

            copy.rows.set(y, copiedRow);
        }

        copy.currentPlayer = this.currentPlayer;
        copy.whiteFigures = this.whiteFigures;
        copy.blackFigures = this.blackFigures;
        copy.capturedWhiteFigures.addAll(cloneFigures(this.capturedWhiteFigures));
        copy.capturedBlackFigures.addAll(cloneFigures(this.capturedBlackFigures));

        return copy;
    }

    private List<Figure> cloneFigures(List<Figure> original) {
        List<Figure> cloned = new ArrayList<>();
        for (Figure f : original) {
            cloned.add(f.clone());
        }
        return cloned;
    }

    public List<Move> getAllLegalMoves(FigureColor color) {
        List<Move> moves = new ArrayList<>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Point from = new Point(col, row);
                Figure figure = getFigure(from);

                if (figure instanceof None || figure.getColor() != color)
                    continue;

                for (int destCol = -2; destCol <= 2; destCol++) {
                    for (int destRow = -2; destRow <= 2; destRow++) {
                        if (Math.abs(destCol) == Math.abs(destRow) && destCol != 0) {
                            Point to = new Point(col + destCol, row + destRow);
                            Move move = new Move(from, to);
                            if (isLegalMove(move)) {
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    public int evaluateScore(FigureColor color) {
        int score = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure f = getFigure(new Point(col, row));
                if (f instanceof None) continue;

                int value = (f instanceof Queen) ? 3 : 1;
                score += (f.getColor() == color) ? value : -value;
            }
        }

        return score;
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

    public Player showWinner() {
        return currentPlayer;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(rows, board.rows) && Objects.equals(currentPlayer, board.currentPlayer)
                && Objects.equals(playerOne, board.playerOne)
                && Objects.equals(playerTwo, board.playerTwo)
                && Objects.equals(capturedWhiteFigures, board.capturedWhiteFigures)
                && Objects.equals(capturedBlackFigures, board.capturedBlackFigures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, currentPlayer, playerOne, playerTwo, capturedWhiteFigures, capturedBlackFigures);
    }
}
