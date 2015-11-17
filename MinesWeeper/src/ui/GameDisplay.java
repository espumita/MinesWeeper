package ui;

import model.Board;
import model.Cell;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class GameDisplay extends javax.swing.JFrame implements ActionListener {
    private javax.swing.JPanel boardPanel;
    private Board board;
    private int rowLimit;
    private int columnLimit;
    private int mines;
    private int flagsNumber;
    public GameDisplay(int rows, int columns,int mines){
        this.board = new Board(rows,columns);
        this.rowLimit=rows-1;
        this.columnLimit =columns-1;
        this.mines=mines;
        this.flagsNumber = 0;
        initComponents();
        displayGrid(mines);
        setLocationRelativeTo(null);
        setVisible(true);
    }






    public void actionPerformed(ActionEvent e){
        Cell cell = (Cell)e.getSource();
        if(cell.isMark() || cell.getDisplayStatus()) return;
        cell.updateDisplayStatus();
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
            }
        }
    }



    private void displaySafeBoard(int i, int j) {
        if(board.getBoard()[i][j].isMark()) return;
        board.getBoard()[i][j].setIcon(new ImageIcon("images/0mine.png"));
        board.getBoard()[i][j].setBackground(Color.GREEN);
        board.getBoard()[i][j].updateDisplayStatus();
        if(i-1 >= 0 && !board.getBoard()[i-1][j].getDisplayStatus() && board.getBoard()[i-1][j].getValue() == 0) displaySafeBoard(i-1,j);else if(i-1 >= 0) displayAlertedCell(i-1,j);
        if(j-1 >= 0 && !board.getBoard()[i][j-1].getDisplayStatus() && board.getBoard()[i][j-1].getValue() == 0) displaySafeBoard(i,j-1);else if(j-1 >= 0) displayAlertedCell(i,j-1);
        if(i+1 <= rowLimit && !board.getBoard()[i+1][j].getDisplayStatus() && board.getBoard()[i+1][j].getValue() == 0) displaySafeBoard(i+1,j);else if(i+1 <= rowLimit) displayAlertedCell(i+1,j);
        if(j+1 <= columnLimit && !board.getBoard()[i][j+1].getDisplayStatus() && board.getBoard()[i][j+1].getValue() == 0) displaySafeBoard(i,j+1);else if(j+1 <= columnLimit) displayAlertedCell(i,j+1);
        if(i-1 >= 0 && j-1 >= 0 && !board.getBoard()[i-1][j-1].getDisplayStatus() && board.getBoard()[i-1][j-1].getValue() == 0) displaySafeBoard(i-1,j-1);else if(i-1 >= 0 && j-1 >= 0) displayAlertedCell(i-1,j-1);
        if(i-1 >= 0 && j+1 <= columnLimit && !board.getBoard()[i-1][j+1].getDisplayStatus() && board.getBoard()[i-1][j+1].getValue() == 0) displaySafeBoard(i-1,j+1);else if(i-1 >= 0 && j+1 <= columnLimit) displayAlertedCell(i-1,j+1);
        if(i+1 <= rowLimit && j-1 >= 0 && !board.getBoard()[i+1][j-1].getDisplayStatus() && board.getBoard()[i+1][j-1].getValue() == 0) displaySafeBoard(i+1,j-1);else if(i+1 <= rowLimit && j-1 >= 0) displayAlertedCell(i+1,j-1);
        if(i+1 <= rowLimit && j+1 <= columnLimit && !board.getBoard()[i+1][j+1].getDisplayStatus() && board.getBoard()[i+1][j+1].getValue() == 0) displaySafeBoard(i+1,j+1);else if(i+1 <= rowLimit && j+1 <= columnLimit) displayAlertedCell(i+1,j+1);
    }

    private void displayAlertedCell(int i, int j) {
        board.getBoard()[i][j].setIcon(new ImageIcon("images/"+ board.getBoard()[i][j].getValue()+"mine.png"));
        board.getBoard()[i][j].setBackground(Color.GREEN);
        board.getBoard()[i][j].updateDisplayStatus();
    }


    private void displayAllBoard() {
        for (int i = 0; i <= rowLimit; i++) {
            for (int j = 0; j <= columnLimit; j++) {
                board.getBoard()[i][j].updateDisplayStatus();
                if(board.getBoard()[i][j].isMark()){
                    if (!board.getBoard()[i][j].isMine()) board.getBoard()[i][j].setIcon(new ImageIcon("images/badMine.png"));
                    board.getBoard()[i][j].setBackground(Color.YELLOW);
                }else{
                    if(board.getBoard()[i][j].isMine()){
                        board.getBoard()[i][j].setIcon(new ImageIcon("images/mine.png"));
                        board.getBoard()[i][j].setBackground(Color.RED);
                    }else{
                        board.getBoard()[i][j].setIcon(new ImageIcon("images/" + board.getBoard()[i][j].getValue() + "mine.png"));
                        board.getBoard()[i][j].setBackground(Color.GREEN);
                    }
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
                if(board.getBoard()[i][j].isMine()) alertPerimeter(i,j);
            }
        }
    }

    private void alertPerimeter(int i, int j) {
        if(i-1 >= 0 && !board.getBoard()[i-1][j].isMine()) board.getBoard()[i-1][j].alert();
        if(j-1 >= 0 && !board.getBoard()[i][j-1].isMine()) board.getBoard()[i][j-1].alert();
        if(i+1 <= rowLimit && !board.getBoard()[i+1][j].isMine()) board.getBoard()[i+1][j].alert();
        if(j+1 <= columnLimit && !board.getBoard()[i][j+1].isMine()) board.getBoard()[i][j+1].alert();
        if(i-1 >= 0 && j-1 >= 0 && !board.getBoard()[i-1][j-1].isMine()) board.getBoard()[i-1][j-1].alert();
        if(i-1 >= 0 && j+1 <= columnLimit && !board.getBoard()[i-1][j+1].isMine()) board.getBoard()[i-1][j+1].alert();
        if(i+1 <= rowLimit && j-1 >= 0 && !board.getBoard()[i+1][j-1].isMine()) board.getBoard()[i+1][j-1].alert();
        if(i+1 <= rowLimit && j+1 <= columnLimit && !board.getBoard()[i+1][j+1].isMine()) board.getBoard()[i+1][j+1].alert();
    }

    private void startGrid() {
        this.boardPanel.setLayout(new java.awt.GridLayout(board.getRows(), board.getColumns()));
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
                temp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(temp.getDisplayStatus()) return;
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if(!temp.isMark()){
                                if(flagsNumber >= mines) return;
                                temp.setIcon(new ImageIcon("images/flag.png"));
                                temp.mark();
                                flagsNumber++;

                            }else{
                                temp.setIcon(new ImageIcon("images/ground.png"));
                                temp.unMark();
                                flagsNumber--;
                            }
                        }
                    }
                });
                temp.setVisible(true);
                this.boardPanel.add(temp);
                board.add(i,j,temp);
            }
        }
    }

    private void dropMines(int mines) {
        int remainingMines=mines;
        Cell temp;
        Random random = new Random();
        while(remainingMines > 0){
            temp = board.getBoard()[random.nextInt(rowLimit+1)][random.nextInt(columnLimit +1)];
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
