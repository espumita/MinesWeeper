package control;

import model.Difficulty;
import model.MediumDifficulty;

import javax.swing.*;


import static control.GameControl.changeDifficulty;
import static control.GameControl.columns;
import static control.GameControl.rows;

public class MediumDifficultyCommand implements DifficultyCommand {
    @Override
    public void execute(JFrame app) {
        Difficulty newDifficulty = new MediumDifficulty();
        changeDifficulty(newDifficulty);
        new ResizeApplicationCommand().execute(app,rows(),columns());
    }

}
