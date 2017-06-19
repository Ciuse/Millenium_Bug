package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.stateModel.StateAllFamilyMember;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;
import it.polimi.ingsw.ps31.model.stateModel.StateTypePlayer;
import it.polimi.ingsw.ps31.model.stateModel.StatePlayerResources;
import it.polimi.ingsw.ps31.model.actions.ActionControlSet;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.card.ExcommunicationTiles;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by giulia on 15/05/2017.
 */
public class Player {
    private final PlayerId playerId;
    private final String nickname;
    private final PlayerColor playerColor;
    private final PlayerResources playerResources;      //setter -->add e sub
    private ResourceList tempPlayerResourcesToGain;
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
    public Player(PlayerColor playerColor, ResourceList initialResources, PlayerId playerId, String nickname, PersonalBoard personalBoard,PersonalBonusTiles personalBonusTiles)
    {
        //Attributi base
        this.playerColor = playerColor;
        this.playerId = playerId;
        this.playerBoard = personalBoard;
        this.nickname = nickname;
        this.permanentBonus = new PermanentBonus();
        this.excommunicationTiles = new ArrayList<>(); //TODO: serve davvero??
        this.tempPlayerResourcesToGain=null;
        //creazione lista dei famigliari
        DiceColor[] diceColor = {DiceColor.WHITE, DiceColor.ORANGE, DiceColor.BLACK, DiceColor.NEUTRAL};
        for(int i =0 ;i<diceColor.length;i++){
            FamilyMember familyMember = new FamilyMember(this,diceColor[i]);
            this.familyMembers.add(familyMember);
        }

        playerResources = new PlayerResources(initialResources);

        //Inizializzazione harvestList e productionList
        this.harvestList = new HarvestList(this,personalBonusTiles.getHarvestEffect()); //TODO: leggere firstHarvest da file
        this.productionList = new ProductionList(this, personalBonusTiles.getProductionEffect()); //TODO: leggere firstProduction da file

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

    public PlayerColor getPlayerColor() {
        return playerColor;
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


    public HarvestList getHarvestList() {
        return harvestList;
    }


    public ProductionList getProductionList() {
        return productionList;
    }


    public List<ResourceList> getFinalBonusResources() {
        return new ArrayList<>(finalBonusResources);
    }

    public DevelopmentCardList getPlayerCardList()
    {
        return this.playerBoard.getPlayerCardList();
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public ActionControlSet getActionControlSet() {
        return playerActionSet.getActionControlSet();
    }

    public ResourceList getTempPlayerResourcesToGain() {
        return tempPlayerResourcesToGain;
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

    public void addTempResources(Resource tempResourceToAdd){
        if(tempResourceToAdd.getValue() >=0)
            this.tempPlayerResourcesToGain.addSpecificResource(tempResourceToAdd);
    }

    public void addTempResoucesToPlayerResources(){
        for(int i = 0;i<tempPlayerResourcesToGain.size();i++){
            playerResources.addResources(tempPlayerResourcesToGain.get(i));
        }
        this.tempPlayerResourcesToGain.clear();
    }

    public void addExcommunication(ExcommunicationTiles excommunicationTiles)
    {
        this.excommunicationTiles.add(excommunicationTiles);
    }

    public void addDevelopmentCard(DevelopmentCard card)
    {
        if ( this.playerBoard.getPlayerCardList().getSpecificCardList(card.getCardColor()).size() <PersonalBoardCardList.getMaxCardOfSameColor())
            this.playerBoard.addCard(card);
        else
        {
            //TODO: eccezione
        }

        //Attivo gli effetti della carta
        card.activeEffectList(this);
    }

    public void addFinalBonusResource(ResourceList bonusResourcesToAdd)
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
        playerActionSet.getFinalResources();
    }

    public StateTypePlayer getStateInfoPlayer(){
        StateTypePlayer stateInfoPlayer = new StateTypePlayer(playerId, nickname,playerColor);
        return stateInfoPlayer;
    }

    public StatePlayerResources getStatePlayerResources(){
        return new StatePlayerResources(playerId, playerResources);

    }
    public StateAllFamilyMember getStateAllFamilyMember(){
        List<StateFamilyMember> stateAllFamilyMembers = new ArrayList<>();
        for (FamilyMember familyMember :familyMembers
                ) {
            stateAllFamilyMembers.add(familyMember.getStateFamilyMember());
        }
        return  new StateAllFamilyMember(stateAllFamilyMembers);

    }

    public StatePlayerAction getStatePlayerAction() {
        List<String> actionList = new ArrayList<>();
        if(!playerActionSet.getPlaceFamilyMemberInBoard().isUsed()){
        actionList.add(playerActionSet.getPlaceFamilyMemberInBoard().toString());
        }
        if(!playerActionSet.getPlaceFamilyMemberInBoard().isUsed()) {
            actionList.add(playerActionSet.getPlaceFamilyMemberInTower().toString());
        }
        if(!playerActionSet.getActiveLeaderCard().isUsed()) {
            actionList.add(playerActionSet.getActiveLeaderCard().toString());
        }
        if (playerActionSet.getActiveEndButton().isActive()) {
            actionList.add(playerActionSet.getActiveEndButton().toString());
        }
        return new StatePlayerAction(playerId,actionList);
    }


    /* Modifiers */
    public void fixFamilyMemberValue(DiceColor memberColor, int value){
        this.getSpecificFamilyMember(memberColor).setFixedValue(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (flagTurnExcommunication != player.flagTurnExcommunication) return false;
        if (nickname != null ? !nickname.equals(player.nickname) : player.nickname != null) return false;
        if (playerColor != player.playerColor) return false;
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
        result = 31 * result + (playerColor != null ? playerColor.hashCode() : 0);
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
