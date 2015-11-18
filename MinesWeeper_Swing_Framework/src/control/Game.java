package control;

import application.App;
import process.AlertPerimeterProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static List<String> mines = new ArrayList<>();
    private static List<String> flags = new ArrayList<>();
    private static List<String> displayedCells = new ArrayList<>();
    public void start(String startCell) {
        App.started();
        dropMines(startCell);
    }

    private void dropMines(String startCell) {
        Random random = new Random();
        while(mines.size() < App.difficulty.getMines()){
            String mine = random.nextInt(App.difficulty.getRows())+"_"+random.nextInt(App.difficulty.getColumns());
            if(!mines.contains(mine) && !mine.equals(startCell)) plantMine(mine);
        }
    }

    private void plantMine(String mine) {
        mines.add(mine);
        new AlertPerimeterProcess().run(mine);
    }

    public static List<String>  mine(){
        return mines;
    }

    public static List<String>  flags(){
        return flags;
    }

    public static List<String> displayedCells(){
        return displayedCells;
    }

}

