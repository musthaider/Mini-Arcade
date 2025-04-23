import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class RockPaperScissors extends JFrame {
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel computerChoiceLabel, playerChoiceLabel, resultLabel, scoreLabel;
    private Timer animationTimer;
    private int currentFrame = 0;
    private String[] choices = {"Rock", "Paper", "Scissors"};
    private int playerScore = 0, computerScore = 0;
    private boolean isAnimating = false;
    private String playerChoice = "";
    private ImageIcon rockIcon, paperIcon, scissorsIcon;

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 900));
        loadImages();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JPanel gameArea = new JPanel(new GridLayout(2, 1, 20, 40));
        gameArea.setPreferredSize(new Dimension(700, 500));

        JPanel computerPanel = new JPanel();
        computerPanel.setLayout(new BoxLayout(computerPanel, BoxLayout.Y_AXIS));
        JLabel computerLabel = new JLabel("Computer", JLabel.CENTER);
        computerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        computerChoiceLabel = new JLabel();
        computerChoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        computerPanel.add(computerLabel);
        computerPanel.add(Box.createVerticalStrut(20));
        computerPanel.add(computerChoiceLabel);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        JLabel playerLabel = new JLabel("Player", JLabel.CENTER);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        playerChoiceLabel = new JLabel();
        playerChoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerPanel.add(playerLabel);
        playerPanel.add(Box.createVerticalStrut(20));
        playerPanel.add(playerChoiceLabel);

        gameArea.add(computerPanel);
        gameArea.add(playerPanel);

        resultLabel = new JLabel("Choose your move!", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 36));
        scoreLabel = new JLabel("Score - Player: 0 Computer: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        rockButton = new JButton(rockIcon);
        paperButton = new JButton(paperIcon);
        scissorsButton = new JButton(scissorsIcon);

        Dimension btnSize = new Dimension(180, 180);
        rockButton.setPreferredSize(btnSize);
        paperButton.setPreferredSize(btnSize);
        scissorsButton.setPreferredSize(btnSize);

        rockButton.addActionListener(e -> playerMove("Rock"));
        paperButton.addActionListener(e -> playerMove("Paper"));
        scissorsButton.addActionListener(e -> playerMove("Scissors"));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        mainPanel.add(gameArea);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(resultLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(scoreLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setupAnimation();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadImages() {
        try {
            rockIcon = scaleIcon(new ImageIcon(getClass().getResource("/images/rock.png")));
            paperIcon = scaleIcon(new ImageIcon(getClass().getResource("/images/paper.png")));
            scissorsIcon = scaleIcon(new ImageIcon(getClass().getResource("/images/scissors.png")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load images.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private ImageIcon scaleIcon(ImageIcon icon) {
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void setupAnimation() {
        animationTimer = new Timer(200, new ActionListener() {
            int count = 0;
            public void actionPerformed(ActionEvent e) {
                if (isAnimating) {
                    currentFrame = (currentFrame + 1) % choices.length;
                    updateIcons(choices[currentFrame], true);
                    updateIcons(choices[currentFrame], false);
                    count++;
                    if (count >= 10) {
                        isAnimating = false;
                        count = 0;
                        animationTimer.stop();
                        determineWinner();
                    }
                }
            }
        });
    }

    private void updateIcons(String choice, boolean isPlayer) {
        ImageIcon icon = switch (choice) {
            case "Rock" -> rockIcon;
            case "Paper" -> paperIcon;
            case "Scissors" -> scissorsIcon;
            default -> null;
        };
        if (isPlayer) {
            playerChoiceLabel.setIcon(icon);
        } else {
            computerChoiceLabel.setIcon(icon);
        }
    }

    private void playerMove(String choice) {
        if (isAnimating) return;
        playerChoice = choice;
        isAnimating = true;
        setButtonsEnabled(false);
        animationTimer.start();
    }

    private void determineWinner() {
        String computerChoice = choices[new Random().nextInt(choices.length)];
        updateIcons(playerChoice, true);
        updateIcons(computerChoice, false);

        String result;
        if (playerChoice.equals(computerChoice)) {
            result = "It's a tie!";
        } else if (
                (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerScore++;
            result = "You win!";
        } else {
            computerScore++;
            result = "Computer wins!";
        }

        resultLabel.setText(result);
        scoreLabel.setText("Score - Player: " + playerScore + " Computer: " + computerScore);

        if (playerScore >= 3 || computerScore >= 3) {
            endGame();
        } else {
            setButtonsEnabled(true);
        }
    }

    private void endGame() {
        String finalMsg = (playerScore >= 3) ? "You won the match!" : "Computer won the match!";
        resultLabel.setText("Game Over - " + finalMsg);

        int option = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            dispose();
        }
    }

    private void resetGame() {
        playerScore = 0;
        computerScore = 0;
        resultLabel.setText("Choose your move!");
        scoreLabel.setText("Score - Player: 0 Computer: 0");
        computerChoiceLabel.setIcon(null);
        playerChoiceLabel.setIcon(null);
        setButtonsEnabled(true);
    }

    private void setButtonsEnabled(boolean enabled) {
        rockButton.setEnabled(enabled);
        paperButton.setEnabled(enabled);
        scissorsButton.setEnabled(enabled);
    }

    public static void main(String[] args) {
        new RockPaperScissors();
    }
}
