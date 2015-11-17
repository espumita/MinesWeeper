package process;

import aplication.Application;
import control.Game;

import javax.swing.*;
import java.awt.*;

public class LeftClickProcess {
    public void run(String cellName) {
        if(Application.startClick()) new Game().start();
        Application.Cell temp = Application.camp.get(cellName);
        if(temp.isMarked() || temp.isDisplayed()) return;
        temp.setDisplayed(true);
        if(temp.isMined()){
            displayAllBoard();
        }else{
            if(temp.getValue() == 0){
                String[] cellIndex = temp.getName().split("_");
               // displaySafeBoard(Integer.parseInt(cellIndex[1]),Integer.parseInt(cellIndex[2]));
            }else{
                temp.setIcon(new ImageIcon("images/" + temp.getValue() + "mine.png"));
            }
        }
    }
    private void displayAllBoard() {
        for (int i = 0; i <= Application.difficulty.getRows(); i++) {
            for (int j = 0; j <= Application.difficulty.getColumns(); j++) {
                Application.camp.get(i+"_"+j).setDisplayed(true);
                if(Application.camp.get(i+"_"+j).isMarked()){
                    if (!Application.camp.get(i+"_"+j).isMined()) Application.camp.get(i+"_"+j).setIcon(new ImageIcon("images/badMine.png"));
                }else{
                    if(Application.camp.get(i+"_"+j).isMined()){
                        Application.camp.get(i+"_"+j).setIcon(new ImageIcon("images/mine.png"));
                    }else{
                        Application.camp.get(i+"_"+j).setIcon(new ImageIcon("images/" + Application.camp.get(i+"_"+j).getValue() + "mine.png"));
                    }
                }
            }
        }
    }
}
