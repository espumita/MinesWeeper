package swing;

import javax.swing.*;

public class SwingCell extends JButton {
    private int value;
    private boolean displayed;
    public SwingCell() {
        value = 0;
        displayed = false;
    }

    public int getValue() {
        return value;
    }

    public void alert() {
        this.value++;
    }

    public boolean isDisplayed() {
        return displayed;
    }
    public void setDisplayed(boolean status){
        displayed = status;
    }
}