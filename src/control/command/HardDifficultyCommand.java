package control.command;

import model.difficulty.Difficulty;
import model.difficulty.HardDifficulty;

import static application.Application.applicationResize;
import static control.GameControl.changeDifficulty;


public class HardDifficultyCommand implements Command {
    @Override
    public void execute() {
        Difficulty newDifficulty = new HardDifficulty();
        changeDifficulty(newDifficulty);
        applicationResize();
    }

}
