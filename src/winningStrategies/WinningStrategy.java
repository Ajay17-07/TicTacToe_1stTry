package winningStrategies;

import TicTacToe_Models.Board;
import TicTacToe_Models.Move;

public interface WinningStrategy {
   boolean checkWinner(Board board, Move move);
}
