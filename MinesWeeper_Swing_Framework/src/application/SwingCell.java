package application;

import javax.swing.*;

public class SwingCell extends JButton {
    private int alertLevel;

    public SwingCell() {
        alertLevel = 0;
        setCellGroundIcon();
    }

    public int getAlertLevel() {
        return alertLevel;
    }

    public void alert() { alertLevel++; }

    public void setCellIcon() {
        setIcon(new ImageIcon("images/"+alertLevel+"mine.png"));
    }

    public void setWrongMineIcon(){
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