package application;


import control.GameControl;
import model.difficulty.CustomDifficulty;
import view.UI.CustomDifficultyDisplay;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static control.GameControl.columns;
import static control.GameControl.rows;

public class CustomDifficultyDialog extends JDialog implements CustomDifficultyDisplay{
    private Map<String,JTextField> fieldsComponents = new HashMap<>();

    public CustomDifficultyDialog() {
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(500,200));
        this.setTitle("Select custom properties: ");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(mainPanel());
        this.pack();
    }

    @Override
    public void display() {
        fieldsComponents.get("rows").setText("");
        fieldsComponents.get("columns").setText("");
        fieldsComponents.get("mines").setText("");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.add(rowsLabel());
        mainPanel.add(rowsField());
        mainPanel.add(columnsLabel());
        mainPanel.add(columnsField());
        mainPanel.add(minesLabel());
        mainPanel.add(minesField());
        mainPanel.add(acceptButton());
        mainPanel.add(cancelButton());
        return mainPanel;
    }

    private JButton cancelButton() {
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> dispose());
        return cancel;
    }

    private JButton acceptButton() {
        JButton accept = new JButton("Accept");
        accept.addActionListener(e -> changeDifficulty());
        return accept;
    }

    private  void changeDifficulty() {
        GameControl.changeDifficulty(new CustomDifficulty(fieldContent("rows"),fieldContent("columns"),fieldContent("mines")));
        new ApplicationNewSize(rows(),columns()).refresh();
        dispose();
    }

    private int fieldContent(String field){
        try{
            return Integer.parseInt(fieldsComponents.get(field).getText());
        }catch (Exception e){ return  0; }
    }

    private JTextField rowsField() {
        JTextField field = new JTextField();
        fieldsComponents.put("rows",field);
        field.setPreferredSize(new Dimension(40,24));
        return field;
    }

    private JTextField columnsField() {
        JTextField field = new JTextField();
        fieldsComponents.put("columns",field);
        field.setPreferredSize(new Dimension(40,24));
        return field;
    }

    private JTextField minesField() {
        JTextField field = new JTextField();
        fieldsComponents.put("mines",field);
        field.setPreferredSize(new Dimension(40,24));
        return field;
    }

    private JLabel rowsLabel() {
        JLabel label = new JLabel("Rows: ");
        return label;
    }
    private JLabel columnsLabel() {
        JLabel label = new JLabel("Columns: ");
        return label;
    }
    private JLabel minesLabel() {
        JLabel label = new JLabel("Mines: ");
        return label;
    }
}
