package model;

public class CustomDifficulty implements Difficulty{

    @Override
    public int getRows() {
        return 19;
    }

    @Override
    public int getColumns() {
        return 45;
    }

    @Override
    public int getMines() {
        return 2;
    }
}
