package model;

import javax.swing.JButton;

public class Cell extends JButton{
    private boolean mine;
    private int value;
    private boolean displayStatus;
    private boolean mark;


    public Cell() {
        super();
        mine = false;
        value = 0;
        displayStatus = false;
        mark = false;
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
    public void updateDisplayStatus(){
        displayStatus = true;
    }

    public void mark() {
        mark = true;
    }
    public  void unMark(){
        mark = false;
    }

    public boolean isMark() {
        return mark;
    }
}
