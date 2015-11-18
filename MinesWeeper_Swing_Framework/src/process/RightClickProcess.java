package process;

import control.Game;

import static application.App.camp;
import static application.App.difficulty;
import static control.Game.flags;

public class RightClickProcess{

    public void run(String cell) {
        if(isDisplayed(cell)) return;
        if(flags().contains(cell)) removeFlag(cell);
        else addFlag(cell);
    }

    private void addFlag(String cell) {
        if(flags().size() >= difficulty.getMines()) return;
        displayFlag(cell);
        flags().add(cell);
    }

    private void removeFlag(String cell) {
        displayGround(cell);
        flags().remove(cell);
    }

    private void displayGround(String cell) {
        camp.get(cell).setCellGroundIcon();
    }

    private void displayFlag(String cell) {
        camp.get(cell).setFlagIcon();
    }

    private boolean isDisplayed(String cell) {
        return Game.displayedCells().contains(cell);
    }
}
