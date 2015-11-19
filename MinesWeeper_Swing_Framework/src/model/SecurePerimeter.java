package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static application.App.camp;
import static control.Game.flags;
import static control.Game.displayedCells;

public class SecurePerimeter {
    private  List<String> perimeter = new ArrayList<>();
    private String[] positions;

    public List<String> get(String cell) {
        if(flags().contains(cell)) return perimeter;
        secureProtocol(cell);
        positions = cell.split("_");
        IntStream.range(cell(0)-1,cell(0)+2).forEach(k -> IntStream.range(cell(1)-1,cell(1)+2).forEach(l -> checkCell(k+"_"+l)));
        return  perimeter;
    }

    private void secureProtocol(String cell) {
        camp.get(cell).setCellStartIcon();
        displayedCells().add(cell);
    }

    private void checkCell(String cell) {
        if(cell != cell(0)+"_"+cell(1) && camp.containsKey(cell) && isSafe(cell)) perimeter.add(cell);
    }

    private int cell(int position) {
        return Integer.parseInt(positions[position]);
    }
    private boolean isSafe(String cell) {
        return !displayedCells().contains(cell) && !flags().contains(cell);
    }

}
