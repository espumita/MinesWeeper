package model;

import java.util.ArrayList;
import java.util.List;

import static application.App.difficulty;
import static application.App.camp;
import static control.Game.flags;
import static control.Game.displayedCells;

public class SecurePerimeter {
    public List<String> get(String cell) {
        List<String> perimeter = new ArrayList<>();
        if(flags().contains(cell)) return perimeter;
        camp.get(cell).setCellStartIcon();
        displayedCells().add(cell);
        String[] a = cell.split("_");
        int i = Integer.parseInt(a[0]);
        int j = Integer.parseInt(a[1]);
        if(lowerLimitOfRows(i) && isSafe((i-1)+"_"+j)) perimeter.add((i-1)+"_"+j);
        if(lowerLimitOfRows(i) && lowerLimitOfColumns(j) && isSafe((i-1)+"_"+(j-1))) perimeter.add((i-1)+"_"+(j-1));
        if(lowerLimitOfRows(i) && upperLimitOfColumns(j) && isSafe((i-1)+"_"+(j+1))) perimeter.add((i-1)+"_"+(j+1));
        if(upperLimitOfRows(i) && isSafe((i+1)+"_"+j)) perimeter.add((i+1)+"_"+j);
        if(upperLimitOfRows(i) && lowerLimitOfColumns(j) && isSafe((i+1)+"_"+(j-1))) perimeter.add((i+1)+"_"+(j-1));
        if(upperLimitOfRows(i) && upperLimitOfColumns(j) && isSafe((i+1)+"_"+(j+1))) perimeter.add((i+1)+"_"+(j+1));
        if(lowerLimitOfColumns(j) && isSafe(i+"_"+(j-1))) perimeter.add(i+"_"+(j-1));
        if(upperLimitOfColumns(j) && isSafe(i+"_"+(j+1))) perimeter.add(i+"_"+(j+1));
        return  perimeter;
    }

    private boolean isSafe(String cell) {
        return !displayedCells().contains(cell) && !flags().contains(cell);
    }

    private boolean upperLimitOfColumns(int j) {
        return j+1 <= difficulty.getColumns()-1;
    }

    private boolean upperLimitOfRows(int i) {
        return i+1 <= difficulty.getRows()-1;
    }

    private boolean lowerLimitOfColumns(int j) {
        return j-1 >= 0;
    }

    private boolean lowerLimitOfRows(int i) {
        return i-1 >= 0;
    }
}
