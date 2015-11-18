package model;

import application.App;

import java.util.ArrayList;
import java.util.List;

public class Perimeter {

    public List<String> get(String cell) {
        List<String> perimeter = new ArrayList<>();
        String[] a = cell.split("_");
        int i = Integer.parseInt(a[0]);
        int j = Integer.parseInt(a[1]);
        if(lowerLimitOfRows(i)) perimeter.add((i-1)+"_"+j);
        if(lowerLimitOfRows(i) && lowerLimitOfColumns(j)) perimeter.add((i-1)+"_"+(j-1));
        if(lowerLimitOfRows(i) && upperLimitOfColumns(j)) perimeter.add((i-1)+"_"+(j+1));
        if(upperLimitOfRows(i)) perimeter.add((i+1)+"_"+j);
        if(upperLimitOfRows(i) && lowerLimitOfColumns(j)) perimeter.add((i+1)+"_"+(j-1));
        if(upperLimitOfRows(i) && upperLimitOfColumns(j)) perimeter.add((i+1)+"_"+(j+1));
        if(lowerLimitOfColumns(j)) perimeter.add(i+"_"+(j-1));
        if(upperLimitOfColumns(j)) perimeter.add(i+"_"+(j+1));
        return  perimeter;
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
