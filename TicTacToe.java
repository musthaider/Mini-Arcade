
public class TicTacToe {


    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private GameMode mode;

    public TicTacToe(GameMode mode) {
            this.mode = mode;
            resetGame();
    }

    public void resetGame() {
        for (int index = 0; index < 3; index++ )
            for(int indx = 0; indx <3; indx++ )
                    board[index][indx] = ' ';
        currentPlayer ='X';
    }
    public boolean makeMove(int row, int col) {
        if (board[row][col]==' '){
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }
}
