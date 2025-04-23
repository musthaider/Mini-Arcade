import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Mini Game Center");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        setVisible(true);
    }

    private void initMenu() {
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton rpsButton = new JButton("Rock Paper Scissors");
        JButton tttButton = new JButton("Tic Tac Toe");
        JButton diceButton = new JButton("Dice Game");
        JButton quitButton = new JButton("Quit");

        rpsButton.addActionListener(e -> openRockPaperScissors());
        tttButton.addActionListener(e -> openTicTacToe());
        diceButton.addActionListener(e -> openDiceGame());
        quitButton.addActionListener(e -> System.exit(0));

        add(rpsButton);
        add(tttButton);
        add(diceButton);
        add(quitButton);
    }

    public void openRockPaperScissors() {
        new RockPaperScissors();
        setVisible(false);
    }

    public void openTicTacToe() {
        new TicTacToe();
        setVisible(false);
    }

    public void openDiceGame() {
        new DiceGame(this);
        setVisible(false);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
