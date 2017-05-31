package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.GameInizialization;
import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.constants.DiceColor;
import it.polimi.ingsw.ps31.effect.EffectList;
import it.polimi.ingsw.ps31.player.Excommunication;
import it.polimi.ingsw.ps31.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class GameBoard {
    /* Singleton */

    private final static int TOWERNUMBER = 4;
    private List<Tower> towers;
    private Market market;
    private CouncilPalace councilPalace;
    private SmallHarvest smallHarvest;

    private SmallProduction smallProduction;
    private BigHarvest bigHarvest;
    private BigProduction bigProduction;
    private List<Dice> dice;
    private MilitaryPointTrack militaryPointTrack;
    private FaithPointTrack faithPointTrack;
    private VictoryPointTrack victoryPointTrack;
    private EndActionButton endActionButton;
    private List<Excommunication> excommunicationList;
    private static GameBoard ourInstance;

    public static GameBoard getInstance() {
        if (ourInstance == null) {
            ourInstance = new GameBoard();
        }
        return ourInstance;
    }

    private GameBoard() {
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

    public void initializateGameBoard(List<List<EffectList>> towerEffectList, List<EffectList> otherEffectList) {
        //creazione torri
        CardColor[] towerColor = {CardColor.GREEN, CardColor.BLUE, CardColor.YELLOW, CardColor.PURPLE};
        for (int i = 0; i < TOWERNUMBER; i++) {
            towers.add(new Tower(towerEffectList.get(i).size(), towerColor[i], towerEffectList.get(i)));
        }
        this.towers = towers;
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
        this.militaryPointTrack = MilitaryPointTrack.getInstance();
        this.faithPointTrack = FaithPointTrack.getInstance();
        this.victoryPointTrack = VictoryPointTrack.getInstance();
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

    public MilitaryPointTrack getMilitaryPointTrack() {
        return militaryPointTrack;
    }

    public FaithPointTrack getFaithPointTrack() {
        return faithPointTrack;
    }

    public VictoryPointTrack getVictoryPointTrack() {
        return victoryPointTrack;
    }

    public List<Excommunication> getExcommunicationList() {
        return new ArrayList<>(excommunicationList);
    }

    public void setExcommunicationList(List<Excommunication> excommunicationList) {
        this.excommunicationList = excommunicationList;
    }

    public EndActionButton getEndActionButton() {
        return endActionButton;
    }

    public void startActionTurn(Player player) {//TODO IMPLEMENTARLO

    }

    public void endActionTurn(Player player) {//TODO IMPLEMENTARLO

    }

    public Dice getSpecificDice(DiceColor diceColor) {
        for (int i = 0; i < this.dice.size(); i++) {
            if (this.dice.get(i).getColor().equals(diceColor)) {
                return dice.get(i);
            }
        }
        return null;
    }

}
