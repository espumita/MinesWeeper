package control.command;

import model.difficulty.MediumDifficulty;


import static application.Application.applicationResize;
import static control.GameControl.changeDifficulty;

public class MediumDifficultyCommand implements Command {
    @Override
    public void execute() {
        changeDifficulty(new MediumDifficulty());
        applicationResize();
    }

}
