package it.polimi.ingsw.ps31.gameThings;

import it.polimi.ingsw.ps31.board.GameBoard;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public abstract  class PointResource extends Resource {
    protected static GameBoard gameBoard = GameBoard.getInstance();

    public PointResource(int value) {
        super(value);
    }

    @Override
    public int getPhysicalResourceValue() {
        return 0;
    }

    @Override
    public int getPointResourceValue() {
        return this.getValue();
    }
}
