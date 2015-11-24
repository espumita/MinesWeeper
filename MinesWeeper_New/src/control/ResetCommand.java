package control;

import javax.swing.*;

import static application.App.*;
import static control.Game.clearInfo;

public class ResetCommand implements Command {

    @Override
    public void execute() {
        if(firstClick) return;
        chronometer.interrupt();
        ((JLabel) components.get("chronometer")).setText("0");
        camp.forEach((s, cell) -> cell.resetCell());
        clearInfo();
        started(false);
    }
}
