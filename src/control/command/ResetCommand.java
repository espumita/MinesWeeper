package control.command;


import application.ChronometerLabel;
import application.RemainingMinesLabel;

import static application.Application.*;
import static control.GameControl.maxMines;
import static control.GameControl.restartGameInfo;

public class ResetCommand implements OperationCommand {

    @Override
    public void execute() {
        restartGameInfo();
        ((RemainingMinesLabel) components().get("mines")).setRemainingMines(maxMines());
        if(firstClick()) return;
        restartChronometer();
        camp().forEach((s, cell) -> cell.cell().alertLevel(0));
        firstClick(true);
    }

    private void restartChronometer() {
        chronometer().interrupt();
        ((ChronometerLabel) components().get("chronometer")).resetTime();
    }
}
