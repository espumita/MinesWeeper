package process;

import application.App;
import control.Game;
import model.SafePerimeter;

public class SafePerimeterProcess {

    public void run(String cell) {
        new SafePerimeter().get(cell).stream().forEach(s -> safe(s));
    }

    private void safe(String cell) {
        if(App.camp.get(cell).getValue() == 0) new SafePerimeterProcess().run(cell);
        else displayCell(cell);
    }

    private void displayCell(String cell) {
        App.camp.get(cell).setCellIcon();
        Game.displayedCells().add(cell);
    }

}
