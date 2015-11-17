package control;

import application.App;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static List<String> mines = new ArrayList<>();
    private static List<String> flags = new ArrayList<>();
    private static List<String> displayedCells = new ArrayList<>();
    public void start(String startCell) {
        App.started();
        dropMines(startCell);
        markPerimeters();
    }

    private void markPerimeters() {
        App.camp.keySet().stream().filter(cell -> mine().contains(cell)).forEach(this::alertPerimeter);
    }

    private void alertPerimeter(String cell) {
        String[] a = cell.split("_");
        int i = Integer.parseInt(a[0]);
        int j = Integer.parseInt(a[1]);
        Game.mine().contains(cell);
        if(i-1 >= 0 && !Game.mine().contains((i-1)+"_"+j)) App.camp.get((i-1)+"_"+j).alert();
        if(j-1 >= 0 && !Game.mine().contains(i+"_"+(j-1))) App.camp.get(i+"_"+(j-1)).alert();
        if(i+1 <= App.difficulty.getRows()-1 && !Game.mine().contains((i+1)+"_"+j)) App.camp.get((i+1)+"_"+j).alert();
        if(j+1 <= App.difficulty.getColumns()-1 && !Game.mine().contains(i+"_"+(j+1))) App.camp.get(i+"_"+(j+1)).alert();
        if(i-1 >= 0 && j-1 >= 0 && !Game.mine().contains((i-1)+"_"+(j-1))) App.camp.get((i-1)+"_"+(j-1)).alert();
        if(i-1 >= 0 && j+1 <= App.difficulty.getColumns()-1 && !Game.mine().contains((i-1)+"_"+(j+1))) App.camp.get((i-1)+"_"+(j+1)).alert();
        if(i+1 <= App.difficulty.getRows()-1 && j-1 >= 0 && !Game.mine().contains((i+1)+"_"+(j-1))) App.camp.get((i+1)+"_"+(j-1)).alert();
        if(i+1 <= App.difficulty.getRows()-1 && j+1 <= App.difficulty.getColumns()-1 && !Game.mine().contains((i+1)+"_"+(j+1))) App.camp.get((i+1)+"_"+(j+1)).alert();
    }

    private void dropMines(String startCell) {
        Random random = new Random();
        while(mines.size() < App.difficulty.getMines()){
            String temp = random.nextInt(App.difficulty.getRows())+"_"+random.nextInt(App.difficulty.getColumns());
            if(!mines.contains(temp) && !temp.equals(startCell)) mines.add(temp);
        }
    }

    public static List<String>  mine(){
        return mines;
    }

    public static List<String>  flags(){
        return flags;
    }

    public static List<String> displayedCells(){
        return displayedCells;
    }

}

