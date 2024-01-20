package botPlayingStrategy;

import TicTacToe_Models.Board;
import TicTacToe_Models.Cell;

public interface BotPlayingStrategy {

    Cell makeMove(Board board);
}
