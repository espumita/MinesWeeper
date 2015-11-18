package process;

import application.App;
import application.SwingCell;
import control.Game;

public class LeftClickProcess {
    public void run(String cell) {
        if(App.startClick()) new Game().start(cell);
        if(isFlag(cell) || isDisplayed(cell)) return;
        setDisplayed(cell);
        if(isMine(cell)) displayAllBoard();
        else if(getCell(cell).getValue() == 0) new SafePerimeterProcess().run(cell);
            else getCell(cell).setCellIcon();
    }

    private boolean setDisplayed(String cell) {
        return Game.displayedCells().add(cell);
    }

    private SwingCell getCell(String cell) {
        return App.camp.get(cell);
    }

    private boolean isFlag(String cell) {
        return Game.flags().contains(cell);
    }

    private boolean isMine(String cell) {
        return Game.mine().contains(cell);
    }

    private boolean isDisplayed(String cell) {
        return Game.displayedCells().contains(cell);
    }

    private void displayAllBoard() {
        App.camp.forEach((s, cell) -> displayCell(s));
    }

    private void displayCell(String cell) {
        setDisplayed(cell);
        if(isFlag(cell) && !isMine(cell)) getCell(cell).setBadMineIcon();
        else if (isMine(cell)) getCell(cell).setMineIcon();
            else getCell(cell).setCellIcon();
    }
}
