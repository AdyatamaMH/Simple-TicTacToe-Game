import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    private final JButton[][] buttons = new JButton[3][3];
    private final char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private boolean gameOver = false;
    private final JLabel statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
    private void resetGame() {
    }

    public TicTacToe() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button;
                button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                button.setBackground(Color.blue);
                button.setOpaque(false);
                button.setBorderPainted(true);
                buttonPanel.add(button);
                buttons[i][j] = button;
                button.addActionListener(this);
            }
        }
        JButton resetButton;
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());

        JPanel controlPanel = new JPanel();
        controlPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        JButton button = (JButton) e.getSource();
        int row = -1, col = -1;

        // find the button's row and column
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if (row == -1 || col == -1 || board[row][col] != '\0') {
            return;
        }

        board[row][col] = currentPlayer;
        button.setText(Character.toString(currentPlayer));

        if (checkForWinner(row, col)) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            gameOver = true;
            return;
        }

        // check for tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    tie = false;
                    break;
                }
            }
        }

        if (tie) {
            statusLabel.setText("It's a tie!");
            gameOver = true;
            return;
        }

        switchPlayer();
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }


    private void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    private boolean checkForWinner(int row, int col) {
        // check row
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
            buttons[row].clone();
            buttons[col].clone();
            return true;
        }

        // check column
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
            buttons[row].clone();
            buttons[col].clone();
            return true;
        }

        // check diagonal
        if (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            buttons[row].clone();
            buttons[col].clone();
            return true;
        }

        // check anti-diagonal
        return row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer;
        
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
