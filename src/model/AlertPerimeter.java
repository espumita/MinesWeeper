package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static application.App.camp;

public class AlertPerimeter implements Perimeter {
    private List<String> perimeter = new ArrayList<>();
    private String[] perimeterLimits;

    @Override
    public List<String> get(String cell) {
        perimeterLimits = cell.split("_");
        IntStream.range(perimeterStart(0), perimeterEnd(0)).forEach(i -> IntStream.range(perimeterStart(1), perimeterEnd(1)).forEach(j -> examineCell(i+"_"+j)));
        return  perimeter;
    }

    @Override
    public int perimeterEnd(int position) {
        return Integer.parseInt(perimeterLimits[position]) + 2;
    }

    @Override
    public int perimeterStart(int position) {
        return Integer.parseInt(perimeterLimits[position]) - 1;
    }

    @Override
    public void examineCell(String cell) {
        if( isNotTheCenter(cell) && exists(cell)) perimeter.add(cell);
    }

    private boolean isNotTheCenter(String cell) {
        return cell != perimeterCenter();
    }

    private String perimeterCenter(){
        return Integer.parseInt(perimeterLimits[0])+"_"+Integer.parseInt(perimeterLimits[1]);
    }

    private boolean exists(String cell) {
        return camp().containsKey(cell);
    }
}
