package control;


import model.Difficulty;
import model.HardDifficulty;

import javax.swing.*;

import static application.App.*;
import static application.App.components;
import static control.GameControl.changeDifficulty;


public class HardDifficultyCommand implements DifficultyCommand {
    @Override
    public void execute(JFrame app) {
        Difficulty newDifficulty = new HardDifficulty();
        changeDifficulty(newDifficulty);
        resizeApplication(app,newDifficulty.getRows(),newDifficulty.getColumns());
    }
    @Override
    public void resizeApplication(JFrame app, int width, int height) {
        applicationResize(app,height,width);
        boardResize(height,width);
        components().get("board").removeAll();
        deployCells((JPanel) components().get("board"),width,height);
        new ResetCommand().execute();
        app.pack();
    }
}
