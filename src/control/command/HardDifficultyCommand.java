package control.command;

import application.ApplicationNewSize;
import model.difficulty.HardDifficulty;

import static control.GameControl.changeDifficulty;
import static control.GameControl.columns;
import static control.GameControl.rows;


public class HardDifficultyCommand implements Command {
    @Override
    public void execute() {
        changeDifficulty(new HardDifficulty());
        new ApplicationNewSize(rows(),columns()).refresh();
    }

}
