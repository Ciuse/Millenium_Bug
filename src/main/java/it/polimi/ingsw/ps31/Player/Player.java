package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Board.Dice;
import it.polimi.ingsw.ps31.Board.PersonalBoard;
import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.Constants.PlayerColor;
import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by giulia on 15/05/2017.
 */
public class Player {
    private static final int MAXCARDLISTSIZE = 6;    //Massimo numero di carte dello stesso colore che si possono avere contemporaneamente

    private final PlayerColor color;
    private PlayerResources resources;      //No getter e setter -->add e sub
    private final PersonalBoard playerBoard;
    private final String nickname;
    private PermanentBonus permanentBonus;
    private ArrayList<Excommunication> excommunications;
    private final ArrayList<FamilyMember> familyMembers;
    private final PlayerActionSet playerActionSet;
    private DevelopmentCardList playerCardList;

    /* Constructor */
    public Player(PlayerColor color, ResourceList initialResources, String nickname)
    {
        //Attributi base
        this.color            = color;
        this.playerBoard      = new PersonalBoard(this);
        this.nickname         = nickname;
        this.permanentBonus   = new PermanentBonus();
        this.excommunications = new ArrayList<Excommunication>(); //TODO: serve davvero??

        //Risorse iniziali
        //TODO: il nome delle risorse deve essere preso da un enumeratore
        //TODO: aspettare che giuse faccia i metodi per leggere le risorse da ResourceList
        /*
        int woodAmt    = initialResources.getResourceList().getClass().get;
        int stoneAmt   = initialResources.get("Stone");
        int coinAmt    = initialResources.get("Coin");
        int servantAmt = initialResources.get("Servant");
        this.resources = new PlayerResources(woodAmt, stoneAmt, coinAmt, servantAmt);
        */
        this.resources = new PlayerResources(0,0,0,0); //Messa altrimenti non compila


        //Creazione familiari
        this.familyMembers = new ArrayList<FamilyMember>();
        for (DiceColor itrColor : DiceColor.values())
        {
            Dice itrDice = new Dice(itrColor); //TODO: NO!! Non si devono creare altri dadi, ma usare quelli già collegati alla board
            familyMembers.add(new FamilyMember(this, itrDice));
        }

        //Inizializzazione liste di carte
        //TODO: usare classe CardList di Giuse
        this.playerCardList = new DevelopmentCardList(null);

        //Instanzio un PlayerActionSet
        playerActionSet = new PlayerActionSet(this);
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

    public void addExcommunication(Excommunication excommunication)
    {
        this.excommunications.add(excommunication);
    }

    public FamilyMember getFamilyMember(DiceColor color)
    {
        Iterator<FamilyMember> itr = familyMembers.iterator();

        FamilyMember itrMember;
        do
        {
            itrMember = itr.next();
        }while (itrMember.getDice().getColor() != color);

        return itrMember;
    }

    public DevelopmentCardList getColorCardList (CardColor cardColor)
    {
        return new DevelopmentCardList(this.playerCardList.getSpecificCardList(cardColor));
    }

    public DevelopmentCardList getPlayerCardList()
    {
        return this.playerCardList;
    }

    public PlayerActionSet getPlayerActionSet()
    {
        return this.playerActionSet;
    }

    /* Class Methods */
    public void addDevelopmentCard(DevelopmentCard card)
    {
        if ( this.playerCardList.getSpecificCardList(card.getCardColor()).size() < MAXCARDLISTSIZE)
            this.playerCardList.addDevelopmentCard(card);
    }

    public void addResources(Resource resourcesToAdd)
    {
        if (resourcesToAdd.getValue() >= 0)
            this.resources.addResources(resourcesToAdd);
        else
            this.resources.subResources(resourcesToAdd);
    }

    public void subResources (Resource resourcesToSub)
    {
        if (resourcesToSub.getValue() >= 0)
            this.resources.subResources(resourcesToSub);
        else
            this.resources.addResources(resourcesToSub);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!resources.equals(player.resources)) return false;
        if (permanentBonus != null ? !permanentBonus.equals(player.permanentBonus) : player.permanentBonus != null)
            return false;
        if (excommunications != null ? !excommunications.equals(player.excommunications) : player.excommunications != null)
            return false;
        return playerCardList != null ? playerCardList.equals(player.playerCardList) : player.playerCardList == null;
    }
}
