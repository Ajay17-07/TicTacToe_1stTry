package TicTacToe_Models;

import Exceptions.DuplicateSymbolException;
import Exceptions.MoreThanOneBotException;
import Exceptions.PlayersCountMismatchException;
import winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;

    private  List<WinningStrategy> winningStrategies;
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMove() {
        return moves;
    }

    public void setMove(List<Move> move) {
        this.moves = move;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.nextPlayerIndex = playerIndex;
    }

    private Game(int dimension,List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board=new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState=GameState.IN_PROG;
        this.nextPlayerIndex=0;
        this.moves=new ArrayList<>();
    }

    public static Builder getBuilder(){
        return  new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player player=players.get(nextPlayerIndex);
        Cell cell= player.makeMove(board);
        Move move= new Move(cell,player);
        moves.add(move);
        if(checkWinner(move,board)){
            this.gameState=GameState.SUCCESS;
            this.winner=player;
            return;
        }
        if(moves.size()== board.getDimension()*board.getDimension()){
            this.gameState=GameState.DRAW;
            return;
        }
        this.nextPlayerIndex++;
        this.nextPlayerIndex= this.nextPlayerIndex % players.size();
    }

    private boolean checkWinner(Move move, Board board) {
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public static class Builder{
        List<Player> players;
        List<WinningStrategy> winningStrategies;
        int dimension;

        public Builder() {
            this.players=new ArrayList<>();
            this.winningStrategies=new ArrayList<>();
            this.dimension=0;
        }

        public Game Build() throws MoreThanOneBotException, DuplicateSymbolException, PlayersCountMismatchException {
            /*
              1) validate bot count
              2)Validate unique symbol for players
              3) Validate dimension and players count
             */
            validateBotCount();
            validateUniqueSymbolForPlayers();
            validateDimensionAndPlayerCount();
            return new Game(dimension,players,winningStrategies);
        }

        private void validateDimensionAndPlayerCount() throws PlayersCountMismatchException {
            if(players.size()!=dimension-1){
                throw new PlayersCountMismatchException();
            }
        }

        private void validateUniqueSymbolForPlayers() throws DuplicateSymbolException {
            HashSet<Character> symmbols = new HashSet<>();

            for(Player player:players){
                if(symmbols.contains(player.getSymbol())){
                    throw new DuplicateSymbolException();
                }else{
                    symmbols.add(player.getSymbol());
                }

            }
        }

        private void validateBotCount() throws MoreThanOneBotException {
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new MoreThanOneBotException();
            }
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
    }
}
