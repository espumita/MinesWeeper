package process;


import application.SwingRemainingMines;

import static application.App.camp;
import static application.App.components;
import static control.GameControl.*;

public class RightClickProcess implements Process{
    @Override
    public void run(String cell) {
        if(isDisplayed(cell)) return;
        if(flagsFirstLevel().contains(cell)) secondLevelOfFlag(cell);
        else addFlag(cell);
    }

    private void addFlag(String cell) {
        displayFirstLevelFlag(cell);
        flagsFirstLevel().add(cell);
        ((SwingRemainingMines)components().get("mines")).setRemainingMines(subMark());
    }


    private void secondLevelOfFlag(String cell) {
        if(flagsSecondLevel().contains(cell)) removeFlag(cell);
        else levelUp(cell);
    }

    private void removeFlag(String cell) {
        flagsSecondLevel().remove(cell);
        flagsFirstLevel().remove(cell);
        ((SwingRemainingMines) components().get("mines")).setRemainingMines(addMark());
        displayGround(cell);
    }

    private void levelUp(String cell) {
        displaySecondLevelFlag(cell);
        flagsSecondLevel().add(cell);
    }

    private void displaySecondLevelFlag(String cell) {
        camp().get(cell).setFlag2Icon();
    }

    private void displayGround(String cell) {
        camp().get(cell).setCellGroundIcon();
    }

    private void displayFirstLevelFlag(String cell) {
        camp().get(cell).setFlagIcon();
    }

    private boolean isDisplayed(String cell) {
        return displayedCells().contains(cell);
    }

}
