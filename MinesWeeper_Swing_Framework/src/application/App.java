package application;

import control.*;
import model.Difficulty;
import process.LeftClickProcess;
import process.RightClickProcess;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {
    private static Map<String, Command> commands = new HashMap<>();
    public static Map<String, SwingCell> camp = new HashMap<>();
    public static Difficulty difficulty = new Difficulty(16,16,40);
    private static boolean startClick = true;


    public static void main(String[] args) {
        new App().setVisible(true);
    }

    public App() throws HeadlessException {
        deployUI();
        createCommands();
    }

    private void createCommands() {
        commands.put("Difficult", new DifficultyCommand());
        commands.put("Restart", new RestartCommand());
        commands.put("Exit", new ExitCommand());
    }

    private void deployUI() {
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(25*difficulty.getRows(),30*difficulty.getColumns()));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setJMenuBar(menuBar());
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/icon.png")));
        this.add(board());
    }

    private JPanel board() {
        JPanel board = new JPanel();
        board.setLayout(new java.awt.GridLayout(difficulty.getRows(), difficulty.getColumns()));
        board.setBackground(Color.BLACK);
        deployCells(board);
        return board;

    }

    private void deployCells(JPanel board) {
        for (int i = 0; i < difficulty.getRows(); i++) for (int j = 0; j < difficulty.getColumns(); j++) board.add(cell(i,j));
    }

    private JButton cell(int i, int j) {
        JButton cell = new SwingCell();
        camp.put(i+"_"+j, (SwingCell) cell);
        cell.setName(i+"_"+j);
        cell.setPreferredSize(new Dimension(25, 25));
        cell.addActionListener(e -> new LeftClickProcess().run(i+"_"+j)  );
        cell.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) new RightClickProcess().run(i+"_"+j);
            }
        });
        return cell;
    }


    private JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu());
        return menuBar;        
    }

    private JMenu gameMenu() {
        JMenu menu = new JMenu("Game");
        menu.add(difficultyOperation());
        menu.add(restartOperation());
        menu.add(exitOperation());
        return  menu;
    }
    private JMenuItem difficultyOperation() {
        JMenuItem operation = new JMenuItem("Difficulty");
        operation.addActionListener(e -> commands.get("Difficult").execute());
        return operation;
    }

    private JMenuItem restartOperation() {
        JMenuItem operation = new JMenuItem("Restart game");
        operation.addActionListener(e -> commands.get("Restart").execute());
        return operation;
    }

    private JMenuItem exitOperation() {
        JMenuItem operation = new JMenuItem("Exit");
        operation.addActionListener(e -> commands.get("Exit").execute());
        return operation;
    }

    public static boolean startClick(){
        return startClick;
    }
    public static void started(){
        startClick = false;
    }

}
