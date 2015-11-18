package process;

import control.Game;
import static application.App.startClick;
import static application.App.camp;
import static control.Game.*;

public class LeftClickProcess {
    public void run(String cell) {
        if(startClick) new Game().start(cell);
        if(isFlag(cell) || isDisplayed(cell)) return;
        setDisplayed(cell);
        handleCellDisplay(cell);
    }

    private void handleCellDisplay(String cell) {
        if(isMine(cell)) displayAllCamp();
        else if(camp.get(cell).getAlertLevel() == 0) new setSecurePerimeterProcess().run(cell);
            else displayCell(cell);
    }

    private void setDisplayed(String cell) {
        displayedCells().add(cell);
    }

    private boolean isFlag(String cell) {
        return flags().contains(cell);
    }

    private boolean isMine(String cell) {
        return mine().contains(cell);
    }

    private boolean isDisplayed(String cell) {
        return displayedCells().contains(cell);
    }

    private void displayAllCamp() {
        camp.forEach((s, cell) -> display(s));
    }

    private void display(String cell) {
        setDisplayed(cell);
        if(isFlag(cell) && !isMine(cell)) displayWrongMine(cell);
        else if (isMine(cell)) displayMine(cell);
            else displayCell(cell);
    }

    private void displayCell(String cell) {
        camp.get(cell).setCellIcon();
    }

    private void displayMine(String cell) {
        camp.get(cell).setMineIcon();
    }

    private void displayWrongMine(String cell) {
        camp.get(cell).setWrongMineIcon();
    }
}
