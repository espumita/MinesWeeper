package control.process;

import application.ChronometerLabel;


import static application.Application.components;

public class ChronometerThread extends Thread {
    private int seconds = 1;
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { break; }
            ((ChronometerLabel)components().get("chronometer")).setTimeTo(seconds++);
        }
    }

}
