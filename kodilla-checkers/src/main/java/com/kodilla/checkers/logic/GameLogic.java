package com.kodilla.checkers.logic;

import com.kodilla.checkers.figures.FigureColor;
import com.kodilla.checkers.ui.MenuEnum;
import com.kodilla.checkers.ui.Settings;
import com.kodilla.checkers.ui.UserInterface;

public class GameLogic {

    public void run() {

        while (true) {

            int menuChoice = UserInterface.mainMenuChoice();
            MenuEnum.MainMenuOption mainMenuOption = MenuEnum.MainMenuOption.fromValue(menuChoice);

            switch (mainMenuOption) {
                case NEW_GAME -> newGameMenu();
                case EXIT -> {
                    if (UserInterface.onExit()) return;
                }
            }
        }
    }

    private void newGameMenu() {

        while (true) {

            int menuChoice = UserInterface.newGameMenu();
            MenuEnum.NewGameMenuOption newGameMenuOption = MenuEnum.NewGameMenuOption.fromValue(menuChoice);

            switch (newGameMenuOption) {
                case PLAYER_VS_PLAYER -> renderGameWith(MenuEnum.HumanOrComputerEnum.HUMAN);
                case PLAYER_VS_COMPUTER -> renderGameWith(MenuEnum.HumanOrComputerEnum.COMPUTER);
                case BACK -> {
                    return;
                }
            }
        }
    }

    private void renderGameWith(MenuEnum.HumanOrComputerEnum playerOfChoice) {

        String playerOneName = UserInterface.choosePlayerName(Settings.PLAYER.FIRST);
        String playerTwoName;
        String figure = UserInterface.chooseFigure();
        Player playerOne = new Player(playerOneName, getFigureColor(figure));
        Player playerTwo = null;
        Computer computer = null;

        Board board = new Board();
        board.init();

        if (playerOfChoice == MenuEnum.HumanOrComputerEnum.HUMAN) {
            playerTwoName = UserInterface.choosePlayerName(Settings.PLAYER.SECOND);
            playerTwo = new Player(playerTwoName, getOpponentFigureColor(figure));
        } else {
            MenuEnum.ComputerLevelEnum computerLevel = UserInterface.chooseComputerLevel();
            computer = new Computer(board, getOpponentFigureColor(figure), computerLevel);
            playerTwoName = computer.getComputerName();
        }

        UserInterface.displayPlayers(playerOneName, playerTwoName);

        System.out.println(board);

        boolean finishedGame = false;
        while (!finishedGame) {

            UserInterface.showWhichPlayerTurn(isCurrentPlayerTurn(board, playerOne) ? playerOneName : playerTwoName);

            Move move;
            if (playerOfChoice == MenuEnum.HumanOrComputerEnum.HUMAN) {

                move = UserInterface.getPlayerMove();

                if (board.isLegalMove(move)) {
                    board.findAndAttackOpponents(move);
                    board.move(move);
                } else {
                    UserInterface.illegalMove();
                    continue;
                }

            } else {

                if (isCurrentPlayerTurn(board, playerOne)) {
                    while (true) {
                        move = UserInterface.getPlayerMove();
                        if (board.isLegalMove(move)) {
                            UserInterface.illegalMove();
                        } else {
                            board.findAndAttackOpponents(move);
                            board.move(move);
                            break;
                        }
                    }

                } else {
                    //board.setFigureToPosition(computer.getComputerFigure(), computer.generateComputerMove());
                }
            }

            if (board.checkWinner() != null) {
                finishedGame = true;
                UserInterface.showWinner(isCurrentPlayerWinner(board, playerOne) ? playerOneName : playerTwoName);
            } else {
                board.switchToNextTurn();
            }

            System.out.println(board);
        }
    }

    private boolean isCurrentPlayerTurn(Board board, Player player) {
        return board.getCurrentPlayer() == player.playerColor();
    }

    private boolean isCurrentPlayerWinner(Board board, Player player) {
        return board.getWinner() == player.playerColor();
    }

    private FigureColor getFigureColor(String color) {
        return color.equals("W") ? FigureColor.WHITE : FigureColor.BLACK;
    }

    private FigureColor getOpponentFigureColor(String color) {
        return color.equals("W") ? FigureColor.BLACK : FigureColor.WHITE;
    }
}
