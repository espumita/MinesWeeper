package control.command;

import application.ApplicationNewSize;
import model.difficulty.MediumDifficulty;


import static control.GameControl.changeDifficulty;
import static control.GameControl.columns;
import static control.GameControl.rows;

public class MediumDifficultyCommand implements Command {
    @Override
    public void execute() {
        changeDifficulty(new MediumDifficulty());
        new ApplicationNewSize(rows(),columns()).refresh();
    }

}
