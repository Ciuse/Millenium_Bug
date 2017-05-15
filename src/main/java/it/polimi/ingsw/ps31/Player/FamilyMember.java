package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Board.Dice;
/**
 * Created by Francesco on 15/05/2017.
 */
public class FamilyMember {

    private final Player player;
    private final Dice dice;

    /* Constructor */
    public FamilyMember(Player player, Dice dice)
    {
        this.player = player;
        this.dice = dice;
    }

    /* Setters & Getters */
    public Player getPlayer()
    {
        return this.player;
    }

    public Dice getDice()
    {
        return this.dice;
    }
}
