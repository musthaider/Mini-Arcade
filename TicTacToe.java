
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

    public void makeComputerMove() {
        for (int aiChoice = 0; aiChoice < 3; aiChoice++) {
            for (int aichoose = 0; aichoose < 3; aichoose++) {
                if (board[aiChoice][aichoose] == ' '){
                    board[aiChoice][aichoose] = currentPlayer;
                    return;
                }

            }

        }
    }
    public char checkWinner() {
        for (int win = 0; win < 3; win++) {
            if (board[win][0] != ' ' && board[win][0] ==
                board [win][1] && board[win][1] == board[win][2])
                return board[win][0];

            if (board[0][win] != ' ' && board [0][win] == board [1][win]&&
                    board[1][win] == board[2][win])
                return board[0][win];
        }

        if (board[0][0]!= ' ' && board[0][0] ==
        board[1][1] && board[1][1] == board[2][2])
            return board [0][0];

        if (board[0][2] != ' ' && board[0][2] == board[1][1] &&
                board[1][1] == board[2][0])
            return board[0][2];


        return ' ';
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
