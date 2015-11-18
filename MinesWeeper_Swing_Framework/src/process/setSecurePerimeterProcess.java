package process;


import model.SecurePerimeter;

import static application.App.camp;
import static control.Game.displayedCells;

public class SetSecurePerimeterProcess {

    public void run(String cell) {
        new SecurePerimeter().get(cell).stream().forEach(s -> safe(s));
    }

    private void safe(String cell) {
        if(camp.get(cell).getAlertLevel() == 0) new SetSecurePerimeterProcess().run(cell);
        else displayCell(cell);
    }

    private void displayCell(String cell) {
        camp.get(cell).setCellIcon();
        displayedCells().add(cell);
    }

}
