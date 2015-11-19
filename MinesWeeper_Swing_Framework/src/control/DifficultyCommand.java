package control;

import model.Difficulty;
import view.OpenDifficultyDialog;

public class DifficultyCommand implements Command {
    private OpenDifficultyDialog OpenDifficultyDialog;
    @Override
    public void execute() {
         OpenDifficultyDialog.display();
    }
}
