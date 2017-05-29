package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PersonalBoard {
    private final Player player;
    private final PersonalBoardCardSpace[] cardSpace = new PersonalBoardCardSpace[6];   //TODO: è una lista e sono 4

    /* Constructor */
    public PersonalBoard(Player player)
    {
        this.player = player;
    }

    /* Getters & Setters */
    public Player getPlayer()
    {
        return this.player;
    }

}
