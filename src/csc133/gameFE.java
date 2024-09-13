package csc133;
import mechanicsBE.slTTTBoard;

public class gameFE {

    slTTTBoard brd;

    public gameFE (slTTTBoard brd) {
        this.brd = brd;
    }

    public void startGame() {
        System.out.println("Hello from the FE");
        brd.printBoard();
    }
}
