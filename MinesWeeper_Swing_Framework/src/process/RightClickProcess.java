package process;



import swing.App;
import control.Game;
import swing.SwingCell;


import javax.swing.*;

public class RightClickProcess{

    public void run(String cell) {
        if(App.camp.get(cell).isDisplayed()) return;
        Game.flags().contains(cell);
        if(Game.flags().contains(cell)){
            App.camp.get(cell).setIcon(new ImageIcon("images/ground.png"));
            Game.flags().remove(cell);
        }else{
            if(Game.flags().size() >= App.difficulty.getMines()) return;
            App.camp.get(cell).setIcon(new ImageIcon("images/flag.png"));
            Game.flags().add(cell);
        }

    }
}
