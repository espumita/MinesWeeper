package model;

import application.App;
import control.Game;

import java.util.ArrayList;
import java.util.List;

public class SafePerimeter {
    public List<String> get(String cell) {
        List<String> perimeter = new ArrayList<>();
        if(Game.flags().contains(cell)) return perimeter;
        App.camp.get(cell).setCellStartIcon();
        Game.displayedCells().add(cell);
        String[] a = cell.split("_");
        int i = Integer.parseInt(a[0]);
        int j = Integer.parseInt(a[1]);
        if(lowerLimitOfRows(i) && !isDisplayed((i-1)+"_"+j)) perimeter.add((i-1)+"_"+j);
        if(lowerLimitOfRows(i) && lowerLimitOfColumns(j) && !isDisplayed((i-1)+"_"+(j-1))) perimeter.add((i-1)+"_"+(j-1));
        if(lowerLimitOfRows(i) && upperLimitOfColumns(j) && !isDisplayed((i-1)+"_"+(j+1))) perimeter.add((i-1)+"_"+(j+1));
        if(upperLimitOfRows(i) && !isDisplayed((i+1)+"_"+j)) perimeter.add((i+1)+"_"+j);
        if(upperLimitOfRows(i) && lowerLimitOfColumns(j) && !isDisplayed((i+1)+"_"+(j-1))) perimeter.add((i+1)+"_"+(j-1));
        if(upperLimitOfRows(i) && upperLimitOfColumns(j) && !isDisplayed((i+1)+"_"+(j+1))) perimeter.add((i+1)+"_"+(j+1));
        if(lowerLimitOfColumns(j) && !isDisplayed(i+"_"+(j-1))) perimeter.add(i+"_"+(j-1));
        if(upperLimitOfColumns(j) && !isDisplayed(i+"_"+(j+1))) perimeter.add(i+"_"+(j+1));
        return  perimeter;
    }

    private boolean isDisplayed(String cell) {
        return Game.displayedCells().contains(cell);
    }

    private boolean upperLimitOfColumns(int j) {
        return j+1 <= App.difficulty.getColumns()-1;
    }

    private boolean upperLimitOfRows(int i) {
        return i+1 <= App.difficulty.getRows()-1;
    }

    private boolean lowerLimitOfColumns(int j) {
        return j-1 >= 0;
    }

    private boolean lowerLimitOfRows(int i) {
        return i-1 >= 0;
    }
}
