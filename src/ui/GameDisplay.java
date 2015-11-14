package ui;

import model.Grid;
import model.Cell;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class GameDisplay extends javax.swing.JFrame implements ActionListener {
    private javax.swing.JPanel boardPanel;
    private Grid grid;
    private int rowLimit;
    private int columnLimit;
    public GameDisplay(int rows, int columns,int mines){
        this.grid = new Grid(rows,columns);
        this.rowLimit=rows-1;
        this.columnLimit =columns-1;
        initComponents();
        displayGrid(mines);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        Cell cell = (Cell)e.getSource();
        if(cell.isMine()){
            displayAllBoard();
            javax.swing.JOptionPane.showMessageDialog(this,"LOST");
        }else{
            if(cell.getValue() == 0){
                String[] cellIndex = cell.getName().split("_");
                displaySafeBoard(Integer.parseInt(cellIndex[1]),Integer.parseInt(cellIndex[2]));
            }else{
                cell.setIcon(new ImageIcon("images/" + cell.getValue() + "mine.png"));
                cell.setBackground(Color.GREEN);
                cell.updateDisplayStatus(true);
            }
        }
    }



    private void displaySafeBoard(int i, int j) {
        grid.getBoard()[i][j].setIcon(new ImageIcon("images/0mine.png"));
        grid.getBoard()[i][j].setBackground(Color.GREEN);
        grid.getBoard()[i][j].updateDisplayStatus(true);
        if(i-1 >= 0 && !grid.getBoard()[i-1][j].getDisplayStatus() && grid.getBoard()[i-1][j].getValue() == 0) displaySafeBoard(i-1,j);else if(i-1 >= 0) displayAlertedCell(i-1,j);
        if(j-1 >= 0 && !grid.getBoard()[i][j-1].getDisplayStatus() && grid.getBoard()[i][j-1].getValue() == 0) displaySafeBoard(i,j-1);else if(j-1 >= 0) displayAlertedCell(i,j-1);
        if(i+1 <= rowLimit && !grid.getBoard()[i+1][j].getDisplayStatus() && grid.getBoard()[i+1][j].getValue() == 0) displaySafeBoard(i+1,j);else if(i+1 <= rowLimit) displayAlertedCell(i+1,j);
        if(j+1 <= columnLimit && !grid.getBoard()[i][j+1].getDisplayStatus() && grid.getBoard()[i][j+1].getValue() == 0) displaySafeBoard(i,j+1);else if(j+1 <= columnLimit) displayAlertedCell(i,j+1);
        if(i-1 >= 0 && j-1 >= 0 && !grid.getBoard()[i-1][j-1].getDisplayStatus() && grid.getBoard()[i-1][j-1].getValue() == 0) displaySafeBoard(i-1,j-1);else if(i-1 >= 0 && j-1 >= 0) displayAlertedCell(i-1,j-1);
        if(i-1 >= 0 && j+1 <= columnLimit && !grid.getBoard()[i-1][j+1].getDisplayStatus() && grid.getBoard()[i-1][j+1].getValue() == 0) displaySafeBoard(i-1,j+1);else if(i-1 >= 0 && j+1 <= columnLimit) displayAlertedCell(i-1,j+1);
        if(i+1 <= rowLimit && j-1 >= 0 && !grid.getBoard()[i+1][j-1].getDisplayStatus() && grid.getBoard()[i+1][j-1].getValue() == 0) displaySafeBoard(i+1,j-1);else if(i+1 <= rowLimit && j-1 >= 0) displayAlertedCell(i+1,j-1);
        if(i+1 <= rowLimit && j+1 <= columnLimit && !grid.getBoard()[i+1][j+1].getDisplayStatus() && grid.getBoard()[i+1][j+1].getValue() == 0) displaySafeBoard(i+1,j+1);else if(i+1 <= rowLimit && j+1 <= columnLimit) displayAlertedCell(i+1,j+1);
    }

    private void displayAlertedCell(int i, int j) {
        grid.getBoard()[i][j].setIcon(new ImageIcon("images/"+ grid.getBoard()[i][j].getValue()+"mine.png"));
        grid.getBoard()[i][j].setBackground(Color.GREEN);
        grid.getBoard()[i][j].updateDisplayStatus(true);
    }


    private void displayAllBoard() {
        for (int i = 0; i <= rowLimit; i++) {
            for (int j = 0; j <= columnLimit; j++) {
                if(grid.getBoard()[i][j].isMine()) {
                    grid.getBoard()[i][j].setIcon(new ImageIcon("images/mine.png"));
                    grid.getBoard()[i][j].setBackground(Color.RED);
                }else{
                    grid.getBoard()[i][j].setIcon(new ImageIcon("images/"+ grid.getBoard()[i][j].getValue()+"mine.png"));
                    grid.getBoard()[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }


    private void displayGrid(int mines) {
        startGrid();
        dropMines(mines);
        markPerimeters();
        this.paintAll(this.getGraphics());
    }

    private void markPerimeters() {
        for (int i = 0; i <= rowLimit; i++) {
            for (int j = 0; j <= columnLimit; j++) {
                if(grid.getBoard()[i][j].isMine()) alertPerimeter(i,j);
            }
        }
    }

    private void alertPerimeter(int i, int j) {
        if(i-1 >= 0 && !grid.getBoard()[i-1][j].isMine()) grid.getBoard()[i-1][j].alert();
        if(j-1 >= 0 && !grid.getBoard()[i][j-1].isMine()) grid.getBoard()[i][j-1].alert();
        if(i+1 <= rowLimit && !grid.getBoard()[i+1][j].isMine()) grid.getBoard()[i+1][j].alert();
        if(j+1 <= columnLimit && !grid.getBoard()[i][j+1].isMine()) grid.getBoard()[i][j+1].alert();
        if(i-1 >= 0 && j-1 >= 0 && !grid.getBoard()[i-1][j-1].isMine()) grid.getBoard()[i-1][j-1].alert();
        if(i-1 >= 0 && j+1 <= columnLimit && !grid.getBoard()[i-1][j+1].isMine()) grid.getBoard()[i-1][j+1].alert();
        if(i+1 <= rowLimit && j-1 >= 0 && !grid.getBoard()[i+1][j-1].isMine()) grid.getBoard()[i+1][j-1].alert();
        if(i+1 <= rowLimit && j+1 <= columnLimit && !grid.getBoard()[i+1][j+1].isMine()) grid.getBoard()[i+1][j+1].alert();
    }

    private void startGrid() {
        this.boardPanel.setLayout(new java.awt.GridLayout(grid.getRows(), grid.getColumns()));
        for (int i = 0; i <= rowLimit; i++) {
            for (int j = 0; j <= columnLimit; j++) {
                Cell temp = new Cell();
                temp.setName("CellButton_"+i+"_"+j);
                temp.setSize(25,25);
                temp.setMaximumSize(temp.getSize());
                temp.setMinimumSize(temp.getSize());
                temp.setIcon(new ImageIcon("images/ground.png"));
                temp.setBackground(Color.LIGHT_GRAY);
                temp.addActionListener(this);
                temp.setVisible(true);
                this.boardPanel.add(temp);
                grid.add(i,j,temp);
            }
        }
    }

    private void dropMines(int mines) {
        int remainingMines=mines;
        Cell temp;
        Random random = new Random();
        while(remainingMines > 0){
            temp = grid.getBoard()[random.nextInt(rowLimit+1)][random.nextInt(columnLimit +1)];
            if(!temp.isMine()) {
                temp.setMined();
                remainingMines--;
            }
        }
    }

    private void initComponents() {
        boardPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }
}
