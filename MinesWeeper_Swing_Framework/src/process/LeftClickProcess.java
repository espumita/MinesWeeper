package process;

import application.App;
import control.Game;


public class LeftClickProcess {
    public void run(String cell) {
        if(App.startClick()) new Game().start(cell);
        if(Game.flags().contains(cell) || isDisplayed(cell)) return;
        Game.displayedCells().add(cell);
        if(Game.mine().contains(cell)) displayAllBoard();
        else{
            if( App.camp.get(cell).getValue() == 0) displaySafeBoard(cell);
            else App.camp.get(cell).setCellIcon();
        }
    }


    private boolean isDisplayed(String cell) {
        return Game.displayedCells().contains(cell);
    }

    private void displayAllBoard() {
        for (String cell : App.camp.keySet()){
            Game.displayedCells().add(cell);
            if(Game.flags().contains(cell)){
                if (!Game.mine().contains(cell)) App.camp.get(cell).setBadMineIcon();
            }else{
                if(Game.mine().contains(cell)) App.camp.get(cell).setMineIcon();
                else  App.camp.get(cell).setCellIcon();
            }
        }
    }



    private void displaySafeBoard(String cell) {
        String[] a = cell.split("_");
        int i = Integer.parseInt(a[0]);
        int j = Integer.parseInt(a[1]);
        if(Game.flags().contains(cell)) return;
        App.camp.get(cell).setCellStartIcon();
        Game.displayedCells().add(cell);
        if(i-1 >= 0) if( !isDisplayed((i-1)+"_"+j) && App.camp.get((i-1)+"_"+j).getValue() == 0) displaySafeBoard((i-1)+"_"+j); else displayAlertedCell(i-1,j);
        if(j-1 >= 0) if( !isDisplayed(i+"_"+(j-1)) && App.camp.get(i+"_"+(j-1)).getValue() == 0) displaySafeBoard(i+"_"+(j-1)); else displayAlertedCell(i,j-1);
        if(i+1 <= App.difficulty.getRows()-1) if(!isDisplayed((i+1)+"_"+j) && App.camp.get((i+1)+"_"+j).getValue() == 0) displaySafeBoard((i+1)+"_"+j);else displayAlertedCell(i+1,j);
        if(j+1 <= App.difficulty.getColumns()-1) if(!isDisplayed(i+"_"+(j+1)) && App.camp.get(i+"_"+(j+1)).getValue() == 0) displaySafeBoard(i+"_"+(j+1));else displayAlertedCell(i,j+1);
        if(i-1 >= 0 && j-1 >= 0) if( !isDisplayed((i-1)+"_"+(j-1)) && App.camp.get((i-1)+"_"+(j-1)).getValue() == 0) displaySafeBoard((i-1)+"_"+(j-1));else displayAlertedCell(i-1,j-1);
        if(i-1 >= 0 && j+1 <= App.difficulty.getColumns()-1) if( !isDisplayed((i-1)+"_"+(j+1)) && App.camp.get((i-1)+"_"+(j+1)).getValue() == 0) displaySafeBoard((i-1)+"_"+(j+1));else displayAlertedCell(i-1,j+1);
        if(i+1 <= App.difficulty.getRows()-1 && j-1 >= 0) if( !isDisplayed((i+1)+"_"+(j-1)) && App.camp.get((i+1)+"_"+(j-1)).getValue() == 0) displaySafeBoard((i+1)+"_"+(j-1));else displayAlertedCell(i+1,j-1);
        if(i+1 <= App.difficulty.getRows()-1 && j+1 <= App.difficulty.getColumns()-1 )if(!isDisplayed((i+1)+"_"+(j+1)) && App.camp.get((i+1)+"_"+(j+1)).getValue() == 0) displaySafeBoard((i+1)+"_"+(j+1));else displayAlertedCell(i+1,j+1);
    }

    private void displayAlertedCell(int i, int j) {
        App.camp.get(i+"_"+j).setCellIcon();
        Game.displayedCells().add(i+"_"+j);
    }
}
