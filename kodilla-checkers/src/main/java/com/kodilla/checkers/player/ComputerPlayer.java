package com.kodilla.checkers.player;

import com.kodilla.checkers.figures.*;
import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Move;
import com.kodilla.checkers.ui.MenuEnum;

public class ComputerPlayer implements Player {

    private final FigureColor color;
    private final MenuEnum.ComputerLevelEnum computerLevel;
    private static final int MAX_DEPTH = 4;

    public ComputerPlayer(FigureColor color, MenuEnum.ComputerLevelEnum computerLevel) {
        this.color = color;
        this.computerLevel = computerLevel;

    }

    public Move findBestMove(Board board, int depth) throws CloneNotSupportedException {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (Move move : board.getAllLegalMoves(color)) {
            Board copy = board.deepCopy();
            copy.moveFigure(move);
            copy.switchToNextTurn();

            int score = minimax(copy, depth - 1, false);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing) throws CloneNotSupportedException {
        FigureColor currentColor = isMaximizing ? color : getOpponentColor(color);

        if (depth == 0 || board.checkWinner() != null) {
            return board.evaluateScore(color);
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Move move : board.getAllLegalMoves(currentColor)) {
            Board copy = board.deepCopy();
            copy.moveFigure(move);
            copy.switchToNextTurn();

            int score = minimax(copy, depth - 1, !isMaximizing);
            bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
        }

        return bestScore;
    }

    private FigureColor getOpponentColor(FigureColor color) {
        return color == FigureColor.WHITE ? FigureColor.BLACK : FigureColor.WHITE;
    }

    public FigureColor getFigureColor() {
        return color;
    }

    @Override
    public Move getMove(Board board) {
        return null;
    }

    public String getName() {
        return "ENIAC";
    }
}
