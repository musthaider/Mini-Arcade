import javax.swing.*;

public class DiceGame extends JFrame {

    private MainMenu mainMenu;

    public DiceGame(MainMenu menu) {
        this.mainMenu = menu;
        setTitle("Dice Game");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGame();
        setVisible(true);
    }

    private void initGame() {
        // Dice side selection, roll animation, result display
    }

    private void returnToMenu() {
        mainMenu.setVisible(true);
        dispose();
    }
}
