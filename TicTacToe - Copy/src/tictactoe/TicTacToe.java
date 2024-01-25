import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe{
  private Shape xShape = new XShape();
    private Shape oShape = new OShape();

    
    int Height = 500;
    int Width = 550; 

    JFrame GameWidow = new JFrame("Tic-Tac-Toe");
    JLabel GameLabel = new JLabel();
    JPanel Table_Panel = new JPanel();
    JPanel TablePanel = new JPanel();

    JButton[][] table = new JButton[3][3];
    SlotState[][] InitialState = new SlotState[3][3]; 
    String x = "X";
    String o = "O";
    String currentPlayer = x;

    boolean Gameover = false;
    int Switchplayer = 0;

    boolean SinglePlayerMode = false;


    
    private static TicTacToe Instance;

    private List<GameObserver> Observers = new ArrayList<>();

    private TicTacToe() {
        initializeGame();
        initializeBoardState();
    }

    TicTacToe(boolean singlePlayerMode) {
        this(); 
        this.SinglePlayerMode = singlePlayerMode;

        if (singlePlayerMode) {
            GameLabel.setText("X's turn.");
        }
    }


    public static TicTacToe getInstance() {
        if (Instance == null) {
            Instance = new TicTacToe();
        }
        return Instance;
    }
    

    private void initializeGame() {
        GameWidow.setVisible(true);
        GameWidow.setSize(Height, Width);
        GameWidow.setLocationRelativeTo(null);
        GameWidow.setResizable(false);
        GameWidow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameWidow.setLayout(new BorderLayout());

        GameLabel.setBackground(Color.GRAY);
        GameLabel.setForeground(Color.RED);
        GameLabel.setFont(new Font("Arial", Font.BOLD, 50));
        GameLabel.setHorizontalAlignment(JLabel.CENTER);
        GameLabel.setText("Tic-Tac-Toe");
        GameLabel.setOpaque(true);

        Table_Panel.setLayout(new BorderLayout());
        Table_Panel.add(GameLabel);
        GameWidow.add(Table_Panel, BorderLayout.NORTH);

        TablePanel.setLayout(new GridLayout(3, 3));
        TablePanel.setBackground(Color.black);
        GameWidow.add(TablePanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                table[r][c] = tile;
                TablePanel.add(tile);

                tile.setBackground(Color.black);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (Gameover) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer);
                            Switchplayer++;
                            checkWinner();
                            if (!Gameover) {
                                currentPlayer = currentPlayer.equals(x) ? o : x;
                                GameLabel.setText(currentPlayer + "'s turn.");

                                if (SinglePlayerMode && currentPlayer.equals(o)) {
                                    computerMove(); // Trigger computer move after player's move
            }
            }
            }
            }
            });
            }
            }
            }
