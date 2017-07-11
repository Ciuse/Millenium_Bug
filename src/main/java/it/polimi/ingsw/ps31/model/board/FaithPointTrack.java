package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.gameResource.FaithPoint;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;

/**
 * Created by Giuseppe on 19/05/2017.
 *
 * Tracciato punti fede, il quale è composto
 */
public class FaithPointTrack extends Track{

    public FaithPointTrack() {
        super(15, FaithPoint.class);
    }

    public void inizializationFaithTrack(PointResource[] extraResourceValue){
        super.setTrackCellExtraValue( extraResourceValue);
    }



}
