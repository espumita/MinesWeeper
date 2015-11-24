package model;


public class Board {
    private Cell[][] board;

    public Cell[][] getBoard() {
        return board;
    }

    public int getRows(){
        return board.length;
    }

    public int getColumns(){
        return board[0].length;
    }

    public Board(int rows  , int columns) {

        this.board = new Cell[rows][columns];
    }

    public void add(int row, int colum, Cell temp) {
        board[row][colum] = temp;
    }
}
