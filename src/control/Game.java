package control;

import model.StartPerimeter;
import process.ChronometerThread;
import process.SetAlertPerimeterProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static application.App.camp;
import static application.App.started;
import static application.App.difficulty;
import static application.App.chronometer;

public class Game {
    public static List<String> mines = new ArrayList<>();
    public static List<String> flagsFirstLevel = new ArrayList<>();
    public static List<String> flagsSecondLevel = new ArrayList<>();
    public static List<String> displayedCells = new ArrayList<>();
    public static int remainingMarks =  difficulty.getMines();
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
        List<String> startPerimeter = new StartPerimeter().get(cell);
        while(mines.size() < difficulty.getMines()){
            String mine = random.nextInt(difficulty.getRows())+"_"+random.nextInt(difficulty.getColumns());
            if(!startPerimeter.contains(mine) && !mines.contains(mine)) plantMine(mine);
        }
    }


    private void plantMine(String mine) {
        mines.add(mine);
        new SetAlertPerimeterProcess().run(mine);
    }


    public static void restartGameInfo() {
        flagsFirstLevel.forEach(flag -> camp.get(flag).setCellGroundIcon());
        flagsFirstLevel.clear();
        flagsSecondLevel.forEach(cell -> camp.get(cell).setCellGroundIcon());
        flagsSecondLevel.clear();
        displayedCells.forEach(cell -> camp.get(cell).setCellGroundIcon());
        displayedCells.clear();
        mines.clear();
        remainingMarks = difficulty.getMines();
    }
}

