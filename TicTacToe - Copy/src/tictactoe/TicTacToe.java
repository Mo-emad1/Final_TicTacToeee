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
   private void initializeBoardState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                InitialState[i][j] = new EmptyState();
             }
             }
             }

    void computerMove() {
        if (Gameover || !SinglePlayerMode || currentPlayer.equals(x)) {
            return;
        }

        Random random = new Random();
        int row, column;
        do {
            row = random.nextInt(3);
            column = random.nextInt(3);
        } while (!table[row][column].getText().equals(""));

        table[row][column].setText(o);
        Switchplayer++;
        setSlotState(row, column, new OState()); // Update state
        checkWinner();

        if (!Gameover) {
            currentPlayer = currentPlayer.equals(x) ? o : x;
            GameLabel.setText(currentPlayer + "'s turn.");
        }
    }


    void setSlotState(int row, int column, SlotState state) {
        InitialState[row][column] = state;
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        GameLabel.setForeground(Color.GREEN);
        GameLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        GameLabel.setText("Tie!");
    }

    void checkWinner() {
        for (int row = 0; row < 3; row++) {
            if (table[row][0].getText() == "") continue;

            if (table[row][0].getText().equals(table[row][1].getText()) &&
                table[row][1].getText().equals(table[row][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(table[row][i]);
                }
                Gameover = true;
                break;
            }
            }

        for (int column = 0; column < 3; column++) {
            if (table[0][column].getText() == "") continue;

            if (table[0][column].getText().equals(table[1][column].getText()) &&
                table[1][column].getText().equals(table[2][column].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(table[i][column]);
                }
                Gameover = true;
                break;
            }
}

        if (table[0][0].getText().equals(table[1][1].getText()) &&
            table[1][1].getText().equals(table[2][2].getText()) &&
            !table[0][0].getText().equals("")) {
            for (int i = 0; i < 3; i++) {
                setWinner(table[i][i]);
            }
            Gameover = true;
         }

        if (table[0][2].getText().equals(table[1][1].getText()) &&
            table[1][1].getText().equals(table[2][0].getText()) &&
            !table[0][2].getText().equals("")) {
            setWinner(table[0][2]);
            setWinner(table[1][1]);
            setWinner(table[2][0]);
            Gameover = true;
        }

        if (Switchplayer == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(table[r][c]);
                }
            }
            Gameover = true;
        }

        notifyObservers(Gameover, Gameover ? (currentPlayer + " is the winner!") : "Tie!");

        drawBoard(TablePanel.getGraphics());
    }

   private void drawBoard(Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String symbol = table[i][j].getText();
                if (symbol.equals(x)) {
                    xShape.draw(g, i, j);
                } else if (symbol.equals(o)) {
                    oShape.draw(g, i, j);
                }
            }
        }
    }


public void addObserver(GameObserver observer) {
        Observers.add(observer);
          }

    private void notifyObservers(boolean isGameOver, String result) {
        for (GameObserver observer : Observers) {
             observer.updateGameStatus(isGameOver, result);
        }
      }
      }
