package it.polimi.ingsw.ps31.Board;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class VictoryPoinTrack extends Track {
    private final static int MAXNUMBER = 100;

    private static VictoryPoinTrack ourInstance = new VictoryPoinTrack();

    public static VictoryPoinTrack getInstance() {
        return ourInstance;
    }

    private VictoryPoinTrack() {
        super(MAXNUMBER);
    }
}
