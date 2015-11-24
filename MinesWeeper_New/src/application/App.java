package application;

import control.*;
import model.*;
import process.ChronometerThread;
import process.LeftClickProcess;
import process.RightClickProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class App extends JFrame {
    private static Map<String, Command> commands = new HashMap<>();
    public static Map<String, SwingCell> camp = new HashMap<>();
    public static Difficulty difficulty = new MediumDifficulty();
    public static boolean firstClick = true;
    public GridBagConstraints gridBagConstraints = new GridBagConstraints();
    public static Map<String,JComponent> components = new HashMap<>();
    public static ChronometerThread chronometer;

    public static void main(String[] args) {
        new App().setVisible(true);
    }

    public App() throws HeadlessException {
        createCommands();
        deployUI();
        pack();
    }

    private void createCommands() {
        commands.put("Difficult", new DifficultyCommand());
        commands.put("Reset", new ResetCommand());
        commands.put("Exit", new ExitCommand());
    }

    private void deployUI() {
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(setApplicationDimension());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setJMenuBar(menuBar());
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/icon.png")));
        this.add(mainPanel());
        setContentPane(mainPanel());
    }

    private Dimension setApplicationDimension() {
        return new Dimension(new Dimension(25*difficulty.getRows()+30,25*difficulty.getColumns()+30+80));
    }

    private JPanel mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setBackground(Color.darkGray);
        mainPanel.add(topPanel(),BorderLayout.NORTH);
        mainPanel.add(board(),BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel topPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.darkGray);
        infoPanel.setPreferredSize(new Dimension(50,90));
        infoPanel.add(resetButton());
        infoPanel.add(chronometer());
        return infoPanel;
    }

    private JButton resetButton() {
        JButton reset = new JButton();
        reset.setIcon(new ImageIcon("images/reset.png"));
        reset.setPreferredSize(new Dimension(75,75));
        reset.addActionListener(e -> commands.get("Reset").execute());
        return reset;

    }

    private JPanel board() {
        JPanel board = new JPanel();
        board.setBackground(Color.darkGray);
        board.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        board.setPreferredSize(new Dimension(difficulty.getRows()*25+30,difficulty.getColumns()*25+30));
        gridBagConstraints.insets = new Insets(0,0,0,0);
        board.setLayout(new GridBagLayout());
        deployCells(board);
        return board;
    }

    private JLabel chronometer() {
        JLabel chronometer = new JLabel();
        components.put("chronometer",chronometer);
        chronometer.setText("0");
        return chronometer;
    }


    private void deployCells(JPanel board) {
        IntStream.range(0,difficulty.getRows()).forEach(i -> IntStream.range(0,difficulty.getColumns()).forEach(j -> board.add(cell(i,j),gridBagConstraints)));
    }

    private JButton cell(int i, int j) {
        JButton cell = new SwingCell();
        camp.put(i+"_"+j, (SwingCell) cell);
        cell.setName(i+"_"+j);
        //cell.setToolTipText(i+"_"+j);
        gridBagConstraints.gridx = i;
        gridBagConstraints.gridy = j;
        cell.setPreferredSize(new Dimension(25,25));
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
        menu.add(exitOperation());
        return menu;
    }

    private JMenuItem difficultyOperation() {
        JMenuItem operation = new JMenuItem("Difficulty");
        operation.addActionListener(e -> commands.get("Difficult").execute());
        return operation;
    }

    private JMenuItem exitOperation() {
        JMenuItem operation = new JMenuItem("Exit");
        operation.addActionListener(e -> commands.get("Exit").execute());
        return operation;
    }

    public static void started(boolean status){
        firstClick = !status;
    }
}
