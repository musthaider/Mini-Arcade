import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] board;
    private char currentPlayer = 'X';
    private boolean vsComputer;

    public TicTacToe() {
        String[] options = {"2 Players", "Play vs Computer"};
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode:", "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        vsComputer = (choice == 1);

        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        board = new char[3][3];

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final int row = r;
                final int col = c;
                board[r][c] = ' ';
                buttons[r][c] = new JButton("");
                buttons[r][c].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[r][c].addActionListener(e -> handleMove(row, col));
                frame.add(buttons[r][c]);
            }
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleMove(int row, int col) {
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));

            if (checkWinner() != ' ') {
                JOptionPane.showMessageDialog(frame, currentPlayer + " wins!");
                resetGame();
                return;
            }

            if (isBoardFull()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                resetGame();
                return;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

            if (vsComputer && currentPlayer == 'O') {
                computerMove();
            }
        }
    }

    private void computerMove() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') {
                    board[r][c] = 'O';
                    buttons[r][c].setText("O");

                    if (checkWinner() != ' ') {
                        JOptionPane.showMessageDialog(frame, "O wins!");
                        resetGame();
                        return;
                    }

                    if (isBoardFull()) {
                        JOptionPane.showMessageDialog(frame, "It's a draw!");
                        resetGame();
                        return;
                    }

                    currentPlayer = 'X';
                    return;
                }
            }
        }
    }

    private boolean isBoardFull() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == ' ')
                    return false;
        return true;
    }

    private char checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];
        return ' ';
    }

    private void resetGame() {
        currentPlayer = 'X';
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
                buttons[r][c].setText("");
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}