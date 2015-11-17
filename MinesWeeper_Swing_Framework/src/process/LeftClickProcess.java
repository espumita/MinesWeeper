package process;

import aplication.App;
import control.Game;

import javax.swing.*;

public class LeftClickProcess {
    public void run(String cellName) {
        if(App.startClick()) new Game().start();
        App.Cell temp = App.camp.get(cellName);
        if(temp.isMarked() || temp.isDisplayed()) return;
        temp.setDisplayed(true);
        if(temp.isMined()){
            displayAllBoard();
        }else{
            String[] position = cellName.split("_");
            if(temp.getValue() == 0) displaySafeBoard(Integer.parseInt(position[0]),Integer.parseInt(position[1]));
            else temp.setIcon(new ImageIcon("images/" + temp.getValue() + "mine.png"));
        }
    }
    private void displayAllBoard() {
        for (int i = 0; i <= App.difficulty.getRows(); i++) {
            for (int j = 0; j <= App.difficulty.getColumns(); j++) {
                App.camp.get(i+"_"+j).setDisplayed(true);
                if(App.camp.get(i+"_"+j).isMarked()){
                    if (!App.camp.get(i+"_"+j).isMined()) App.camp.get(i+"_"+j).setIcon(new ImageIcon("images/badMine.png"));
                }else{
                    if(App.camp.get(i+"_"+j).isMined()){
                        App.camp.get(i+"_"+j).setIcon(new ImageIcon("images/mine.png"));
                    }else{
                        App.camp.get(i+"_"+j).setIcon(new ImageIcon("images/" + App.camp.get(i+"_"+j).getValue() + "mine.png"));
                    }
                }
            }
        }
    }
    private void displaySafeBoard(int i,int j) {
        if(App.camp.get(i+"_"+j).isMarked()) return;
        App.camp.get(i+"_"+j).setIcon(new ImageIcon("images/0mine.png"));
        App.camp.get(i+"_"+j).setDisplayed(true);
        if(i-1 >= 0 && !App.camp.get((i-1)+"_"+j).isDisplayed() && App.camp.get((i-1)+"_"+j).getValue() == 0) displaySafeBoard(i-1,j);else if(i-1 >= 0) displayAlertedCell(i-1,j);
        if(j-1 >= 0 && !App.camp.get(i+"_"+(j-1)).isDisplayed() && App.camp.get(i+"_"+(j-1)).getValue() == 0) displaySafeBoard(i,j-1);else if(j-1 >= 0) displayAlertedCell(i,j-1);
        if(i+1 <= App.difficulty.getRows()-1 && !App.camp.get((i+1)+"_"+j).isDisplayed() && App.camp.get((i+1)+"_"+j).getValue() == 0) displaySafeBoard(i+1,j);else if(i+1 <= App.difficulty.getRows()-1) displayAlertedCell(i+1,j);
        if(j+1 <= App.difficulty.getColumns() && !App.camp.get(i+"_"+(j+1)).isDisplayed() && App.camp.get(i+"_"+(j+1)).getValue() == 0) displaySafeBoard(i,j+1);else if(j+1 <= App.difficulty.getColumns()) displayAlertedCell(i,j+1);
        if(i-1 >= 0 && j-1 >= 0 && !App.camp.get((i-1)+"_"+(j-1)).isDisplayed() && App.camp.get((i-1)+"_"+(j-1)).getValue() == 0) displaySafeBoard(i-1,j-1);else if(i-1 >= 0 && j-1 >= 0) displayAlertedCell(i-1,j-1);
        if(i-1 >= 0 && j+1 <= App.difficulty.getColumns() && !App.camp.get((i-1)+"_"+(j+1)).isDisplayed() && App.camp.get((i-1)+"_"+(j+1)).getValue() == 0) displaySafeBoard(i-1,j+1);else if(i-1 >= 0 && j+1 <= App.difficulty.getColumns()) displayAlertedCell(i-1,j+1);
        if(i+1 <= App.difficulty.getRows() && j-1 >= 0 && !App.camp.get((i+1)+"_"+(j-1)).isDisplayed() && App.camp.get((i+1)+"_"+(j-1)).getValue() == 0) displaySafeBoard(i+1,j-1);else if(i+1 <= App.difficulty.getRows() && j-1 >= 0) displayAlertedCell(i+1,j-1);
        if(i+1 <= App.difficulty.getRows() && j+1 <= App.difficulty.getColumns() && !App.camp.get((i+1)+"_"+(j+1)).isDisplayed() && App.camp.get((i+1)+"_"+(j+1)).getValue() == 0) displaySafeBoard(i+1,j+1);else if(i+1 <= App.difficulty.getRows() && j+1 <= App.difficulty.getColumns()) displayAlertedCell(i+1,j+1);
    }

    private void displayAlertedCell(int i, int j) {

    }
}
