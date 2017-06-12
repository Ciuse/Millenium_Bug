package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.messageMV.MVToPrint;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.StateModel.StatePlayerAction;
import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.VictoryPoint;
import it.polimi.ingsw.ps31.model.card.ExcommunicationTiles;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.messageMV.MVStateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class GameBoard extends Model{

    private final static int TOWERNUMBER = 4;
    private List<Tower> towers= new ArrayList<>();
    private Market market;
    private CouncilPalace councilPalace;
    private SmallHarvest smallHarvest;

    private SmallProduction smallProduction;
    private BigHarvest bigHarvest;
    private BigProduction bigProduction;
    private List<Dice> dice = new ArrayList<>();
    private static MilitaryPointTrack militaryPointTrack;
    private static FaithPointTrack faithPointTrack;
    private static VictoryPointTrack victoryPointTrack;
    private EndActionButton endActionButton;
    private List<ExcommunicationTiles> excommunicationTilesList;

    public GameBoard() {
    }

    public void add4PlayerMarketSpace(List<EffectList> otherEffectList) {
        this.market.add4PlayerMarketSpace(otherEffectList.get(7), otherEffectList.get(8));
    }

    public void remove4PlayerMarketSpace() {
        this.market.remove4PlayerActionSpace();
    }

    public void rollTheDice() {
        for (int i = 0; i < dice.size(); i++) {
            if (!dice.get(i).getColor().equals(DiceColor.NEUTRAL)) {
                int randomValue = (int) (Math.random() * 6 + 1);
                dice.get(i).setValue(randomValue);
            }
        }
    }

    public void initializateGameBoard(List<List<EffectList>> towerEffectList, List<EffectList> otherEffectList,VictoryPoint[] faithTrackExtraValue) {
        //creazione torri
        CardColor[] towerColor = {CardColor.GREEN, CardColor.BLUE, CardColor.YELLOW, CardColor.PURPLE};
        for (int i = 0; i < TOWERNUMBER; i++) {
            towers.add(new Tower(towerEffectList.get(i).size(), towerColor[i], towerEffectList.get(i)));
        }

        //creazione dadi
        DiceColor[] diceColor = {DiceColor.WHITE, DiceColor.ORANGE, DiceColor.BLACK, DiceColor.NEUTRAL};
        for (int i = 0; i < 4; i++) {
            dice.add(new Dice(diceColor[i]));
            dice.get(i).setValue(0);
        }

        //creazione del resto
        this.councilPalace = new CouncilPalace(otherEffectList.get(0));
        this.smallHarvest = new SmallHarvest(1, otherEffectList.get(1));
        this.bigHarvest = new BigHarvest(-1, otherEffectList.get(2));
        this.smallProduction = new SmallProduction(1, otherEffectList.get(3));
        this.bigProduction = new BigProduction(-1, otherEffectList.get(4));
        this.market = new Market();
        this.market.add2PlayerMarketSpace(otherEffectList.get(5), otherEffectList.get(6));
        this.militaryPointTrack = new MilitaryPointTrack();
        this.faithPointTrack = new FaithPointTrack();
        this.faithPointTrack.inizializationFaithTrack(faithTrackExtraValue);
        this.victoryPointTrack = new VictoryPointTrack();
        this.endActionButton= new EndActionButton(false);
    }

    public static int getTOWERNUMBER() {
        return TOWERNUMBER;
    }

    public List<Tower> getTowers() {
        return new ArrayList<>(towers);
    }

    public Market getMarket() {
        return market;
    }

    public CouncilPalace getCouncilPalace() {
        return councilPalace;
    }

    public SmallHarvest getSmallHarvest() {
        return smallHarvest;
    }

    public SmallProduction getSmallProduction() {
        return smallProduction;
    }

    public BigHarvest getBigHarvest() {
        return bigHarvest;
    }

    public BigProduction getBigProduction() {
        return bigProduction;
    }

    public List<Dice> getDice() {
        return new ArrayList<>(dice);
    }

    public static MilitaryPointTrack getMilitaryPointTrack() {
        return militaryPointTrack;
    }

    public static FaithPointTrack getFaithPointTrack() {
        return faithPointTrack;
    }

    public static VictoryPointTrack getVictoryPointTrack() {
        return victoryPointTrack;
    }

    public List<ExcommunicationTiles> getExcommunicationTilesList() {
        return new ArrayList<>(excommunicationTilesList);
    }

    public void setExcommunicationTilesList(List<ExcommunicationTiles> excommunicationTiles) {
        this.excommunicationTilesList = excommunicationTiles;
    }

    public EndActionButton getEndActionButton() {
        return endActionButton;
    }

    public void startActionTurn(Player player) {
        notifyViews(new MVToPrint());
        List<Action> actionList = new ArrayList<>();
        actionList.add(player.getPlayerActionSet().getPlaceFamilyMemberInBoard());
        actionList.add(player.getPlayerActionSet().getPlaceFamilyMemberInTower());
        actionList.add(player.getPlayerActionSet().getActiveLeaderCard());
        if(this.getEndActionButton().getActive()){
            actionList.add(player.getPlayerActionSet().getActiveEndButton());
        }
        notifyViews(new MVStateInfo(new StatePlayerAction(player.getPlayerId(),actionList)));
    }

    public void endActionTurn(Player player) {//TODO IMPLEMENTARLO

    }

    public Dice getSpecificDice(DiceColor diceColor) {
        for (int i = 0; i < dice.size(); i++) {           if (dice.get(i).getColor().equals(diceColor)) {
                return dice.get(i);
            }
        }
        return null;
    }



}
