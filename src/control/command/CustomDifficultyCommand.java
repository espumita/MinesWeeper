package control.command;


import view.DisplayCustomDifficultyDialog;

public class CustomDifficultyCommand implements Command {
    @Override
    public void execute() {
        new DisplayCustomDifficultyDialog().display();
    }

}
