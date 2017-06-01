package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.board.PersonalBoard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.card.ExcommunicationTiles;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.gameThings.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by giulia on 15/05/2017.
 */
public class Player {
    private static final int MAXCARDLISTSIZE = 6;    //Massimo numero di carte dello stesso colore che si possono avere contemporaneamente
    private final String nickname;
    private final PlayerColor color;
    private final PlayerResources playerResources;      //setter -->add e sub
    private final PersonalBoard playerBoard;
    private final List<FamilyMember> familyMembers = new ArrayList<>();
    private FamilyMember lastUsedFamilyMember;
    private PlayerActionSet playerActionSet;
    private List<ExcommunicationTiles> excommunicationTiles;
    private int flagTurnExcommunication;
    private PermanentBonus permanentBonus;
    private HarvestList harvestList;
    private ProductionList productionList;
    private List<ResourceList> finalBonusResources;

    /* Constructor */
    public Player(PlayerColor color, ResourceList initialResources, String nickname, PersonalBoard personalBoard)
    {
        //Attributi base
        this.color            = color;
        this.playerBoard      = personalBoard;
        this.nickname         = nickname;
        this.permanentBonus   = new PermanentBonus();
        this.excommunicationTiles = new ArrayList<>(); //TODO: serve davvero??
        //creazione lista dei famigliari
        DiceColor[] diceColor = {DiceColor.WHITE, DiceColor.ORANGE, DiceColor.BLACK, DiceColor.NEUTRAL};
        for(int i =0 ;i<diceColor.length;i++){
            FamilyMember familyMember = new FamilyMember(this,diceColor[i]);
            this.familyMembers.add(familyMember);
        }

        playerResources = new PlayerResources(initialResources);

        //Inizializzazione harvestList e productionList
        this.harvestList = new HarvestList(this, null); //TODO: leggere firstHarvest da file
        this.productionList = new ProductionList(this, null); //TODO: leggere firstProduction da file

        //Instanzio un PlayerActionSet
        playerActionSet = new PlayerActionSet(this);

        //Inizializzo lastUsedFamilyMember
        this.lastUsedFamilyMember = null;

        //Inizializzo la lista di risorse bonus
        this.finalBonusResources = new ArrayList<>();
    }

    /* Setters & Getters */
    public String getNickname() {
        return nickname;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PlayerResources getPlayerResources() {
        return playerResources;
    }

    public PersonalBoard getPlayerBoard() {
        return playerBoard;
    }

    public PermanentBonus getPermanentBonus() {
        return permanentBonus;
    }

    public void setPermanentBonus(PermanentBonus permanentBonus) {
        this.permanentBonus = permanentBonus;
    }

    public List<ExcommunicationTiles> getExcommunicationTiles() {
        return new ArrayList<>(excommunicationTiles);
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public int getFlagTurnExcommunication() {
        return flagTurnExcommunication;
    }

    public void setFlagTurnExcommunication(int flagTurnExcommunication) {
        this.flagTurnExcommunication = flagTurnExcommunication;
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
        return this.playerBoard.getPlayerCardList();
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

    public void addExcommunication(ExcommunicationTiles excommunicationTiles)
    {
        this.excommunicationTiles.add(excommunicationTiles);
    }

    public void addDevelopmentCard(DevelopmentCard card)
    {
        if ( this.playerBoard.getPlayerCardList().getSpecificCardList(card.getCardColor()).size() < MAXCARDLISTSIZE)
            this.playerBoard.addCard(card);
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
        }while (itrMember.getDiceColor() != color);

        return itrMember;
    }

    public DevelopmentCardList getColorCardList (CardColor cardColor)
    {
        return new DevelopmentCardList(this.playerBoard.getPlayerCardList().getSpecificCardList(cardColor));
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

        if (flagTurnExcommunication != player.flagTurnExcommunication) return false;
        if (nickname != null ? !nickname.equals(player.nickname) : player.nickname != null) return false;
        if (color != player.color) return false;
        if (playerResources != null ? !playerResources.equals(player.playerResources) : player.playerResources != null)
            return false;
        if (playerBoard != null ? !playerBoard.equals(player.playerBoard) : player.playerBoard != null) return false;
        if (familyMembers != null ? !familyMembers.equals(player.familyMembers) : player.familyMembers != null)
            return false;
        if (lastUsedFamilyMember != null ? !lastUsedFamilyMember.equals(player.lastUsedFamilyMember) : player.lastUsedFamilyMember != null)
            return false;
        if (playerActionSet != null ? !playerActionSet.equals(player.playerActionSet) : player.playerActionSet != null)
            return false;
        if (excommunicationTiles != null ? !excommunicationTiles.equals(player.excommunicationTiles) : player.excommunicationTiles != null)
            return false;
        if (permanentBonus != null ? !permanentBonus.equals(player.permanentBonus) : player.permanentBonus != null)
            return false;
        if (harvestList != null ? !harvestList.equals(player.harvestList) : player.harvestList != null) return false;
        if (productionList != null ? !productionList.equals(player.productionList) : player.productionList != null)
            return false;
        return finalBonusResources != null ? finalBonusResources.equals(player.finalBonusResources) : player.finalBonusResources == null;
    }

    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (playerResources != null ? playerResources.hashCode() : 0);
        result = 31 * result + (playerBoard != null ? playerBoard.hashCode() : 0);
        result = 31 * result + (familyMembers != null ? familyMembers.hashCode() : 0);
        result = 31 * result + (lastUsedFamilyMember != null ? lastUsedFamilyMember.hashCode() : 0);
        result = 31 * result + (playerActionSet != null ? playerActionSet.hashCode() : 0);
        result = 31 * result + (excommunicationTiles != null ? excommunicationTiles.hashCode() : 0);
        result = 31 * result + flagTurnExcommunication;
        result = 31 * result + (permanentBonus != null ? permanentBonus.hashCode() : 0);
        result = 31 * result + (harvestList != null ? harvestList.hashCode() : 0);
        result = 31 * result + (productionList != null ? productionList.hashCode() : 0);
        result = 31 * result + (finalBonusResources != null ? finalBonusResources.hashCode() : 0);
        return result;
    }
}
