package control.command;

import application.ApplicationNewSize;
import model.difficulty.EasyDifficulty;

import static control.GameControl.changeDifficulty;
import static control.GameControl.columns;
import static control.GameControl.rows;


public class EasyDifficultyCommand implements Command {

    @Override
    public void execute() {
        changeDifficulty(new EasyDifficulty());
        new ApplicationNewSize(rows(),columns()).refresh();
    }


}
