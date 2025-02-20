package com.kodilla.checkers;

import com.kodilla.checkers.logic.Board;

public class CheckersRunner {
    public static void main(String[] args) {
        Board board = new Board();
        board.init();
        System.out.println(board);
//        board.setFigure(0, 0, new Pawn(FigureColor.WHITE));
//        board.setFigure(7, 7, new Pawn(FigureColor.BLACK));
//        System.out.println(board);
//        board.move(new Move(0, 0, 0, 1));
//        System.out.println(board);
    }
}
