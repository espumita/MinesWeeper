package process;

import application.SwingChronometer;


import static application.App.components;

public class ChronometerThread extends Thread {
    private int seconds = 1;
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { break; }
            ((SwingChronometer)components().get("chronometer")).setTimeTo(seconds++);
        }
    }

}
