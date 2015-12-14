package process;


import model.CellPerimeter;

import static  application.Application.camp;

public class SetAlertPerimeterProcess implements Process {
    @Override
    public void run(String cell) {
        new CellPerimeter().get(cell).stream().forEach(c -> camp().get(c).cell().alert());
    }

}
