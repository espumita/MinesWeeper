package process;



import aplication.Application;
import control.Game;


import javax.swing.*;

public class RightClickProcess{

    public void run(String cellName) {
        Application.Cell temp = Application.camp.get(cellName);
        if(temp.isDisplayed()) return;
        if(!temp.isMarked()){
            if(Game.flagsNumber >= Application.difficulty.getMines()) return;
            temp.setIcon(new ImageIcon("images/flag.png"));
            temp.setMarked(true);
            Game.addFlag();

        }else{
            temp.setIcon(new ImageIcon("images/ground.png"));
            temp.setMarked(false);
            Game.minusFlag();
        }

    }
}
