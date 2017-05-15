package it.polimi.ingsw.ps31.Board;

/**
 * Created by Francesco on 12/05/2017.
 */
public class GameBoard {
    private static GameBoard ourInstance = new GameBoard();

    public static GameBoard getInstance() {
        return ourInstance;
    }

    private GameBoard() {
    }
}
