package application;


import javax.swing.*;

public class SwingRemainingMines extends JLabel{
    public void setRemainingMines(int marks){
        this.setText(Integer.toString(marks));
    }
}
