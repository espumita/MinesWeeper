package view.process;


import model.Perimeter;

import static application.Application.campCell;

public class SetAlertPerimeterProcess implements Process {
    @Override
    public void run(String cell) {
        new Perimeter(cell).get().
                stream().
                forEach(c -> campCell(c).alert());
    }

}
