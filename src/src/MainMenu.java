import javax.swing.*;
import java.awt.*;
/**
 * Launcher window for our mini arcade
 * It lets the user choose between Rock Paper Scissors, Tic Tac Toe,
 * or a Dice Game, or just quit the application.
 * Each game is launched in a new window, and the main menu is hidden
 * while a game is active
 *
 * @author Mustafa Haider
 */

public class MainMenu extends JFrame {

    /**
     * The main entry point for the Mini Arcade application.
     */
    public static void main(String[] args) {
        new MainMenu();
    }

    /**
     * Makes the main menu window and initializes the layout and buttons
     */
    public MainMenu() {
        setTitle("Mini Arcade");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        setVisible(true);
    }

    /**
     * Sets up the menu with buttons for launching games or quitting
     */
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

    /**
     * Launches the Rock Paper Scissors game and hides the menu
     */
    public void openRockPaperScissors() {
        new RockPaperScissors();
        setVisible(false);
    }

    /**
     * Launches the Tic Tac Toe game and hides the menu
     */
    public void openTicTacToe() {
        new TicTacToe();
        setVisible(false);
    }

    /**
     * Launches the Dice Game
     */
    public void openDiceGame() {
        new DiceGame(this);
        setVisible(false);
    }

}
