package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.GameThings.FaithPoint;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class FaithPointTrack extends Track{

    private final static int MAXNUMBER = 15;
    private static FaithPointTrack ourInstance = new FaithPointTrack();

    public static FaithPointTrack getInstance() {
        return ourInstance;
    }

    private FaithPointTrack() {
        super(MAXNUMBER, FaithPoint.class);
    }



}
