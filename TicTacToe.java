import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {


    private char[][] board = new char[3][3];
    private String currentPlayer = "X";
    private GameMode mode;

    public TicTacToe(GameMode mode) {

    }

    public char[][] getBoard() {
        return board;
    }
}
