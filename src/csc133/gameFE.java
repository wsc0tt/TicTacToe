package csc133;
import mechanicsBE.slTTTBoard;
import java.util.*;

import static csc133.spot.*;

public class gameFE {

    public boolean promptToStart() {
        Scanner console = new Scanner(System.in);
        System.out.print("Do you want to start the game? 'n' for the machine to start, 'y' for you to start: ");
        String input = console.next();
        if (input.equals("y")) {
            return false;
        }
        else if (input.equals("n")) {
            return true;
        }
        else {
            System.out.println("Invalid input. Please enter 'n' or 'y'.");
            return promptToStart();
        }
    }

    public void print_exit_message(int status) {
        switch (status) {
            case GAME_QUIT:
                System.out.println("Sorry to see you go. See you next time!");
                break;
            case MACHINE_WIN:
                System.out.println("Sorry, you lose. Better luck next time!");
                break;
            case PLAYER_WIN:
                System.out.println("Congratulations! You win!");
                break;
            case DRAW:
                System.out.println("You almost beat me! Let's play again!");
                break;
            default:
                break;
        }
    }

}
