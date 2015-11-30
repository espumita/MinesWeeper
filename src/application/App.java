package application;

import control.*;
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
    private static Map<String, SwingCell> camp = new HashMap<>();
    private static boolean firstClick = true;
    private static GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private static Map<String,JComponent> components = new HashMap<>();
    private static ChronometerThread chronometer;

    public static void main(String[] args) {
        new App().setVisible(true);
    }

    public App() throws HeadlessException {
        createCommands();
        deployUI();
        pack();
    }

    private void createCommands() {
        commands.put("Easy", new EasyDifficultyCommand());
        commands.put("Medium", new MediumDifficultyCommand());
        commands.put("Hard", new HardDifficultyCommand());
        commands.put("Custom", new CustomDifficultyCommand());
        commands.put("Reset", new ResetCommand());
        commands.put("Exit", new ExitCommand());
    }

    private void deployUI() {
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        applicationResize(this,16,16);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setJMenuBar(menuBar());
        this.setIconImage(new ImageIcon("images/icon.png").getImage());
        this.add(mainPanel());
        setContentPane(mainPanel());
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
        infoPanel.add(remainingMines());
        infoPanel.add(resetButton());
        infoPanel.add(chronometerPanel());
        return infoPanel;
    }

    private JLabel remainingMines() {
        SwingRemainingMines mines = new SwingRemainingMines();
        mines.setBackground(Color.cyan);
        mines.setOpaque(true);
        mines.setRemainingMines(40);
        components.put("mines",mines);
        return mines;
    }

    private JButton resetButton() {
        JButton reset = new JButton();
        reset.setIcon(new ImageIcon("images/reset.png"));
        reset.setPreferredSize(new Dimension(75,75));
        reset.addActionListener(e -> ((OperationCommand) commands.get("Reset")).execute());
        return reset;

    }

    private JPanel board() {
        JPanel board = new JPanel();
        board.setBackground(Color.darkGray);
        board.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        components.put("board",board);
        boardResize(16,16);
        gridBagConstraints.insets = new Insets(0,0,0,0);
        board.setLayout(new GridBagLayout());
        deployCells(board,16,16);
        return board;
    }

    private JLabel chronometerPanel() {
        SwingChronometer chronometer = new SwingChronometer();
        chronometer.setBackground(Color.cyan);
        chronometer.setOpaque(true);
        components.put("chronometer",chronometer);
        chronometer.resetTime();
        return chronometer;
    }


    public static void deployCells(JPanel board,int i,int j) {
        IntStream.range(0,i).forEach(k -> IntStream.range(0,j).forEach(l -> board.add(cell(k,l),gridBagConstraints)));
    }

    private static JButton cell(int i, int j) {
        JButton cell = new SwingCell();
        camp.put(i+"_"+j, (SwingCell) cell);
        cell.setName(i+"_"+j);
        cell.setToolTipText(i+"_"+j);
        gridBagConstraints.gridx = j;
        gridBagConstraints.gridy = i;
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
        JMenu menu = new JMenu("GameControl");
        menu.add(changeDifficultyOperation());
        menu.add(exitOperation());
        return menu;
    }

    private JMenu changeDifficultyOperation() {
        JMenu operation = new JMenu("Difficulty");
        operation.add(easyMenu());
        operation.add(mediumMenu());
        operation.add(hardMenu());
        operation.add(customMenu());
        return operation;
    }

    private JMenuItem customMenu() {
        JMenuItem custom = new JMenuItem("Custom");
        custom.addActionListener(e -> ((DifficultyCommand) commands.get("Custom")).execute(this));
        return custom;
    }

    private JMenuItem hardMenu() {
        JMenuItem hard = new JMenuItem("Hard");
        hard.addActionListener(e -> ((DifficultyCommand) commands.get("Hard")).execute(this));
        return hard;
    }

    private JMenuItem mediumMenu() {
        JMenuItem medium = new JMenuItem("Medium");
        medium.addActionListener(e -> ((DifficultyCommand) commands.get("Medium")).execute(this));
        return medium;
    }

    private JMenuItem easyMenu() {
        JMenuItem easy = new JMenuItem("Easy");
        easy.addActionListener(e -> ((DifficultyCommand) commands.get("Easy")).execute(this));
        return easy;
    }

    private JMenuItem exitOperation() {
        JMenuItem operation = new JMenuItem("Exit");
        operation.addActionListener(e -> ((OperationCommand) commands.get("Exit")).execute());
        return operation;
    }

    public static void firstClick(boolean status){
        firstClick = status;
    }

    public static boolean firstClick(){
        return firstClick;
    }

    public static  Map<String, SwingCell> camp(){
        return camp;
    }

    public static  Map<String, JComponent> components(){
        return components;
    }

    public static ChronometerThread chronometer(){
        return chronometer;
    }

    public static void chronometer(ChronometerThread newChronometer){
        chronometer = newChronometer;
    }

    public static void applicationResize(JFrame frame, int height, int width){
        frame.setMinimumSize(new Dimension(25*height,25*width+90));
    }

    public static void boardResize(int height,int width) {
        components.get("board").setMinimumSize(new Dimension(25*height,25*width));
    }
}
