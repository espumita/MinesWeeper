package process;

import swing.App;
import control.Game;

import javax.swing.*;

public class LeftClickProcess {
    public void run(String cell) {
        if(App.startClick()) new Game().start(cell);
        if(Game.flags().contains(cell) || App.camp.get(cell).isDisplayed()) return;
        App.camp.get(cell).setDisplayed(true);
        if(Game.mine().contains(cell)) displayAllBoard();
        else{
            if( App.camp.get(cell).getValue() == 0) displaySafeBoard(cell);
            else setCellIcon(cell);
        }
    }
    private void displayAllBoard() {
        for (String cell : App.camp.keySet()){
            App.camp.get(cell).setDisplayed(true);
            if(Game.flags().contains(cell)){
                if (!Game.mine().contains(cell)) setBadMineIcon(cell);
            }else{
                if(Game.mine().contains(cell)) setMineIcon(cell);
                else setCellIcon(cell);
            }
        }
    }

    private void setBadMineIcon(String cell) {
        App.camp.get(cell).setIcon(new ImageIcon("images/badMine.png"));
    }

    private void setMineIcon(String cell) {
        App.camp.get(cell).setIcon(new ImageIcon("images/mine.png"));
    }

    private void setCellIcon(String cell) {
        App.camp.get(cell).setIcon(new ImageIcon("images/" + App.camp.get(cell).getValue() + "mine.png"));
    }

    private void displaySafeBoard(String cell) {
        String[] a = cell.split("_");
        int i = Integer.parseInt(a[0]);
        int j = Integer.parseInt(a[1]);
        if(Game.flags().contains(cell)) return;
        App.camp.get(cell).setIcon(new ImageIcon("images/0mine.png"));
        App.camp.get(cell).setDisplayed(true);
        if(i-1 >= 0) if( !App.camp.get((i-1)+"_"+j).isDisplayed() && App.camp.get((i-1)+"_"+j).getValue() == 0) displaySafeBoard((i-1)+"_"+j); else displayAlertedCell(i-1,j);
        if(j-1 >= 0) if( !App.camp.get(i+"_"+(j-1)).isDisplayed() && App.camp.get(i+"_"+(j-1)).getValue() == 0) displaySafeBoard(i+"_"+(j-1)); else displayAlertedCell(i,j-1);
        if(i+1 <= App.difficulty.getRows()-1) if(!App.camp.get((i+1)+"_"+j).isDisplayed() && App.camp.get((i+1)+"_"+j).getValue() == 0) displaySafeBoard((i+1)+"_"+j);else displayAlertedCell(i+1,j);
        if(j+1 <= App.difficulty.getColumns()-1) if(!App.camp.get(i+"_"+(j+1)).isDisplayed() && App.camp.get(i+"_"+(j+1)).getValue() == 0) displaySafeBoard(i+"_"+(j+1));else displayAlertedCell(i,j+1);
        if(i-1 >= 0 && j-1 >= 0) if( !App.camp.get((i-1)+"_"+(j-1)).isDisplayed() && App.camp.get((i-1)+"_"+(j-1)).getValue() == 0) displaySafeBoard((i-1)+"_"+(j-1));else displayAlertedCell(i-1,j-1);
        if(i-1 >= 0 && j+1 <= App.difficulty.getColumns()-1) if( !App.camp.get((i-1)+"_"+(j+1)).isDisplayed() && App.camp.get((i-1)+"_"+(j+1)).getValue() == 0) displaySafeBoard((i-1)+"_"+(j+1));else displayAlertedCell(i-1,j+1);
        if(i+1 <= App.difficulty.getRows()-1 && j-1 >= 0) if( !App.camp.get((i+1)+"_"+(j-1)).isDisplayed() && App.camp.get((i+1)+"_"+(j-1)).getValue() == 0) displaySafeBoard((i+1)+"_"+(j-1));else displayAlertedCell(i+1,j-1);
        if(i+1 <= App.difficulty.getRows()-1 && j+1 <= App.difficulty.getColumns()-1 )if(!App.camp.get((i+1)+"_"+(j+1)).isDisplayed() && App.camp.get((i+1)+"_"+(j+1)).getValue() == 0) displaySafeBoard((i+1)+"_"+(j+1));else displayAlertedCell(i+1,j+1);
    }

    private void displayAlertedCell(int i, int j) {
        setCellIcon(i+"_"+j);
        App.camp.get(i+"_"+j).setDisplayed(true);
    }
}
