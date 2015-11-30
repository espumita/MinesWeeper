package control;


import model.Difficulty;
import model.HardDifficulty;

import javax.swing.*;

import static control.GameControl.changeDifficulty;
import static control.GameControl.columns;
import static control.GameControl.rows;


public class HardDifficultyCommand implements DifficultyCommand {
    @Override
    public void execute(JFrame app) {
        Difficulty newDifficulty = new HardDifficulty();
        changeDifficulty(newDifficulty);
        new ResizeApplicationCommand().execute(app,rows(),columns());
    }

}
