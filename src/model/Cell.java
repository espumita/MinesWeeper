package model;

public class Cell {
    private int alertLevel;

    public Cell() {
        alertLevel = 0;
    }

    public int alertLevel() {
        return alertLevel;
    }

    public void alert() {
        alertLevel++;
    }

    public void alertLevel(int level) {
        alertLevel = level;
    }
}
