package control;

import javax.swing.*;

import static application.App.*;
import static control.GameControl.maxMines;
import static control.GameControl.restartGameInfo;

public class ResetCommand implements Command {

    @Override
    public void execute() {
        restartGameInfo();
        ((JLabel) components().get("mines")).setText(Integer.toString(maxMines()));
        if(firstClick()) return;
        restartChronometer();
        camp().forEach((s, cell) -> cell.resetCell());
        firstClick(true);
    }

    private void restartChronometer() {
        chronometer().interrupt();
        ((JLabel) components().get("chronometer")).setText("0");
    }
}
