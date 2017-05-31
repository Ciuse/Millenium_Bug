package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.gameThings.FaithPoint;
import it.polimi.ingsw.ps31.gameThings.PointResource;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class FaithPointTrack extends Track{
    private static FaithPointTrack ourInstance = new FaithPointTrack();

    public static FaithPointTrack getInstance() {
        if(ourInstance == null) {
            ourInstance = new FaithPointTrack();
        }
        return ourInstance;    }

    private FaithPointTrack() {
        super(15, FaithPoint.class);
    }

    public void inizializationFaithTrack(PointResource[] extraResourceValue){
        super.setTrackCellExtraValue( extraResourceValue);
    }



}
