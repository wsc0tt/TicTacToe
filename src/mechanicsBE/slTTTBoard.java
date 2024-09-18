/**
 * @author William Scott
 * @project Assignment 1 - Tic Tac Toe
 * @course CSC 133 - Section 9
 */

package mechanicsBE;
import java.util.*;
import static csc133.spot.*;

public class slTTTBoard {

    private int moveCount = 0;  // keep track of the number of moves
    private char lastMove = defChar;  // keep track of the last move
    
    // 3x3 array to represent the board
    public char[][] board = {
            {defChar, defChar, defChar},
            {defChar, defChar, defChar},
            {defChar, defChar, defChar}
    };
    
    private ArrayList<Integer> openCellsArray;
    
    public slTTTBoard() {
        openCellsArray = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            openCellsArray.add(i);
        }
    }

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
    public void setBoard(String move, char team) {
        moveCount++;
        int row = move.charAt(0) - '0';   // subtract the character 0 to offset char to correct int values
        int col = move.charAt(2) - '0';
        board[row][col] = team;         // set the board to the team char
        updateBoardArray(move);
    }
    
    public void updateBoardArray(String move) {
        int cell = switch (move) {
            case "0 0" -> 0;
            case "0 1" -> 1;
            case "0 2" -> 2;
            case "1 0" -> 3;
            case "1 1" -> 4;
            case "1 2" -> 5;
            case "2 0" -> 6;
            case "2 1" -> 7;
            case "2 2" -> 8;
            default -> 9;
        };
        openCellsArray.remove(Integer.valueOf(cell));
    }

    public void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = defChar;
            }
        }
        openCellsArray.clear();
        for (int i = 0; i < 9; i++) {
            openCellsArray.add(i);
        }
        moveCount = 0;
    }

    public void playRandom() {
        Random rand = new Random();
        int row = rand.nextInt(3);
        int col = rand.nextInt(3);
        String move = row + " " + col;
        if (isCellOpen(move)){
            setBoard(move, MACHINE_CHAR);
            lastMove = MACHINE_CHAR;
        }
        else {
            playRandom();
        }
    }

    public void playSpline() {
        if (moveCount == 0) {
            setBoard("0 0", MACHINE_CHAR);
        }
        else if (moveCount == 1) {
            if (board[1][1] == PLAYER_CHAR) {
                setBoard("0 0", MACHINE_CHAR);
            }
            else {
                setBoard("1 1", MACHINE_CHAR);
            }
        }
        else if (moveCount == 2) {
            if (board[0][0] == PLAYER_CHAR) {
                setBoard("2 2", MACHINE_CHAR);
            }
            else if (board[0][2] == PLAYER_CHAR) {
                setBoard("2 0", MACHINE_CHAR);
            }
            else if (board[2][0] == PLAYER_CHAR) {
                setBoard("0 2", MACHINE_CHAR);
            }
            else if (board[2][2] == PLAYER_CHAR) {
                setBoard("0 0", MACHINE_CHAR);
            }
            else {
                setBoard("0 1", MACHINE_CHAR);
            }
        }
        else {
            String move = bestSplineMove();
            if (move.equals("error")) {
                playRandom();
            }
            else {
                setBoard(move, MACHINE_CHAR);
            }
        }
    }

    public String bestSplineMove() {
        String[] splineMoves = {
                splineWinningMove(),
                splineBlockingMove(),
                splineCenterMove(),
                splineSideMove(),
                splineCornerMove()
        };
        for (String move : splineMoves) {
            if (!move.equals("next")) {
                return move;
            }
        }
        return "error";
    }
    // 1. check for winning move
    public String splineWinningMove() {
        for (int row = 0; row < ROWS; row++) {  // check rows
            if (board[row][0] == MACHINE_CHAR &&
                board[row][1] == MACHINE_CHAR &&
                board[row][2] == defChar) {
                return row + " 2";
            }
            else if (board[row][0] == MACHINE_CHAR &&
                    board[row][1] == defChar &&
                    board[row][2] == MACHINE_CHAR) {
                return row + " 1";
            }
            else if (board[row][0] == defChar &&
                    board[row][1] == MACHINE_CHAR &&
                    board[row][2] == MACHINE_CHAR) {
                return row + " 0";
            }
        }
        for (int col = 0; col < COLS; col++) {  // check cols
            if (board[0][col] == MACHINE_CHAR &&
                board[1][col] == MACHINE_CHAR &&
                board[2][col] == defChar) {
                return "2 " + col;
            }
            else if (board[0][col] == MACHINE_CHAR &&
                    board[1][col] == defChar &&
                    board[2][col] == MACHINE_CHAR) {
                return "1 " + col;
            }
            else if (board[0][col] == defChar &&
                    board[1][col] == MACHINE_CHAR &&
                    board[2][col] == MACHINE_CHAR) {
                return "0 " + col;
            }
        }
        if (board[0][0] == MACHINE_CHAR &&  // check first diagonal
            board[1][1] == MACHINE_CHAR &&
            board[2][2] == defChar) {
            return "2 2";
        }
        else if (board[0][0] == MACHINE_CHAR &&
                board[1][1] == defChar &&
                board[2][2] == MACHINE_CHAR) {
            return "1 1";
        }
        else if (board[0][0] == defChar &&
                board[1][1] == MACHINE_CHAR &&
                board[2][2] == MACHINE_CHAR) {
            return "0 0";
        }
        if (board[0][2] == MACHINE_CHAR &&  // check second diagonal
            board[1][1] == MACHINE_CHAR &&
            board[2][0] == defChar) {
            return "2 0";
        }
        else if (board[0][2] == MACHINE_CHAR &&
                board[1][1] == defChar &&
                board[2][0] == MACHINE_CHAR) {
            return "1 1";
        }
        else if (board[0][2] == defChar &&
                board[1][1] == MACHINE_CHAR &&
                board[2][0] == MACHINE_CHAR) {
            return "0 2";
        }
        return "next";
    }

    // 2. check for blocking move
    public String splineBlockingMove() {
        for (int row = 0; row < ROWS; row++) {  // check rows
            if (board[row][0] == PLAYER_CHAR &&
                    board[row][1] == PLAYER_CHAR &&
                    board[row][2] == defChar) {
                return row + " 2";
            } else if (board[row][0] == PLAYER_CHAR &&
                    board[row][1] == defChar &&
                    board[row][2] == PLAYER_CHAR) {
                return row + " 1";
            } else if (board[row][0] == defChar &&
                    board[row][1] == PLAYER_CHAR &&
                    board[row][2] == PLAYER_CHAR) {
                return row + " 0";
            }
        }
        for (int col = 0; col < COLS; col++) {  // check cols
            if (board[0][col] == PLAYER_CHAR &&
                    board[1][col] == PLAYER_CHAR &&
                    board[2][col] == defChar) {
                return "2 " + col;
            } else if (board[0][col] == PLAYER_CHAR &&
                    board[1][col] == defChar &&
                    board[2][col] == PLAYER_CHAR) {
                return "1 " + col;
            } else if (board[0][col] == defChar &&
                    board[1][col] == PLAYER_CHAR &&
                    board[2][col] == PLAYER_CHAR) {
                return "0 " + col;
            }
        }
        if (board[0][0] == PLAYER_CHAR &&  // check first diagonal
                board[1][1] == PLAYER_CHAR &&
                board[2][2] == defChar) {
            return "2 2";
        } else if (board[0][0] == PLAYER_CHAR &&
                board[1][1] == defChar &&
                board[2][2] == PLAYER_CHAR) {
            return "1 1";
        } else if (board[0][0] == defChar &&
                board[1][1] == PLAYER_CHAR &&
                board[2][2] == PLAYER_CHAR) {
                return "0 0";
        }
        return "next";
    }
    // 3. check for center move
    public String splineCenterMove() {
        if (board[1][1] == defChar) {
            return "1 1";
        }
        return "next";
    }
    // 4. check for corner move
    public String splineCornerMove() {
        List<String> possibleMoves = new ArrayList<>();
        if (board[0][0] == defChar) {
            possibleMoves.add("0 0");
        }
        if (board[0][2] == defChar) {
            possibleMoves.add("0 2");
        }
        if (board[2][0] == defChar) {
            possibleMoves.add("2 0");
        }
        if (board[2][2] == defChar) {
            possibleMoves.add("2 2");
        }
        if (possibleMoves.isEmpty()) {
            return "next";
        }
        else {
            Random rand = new Random();
            return possibleMoves.get(rand.nextInt(possibleMoves.size()));
        }
    }
    // 5. check for side move
    public String splineSideMove() {
        List<String> possibleMoves = new ArrayList<>();
        if (board[0][1] == defChar) {
            possibleMoves.add("0 1");
        }
        if (board[1][0] == defChar) {
            possibleMoves.add("1 0");
        }
        if (board[1][2] == defChar) {
            possibleMoves.add("1 2");
        }
        if (board[2][1] == defChar) {
            possibleMoves.add("2 1");
        }
        if (possibleMoves.isEmpty()) {
            return "next";
        }
        else {
            Random rand = new Random();
            return possibleMoves.get(rand.nextInt(possibleMoves.size()));
        }
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
    public int play() {
        String move = "";               // init empty string to store the current move
        while (!move.equals("quit")) {  // while the move is not equal to quit; loop this
            prompt();// ask the user for move
            move = getMove();
            while (move.equals("error")) {
                prompt();
                move = getMove();
            }
            if (move.equals("quit")) {
                return GAME_QUIT;
            }
            else {    // if the move is not an error or quit
                if (isCellOpen(move)){
                    setBoard(move, PLAYER_CHAR);     // if the cell is open, mark the board
                    lastMove = PLAYER_CHAR;          // set the last move to the player
                }
                else {
                    System.out.println("cell has already been marked - try a different cell");
                }
                //printBoard();
            }

            // if there is a 3 in a row, end game
            if (check3InARow()) {
                return PLAYER_WIN;
            }
            // if there is a full board, end game
            else if (checkFull()) {
                return DRAW;
            }
            else {
                playSpline();   // machine plays a random move
                printBoard();
                if (check3InARow()) {
                    return MACHINE_WIN;
                }
                else if (checkFull()) {
                    return DRAW;
                }
            }
        }
        return GAME_QUIT;
    }
}
