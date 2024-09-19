/**
 * @author William Scott
 * @project Assignment 2 - Tic Tac Toe
 * @course CSC 133 - Section 9
 * Constants class for the game
 */

package csc133;

// spot class to hold constants
public class spot {
    public static final int GAME_INCOMPLETE = 0;
    public static final int GAME_QUIT = 1;
    public static final int MACHINE_WIN = 2;
    public static final int PLAYER_WIN = 3;
    public static final int DRAW = 4;
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static final char defChar = '-'; // default character
    public static final char MACHINE_CHAR = 'x'; // machine character
    public static final char PLAYER_CHAR = 'o'; // player character
    public static final int SPLINE_MOVES = 5; // number of spline paths
}
