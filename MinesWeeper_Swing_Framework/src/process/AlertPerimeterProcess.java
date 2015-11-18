package process;

import application.App;
import model.AlertPerimeter;

public class AlertPerimeterProcess {
    public void run(String cell) {
        new AlertPerimeter().get(cell).stream().forEach(s -> alert(s));
    }

    private void alert(String cell){
        App.camp.get(cell).alert();
    }
}
