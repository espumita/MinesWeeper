package control;

import process.ChronometerThread;
import process.SetAlertPerimeterProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static application.App.started;
import static application.App.difficulty;
import static application.App.chronometer;

public class Game {
    private static List<String> mines = new ArrayList<>();
    private static List<String> flags = new ArrayList<>();
    private static List<String> displayedCells = new ArrayList<>();

    public void startGame(String cell) {
        started(true);
        dropMines(cell);
        chronometer = new ChronometerThread();
        chronometer.start();
    }

    public static void endGame(){
        chronometer.interrupt();
    }



    private void dropMines(String cell) {
        Random random = new Random();
        while(mines.size() < difficulty.getMines()){
            String mine = random.nextInt(difficulty.getRows())+"_"+random.nextInt(difficulty.getColumns());
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

    public static void clearInfo() {
        mine().clear();
        flags().clear();
        displayedCells().clear();
    }
}

