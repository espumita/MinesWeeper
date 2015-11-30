package control;

import javax.swing.*;

import static application.App.*;
import static application.App.components;

public class ResizeApplicationCommand implements Command {
    public void execute(JFrame app,int width, int height){
        applicationResize(app,height,width);
        boardResize(height,width);
        components().get("board").removeAll();
        deployCells((JPanel) components().get("board"),width,height);
        new ResetCommand().execute();
        app.pack();
    }
}
