package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static application.App.camp;

public class AlertPerimeter implements Perimeter {
    private List<String> perimeter = new ArrayList<>();
    private String[] positions;

    @Override
    public List<String> get(String cell) {
        positions = cell.split("_");
        IntStream.range(cell(0)-1,cell(0)+2).forEach(k -> IntStream.range(cell(1)-1,cell(1)+2).forEach(l -> checkCell(k+"_"+l)));
        return  perimeter;
    }

    private int cell(int position) {
        return Integer.parseInt(positions[position]);
    }

    private void checkCell(String cell) {
        if( isNotThis(cell) && exists(cell)) perimeter.add(cell);
    }

    private boolean isNotThis(String cell) {
        return cell != cell(0)+"_"+cell(1);
    }

    private boolean exists(String cell) {
        return camp.containsKey(cell);
    }
}
