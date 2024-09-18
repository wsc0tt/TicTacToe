package csc133;
import mechanicsBE.slTTTBoard;
import static csc133.gameFE.*;
import static csc133.spot.GAME_INCOMPLETE;
import static csc133.spot.GAME_QUIT;
public class csc133Driver {
    private final slTTTBoard my_board = new slTTTBoard();
    private final csc133.gameFE my_fe = new csc133.gameFE();
    public static void main(String[] args) {
        (new csc133Driver()).startGame();
    } // public static void main(String] args)
    private void startGame() {
        my_board.printBoard();
        int game_status = GAME_INCOMPLETE;
        while (GAME_QUIT != game_status) {  // while the game is not quit
            my_fe.print_exit_message(game_status);  // print the exit message
            my_board.resetBoard();  // reset the board
            if (my_fe.promptToStart()) {    // if the machine starts returns true
                my_board.playRandom();  // machine plays a random move
                my_board.printBoard();  // print the board
            } // if (my_fe.promptToStart())
            game_status = my_board.play();
        } // while (...)
        //my_fe.promptToStart();
        my_fe.print_exit_message(game_status);
    } // public static void startGame()
} // public class csc133Driver