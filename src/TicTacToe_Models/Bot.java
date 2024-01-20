package TicTacToe_Models;

import botPlayingStrategy.BotPlayingStrategy;
import botPlayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player{
   private BotDifficultyLevel botDifficultyLevel;
   private BotPlayingStrategy botPlayingStrategy;
    public Bot(int id, String name, char symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }

    @Override
    public Cell makeMove(Board board){
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }
}
