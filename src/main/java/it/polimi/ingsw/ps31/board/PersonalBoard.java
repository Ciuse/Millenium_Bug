package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.player.Player;

import java.util.List;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PersonalBoard {
    private final Player player;
    private final List<PersonalBoardCardSpace> cardSpaceList=null;
    private final static int MAXCARDSPACELIST=4;

    /* Constructor */
    public PersonalBoard(Player player) {                       //TODO SERVE IMPLEMENTARE LE CARD SPACE
        this.player = player;
        for(int i=0; i<MAXCARDSPACELIST;i++){
            this.cardSpaceList.add(new PersonalBoardCardSpace());
        }

    }

    /* Getters & Setters */
    public Player getPlayer()
    {
        return this.player;
    }

}
