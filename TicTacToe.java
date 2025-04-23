
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
    public char checkWinner() {
        // Rows and Columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != ' ' &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // Diagonals
        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return ' '; // No winner
    }


    public void computerMove() {
        if (tryWinningMove('O')) return;
        if (tryWinningMove('X')) return;

        if (board[1][1] == ' ') {
            board[1][1] = 'O';
            return;
        }

        int[][] corners = {{0,0},{0,2},{2,0},{2,2}};
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == ' ') {
                board[corner[0]][corner[1]] = 'O';
                return;
            }
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') {
                    board[r][c] = 'O';
                    return;
                }
            }
        }
    }

    private boolean tryWinningMove(char player) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') {
                    board[r][c] = player;
                    if (checkWinner() == player) {
                        if (player == 'O') return true;
                        board[r][c] = 'O'; // block
                        return true;
                    }
                    board[r][c] = ' ';
                }
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for(int index = 0; index<3; index++)
            for(int indx = 0; indx <3; indx++)
                if(board[index][indx] == ' ')
                    return false;

        return true;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X' ;
    }

    public char[][] getBoard() {
        return board;
    }

    public GameMode getMode(){
        return mode;
    }
}
