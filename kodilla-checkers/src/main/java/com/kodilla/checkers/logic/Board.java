package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.*;
import com.kodilla.checkers.player.Player;
import com.kodilla.checkers.ui.UserInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {

    private final List<BoardRow> rows = new ArrayList<>();
    private Player currentPlayer;
    private final Player playerOne;
    private final Player playerTwo;
    private int whiteFigures = 0;
    private int blackFigures = 0;
    private final List<Figure> capturedWhiteFigures = new ArrayList<>();
    private final List<Figure> capturedBlackFigures = new ArrayList<>();
    private final static int DIR_UPWARDS = -1;
    private final static int DIR_DOWNWARDS = 1;
    private Point forcedMovePosition = null;


    public Board(Player playerOne, Player playerTwo) {
        for (int row = 0; row < 8; row++)
            rows.add(new BoardRow());
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = playerOne;
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

        if (forcedMovePosition != null && !forcedMovePosition.equals(move.getFromPoint())) {
            UserInterface.continueMove();
            return false;
        }

        if (!isLegalMove(move)) {
            UserInterface.illegalMove();
            return false;
        }

        Figure movingFigure = getFigure(move.getFromPoint());
        performBasicMove(move, movingFigure);

        boolean capturePerformed = isCaptureHandled(move);

        if (capturePerformed && canContinueCaptures(move.getToPoint(), movingFigure.getColor())) {
            forcedMovePosition = move.getToPoint();
        } else {
            switchToNextTurn();
            forcedMovePosition = null;
        }

        setPawnToQueen(move.getToPoint(), movingFigure.getColor());

        return true;
    }

    private void performBasicMove(Move move, Figure figure) {
        if (move == null || figure == null) return;
        setFigure(move.getToPoint(), figure);
        setFigure(move.getFromPoint(), new None());
    }

    private boolean isCaptureHandled(Move move) {
        if (!isOpponentAdjacent(move)) {
            return false;
        }

        Point capturedPoint = getCapturedPoint(move);
        if (capturedPoint == null) {
            return false;
        }

        captureFigure(capturedPoint);
        return true;
    }

    private void captureFigure(Point capturedPoint) {
        Figure capturedFigure = getFigure(capturedPoint);

        if (capturedFigure.getColor() == FigureColor.WHITE) {
            capturedWhiteFigures.add(capturedFigure);
            whiteFigures--;
        } else {
            capturedBlackFigures.add(capturedFigure);
            blackFigures--;
        }

        setFigure(capturedPoint, new None());
    }

    private Point getCapturedPoint(Move move) {
        if (move == null) return null;
        int x = (move.getFromPoint().x + move.getToPoint().x) / 2;
        int y = (move.getFromPoint().y + move.getToPoint().y) / 2;
        Point capturedPoint = new Point(x, y);

        if (isInBounds(capturedPoint) && isOpponentFigure(capturedPoint)) {
            return capturedPoint;
        }
        return null;
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

    private List<Move> getAllLegalCaptures(Point position, FigureColor color) {
        List<Move> possibleCaptures = new ArrayList<>();
        int[] directions = {-1, 1};

        Figure figure = getFigure(position);
        if (figure == null || figure.getColor() != color) {
            return possibleCaptures;
        }

        for (int dx : directions) {
            for (int dy : directions) {
                Point adjacentPoint = new Point(position.x + dx, position.y + dy);
                Point landingPoint = new Point(position.x + 2*dx, position.y + 2*dy);

                if (isInBounds(adjacentPoint) && isInBounds(landingPoint)) {
                    Figure adjacentFigure = getFigure(adjacentPoint);

                    if (!(adjacentFigure instanceof None) && adjacentFigure.getColor() != color &&
                            isPlaceEmpty(landingPoint)) {
                        possibleCaptures.add(new Move(position, landingPoint));
                    }
                }
            }
        }
        return possibleCaptures;
    }

    private boolean canContinueCaptures(Point currentPosition, FigureColor color) {
        return !getAllLegalCaptures(currentPosition, color).isEmpty();
    }

    public Point getForcedMovePosition() {
        return forcedMovePosition;
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

    private boolean isLegalMove(Move move) {
        if (move == null) return false;

        Point from = move.getFromPoint();
        Point to = move.getToPoint();

        if (!isInBounds(from) || !isInBounds(to)) return false;
        if (isPlaceEmpty(from) || !isPlaceEmpty(to)) return false;
        if (isOpponentFigure(from)) return false;
        if (!isMoveDiagonal(move)) return false;

        Figure figure = getFigure(from);

        if (figure instanceof Pawn) {
            if (!isDirectionCorrect(move)) return false;

            int maxDistance = isOpponentAdjacent(move) ? 2 : 1;
            return !isDifferenceInMoveBiggerThan(move, maxDistance);
        }

        return true;
    }

    private boolean isInBounds(Point point) {
        return point != null && point.x >= 0 && point.x < 8 && point.y >= 0 && point.y < 8;
    }

    private boolean isPlaceEmpty(Point point) {
        return getFigure(point) instanceof None;
    }

    private boolean isOpponentFigure(Point point) {
        Figure figure = getFigure(point);
        return !(figure instanceof None) && figure.getColor() != currentPlayer.getFigureColor();
    }

    private boolean isMoveDiagonal(Move move) {
        if (move == null) return false;
        Point from = move.getFromPoint();
        Point to = move.getToPoint();
        return Math.abs(to.x - from.x) == Math.abs(to.y - from.y);
    }

    private boolean isDifferenceInMoveBiggerThan(Move move, int maxDistance) {
        if (move == null) return false;
        Point from = move.getFromPoint();
        Point to = move.getToPoint();
        return Math.abs(to.x - from.x) > maxDistance || Math.abs(to.y - from.y) > maxDistance;
    }

    private boolean isOpponentAdjacent(Move move) {
        if (move == null) return false;
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

    private boolean isDirectionCorrect(Move move) {
        if (move == null) return false;

        Point from = move.getFromPoint();
        Point to = move.getToPoint();

        int direction = Integer.compare(to.y, from.y);

        if (currentPlayer.getFigureColor() == FigureColor.WHITE) {
            return direction == DIR_UPWARDS;
        } else if (currentPlayer.getFigureColor() == FigureColor.BLACK) {
            return direction == DIR_DOWNWARDS;
        }
        return false;
    }

    public boolean setPawnToQueen(Point point, FigureColor color) {
        Figure figure = getFigure(point);
        if (point == null || !(figure instanceof Pawn)) return false;
        if (figure.getColor() != color) return false;
        if ((color == FigureColor.BLACK && point.y == 7) ||
                (color == FigureColor.WHITE && point.y == 0)) {
            setFigure(point, new Queen(color));
            return true;
        }
        return false;
    }

    public Board deepCopy() {
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
        if (original == null) return null;
        List<Figure> cloned = new ArrayList<>();
        for (Figure f : original) {
            cloned.add(f.clone());
        }
        return cloned;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return whiteFigures == board.whiteFigures
                && blackFigures == board.blackFigures
                && Objects.equals(rows, board.rows)
                && Objects.equals(currentPlayer, board.currentPlayer)
                && Objects.equals(playerOne, board.playerOne)
                && Objects.equals(playerTwo, board.playerTwo)
                && Objects.equals(capturedWhiteFigures, board.capturedWhiteFigures)
                && Objects.equals(capturedBlackFigures, board.capturedBlackFigures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, currentPlayer, playerOne, playerTwo,
                whiteFigures, blackFigures, capturedWhiteFigures, capturedBlackFigures);
    }

    public void setWhiteFigures(int whiteFigures) {
        this.whiteFigures = whiteFigures;
    }

    public void setBlackFigures(int blackFigures) {
        this.blackFigures = blackFigures;
    }
}
