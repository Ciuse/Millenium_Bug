package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.actions.ActionControlSet;
import it.polimi.ingsw.ps31.model.board.MarkerDisc;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.card.ExcommunicationTiles;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.stateModel.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by giulia on 15/05/2017.
 */
public class Player {
    private final PlayerId playerId;
    private final String nickname;
    private PlayerColor playerColor;
    private final ResourceList playerResources;      //setter -->add e sub
    private ResourceList tempPlayerResourcesToGain;
    private final PersonalBoard personalBoard;
    private final List<FamilyMember> familyMembers = new ArrayList<>();
    private FamilyMember lastUsedFamilyMember;
    private PlayerActionSet playerActionSet;
    private List<ExcommunicationTiles> excommunicationTiles;
    private int flagTurnExcommunication;
    private PermanentBonus permanentBonus;
    private HarvestList harvestList;
    private ProductionList productionList;
    private List<ResourceList> finalBonusResources;
    private boolean wannaEndTurn = false;
    private List<MarkerDisc> markerDiscList = new ArrayList<>();
    private List<LeaderCard> leaderCardList = new ArrayList<>();
    private final Model model;

    /* Constructor */
    public Player(Model model, PlayerId playerId, ResourceList initialResources, String nickname, PersonalBoard personalBoard) {
        this.model = model;

        //Attributi base
        this.playerId = playerId;
        this.personalBoard = personalBoard;
        this.nickname = nickname;
        this.permanentBonus = new PermanentBonus();
        this.excommunicationTiles = new ArrayList<>(); //TODO: serve davvero??

        ResourceList resourceListToAdd = new ResourceList();
        resourceListToAdd.addSpecificResource(new Wood(0));
        resourceListToAdd.addSpecificResource(new Stone(0));
        resourceListToAdd.addSpecificResource(new Servant(0));
        resourceListToAdd.addSpecificResource(new Coin(0));
        resourceListToAdd.addSpecificResource(new MilitaryStrength(0));
        resourceListToAdd.addSpecificResource(new FaithPoint(0));
        resourceListToAdd.addSpecificResource(new VictoryPoint(0));
        this.tempPlayerResourcesToGain = resourceListToAdd;

        //creazione lista dei famigliari
        DiceColor[] diceColor = {DiceColor.WHITE, DiceColor.ORANGE, DiceColor.BLACK, DiceColor.NEUTRAL};
        for (int i = 0; i < diceColor.length; i++) {
            FamilyMember familyMember = new FamilyMember(this, diceColor[i]);
            this.familyMembers.add(familyMember);
        }
        //creazione dei markerdisc
        MarkerDisc markerDisc1 = new MarkerDisc(FaithPoint.class, this);
        MarkerDisc markerDisc2 = new MarkerDisc(VictoryPoint.class, this);
        MarkerDisc markerDisc3 = new MarkerDisc(MilitaryStrength.class, this);
        markerDiscList.add(markerDisc1);
        markerDiscList.add(markerDisc2);
        markerDiscList.add(markerDisc3);


        playerResources = new ResourceList(initialResources.getListOfResource());


        //Instanzio un PlayerActionSet
        playerActionSet = new PlayerActionSet(this);

        //Inizializzo lastUsedFamilyMember
        this.lastUsedFamilyMember = null;

        //Inizializzo la lista di risorse bonus
        this.finalBonusResources = new ArrayList<>();
    }

    /* Setters & Getters */

    public void setPlayerColor(PlayerColor playerColor){
        this.playerColor = playerColor;
    }

    public void setPersonalBonusTiles(PersonalBonusTiles personalBonusTiles){
        //Inizializzazione harvestList e productionList
        this.harvestList = new HarvestList(this,personalBonusTiles.getHarvestEffect());
        this.productionList = new ProductionList(this, personalBonusTiles.getProductionEffect());
    }

    public void setWannaEndTurn(boolean wannaEndTurn) {
        this.wannaEndTurn = wannaEndTurn;
    }

    public List<MarkerDisc> getMarkerDiscList() {
        return markerDiscList;
    }

    public String getNickname() {
        return nickname;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public ResourceList getPlayerResources() {
        return new ResourceList(playerResources.getListOfResource());
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
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
        return this.personalBoard.getPlayerCardList();
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

    public List<LeaderCard> getLeaderCardList() {
        return leaderCardList;
    }

    public Model getModel() {
        return model;
    }

    public boolean isWannaEndTurn() {
        return wannaEndTurn;
    }

    /* Class Methods */
    public void addResources(Resource resourcesToAdd)
    {
        this.playerResources.addSpecificResource(resourcesToAdd);
    }

    public void subResources (Resource resourcesToSub)
    {
        this.playerResources.subSpecificResource(resourcesToSub);
    }

    public void addTempResources(Resource tempResourceToAdd){
            this.tempPlayerResourcesToGain.addSpecificResource(tempResourceToAdd);
    }

    public void addTempResoucesToPlayerResources(){
        for(int i = 0;i<tempPlayerResourcesToGain.size();i++){
            playerResources.addSpecificResource(tempPlayerResourcesToGain.get(i));
        }
        model.notifyViews(new MVUpdateState("Aggiornato stato PlayerResources", getStatePlayerResources()));
        this.tempPlayerResourcesToGain.clear();
    }

    public void addExcommunication(ExcommunicationTiles excommunicationTiles)
    {
        this.excommunicationTiles.add(excommunicationTiles);
        excommunicationTiles.activeBonus(this);
    }

    public void addDevelopmentCard(DevelopmentCard card)
    {

        if ( this.personalBoard.getSpecificPersonalBoardCardList(card.getCardColor()).numberOfCard() <PersonalBoardCardList.getMaxCardOfSameColor()) {

            this.personalBoard.addCard(card);

            addTempResoucesToPlayerResources();

            //Attivo gli effetti della carta
            card.activeEffectList(this);
        }
    }

    public void addLeaderCard(LeaderCard leaderCard)
    {
        leaderCardList.add(leaderCard);
    }

    public void removeLeaderCard(LeaderCard leaderCard)
    {
        leaderCardList.remove(leaderCard);
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

    public MarkerDisc getSpecificMarkerDisc(Class<? extends PointResource> resourceType){
        for (MarkerDisc markerDisc : markerDiscList
                ) {
            if (markerDisc.getResourceType().equals(resourceType)){
                return markerDisc;
            }
        }
        return null;
    }

    public DevelopmentCardList getColorCardList (CardColor cardColor)
    {
        return new DevelopmentCardList(this.personalBoard.getPlayerCardList().getSpecificCardList(cardColor));
    }

    public boolean checkIfOnlyNEUTRALRemained(){
        if(this.getSpecificFamilyMember(DiceColor.NEUTRAL).isPlaced()){
            return false;
        }
        else{
            if(this.getSpecificFamilyMember(DiceColor.BLACK).isPlaced()
                    &&this.getSpecificFamilyMember(DiceColor.ORANGE).isPlaced()
                    &&this.getSpecificFamilyMember(DiceColor.WHITE).isPlaced()){
                return true;
            }
        }
        return false;
    }

    public void activateFinalEffects()
    {
        playerActionSet.getFinalResources();
    }

    public StatePlayer getStateInfoPlayer(){
        return new StatePlayer(playerId, nickname,playerColor);
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
        if(!checkIfOnlyNEUTRALRemained() || !(getPlayerResources().getSpecificResource(Servant.class).getValue()<=0)) {
            if (!playerActionSet.getPlaceFamilyMemberInBoard().isUsed() && !playerActionSet.getPlaceFamilyMemberInTower().isUsed()) {
                actionList.add(playerActionSet.getPlaceFamilyMemberInBoard().toString());        //aggiungo l azione piazza fagmigliare nella board
                actionList.add(playerActionSet.getPlaceFamilyMemberInTower().toString());       //aggiungo l azione piazza famigliare nella torre
            }
        }
        if(leaderCardList.size()>0) {
            actionList.add(playerActionSet.getActiveLeaderCard().toString());             //aggiungo l azione gioca leader
        }
        if(leaderCardList.size()>0){
            boolean found=false;
            for (LeaderCard leaderCard:leaderCardList
                    ) {
                if(!leaderCard.isPlayed()){
                    found=true;
                }
            }
            if(found)
                actionList.add(playerActionSet.getDiscardLeaderCard().toString());      //aggiungo l azione scarta leader se ne hai ancora uno in mano
        }

//        if (playerActionSet.getActiveEndButton().isActive()) {        //TODO DA RIMETTERE! (CHEAT)
            actionList.add(playerActionSet.getActiveEndButton().toString());             //se l oggetto fine turno è attivo aggiungo l azione per finire il turno
//        }


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
        if (personalBoard != null ? !personalBoard.equals(player.personalBoard) : player.personalBoard != null) return false;
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
        result = 31 * result + (personalBoard != null ? personalBoard.hashCode() : 0);
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
