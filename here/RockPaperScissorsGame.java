import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RockPaperScissorsGame extends JFrame {
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel computerChoiceLabel, playerChoiceLabel, resultLabel, scoreLabel;
    private Timer animationTimer;
    private int currentAnimationFrame = 0;
    private String[] choices = {"Rock", "Paper", "Scissors"};
    private int playerScore = 0;
    private int computerScore = 0;
    private boolean isAnimating = false;
    private String playerChoice = "";
    private ImageIcon rockIcon, paperIcon, scissorsIcon;
    private JPanel computerPanel, playerPanel;
    
    public RockPaperScissorsGame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);
        setMinimumSize(new Dimension(800, 900));
        
        loadImages();
        
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        // Create game area panel
        JPanel gameArea = new JPanel(new GridLayout(2, 1, 20, 40));
        gameArea.setPreferredSize(new Dimension(700, 500));
        
        // Create computer panel
        computerPanel = new JPanel();
        computerPanel.setLayout(new BoxLayout(computerPanel, BoxLayout.Y_AXIS));
        JLabel computerLabel = new JLabel("Computer");
        computerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        computerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        computerChoiceLabel = new JLabel();
        computerChoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        computerPanel.add(computerLabel);
        computerPanel.add(Box.createVerticalStrut(20));
        computerPanel.add(computerChoiceLabel);
        
        // Create player panel
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        JLabel playerLabel = new JLabel("Player");
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        playerChoiceLabel = new JLabel();
        playerChoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerPanel.add(playerLabel);
        playerPanel.add(Box.createVerticalStrut(20));
        playerPanel.add(playerChoiceLabel);
        
        gameArea.add(computerPanel);
        gameArea.add(playerPanel);
        
        // Create result display
        resultLabel = new JLabel("Choose your move!");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 36));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create score display
        scoreLabel = new JLabel("Score - Player: 0 Computer: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        rockButton = new JButton(rockIcon);
        paperButton = new JButton(paperIcon);
        scissorsButton = new JButton(scissorsIcon);
        
        // Set size for buttons
        Dimension buttonSize = new Dimension(180, 180);
        rockButton.setPreferredSize(buttonSize);
        paperButton.setPreferredSize(buttonSize);
        scissorsButton.setPreferredSize(buttonSize);
        
        // action listeners
        rockButton.addActionListener(e -> playerMove("Rock"));
        paperButton.addActionListener(e -> playerMove("Paper"));
        scissorsButton.addActionListener(e -> playerMove("Scissors"));
        
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        
        // Add components to main panel
        mainPanel.add(gameArea);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(resultLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(scoreLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
        
        // animation timer
        animationTimer = new Timer(200, new ActionListener() {
            private int animationCount = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAnimating) {
                    currentAnimationFrame = (currentAnimationFrame + 1) % choices.length;
                    updateChoiceLabel(computerChoiceLabel, choices[currentAnimationFrame]);
                    updateChoiceLabel(playerChoiceLabel, choices[currentAnimationFrame]);
                    animationCount++;
                    
                    if (animationCount >= 10) { // Stop after 10 frames
                        isAnimating = false;
                        animationCount = 0;
                        animationTimer.stop();
                        determineWinner();
                    }
                }
            }
        });
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void loadImages() {
        try {
            // Load images
            rockIcon = new ImageIcon(ImageIO.read(new File("images/rock.png")));
            paperIcon = new ImageIcon(ImageIO.read(new File("images/paper.png")));
            scissorsIcon = new ImageIcon(ImageIO.read(new File("images/scissors.png")));
            
            // Scale images to larger size
            rockIcon = scaleIcon(rockIcon, 150, 150);
            paperIcon = scaleIcon(paperIcon, 150, 150);
            scissorsIcon = scaleIcon(scissorsIcon, 150, 150);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading images. Please ensure images are in the correct directory.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
    
    private void updateChoiceLabel(JLabel label, String choice) {
        switch (choice) {
            case "Rock":
                label.setIcon(rockIcon);
                break;
            case "Paper":
                label.setIcon(paperIcon);
                break;
            case "Scissors":
                label.setIcon(scissorsIcon);
                break;
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
        Random random = new Random();
        String computerChoice = choices[random.nextInt(choices.length)];
        updateChoiceLabel(computerChoiceLabel, computerChoice);
        updateChoiceLabel(playerChoiceLabel, playerChoice);
        
        String result;
        if (playerChoice.equals(computerChoice)) {
            result = "It's a tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                   (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                   (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = "You win!";
            playerScore++;
        } else {
            result = "Computer wins!";
            computerScore++;
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
        String finalResult;
        if (playerScore >= 3) {
            finalResult = "Game Over - You won the match!";
        } else {
            finalResult = "Game Over - Computer won the match!";
        }
        
        resultLabel.setText(finalResult);
        setButtonsEnabled(false);
        
        int choice = JOptionPane.showConfirmDialog(this, 
            "Would you like to play again?",     	// play again button 
            "Game Over", 
            JOptionPane.YES_NO_OPTION);
            
        if (choice == JOptionPane.YES_OPTION) {
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
} 
