package mechanicsBE;
import java.util.*;

public class slTTTBoard {
    public char[][] board = {
            {'-','-','-'},
            {'-','-','-'},
            {'-','-','-'}
    };

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
    public void setBoard() {

    }

    // method to check board for 3 in a row or full board
    public void checkBoard(int move){
        switch (move) {
            case(0):
        }
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

    // method to start a new tic-tac-toe game
    public void play() {
        String move = "";               // init empty string to store the current move
        while (!move.equals("quit")) {  // while the move is not equal to quit loop this
            prompt();                   // ask the user for move
            move = getMove();           // save the move
            if (!move.equals("error") && !move.equals("quit")) {    // if the move is not an error or quit
                //checkBoard(move);                                 //check the board then mark the board
                //setBoard();
                printBoard();
            }
        }

    }
}
