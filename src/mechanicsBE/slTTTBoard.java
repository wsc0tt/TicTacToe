/**
 * @author William Scott
 * @project Assignment 1 - Tic Tac Toe
 * @course CSC 133 - Section 9
 */

package mechanicsBE;
import java.util.*;

public class slTTTBoard {

    final char defChar = '-';
    private int moveCount = 0;

    public char[][] board = {
            {defChar, defChar, defChar},
            {defChar, defChar, defChar},
            {defChar, defChar, defChar}
    };

    final int ROWS = board.length;
    final int COLS = board[0].length;

    // method to prompt user
    public void prompt() {
        System.out.print("Enter row col numbers seperated by exactly one space (or type q to quit): ");
    }

    // Get user input for next move or quit; input validation
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
    // Mark the board, alternating x and o
    public void setBoard(String move) {
        moveCount++;
        char team;
        int row = move.charAt(0) - '0';   // subtract the character 0 to offset char to correct int values
        int col = move.charAt(2) - '0';
        if (moveCount % 2 != 0) {
            team = 'x';
        }
        else {
            team = 'o';
        }
        board[row][col] = team;
    }

    // method to inquire if a cell is open
    public boolean isCellOpen(String move){
        int row = move.charAt(0) - '0';
        int col = move.charAt(2) - '0';
        if (board[row][col] == defChar) {
            return true;
        }
        else {
            return false;
        }
    }

    // method to check for 3 in a row
    public boolean check3InARow() {
        // check rows
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0] != defChar &&
                board[row][0] == board[row][1] &&
                board[row][0] == board[row][2]) {
                return true;
            }
        }
        // check cols
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] != defChar &&
                    board[0][col] == board[1][col] &&
                    board[0][col] == board[2][col]) {
                return true;
            }
        }
        // check first diagonal
        if (board[0][0] != defChar &&
            board[0][0] == board[1][1] &&
            board[0][0] == board[2][2]) {
            return true;
        }
        // check second diagonal
        if (board[0][2] != defChar &&
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0]) {
            return true;
        }
        return false;
    }

    // check if the board is completely full
    public boolean checkFull() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == defChar){    // if any spot of the board = default char, return false
                    return false;
                }
            }
        }
        return true;
    }

    // method to print and format the board
    public void printBoard() {
        System.out.println();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // method to start a new tic-tac-toe game
    public void play() {
        String move = "";               // init empty string to store the current move
        while (!move.equals("quit")) {  // while the move is not equal to quit; loop this
            prompt();                   // ask the user for move
            move = getMove();           // save the move
            if (!move.equals("error") && !move.equals("quit")) {    // if the move is not an error or quit
                if (isCellOpen(move)){
                    setBoard(move);     // if the cell is open, mark the board
                }
                else {
                    System.out.println("cell has already been marked - try a different cell");
                }
                printBoard();
            }

            // if there is a 3 in a row, end game
            if (check3InARow()) {
                System.out.println("3 in a row!!!");
                System.out.println("*** GAME OVER ***");
                break;
            }
            // if there is a full board, end game
            if (checkFull()) {
                System.out.println("The board is full!!!");
                System.out.println("*** GAME OVER ***");
                break;
            }
        }
    }
}
