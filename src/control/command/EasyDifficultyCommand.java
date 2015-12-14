package control.command;

import model.difficulty.Difficulty;
import model.difficulty.EasyDifficulty;

import javax.swing.*;

import static control.GameControl.changeDifficulty;
import static control.GameControl.columns;
import static control.GameControl.rows;

public class EasyDifficultyCommand implements DifficultyCommand {
    @Override
    public void execute(JFrame app) {
        Difficulty newDifficulty = new EasyDifficulty();
        changeDifficulty(newDifficulty);
        new ResizeApplicationCommand().execute(app,rows(),columns());
    }


}
