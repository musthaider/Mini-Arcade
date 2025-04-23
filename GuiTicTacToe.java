import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiTicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private TicTacToe game;
    private ImageIcon xIcon;
    private ImageIcon oIcon;

    public GuiTicTacToe() {
        String[] option = {"2 players", "Play vs computer"};
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode: ",
                "Tic tac toe!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, option, option[0]);
        GameMode mode = (choice == 0) ? GameMode.PLAYER_VS_PLAYER : GameMode.PLAYER_VS_COMPUTER;
        game = new TicTacToe(mode);

        // Load images for X and O
        xIcon = new ImageIcon("x.png");
        oIcon = new ImageIcon("o.png");

        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int r = row, c = col;
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                buttons[row][col].addActionListener(e -> handleMove(r, c));
                frame.add(buttons[row][col]);
            }
        }
        frame.setVisible(true);
    }

    private void handleMove(int row, int col) {
        if (game.makeMove(row, col)) {
            updateButton(row, col);
            char winner = game.checkWinner();

            if (winner != ' ') {
                showWinnerMessage(winner);
                game.resetGame();
                resetBoard();
                return;
            } else if (game.isBoardFull()) {
                JOptionPane.showMessageDialog(frame, "Game ends in a Draw!");
                game.resetGame();
                resetBoard();
                return;
            }
            game.switchPlayer();

            if (game.getMode() == GameMode.PLAYER_VS_COMPUTER && game.getCurrentPlayer() == 'O') {
                makeComputerMove();
            }
        }
    }

    private void makeComputerMove() {
        game.computerMove();
        updateAllButtons();

        char winner = game.checkWinner();
        if (winner != ' ') {
            showWinnerMessage(winner);
            game.resetGame();
            resetBoard();
        } else if (game.isBoardFull()) {
            JOptionPane.showMessageDialog(frame, "Game ends in a draw!");
            game.resetGame();
            resetBoard();
        } else {
            game.switchPlayer();
        }
    }

    private void updateButton(int r, int c) {
        char mark = game.getBoard()[r][c];
        if (mark == 'X') {
            buttons[r][c].setIcon(xIcon);
        } else if (mark == 'O') {
            buttons[r][c].setIcon(oIcon);
        }
    }

    private void updateAllButtons() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                updateButton(r, c);
            }
        }
    }

    private void showWinnerMessage(char winner) {
        String message;
        if (game.getMode() == GameMode.PLAYER_VS_COMPUTER && winner == 'O') {
            message = "Computer wins!";
        } else if (game.getMode() == GameMode.PLAYER_VS_COMPUTER) {
            message = "Player wins!";
        } else {
            message = (winner == 'X') ? "Player One wins!" : "Player Two wins!";
        }
        JOptionPane.showMessageDialog(frame, message);
    }

    private void resetBoard() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setIcon(null);
            }
        }
    }



    public static void main(String[] args){
        new GuiTicTacToe();
    }
}