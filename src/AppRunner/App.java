package AppRunner;

import Controller.GameController;
import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCountMismatchException;
import TicTacToe_Models.Player;
import TicTacToe_Models.*;
import winningStrategies.ColWinningStrategy;
import winningStrategies.DiagonalWinningStrategy;
import winningStrategies.RowWinningStrategy;
import winningStrategies.WinningStrategy;
//import Tictactoe_Models.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws DuplicateSymbolException, PlayersCountMismatchException, MoreThanOneBotException {
        GameController gameController=new GameController();
        int dimension=3;
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(1,"Ajay",'X',PlayerType.HUMAN));
        playerList.add(new Bot(1,"GPT",'O',PlayerType.BOT,BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game=gameController.startGame(3,playerList,winningStrategies);

        while(game.getGameState().equals(GameState.IN_PROG)){
            game.printBoard();
            gameController.makeMove(game);
        }

        if(GameState.SUCCESS.equals(game.getGameState())){
            System.out.println(game.getWinner().getName()+" congrats you won the TIC TAC TOE");
        }

        if(GameState.DRAW.equals(game.getGameState())){
            System.out.println("Match Tied");
        }
//    List<Integer> li = new ArrayList<>();
//    li.add(1);
    }

}
