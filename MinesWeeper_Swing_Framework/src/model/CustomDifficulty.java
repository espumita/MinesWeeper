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
        return rows < 1 ? 1 : rows > 99 ? 99 : rows;
    }

    @Override
    public int getColumns() {
        return columns < 8 ? 8 : columns > 99 ? 99 : columns;
    }

    @Override
    public int getMines() {
        return mines < 0 ? 0 : mines > 9800 ? 9800 : mines;
    }
}
