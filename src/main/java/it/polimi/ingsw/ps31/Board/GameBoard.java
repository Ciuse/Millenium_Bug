package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.Effect.EffectList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class GameBoard {
    /* Singleton */

    private final static int TOWERNUMBER = 4;
    private Tower[] towers;
    private Market market;
    private CouncilPalace councilPalace;
    private SmallHarvest smallHarvest;

    private SmallProduction smallProduction;
    private BigHarvest bigHarvest;
    private BigProduction bigProduction;
    private Dice[] dice;
    private MilitaryPointTrack militaryPointTrack;
    private FaithPointTrack faithPointTrack;
    private VictoryPointTrack victoryPointTrack;
    private static GameBoard ourInstance;

    public static GameBoard getInstance( ) {
        return ourInstance;
    }
    private GameBoard(List<EffectList> towerEffectList[], List<EffectList> otherEffectList)
    {
        CardColor[] towerColor= {CardColor.GREEN,CardColor.BLUE,CardColor.YELLOW,CardColor.PURPLE};
        for(int i=0; i<TOWERNUMBER; i++) {
            this.towers[i] = new Tower(towerEffectList[i].size(), towerColor[i], towerEffectList[i]);
        }

        DiceColor[] diceColor={DiceColor.WHITE, DiceColor.ORANGE, DiceColor.BLACK, DiceColor.NEUTRAL};
        for(int i=0; i<4; i++) {
            this.dice[i]= new Dice(diceColor[i]);
            this.dice[i].setValue(0);
        }

        this.councilPalace = new CouncilPalace(otherEffectList.get(0));
        this.smallHarvest = new SmallHarvest(1, otherEffectList.get(1));
        this.bigHarvest = new BigHarvest(-1, otherEffectList.get(2));
        this.smallProduction = new SmallProduction(1, otherEffectList.get(3));
        this.bigProduction = new BigProduction(-1, otherEffectList.get(4));
        this.market = new Market();
        this.market.add2PlayerMarketSpace(otherEffectList.get(5),otherEffectList.get(6));
        this.militaryPointTrack=MilitaryPointTrack.getInstance();
        this.faithPointTrack = FaithPointTrack.getInstance();
        this.victoryPointTrack = VictoryPointTrack.getInstance();
    }

    public void add4PlayerMarketSpace(List<EffectList> otherEffectList){
        this.market.add4PlayerMarketSpace(otherEffectList.get(7),otherEffectList.get(8));
    }

    public void remove4PlayerMarketSpace(){
        this.market.remove4PlayerActionSpace();
    }

    public void rollTheDice(){
        for(int i=0; i<3; i++)
        {
            int randomValue= (int)(Math.random()*6 + 1);
            this.dice[i].setValue(randomValue);
        }
    }


    public static int getTOWERNUMBER() {
        return TOWERNUMBER;
    }

    public Tower[] getTowers() {
        return towers;
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

    public Dice[] getDice() {
        return dice;
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

}
