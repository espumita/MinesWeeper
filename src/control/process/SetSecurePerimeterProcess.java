package control.process;


import model.Perimeter;

import static application.Application.campButton;
import static application.Application.campCell;
import static control.GameControl.displayedCells;
import static control.GameControl.flagsFirstLevel;

public class SetSecurePerimeterProcess implements Process{
    @Override
    public void run(String cell) {
        displayCell(cell);
        new Perimeter(cell).get().
                stream().
                filter(c -> isAvailableToSpread(c)).
                forEach(s -> checkSpread(s));
    }

    private void checkSpread(String cell) {
        if(campCell(cell).alertLevel() == 0) spread(cell);
        else displayCell(cell);
    }

    private void spread(String cell) {
        new SetSecurePerimeterProcess().run(cell);
    }

    private void displayCell(String cell) {
        campButton(cell).icon("images/" + campCell(cell).alertLevel() + "mine.png");
        displayedCells().add(cell);
    }

    private boolean isAvailableToSpread(String cell) {
        return !displayedCells().contains(cell) && !flagsFirstLevel().contains(cell);
    }

}
