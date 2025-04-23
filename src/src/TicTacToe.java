import javax.swing.*;
import java.awt.*;

/**
 * A Tic Tac Toe game. Supports both two-player mode and
 * player vs computer mode with a basic AI that plays the first empty spot it finds
 * After each game, you can choose to restart or return to the game mode selection menu
 *
 * @author AbdulAziz Heji
 */
public class TicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] board;
    private char currentPlayer = 'X';
    private boolean vsComputer;

    /**
     * Constructs a new TicTacToe game by showing the initial game mode menu
     */
    public TicTacToe() {
        showMenu();
    }

    /**
     * Displays a menu dialog to let the user choose between two-player or vs computer mode.
     * If the user closes the dialog, the program exits.
     */
    private void showMenu() {
        String[] options = {"2 Players", "Play vs Computer"};
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode:", "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) return;
        vsComputer = (choice == 1);
        setupGame();
    }

    /**
     * Sets up the game window and initializes the game board UI
     */
    private void setupGame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        board = new char[3][3];
        currentPlayer = 'X';

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

    /**
     * Handles a player's move at the specified row and column.
     * @param row the row index of the move
     * @param col the column index of the move
     */
    private void handleMove(int row, int col) {
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));

            if (checkWinner() != ' ') {
                endGame(currentPlayer + " wins!");
                return;
            }

            if (isBoardFull()) {
                endGame("It's a draw!");
                return;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

            if (vsComputer && currentPlayer == 'O') {
                computerMove();
            }
        }
    }
    /**
     * Makes a move for the computer player by placing 'O' in the first available spot
     */
    private void computerMove() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') {
                    board[r][c] = 'O';
                    buttons[r][c].setText("O");

                    if (checkWinner() != ' ') {
                        endGame("O wins!");
                        return;
                    }

                    if (isBoardFull()) {
                        endGame("It's a draw!");
                        return;
                    }

                    currentPlayer = 'X';
                    return;
                }
            }
        }
    }

    /**
     * Checks whether the board is full.
     * @return true if there are no empty cells left, false otherwise
     */
    private boolean isBoardFull() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == ' ')
                    return false;
        return true;
    }

    /**
     * Checks for a winning condition.
     * @return the character ('X' or 'O') of the winner, or a space ' ' if no winner yet
     */
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

    /**
     * Ends the current game and shows a dialog with the result message.
     * Lets the user restart the game or return to the game mode selection menu.
     * @param message the result message to show (e.g., who won or if it's a draw)
     * @throws HeadlessException if GUI components are not supported
     */
    private void endGame(String message) throws HeadlessException {
        String[] options = {"Restart Game", "Return to Menu"};
        int choice = JOptionPane.showOptionDialog(frame, message, "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            resetGame();
        } else {
            frame.dispose();
            showMenu();
        }
    }
    /**
     * Resets the game board and updates the UI to start a new round.
     */
    private void resetGame() {
        currentPlayer = 'X';
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
                buttons[r][c].setText("");
            }
        }
    }
}
