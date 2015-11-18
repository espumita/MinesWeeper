package process;

import application.App;
import application.SwingCell;
import control.Game;

public class RightClickProcess{

    public void run(String cell) {
        if(isDisplayed(cell)) return;
        if(Game.flags().contains(cell)){
            getCell(cell).setCellGroundIcon();
            Game.flags().remove(cell);
        }else{
            if(Game.flags().size() >= App.difficulty.getMines()) return;
            getCell(cell).setFlagIcon();
            Game.flags().add(cell);
        }

    }

    private SwingCell getCell(String cell) {
        return App.camp.get(cell);
    }

    private boolean isDisplayed(String cell) {
        return Game.displayedCells().contains(cell);
    }
}
