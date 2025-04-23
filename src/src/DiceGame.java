import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * DiceGame is a simple GUI-based dice game where a player competes against a computer.
 * The first to reach or pass 24 points wins. If a player rolls the same number
 * three times in a row, their score resets. Game logs are shown in a text area.
 * The game includes a button to return to the Main Menu at any time.
 *
 * @author Mustafa Haider
 */
public class DiceGame extends JFrame {

    private MainMenu mainMenu;
    private JButton rollClick;
    private JLabel playerLabel, aiLabel, messageLabel;
    private JTextArea turnLog;
    private int playerScore = 0, aiScore = 0;
    private int previousRoll = -1, streak = 0;
    private Random random;

    /**
     * Constructs the DiceGame window
     * @param menu the MainMenu instance to return to after quitting the game
     */
    public DiceGame(MainMenu menu) {
        mainMenu = menu;
        setTitle("Dice Game");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        random = new Random();
        setupField();
        setVisible(true);
    }

    /**
     * Sets up the GUI layout for the game, including labels, buttons, and log area
     */
    private void setupField() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(3, 1));

        playerLabel = new JLabel("Player Score: 0", SwingConstants.CENTER);
        aiLabel = new JLabel("AI Score: 0", SwingConstants.CENTER);
        messageLabel = new JLabel("Click roll to play!", SwingConstants.CENTER);

        top.add(playerLabel);
        top.add(aiLabel);
        top.add(messageLabel);

        rollClick = new JButton("Roll Dice");
        rollClick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(rollClick);
        buttons.add(backButton);

        turnLog = new JTextArea();
        turnLog.setEditable(false);
        JScrollPane logScroll = new JScrollPane(turnLog);

        add(top, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(logScroll, BorderLayout.SOUTH);
    }

    /**
     * Rolls the dice for both player and AI, updates scores and streaks,
     * and checks for any winning condition.
     */
    private void rollDice() {
        int playerRoll = random.nextInt(6) + 1;
        int aiRoll = random.nextInt(6) + 1;

        if (playerRoll == previousRoll) {
            streak++;
        } else {
            streak = 1;
        }
        previousRoll = playerRoll;

        if (streak == 3) {
            playerScore = 0;
            streak = 0;
            messageLabel.setText("Rolled " + playerRoll + " again! Streak reset!");
            turnLog.append("Player rolled " + playerRoll + " (Streak reset!)\n");
        } else {
            playerScore += playerRoll;
            aiScore += aiRoll;
            messageLabel.setText("You rolled: " + playerRoll + ", AI rolled: " + aiRoll);
            turnLog.append("You: " + playerRoll + " | AI: " + aiRoll + "\n");
        }

        playerScores();
        checkWinner();
    }

    /**
     * Updates the score labels to reflect the current scores.
     */
    private void playerScores() {
        playerLabel.setText("Player Score: " + playerScore);
        aiLabel.setText("AI Score: " + aiScore);
    }

    /**
     * Checks if the player or AI has reached the winning score and handles
     * win/tie messages accordingly.
     *
     * @throws HeadlessException if the environment does not support a keyboard, display, or mouse
     */
    private void checkWinner() throws HeadlessException {
        if (playerScore >= 24 && aiScore >= 24) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            resetGame();
        } else if (playerScore >= 24) {
            JOptionPane.showMessageDialog(this, "You win!");
            resetGame();
        } else if (aiScore >= 24) {
            JOptionPane.showMessageDialog(this, "AI wins!");
            resetGame();
        }
    }

    /**
     * Resets all game state variables to their starting values.
     */
    private void resetGame() {
        playerScore = 0;
        aiScore = 0;
        previousRoll = -1;
        streak = 0;
        messageLabel.setText("Click roll to play");
        playerScores();
        turnLog.append("--- New Game ---\n");
    }

    /**
     * Returns to the main menu and closes the current game window.
     */
    private void returnToMenu() {
        mainMenu.setVisible(true);
        dispose();
    }
}
