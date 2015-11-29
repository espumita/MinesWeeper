package process;

import control.GameControl;

import static application.App.firstClick;
import static application.App.camp;
import static control.GameControl.*;


public class LeftClickProcess implements Process{
    @Override
    public void run(String cell) {
        if(firstClick()) new GameControl().startGame(cell);
        if(isFlag(cell) || isDisplayed(cell)) return;
        setDisplayed(cell);
        handleCellDisplay(cell);
    }

    private void handleCellDisplay(String cell) {
        if(isMine(cell)) {
            stopChronometer();
            displayAllCamp();
        }else if(camp().get(cell).getAlertLevel() == 0) new SetSecurePerimeterProcess().run(cell);
            else displayCell(cell);
    }

    private void setDisplayed(String cell) {
        displayedCells().add(cell);
    }

    private boolean isFlag(String cell) {
        return flagsFirstLevel().contains(cell);
    }

    private boolean isMine(String cell) {
        return mines().contains(cell);
    }

    private boolean isDisplayed(String cell) {
        return displayedCells().contains(cell);
    }

    private void displayAllCamp() {
        camp().forEach((s, cell) -> display(s));
    }

    private void display(String cell) {
        setDisplayed(cell);
        if(isFlag(cell) && !isMine(cell)) displayWrongMine(cell);
        else if (isMine(cell)) displayMine(cell);
            else displayCell(cell);
    }

    private void displayCell(String cell) {
        camp().get(cell).setCellIcon();
    }

    private void displayMine(String cell) {
        camp().get(cell).setMineIcon();
    }

    private void displayWrongMine(String cell) {
        camp().get(cell).setWrongMineIcon();
    }
}
