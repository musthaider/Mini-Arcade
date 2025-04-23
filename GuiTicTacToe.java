import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiTicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private TicTacToe game;


    public GuiTicTacToe () {
        //Will Ask player if they want play against ai or another player
        String[] option = {"2 players", "Play vs computer"};
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode: ",
                "Tic tac toe", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, option, option[0]);
        GameMode mode = (choice == 0) ? GameMode.PLAYER_VS_PLAYER : GameMode.PLAYER_VS_COMPUTER;
        game = new TicTacToe(mode);

        //GUI PART 2
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

        private void handleMove (int row, int col ) {
            if (game.makeMove(row, col)) {
                buttons[row][col].setText(String.valueOf(game.getCurrentPlayer()));
                char winner = game.checkWinner();


                if (winner != ' ') {
                    JOptionPane.showMessageDialog(frame, winner + " VICYORY!");
                    game.resetGame();
                    resetBoard();
                    return;

                }else if (game.isBoardFull()){
                    JOptionPane.showMessageDialog(frame, "Game ends in a Draw!");
                    game.resetGame();
                    resetBoard();
                    return;
                }
                game.switchPlayer();

                //AI play if its a Human vs Computer game and its O's turn
                if (game.getMode() == GameMode.PLAYER_VS_COMPUTER && game.getCurrentPlayer()== 'O' ) {
                    makeComputerMove();
                }
            }
        }

        private void makeComputerMove() {
            game.makeComputerMove();
            char[][] board = game.getBoard();

            for (int r= 0; r < 3; r++){
                for(int c = 0; c < 3; c++) {
                    buttons[r][c].setText(String.valueOf(board[r][c]));
                }
            }

            char winner = game.checkWinner();
            if (winner != ' ') {
                game.resetGame();
                resetBoard();
            } else if (game.isBoardFull()) {
                JOptionPane.showMessageDialog(frame,"Game ends in a draw!");
                game.resetGame();
                resetBoard();
            } else {
                game.switchPlayer();
                }
            }

            private void resetBoard() {
            for(JButton[] row : buttons) {
                for(JButton button : row ) {
                    button.setText(" ");
                }
            }



        }

        public static void main(String[] args) {
        new GuiTicTacToe();
        }
    }






