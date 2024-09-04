package mechanicsBE;
import java.util.*;

public class slTTTBoard {
    public char[][] board = {
            {'-','-','-'},
            {'-','-','-'},
            {'-','-','-'}
    };

    final int ROWS = board.length;
    final int COLS = board[0].length;
    public int moveCount = 0;

    // method to prompt user
    public void prompt() {
        System.out.print("Enter row col numbers seperated by exactly one space (or type q to quit): ");
    }


    // Get user input for next move or quit
    public String getMove() {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        switch (line) {
            case "0 0":
            case "0 1":
            case "0 2":
            case "1 0":
            case "1 1":
            case "1 2":
            case "2 0":
            case "2 1":
            case "2 2":
                return line;
            case "q":
            case "Q":
                return "quit";
            default:
                System.out.println("invalid entry - try again");
                return "error";
        }
    }

    // Mark the board
    public void setBoard(String move) {
        moveCount++;
        char team;
        int i = move.charAt(0) - '0';
        int j = move.charAt(2) - '0';
        if (moveCount % 2 != 0) {
            team = 'x';
        }
        else {
            team = 'o';
        }
        board[i][j] = team;
    }

    // method to inquire if a cell is open
    public boolean isCellOpen(String move){
        int i = move.charAt(0) - '0';
        int j = move.charAt(2) - '0';
        if (board[i][j] == '-') {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean check3() {
        // check rows
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0] != '-' &&
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2]) {
                return true;
            }
        }
        // check cols
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] != '-' &&
                    board[0][j] == board[1][j] &&
                    board[0][j] == board[2][j]) {
                return true;
            }
        }
        // check first diagonal
        if (board[0][0] != '-' &&
            board[0][0] == board[1][1] &&
            board[0][0] == board[2][2]) {
            return true;
        }
        // check second diagonal
        if (board[0][2] != '-' &&
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0]) {
            return true;
        }
        return false;
    }

    public boolean checkFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == '-'){
                    return false;
                }
            }
        }
        return true;
    }


    // method to print the board
    public void printBoard() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // method to start a new tic-tac-toe game
    public void play() {
        String move = "";               // init empty string to store the current move
        while (!move.equals("quit")) {  // while the move is not equal to quit and game is not over, loop this
            prompt();                   // ask the user for move
            move = getMove();           // save the move
            if (!move.equals("error") && !move.equals("quit")) {    // if the move is not an error or quit
                if (isCellOpen(move)){
                    setBoard(move);
                }
                else {
                    System.out.println("cell has already been marked - try a different cell");
                }
                printBoard();
            }


            if (check3()) {
                System.out.println("3 in a row!!!");
                System.out.println("*** GAME OVER ***");
                break;
            }
            if (checkFull()) {
                System.out.println("The board is full!!!");
                System.out.println("*** GAME OVER ***");
                break;
            }
        }


    }
}
