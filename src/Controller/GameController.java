package Controller;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCountMismatchException;
import TicTacToe_Models.Game;
import TicTacToe_Models.Player;
import winningStrategies.WinningStrategy;

import java.util.List;
public class GameController {
    public Game startGame(int dimension, List<Player> playerList, List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayersCountMismatchException, MoreThanOneBotException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(playerList)
                .setWinningStrategies(winningStrategies)
                .Build();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }
}
