package control.process;


import model.Perimeter;

import static  application.Application.camp;

public class SetAlertPerimeterProcess implements Process {
    @Override
    public void run(String cell) {
        new Perimeter(cell).get().stream().forEach(c -> camp().get(c).cell().alert());
    }

}
