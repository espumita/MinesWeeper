package control;


import aplication.Application;
import model.Difficulty;


public class Game {
    public static int flagsNumber;
    public void start() {
        Application.started();
        flagsNumber = 0;


    }

    public static void addFlag(){
        flagsNumber++;
    }
    public static void minusFlag(){
        flagsNumber++;
    }
}
