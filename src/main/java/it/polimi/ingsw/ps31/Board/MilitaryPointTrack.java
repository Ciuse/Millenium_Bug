package it.polimi.ingsw.ps31.Board;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class MilitaryPointTrack extends Track {
    private final static int MAXNUMBER = 25;

    private static MilitaryPointTrack ourInstance = new MilitaryPointTrack();

    public static MilitaryPointTrack getInstance() {
        return ourInstance;
    }

    private MilitaryPointTrack() {
        super(MAXNUMBER);
    }
}
