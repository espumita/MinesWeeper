package model.difficulty;

public class HardDifficulty implements Difficulty{

    @Override
    public int getRows() {
        return 16;
    }

    @Override
    public int getColumns() {
        return 30;
    }

    @Override
    public int getMines() {
        return 99;
    }
}
