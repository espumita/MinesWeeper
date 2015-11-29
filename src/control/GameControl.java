package control;

import model.Difficulty;
import model.MediumDifficulty;
import model.CellPerimeter;
import process.ChronometerThread;
import process.SetAlertPerimeterProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static application.App.*;

public class GameControl {
    private static Difficulty difficulty = new MediumDifficulty();
    private static List<String> mines = new ArrayList<>();
    private static List<String> flagsFirstLevel = new ArrayList<>();
    private static List<String> flagsSecondLevel = new ArrayList<>();
    private static List<String> displayedCells = new ArrayList<>();
    private static int remainingMarks =  maxMines();

    public void startGame(String cell) {
        firstClick(false);
        dropMines(cell);
        startChronometer();
    }

    private void startChronometer() {
        chronometer( new ChronometerThread());
        chronometer().start();
    }

    public static void stopChronometer(){
        chronometer().interrupt();
    }

    private void dropMines(String cell) {
        Random random = new Random();
        List<String> startPerimeter = new CellPerimeter().get(cell);
        while(mines.size() < maxMines()){
            String mine = random.nextInt(rows())+"_"+random.nextInt(columns());
            if(!startPerimeter.contains(mine) && !mine.equals(cell) && !mines.contains(mine)) plantMine(mine);
        }
    }

    private void plantMine(String mine) {
        mines.add(mine);
        new SetAlertPerimeterProcess().run(mine);
    }

    public static void restartGameInfo() {
        flagsFirstLevel.forEach(flag -> camp().get(flag).setCellGroundIcon());
        flagsFirstLevel.clear();
        flagsSecondLevel.forEach(cell -> camp().get(cell).setCellGroundIcon());
        flagsSecondLevel.clear();
        displayedCells.forEach(cell -> camp().get(cell).setCellGroundIcon());
        displayedCells.clear();
        mines.clear();
        remainingMarks = maxMines();
    }

    public static int maxMines(){
        return difficulty.getMines();
    }

    public static int rows(){
        return difficulty.getRows();
    }

    public static int columns(){
        return  difficulty.getColumns();
    }

    public static int subMark(){
        remainingMarks--;
        return remainingMarks;
    }

    public static  int addMark(){
        remainingMarks++;
        return remainingMarks;
    }

    public static List<String> mines(){
        return  mines;
    }

    public static List<String> flagsFirstLevel(){
        return  flagsFirstLevel;
    }

    public static List<String> flagsSecondLevel(){
        return  flagsSecondLevel;
    }

    public static List<String> displayedCells(){
        return  displayedCells;
    }
}

