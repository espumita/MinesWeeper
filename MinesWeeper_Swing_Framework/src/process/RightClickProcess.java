package process;



import aplication.App;
import control.Game;


import javax.swing.*;

public class RightClickProcess{

    public void run(String cellName) {
        App.Cell temp = App.camp.get(cellName);
        if(temp.isDisplayed()) return;
        if(!temp.isMarked()){
            if(Game.flagsNumber >= App.difficulty.getMines()) return;
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
