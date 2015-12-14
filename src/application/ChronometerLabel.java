package application;

import javax.swing.*;

public class ChronometerLabel extends JLabel {
    public void resetTime(){
        this.setText("0");
    }

    public void time(int time){
        this.setText(Integer.toString(time));
    }
}
