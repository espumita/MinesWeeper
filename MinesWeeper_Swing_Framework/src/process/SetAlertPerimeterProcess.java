package process;

import model.AlertPerimeter;

import static  application.App.camp;

public class SetAlertPerimeterProcess implements Process {
    @Override
    public void run(String cell) {
        new AlertPerimeter().get(cell).stream().forEach(s -> alert(s));
    }

    private void alert(String cell){
        camp.get(cell).alert();
    }
}
