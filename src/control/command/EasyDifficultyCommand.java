package control.command;

import model.difficulty.EasyDifficulty;

import static application.Application.applicationResize;
import static control.GameControl.changeDifficulty;


public class EasyDifficultyCommand implements Command {

    @Override
    public void execute() {
        changeDifficulty(new EasyDifficulty());
        applicationResize();
    }


}
