package application;


import javax.swing.*;

public class RemainingMinesLabel extends JLabel{
    public void remainingMines(int marks){
        this.setText(Integer.toString(marks));
    }
}
