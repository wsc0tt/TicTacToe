package mechanicsBE;

public class slTTTBoard {
    public char[][] board = {
            {'-','-','-'},
            {'-','-','-'},
            {'-','-','-'}
    };

    // method to prompt user
    public void prompt() {

    }


    // Get user input for next move
    public void getMove() {

    }

    // Mark the board
    public void setBoard() {

    }

    // method to check board for game over or continue
    public void checkBoard(){

    }

    // method to print the board
    public void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // method to start a new tic tac toe game
    public void play() {

    }
}
