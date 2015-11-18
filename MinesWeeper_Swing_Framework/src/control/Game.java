package control;

import application.App;
import process.SetAlertPerimeterProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static application.App.started;

public class Game {
    private static List<String> mines = new ArrayList<>();
    private static List<String> flags = new ArrayList<>();
    private static List<String> displayedCells = new ArrayList<>();

    public void start(String cell) {
        started();
        dropMines(cell);
    }

    private void dropMines(String cell) {
        Random random = new Random();
        while(mines.size() < App.difficulty.getMines()){
            String mine = random.nextInt(App.difficulty.getRows())+"_"+random.nextInt(App.difficulty.getColumns());
            if(!mines.contains(mine) && !mine.equals(cell)) plantMine(mine);
        }
    }

    private void plantMine(String mine) {
        mines.add(mine);
        new SetAlertPerimeterProcess().run(mine);
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

