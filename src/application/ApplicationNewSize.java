package application;

import control.command.ResetCommand;

import javax.swing.*;
import java.awt.*;

public class ApplicationNewSize {
    private final int width;
    private final int height;

    public ApplicationNewSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void refresh(){
        Application.staticApplicationReference().setMinimumSize(new Dimension(25*height,25* width+90));
        Application.components().get("board").setMinimumSize(new Dimension(25*height,25*width));
        Application.components().get("board").removeAll();
        Application.deployCells((JPanel) Application.components().get("board"),width,height);
        new ResetCommand().execute();
        Application.staticApplicationReference().pack();
    }
}
