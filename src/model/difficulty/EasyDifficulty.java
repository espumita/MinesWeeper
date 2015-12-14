package model.difficulty;


public class EasyDifficulty implements Difficulty{

    @Override
    public int getRows() {
        return 9;
    }

    @Override
    public int getColumns() {
        return 9;
    }

    @Override
    public int getMines() {
        return 10;
    }
}
