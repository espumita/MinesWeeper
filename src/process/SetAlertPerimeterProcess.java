package process;

import model.AlertPerimeter;

import static  application.App.camp;

public class SetAlertPerimeterProcess implements Process {
    @Override
    public void run(String cell) {
        new AlertPerimeter().get(cell).stream().forEach(a -> camp().get(a).alert());
    }

}
