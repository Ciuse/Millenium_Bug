package it.polimi.ingsw.ps31.Board;

/**
 * Created by Francesco on 12/05/2017.
 */
public class GameBoard {
    /* Singleton */

    private final static int TOWERNUMBER = 4;
    private Tower[] towers = new Tower[TOWERNUMBER];
    private Market market = new Market();
    private CouncilPalace councilPalace = new CouncilPalace();
    private SmallHarvest smallHarvest = new SmallHarvest(1);
    private SmallProduction smallProduction = new SmallProduction(1);
    private BigHarvest bigHarvest = new BigHarvest(-1);
    private BigProduction bigProduction = new BigProduction(-1);
    private static GameBoard ourInstance = new GameBoard();

    public static GameBoard getInstance() {
        return ourInstance;
    }
    private GameBoard()
    {
        towers = new Tower[TOWERNUMBER];
        market = new Market();
        councilPalace = new CouncilPalace();
        smallHarvest = new SmallHarvest(1);
        smallProduction = new SmallProduction(1);
        BigHarvest bigHarvest = new BigHarvest(-1);
        BigProduction bigProduction = new BigProduction(-1);

    }


}
