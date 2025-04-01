package com.kodilla.checkers.player;

import com.kodilla.checkers.figures.FigureColor;
import com.kodilla.checkers.ui.MenuEnum;
import com.kodilla.checkers.ui.Settings;
import com.kodilla.checkers.ui.UserInterface;

public class PlayerFactory {

    public static Player[] createPlayers(MenuEnum.HumanOrComputerEnum choice) {
        Player firstPlayer = createHumanPlayer();
        Player secondPlayer = createSecondPlayer(choice, firstPlayer);
        return new Player[]{firstPlayer, secondPlayer};
    }

    private static Player createHumanPlayer() {
        String name = UserInterface.choosePlayerName(Settings.PLAYER.FIRST);
        FigureColor color = UserInterface.chooseFigureColor();
        return new HumanPlayer(name, color);
    }

    private static Player createSecondPlayer(MenuEnum.HumanOrComputerEnum choice, Player playerOne) {
        if (choice == MenuEnum.HumanOrComputerEnum.HUMAN) {
            String name = UserInterface.choosePlayerName(Settings.PLAYER.SECOND);
            return new HumanPlayer(name, getOpponentFigureColor(playerOne.getFigureColor()));
        } else {
            MenuEnum.ComputerLevelEnum level = UserInterface.chooseComputerLevel();
            return new ComputerPlayer(getOpponentFigureColor(playerOne.getFigureColor()), level);
        }
    }

    private static FigureColor getOpponentFigureColor(FigureColor color) {
        return color.equals(FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }
}
