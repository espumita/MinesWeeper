package control;

import javax.swing.*;

public interface DifficultyCommand extends Command {
    void execute(JFrame app);
    void resizeApplication(JFrame app, int i, int j);
}
