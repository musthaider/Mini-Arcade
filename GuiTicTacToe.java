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
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode: " ,
                "Tic tac toe", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, option, option[0]);
        GameMode mode = (choice == 0 ) ? GameMode.PLAYER_VS_PLAYER : GameMode.PLAYER_VS_COMPUTER;
        game = new TicTacToe(mode);

        //GUI PART 2
        frame =  new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLayout(new GridLayout(3,3));
        buttons = new JButton[3][3];


    }



}


