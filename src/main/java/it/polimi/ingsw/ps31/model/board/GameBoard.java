package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.card.ExcommunicationTiles;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.VictoryPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Classe che rappresenta la gameBoard nel suo intero e i metodi per gestire gli elementi al suo interno
 * Possiede tutti i riferimenti agli action space, alle torri, e ai tracciati, oltre a un riferimento al model
 */
public class GameBoard{

    private final static int TOWERNUMBER = 4;
    private List<Tower> towers= new ArrayList<>();
    private Market market;
    private CouncilPalace councilPalace;
    private SmallHarvest smallHarvest;
    private SmallProduction smallProduction;
    private BigHarvest bigHarvest;
    private BigProduction bigProduction;
    private List<Dice> dice = new ArrayList<>();
    private MilitaryPointTrack militaryPointTrack;
    private FaithPointTrack faithPointTrack;
    private VictoryPointTrack victoryPointTrack;
    private List<ExcommunicationTiles> excommunicationTilesList;
    private final Model model;

    public GameBoard(List<List<EffectList>> towerEffectList, List<EffectList> otherEffectList, VictoryPoint[] faithTrackExtraValue, Model model)
    {
        this.model = model;

        //creazione torri
        CardColor[] towerColor = CardColor.values();
        for (int i = 0; i < TOWERNUMBER; i++) {
            towers.add(new Tower(towerColor[i], towerEffectList.get(i), model));
        }
        setTowerSpaceId();

        //creazione dadi
        DiceColor[] diceColor = DiceColor.values();
        for (int i = 0; i < 4; i++) {
            dice.add(new Dice(diceColor[i]));
            dice.get(i).setValue(0);
        }

        //creazione action Space e tracciati
        this.councilPalace = new CouncilPalace(otherEffectList.get(0));
        this.councilPalace.setActionSpaceId(17);
        this.smallHarvest = new SmallHarvest(1, otherEffectList.get(1));
        this.smallHarvest.setActionSpaceId(18);
        this.smallProduction = new SmallProduction(1, otherEffectList.get(3));
        this.smallProduction.setActionSpaceId(20);
        this.market = new Market();
        this.market.add2PlayerMarketSpace(otherEffectList.get(5), otherEffectList.get(6));
        this.militaryPointTrack = new MilitaryPointTrack();
        this.faithPointTrack = new FaithPointTrack();
        this.faithPointTrack.inizializationFaithTrack(faithTrackExtraValue);
        this.victoryPointTrack = new VictoryPointTrack();
    }

    /**
     * Funzione per abilitare gli action space da 3 giocatori
     * @param otherEffectList lista di effetti per crearli
     */
    public void add3PlayerActionSpace(List<EffectList> otherEffectList){
        this.bigHarvest = new BigHarvest(-1, otherEffectList.get(2));
        this.bigHarvest.setActionSpaceId(19);
        this.bigProduction = new BigProduction(-1, otherEffectList.get(4));
        this.bigProduction.setActionSpaceId(21);
    }

    /**
     * Funzione per abilitare gli action space da 4 giocatori
     * @param otherEffectList lista di effetti per crearli
     */
    public void add4PlayerMarketSpace(List<EffectList> otherEffectList) {
        this.market.add4PlayerMarketSpace(otherEffectList.get(7), otherEffectList.get(8));
    }

    /**
     * Funzione per rimuovere gli action space da 4 giocatori
     */
    public void remove4PlayerMarketSpace() {
        this.market.remove4PlayerActionSpace();
    }

    public void setTowerSpaceId(){
        int i=1;
        for (Tower tower: towers
             ) {
            for (TowerCardSpace towerCardSpace: tower.getTowerCardSpaceList()
                 ) {
                towerCardSpace.getActionSpace().setActionSpaceId(i);
                i++;
            }
        }
    }

    public void setMarketId(){
        int i=22;
        for (ActionSpace actionSpace:market.getActionSpaceList()
             ) {
            actionSpace.setActionSpaceId(i);
            i++;
        }
    }

    /**
     * Funzione per tirare i dadi in modo casuale
     */
    public void rollTheDice() {
        for (Dice aDice : dice) {
            if (!aDice.getColor().equals(DiceColor.NEUTRAL)) {
                int randomValue = (int) (Math.random() * 6 + 1);
                aDice.setValue(randomValue);
            }
        }
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

    public Model getModel() {
        return model;
    }

    public List<ExcommunicationTiles> getExcommunicationTilesList() {
        return excommunicationTilesList;
    }

    public void setExcommunicationTilesList(List<ExcommunicationTiles> excommunicationTiles) {
        this.excommunicationTilesList = excommunicationTiles;
    }
    public Dice getSpecificDice(DiceColor diceColor) {
        for (Dice aDice : dice) {
            if (aDice.getColor().equals(diceColor)) {
                return aDice;
            }
        }
        return null;
    }



}
