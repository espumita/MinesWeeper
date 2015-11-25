package process;


import model.SecurePerimeter;

import static application.App.camp;
import static control.Game.displayedCells;

public class SetSecurePerimeterProcess implements Process{
    @Override
    public void run(String cell) {
        new SecurePerimeter().get(cell).stream().forEach(s -> secure(s));
    }

    private void secure(String cell) {
        if(camp.get(cell).getAlertLevel() == 0) new SetSecurePerimeterProcess().run(cell);
        else displayCell(cell);
    }

    private void displayCell(String cell) {
        camp.get(cell).setCellIcon();
        displayedCells.add(cell);
    }

}
