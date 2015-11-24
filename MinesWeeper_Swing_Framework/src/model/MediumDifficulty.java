package model;


public class MediumDifficulty implements Difficulty {

    @Override
    public int getRows() {
        return 16;
    }

    @Override
    public int getColumns() {
        return 16;
    }

    @Override
    public int getMines() {
        return 40;
    }
}
