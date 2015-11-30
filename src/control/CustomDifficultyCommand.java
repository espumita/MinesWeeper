package control;

import view.DisplayCustomDifficultyDialog;

import javax.swing.*;


public class CustomDifficultyCommand implements DifficultyCommand {
    public void execute(JFrame app) {
        new DisplayCustomDifficultyDialog().display(app);
    }

}
