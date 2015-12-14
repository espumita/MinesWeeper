package process;


import model.CellPerimeter;

import static application.Application.camp;
import static control.GameControl.displayedCells;
import static control.GameControl.flagsFirstLevel;

public class SetSecurePerimeterProcess implements Process{
    @Override
    public void run(String cell) {
        displayCell(cell);
        new CellPerimeter().get(cell).stream().filter(c -> isAvailableToSpread(c)).forEach(s -> checkSpread(s));
    }

    private void checkSpread(String cell) {
        if(camp().get(cell).cell().alertLevel() == 0) spread(cell);
        else displayCell(cell);
    }

    private void spread(String cell) {
        new SetSecurePerimeterProcess().run(cell);
    }

    private void displayCell(String cell) {
        camp().get(cell).icon("images/" + camp().get(cell).cell().alertLevel() + "mine.png");
        displayedCells().add(cell);
    }

    private boolean isAvailableToSpread(String cell) {
        return !displayedCells().contains(cell) && !flagsFirstLevel().contains(cell);
    }

}
