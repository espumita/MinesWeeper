package view.process;

import control.GameControl;

import java.lang.*;

import static application.Application.*;
import static control.GameControl.*;


public class LeftClickProcess implements Process {
    @Override
    public void run(String cell) {
        if(firstClick()) new GameControl().startGame(cell);
        if(isFlag(cell) || isDisplayed(cell)) return;
        displayed(cell);
        handleCellDisplay(cell);
    }

    private void handleCellDisplay(String cell) {
        if(isMine(cell)) {
            stopChronometer();
            displayAllCamp();
        }else if(campCell(cell).alertLevel() == 0) new SetSecurePerimeterProcess().run(cell);
            else displayCell(cell);
    }

    private void displayed(String cell) {
        displayedCells().add(cell);
    }

    private boolean isFlag(String cell) {
        return flagsFirstLevel().contains(cell);
    }

    private boolean isMine(String cell) {
        return mines().contains(cell);
    }

    private boolean isDisplayed(String cell) {
        return displayedCells().contains(cell);
    }

    private void displayAllCamp() {
        camp().forEach((cellString, cell) -> display(cellString));
    }

    private void display(String cell) {
        displayed(cell);
        if(isFlag(cell) && !isMine(cell)) displayWrongMine(cell);
        else if (isMine(cell)) displayMine(cell);
            else displayCell(cell);
    }

    private void displayCell(String cell) {
        campButton(cell).icon("images/" + campCell(cell).alertLevel() + "mine.png");
    }

    private void displayMine(String cell) {
        campButton(cell).icon("images/mine.png");
    }

    private void displayWrongMine(String cell) {
        campButton(cell).icon("images/badMine.png");
    }
}
