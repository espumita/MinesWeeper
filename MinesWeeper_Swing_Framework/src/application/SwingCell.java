package application;

import javax.swing.*;

public class SwingCell extends JButton {
    private int value;

    public SwingCell() {
        value = 0;
        setCellGroundIcon();
    }

    public int getValue() {
        return value;
    }

    public void alert() { this.value++; }

    public void setCellIcon() {
        setIcon(new ImageIcon("images/"+value+"mine.png"));
    }

    public void setBadMineIcon(){
        setIcon(new ImageIcon("images/badMine.png"));
    }

    public void setMineIcon() {
       setIcon(new ImageIcon("images/mine.png"));
    }

    public void setCellStartIcon() {
        setIcon(new ImageIcon("images/0mine.png"));
    }

    public void setCellGroundIcon(){
        setIcon(new ImageIcon("images/ground.png"));
    }

    public void setFlagIcon() {
        setIcon(new ImageIcon("images/flag.png"));
    }
}