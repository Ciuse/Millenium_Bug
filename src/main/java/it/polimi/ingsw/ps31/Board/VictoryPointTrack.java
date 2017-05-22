package it.polimi.ingsw.ps31.Board;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class VictoryPointTrack extends Track {
    private final static int MAXNUMBER = 100;

    private static VictoryPointTrack ourInstance = new VictoryPointTrack();

    public static VictoryPointTrack getInstance() {
        return ourInstance;
    }

    private VictoryPointTrack() {
        super(MAXNUMBER);
    }
}
