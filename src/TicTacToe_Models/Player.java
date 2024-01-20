package TicTacToe_Models;

import java.util.Scanner;

public class Player {
   private  int id;
   private String name;
   private char symbol;
   private PlayerType playerType;

   private Scanner scanner;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner=new Scanner(System.in);
    }

    public Cell makeMove(Board board) {
        System.out.println(this.name+" , it's your turn");
        int row=scanner.nextInt();
        int col=scanner.nextInt();
        while(validateMove(row,col,board)){
            System.out.println(this.name+ " ,invalid move. Please try again!");
            row=scanner.nextInt();
            col=scanner.nextInt();
        }
        Cell cell=board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }

    private boolean validateMove(int row, int col, Board board) {
        if(row>=board.getDimension()||row<0){
            return true;
        }
        if(col>=board.getDimension()||col<0){
            return true;
        }
        if(!CellState.EMPTY.equals(board.getBoard().get(row).get(col).getCellState())){
            return true;
        }
        return false;
    }
}
