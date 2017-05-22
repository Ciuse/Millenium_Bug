package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.Board.Dice;
import it.polimi.ingsw.ps31.Board.PersonalBoard;
import it.polimi.ingsw.ps31.Card.Building;
import it.polimi.ingsw.ps31.Card.Territory;
import it.polimi.ingsw.ps31.Card.Venture;
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
    private ArrayList<FamilyMember> familyMembers;

    private ArrayList<Building> yellowCardList;
    private ArrayList<Character> blueCardList;
    private ArrayList<Territory> greenCardList;
    private ArrayList<Venture> purpleCardList;

    /* Constructor */
    public Player(PlayerColor color, HashMap<String, Integer> initialResources, String nickname)
    {
        //Attributi base
        this.color            = color;
        this.playerBoard      = new PersonalBoard(this);
        this.nickname         = nickname;
        this.permanentBonus   = new PermanentBonus();
        this.excommunications = new ArrayList<Excommunication>();

        //Risorse iniziali
        //TODO: il nome delle risorse deve essere preso da un enumeratore
        int woodAmt    = initialResources.get("Wood");
        int stoneAmt   = initialResources.get("Stone");
        int coinAmt    = initialResources.get("Coin");
        int servantAmt = initialResources.get("Servant");
        this.resources = new PlayerResources(woodAmt, stoneAmt, coinAmt, servantAmt);

        //Creazione familiari
        this.familyMembers = new ArrayList<FamilyMember>();
        for (DiceColor itrColor : DiceColor.values())
        {
            Dice itrDice = new Dice(itrColor); //TODO: NO!! Non si devono creare altri dadi, ma usare quelli già collegati alla board
            familyMembers.add(new FamilyMember(this, itrDice));
        }

        //Inizializzazione liste di carte
        //TODO: usare classe CardList di Giuse
        this.yellowCardList = new ArrayList<>();
        this.blueCardList   = new ArrayList<>();
        this.greenCardList  = new ArrayList<>();
        this.purpleCardList = new ArrayList<>();
    }

    /* Setters & Getters */
    public PlayerColor getColor()
    {
        return this.color;
    }

    public HashMap<String, Resource> getResources()
    {
        return this.resources.getResourcesMap();
    }

    public void addResources(Resource resourcesToAdd)
    {
        if (resourcesToAdd.getValue() >= 0)
            this.resources.addResources(resourcesToAdd);
        else
            this.resources.subResources(resourcesToAdd);
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

    public ArrayList<Building> getYellowCardList ()
    {
        return this.yellowCardList;
    }

    public ArrayList<Character> getBlueCardList()
    {
        return this.blueCardList;
    }

    public ArrayList<Territory> getGreenCardList()
    {
        return this.greenCardList;
    }

    public ArrayList<Venture> getPurpleCardList()
    {
        return this.purpleCardList;
    }

    //TODO: si può riutilizzare il codice dei prossimi 4 metodo?
    public void addDevelopmentCard(Building card)
    {
        if ( this.yellowCardList.size() < MAXCARDLISTSIZE)
            this.yellowCardList.add(card);
    }

    public void addDevelopmentCard(Character card)
    {
        if ( this.blueCardList.size() < MAXCARDLISTSIZE)
            this.blueCardList.add(card);
    }

    public void addDevelopmentCard(Territory card)
    {
        if ( this.greenCardList.size() < MAXCARDLISTSIZE)
            this.greenCardList.add(card);
    }

    public void addDevelopmentCard(Venture card)
    {
        if ( this.purpleCardList.size() < MAXCARDLISTSIZE)
            this.purpleCardList.add(card);
    }


    /* Class Methods */

}
