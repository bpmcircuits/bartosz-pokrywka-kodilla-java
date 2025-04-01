package com.kodilla.checkers.logic;

import com.kodilla.checkers.player.Player;
import com.kodilla.checkers.player.PlayerFactory;
import com.kodilla.checkers.ui.MenuEnum;
import com.kodilla.checkers.ui.UserInterface;

public class GameLogic {

    public void run() {
        while (true) {
            MenuEnum.MainMenuOption mainMenuOption = UserInterface.mainMenuChoice();
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
            MenuEnum.NewGameMenuOption newGameMenuOption = UserInterface.newGameMenu();
            switch (newGameMenuOption) {
                case PLAYER_VS_PLAYER -> playGameWith(MenuEnum.HumanOrComputerEnum.HUMAN);
                case PLAYER_VS_COMPUTER -> playGameWith(MenuEnum.HumanOrComputerEnum.COMPUTER);
                case BACK -> {
                    return;
                }
            }
        }
    }

    private void playGameWith(MenuEnum.HumanOrComputerEnum playerOfChoice) {
        Player[] players = PlayerFactory.createPlayers(playerOfChoice);

        Player playerOne = players[0];
        Player playerTwo = players[1];

        UserInterface.displayPlayers(playerOne.getName(), playerTwo.getName());

        Board board = new Board(playerOne, playerTwo).init();

        while (true) {
            System.out.println(board);
            UserInterface.showWhichPlayerTurn(board.getCurrentPlayer().getName());

            if (!isPlayerMoveHandled(board.getCurrentPlayer(), board)) {
                continue;
            }

            if (board.checkWinner() != null) {
                UserInterface.showWinner(board.showWinner().getName());
                return;
            }

            board.switchToNextTurn();
        }
    }

    private boolean isPlayerMoveHandled(Player player, Board board) {
        Move move = player.getMove(board);
        return board.moveFigure(move);
    }
}
