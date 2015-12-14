package control.command;

import model.difficulty.Difficulty;
import model.difficulty.EasyDifficulty;

import static application.Application.applicationResize;
import static control.GameControl.changeDifficulty;


public class EasyDifficultyCommand implements Command {

    @Override
    public void execute() {
        Difficulty newDifficulty = new EasyDifficulty();
        changeDifficulty(newDifficulty);
        applicationResize();
    }


}
