package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.board.PersonalBoard;
import it.polimi.ingsw.ps31.card.DevelopmentCard;
import it.polimi.ingsw.ps31.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.constants.DiceColor;
import it.polimi.ingsw.ps31.constants.PlayerColor;
import it.polimi.ingsw.ps31.gameThings.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by giulia on 15/05/2017.
 */
public class Player {
    private static final int MAXCARDLISTSIZE = 6;    //Massimo numero di carte dello stesso colore che si possono avere contemporaneamente

    private final PlayerColor color;
    private PlayerResources playerResources;      //setter -->add e sub
    private final PersonalBoard playerBoard;
    private final String nickname;
    private PermanentBonus permanentBonus;
    private List<Excommunication> excommunications;
    private final List<FamilyMember> familyMembers;
    private int flagExcommunication;
    private DevelopmentCardList playerCardList;
    private FamilyMember lastUsedFamilyMember;
    private PlayerActionSet playerActionSet;
    private HarvestList harvestList;
    private ProductionList productionList;
    private List<ResourceList> finalBonusResources;

    /* Constructor */
    public Player(PlayerColor color, ResourceList initialResources, String nickname, List<FamilyMember> familyMembers)
    {
        //Attributi base
        this.color            = color;
        this.familyMembers    = familyMembers;
        this.playerBoard      = new PersonalBoard(this);
        this.nickname         = nickname;
        this.permanentBonus   = new PermanentBonus();
        this.excommunications = new ArrayList<>(); //TODO: serve davvero??

        //Risorse iniziali
        //TODO: il nome delle risorse deve essere preso da un enumeratore
        int woodAmt    = initialResources.getSpecificResource(Wood.class).getValue();
        int stoneAmt   = initialResources.getSpecificResource(Stone.class).getValue();
        int coinAmt    = initialResources.getSpecificResource(Coin.class).getValue();
        int servantAmt = initialResources.getSpecificResource(Servant.class).getValue();
        this.playerResources = new PlayerResources(woodAmt, stoneAmt, coinAmt, servantAmt);

        //Creazione familiari
        /*
        this.familyMembers = new ArrayList<FamilyMember>();
        for (DiceColor itrColor : DiceColor.values())
        {
            Dice itrDice = new Dice(itrColor); //TODO: NO!! Non si devono creare altri dadi, ma usare quelli già collegati alla board
            familyMembers.add(new FamilyMember(this, itrDice));
        }*/

        //Inizializzazione harvestList e productionList
        this.harvestList = new HarvestList(this, null); //TODO: leggere firstHarvest da file
        this.productionList = new ProductionList(this, null); //TODO: leggere firstProduction da file

        //Inizializzazione liste di carte
        //TODO: usare classe CardList di Giuse
        this.playerCardList = new DevelopmentCardList(null);

        //Instanzio un PlayerActionSet
        playerActionSet = new PlayerActionSet(this);

        //Inizializzo lastUsedFamilyMember
        this.lastUsedFamilyMember = null;

        //Inizializzo la lista di risorse bonus
        this.finalBonusResources = new ArrayList<>();
    }

    /* Setters & Getters */
    public PlayerColor getColor() {
        return color;
    }

    public PlayerResources getPlayerResources() {
        return playerResources;
    }

    public void setResources(PlayerResources resources) {
        this.playerResources = resources;
    }

    public PersonalBoard getPlayerBoard() {
        return playerBoard;
    }

    public String getNickname() {
        return nickname;
    }

    public PermanentBonus getPermanentBonus() {
        return permanentBonus;
    }

    public void setPermanentBonus(PermanentBonus permanentBonus) {
        this.permanentBonus = permanentBonus;
    }

    public List<Excommunication> getExcommunications() {
        return new ArrayList<>(excommunications);
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public int getFlagExcommunication() {
        return flagExcommunication;
    }

    public void setFlagExcommunication(int flagExcommunication) {
        this.flagExcommunication = flagExcommunication;
    }

    public FamilyMember getLastUsedFamilyMember() {
        return lastUsedFamilyMember;
    }

    public void setLastUsedFamilyMember(FamilyMember lastUsedFamilyMember) {
        this.lastUsedFamilyMember = lastUsedFamilyMember;
    }

    public PlayerActionSet getPlayerActionSet() {
        return playerActionSet;
    }

    public void setPlayerActionSet(PlayerActionSet playerActionSet) {
        this.playerActionSet = playerActionSet;
    }

    public HarvestList getHarvestList() {
        return harvestList;
    }

    public void setHarvestList(HarvestList harvestList) {
        this.harvestList = harvestList;
    }

    public ProductionList getProductionList() {
        return productionList;
    }

    public void setProductionList(ProductionList productionList) {
        this.productionList = productionList;
    }

    public List<ResourceList> getFinalBonusResources() {
        return new ArrayList<>(finalBonusResources);
    }

    public void setFinalBonusResources(List<ResourceList> finalBonusResources) {
        this.finalBonusResources = finalBonusResources;
    }

    public DevelopmentCardList getPlayerCardList()
    {
        return this.playerCardList;
    }


    /* Class Methods */
    public void addResources(Resource resourcesToAdd)
    {
        if (resourcesToAdd.getValue() >= 0)
            this.playerResources.addResources(resourcesToAdd);
        else
            this.playerResources.subResources(resourcesToAdd);
    }

    public void subResources (Resource resourcesToSub)
    {
        if (resourcesToSub.getValue() >= 0)
            this.playerResources.subResources(resourcesToSub);
        else
            this.playerResources.addResources(resourcesToSub);
    }

    public void addExcommunication(Excommunication excommunication)
    {
        this.excommunications.add(excommunication);
    }

    public void addDevelopmentCard(DevelopmentCard card)
    {
        if ( this.playerCardList.getSpecificCardList(card.getCardColor()).size() < MAXCARDLISTSIZE)
            this.playerCardList.add(card);
        else
        {
            //TODO: eccezione
        }

        //Attivo gli effetti della carta
        card.activeEffectList(this);
    }

    public void addBonusResource(ResourceList bonusResourcesToAdd)
    {
        this.finalBonusResources.add(bonusResourcesToAdd);
    }
    public FamilyMember getSpecificFamilyMember(DiceColor color)
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

    public boolean checkIfOnlyNEUTRALRemained(){
        if(this.getSpecificFamilyMember(DiceColor.NEUTRAL).isPlaced()==true){
            return false;
        }
        else{
            if(this.getSpecificFamilyMember(DiceColor.BLACK).isPlaced()==true
                    &&this.getSpecificFamilyMember(DiceColor.ORANGE).isPlaced()==true
                    &&this.getSpecificFamilyMember(DiceColor.WHITE).isPlaced()==true){
                return true;
            }
        }
        return false;
    }

    public void activateFinalEffects()
    {
        //TODO: implementare
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!playerResources.equals(player.playerResources)) return false;
        if (permanentBonus != null ? !permanentBonus.equals(player.permanentBonus) : player.permanentBonus != null)
            return false;
        if (excommunications != null ? !excommunications.equals(player.excommunications) : player.excommunications != null)
            return false;
        return playerCardList != null ? playerCardList.equals(player.playerCardList) : player.playerCardList == null;
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + (playerResources != null ? playerResources.hashCode() : 0);
        result = 31 * result + (playerBoard != null ? playerBoard.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (permanentBonus != null ? permanentBonus.hashCode() : 0);
        result = 31 * result + (excommunications != null ? excommunications.hashCode() : 0);
        result = 31 * result + (familyMembers != null ? familyMembers.hashCode() : 0);
        result = 31 * result + flagExcommunication;
        result = 31 * result + (playerCardList != null ? playerCardList.hashCode() : 0);
        return result;
    }
}
