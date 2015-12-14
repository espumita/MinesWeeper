package control.command;


import view.UI.CustomDifficultyDisplay;

public class CustomDifficultyCommand implements Command {
    private CustomDifficultyDisplay display;

    public CustomDifficultyCommand(CustomDifficultyDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.display();
    }

}
