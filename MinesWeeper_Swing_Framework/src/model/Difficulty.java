package model;

public class Difficulty {
    private int rows;
    private int columns;
    private int mines;

    public Difficulty(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMines() {
        return mines;
    }
}
