package model;

import javax.swing.JButton;

public class Cell extends JButton{
    private boolean mine;
    private int value;
    private boolean displayStatus;


    public Cell() {
        super();
        mine = false;
        value = 0;
        displayStatus = false;
    }
    public boolean isMine() {
        return mine;
    }

    public int getValue() {
        return value;
    }

    public void setMined() {
        mine = true;
    }

    public void alert() {
        this.value++;
    }

    public boolean getDisplayStatus() {
        return displayStatus;
    }
    public void updateDisplayStatus(boolean status){
        displayStatus = status;
    }
}
