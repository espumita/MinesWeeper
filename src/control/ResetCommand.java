package control;

import javax.swing.*;

import static application.App.*;
import static control.Game.restartGameInfo;

public class ResetCommand implements Command {

    @Override
    public void execute() {
        restartGameInfo();
        if(firstClick) return;
        chronometer.interrupt();
        ((JLabel) components.get("chronometer")).setText("0");
        ((JLabel) components.get("mines")).setText(Integer.toString(difficulty.getMines()));
        camp.forEach((s, cell) -> cell.resetCell());
        started(false);
    }
}
