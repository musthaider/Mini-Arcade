import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {
    public enum GameMode {
        PLAYER_VS_PLAYER;


    }

    private char[][] board = new char[3][3];
    private String currentPlayer = "X";
    private GameMode mode;

    public char[][] getBoard() {
        return board;
    }
}
