package control;


import application.SwingChronometer;
import application.SwingRemainingMines;

import static application.App.*;
import static control.GameControl.maxMines;
import static control.GameControl.restartGameInfo;

public class ResetCommand implements OperationCommand {

    @Override
    public void execute() {
        restartGameInfo();
        ((SwingRemainingMines) components().get("mines")).setRemainingMines(maxMines());
        if(firstClick()) return;
        restartChronometer();
        camp().forEach((s, cell) -> cell.resetCell());
        firstClick(true);
    }

    private void restartChronometer() {
        chronometer().interrupt();
        ((SwingChronometer) components().get("chronometer")).resetTime();
    }
}
