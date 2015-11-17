package process;



import application.App;
import control.Game;

public class RightClickProcess{

    public void run(String cell) {
        if(isDisplayed(cell)) return;
        Game.flags().contains(cell);
        if(Game.flags().contains(cell)){
            App.camp.get(cell).setCellGroundIcon();
            Game.flags().remove(cell);
        }else{
            if(Game.flags().size() >= App.difficulty.getMines()) return;
            App.camp.get(cell).setFlagIcon();
            Game.flags().add(cell);
        }

    }

    private boolean isDisplayed(String cell) {
        return Game.displayedCells().contains(cell);
    }
}
