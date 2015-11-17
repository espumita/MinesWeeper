package control;


import aplication.App;


public class Game {
    public static int flagsNumber;
    public void start() {
        App.started();
        flagsNumber = 0;


    }

    public static void addFlag(){
        flagsNumber++;
    }
    public static void minusFlag(){
        flagsNumber++;
    }
}
