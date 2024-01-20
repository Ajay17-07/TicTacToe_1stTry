package winningStrategies;

import TicTacToe_Models.Board;
import TicTacToe_Models.Move;

public class RowWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
