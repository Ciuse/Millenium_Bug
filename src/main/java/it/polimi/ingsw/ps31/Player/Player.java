package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Board.PersonalBoard;
import it.polimi.ingsw.ps31.Constants.PlayerColor;
import it.polimi.ingsw.ps31.Excommunication;
import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.ArrayList;

/**
 * Created by giulia on 15/05/2017.
 */
public class Player {

    private final PlayerColor color;
    private PlayerResources resources;
    private final PersonalBoard playerBoard;
    private final String nickname;
    private PermanentBonus permanentBonus;
    private ArrayList<Excommunication> excommunications;

    /* Constructor */
    public Player(PlayerColor color, ResourceList initialResources, String nickname)
    {
        this.color = color;
        this.resources = new PlayerResources(initialResources, null);
        this.playerBoard = new PersonalBoard(this);
        this.nickname = nickname;
        this.permanentBonus = new PermanentBonus();
        this.excommunications = new ArrayList<Excommunication>();
    }

    /* Setters & Getters */
    public PlayerColor getColor()
    {
        return this.color;
    }

    public PlayerResources getResources()
    {
        return this.resources;
    }

    public void setResources(PlayerResources resources)
    {
        this.resources = resources; //TODO: non modificare così ma con metodi add() e sub()
    }

    public PersonalBoard getPlayerBoard()
    {
        return playerBoard;
    }

    public String getNickname()
    {
        return nickname;
    }

    public PermanentBonus getPermanentBonus()
    {
        return permanentBonus;
    }

    public void setPermanentBonus(PermanentBonus permanentBonus)
    {
        this.permanentBonus = permanentBonus;   //TODO: non modificare così ma con metodi add() e sub()
    }

    public ArrayList<Excommunication> getExcommunications()
    {
        return excommunications;
    }

    public void addExcommunications(Excommunication excommunication)
    {
        this.excommunications.add(excommunication);
    }
}
