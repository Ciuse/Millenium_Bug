package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.gameResource.FaithPoint;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class FaithPointTrack extends Track{

    private FaithPointTrack() {
        super(15, FaithPoint.class);
    }

    public void inizializationFaithTrack(PointResource[] extraResourceValue){
        super.setTrackCellExtraValue( extraResourceValue);
    }



}
