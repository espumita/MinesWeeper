package model;

public class CustomDifficulty implements Difficulty{
    private final int rows;
    private final int columns;
    private final int mines;

    public CustomDifficulty(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
    }

    @Override
    public int getRows() {
        return rows < 1 ? 1 : rows > 24 ? 24 : rows;
    }

    @Override
    public int getColumns() {
        return columns < 8 ? 8 : columns > 30 ? 30 : columns;
    }

    @Override
    public int getMines() {
        return mines < 0 ? 0 : mines > (getRows()-1)*(getColumns()-1)+1 ? (getRows()-1)*(getColumns()-1)+1: mines;
    }
}
