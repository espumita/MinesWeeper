package application;

import model.Cell;
import view.UI.CellDisplay;

import javax.swing.*;

public class CellButton extends JButton implements CellDisplay {
    private Cell cell;

    public CellButton(Cell cell) {
        this.cell = cell;
        this.icon("images/ground.png");
    }

    @Override
    public void icon(String path){
        setIcon(new ImageIcon(path));
        this.repaint();
    }

    @Override
    public Cell cell() {
        return cell;
    }
}