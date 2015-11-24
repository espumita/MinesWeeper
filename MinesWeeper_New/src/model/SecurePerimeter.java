package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static application.App.camp;
import static control.Game.flags;
import static control.Game.displayedCells;

public class SecurePerimeter implements Perimeter {
    private  List<String> perimeter = new ArrayList<>();
    private String[] perimeterLimits;

    @Override
    public List<String> get(String cell) {
        if(flags().contains(cell)) return perimeter;
        secureProtocol(cell);
        perimeterLimits = cell.split("_");
        IntStream.range(perimeterStart(0),perimeterEnd(0)).forEach(k -> IntStream.range(perimeterStart(1),perimeterEnd(1)).forEach(l -> markCell(k+"_"+l)));
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
    public void markCell(String cell) {
        if(isNotTheCenter(cell) && exists(cell) && isSafe(cell)) perimeter.add(cell);
    }

    private void secureProtocol(String cell) {
        camp.get(cell).setCellStartIcon();
        displayedCells().add(cell);
    }

    private boolean isNotTheCenter(String cell) {
        return cell != perimeterCenter();
    }

    private String perimeterCenter(){
        return Integer.parseInt(perimeterLimits[0])+"_"+Integer.parseInt(perimeterLimits[1]);
    }


    private boolean exists(String cell) {
        return camp.containsKey(cell);
    }


    private boolean isSafe(String cell) {
        return !displayedCells().contains(cell) && !flags().contains(cell);
    }

}
