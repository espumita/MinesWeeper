package control;

import view.OpenDifficultyDialog;

public class DifficultyCommand implements Command {
    private OpenDifficultyDialog OpenDifficultyDialog = new OpenDifficultyDialog();
    @Override
    public void execute() {
         OpenDifficultyDialog.display();
    }
}
