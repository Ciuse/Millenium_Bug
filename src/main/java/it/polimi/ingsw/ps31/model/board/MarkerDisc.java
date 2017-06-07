package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 18/05/2017.
 */
public class MarkerDisc {
    private final Class<? extends PointResource> resourceType;
    private final Player player;
    private TrackCell trackCell;

    public MarkerDisc(Class<? extends PointResource> resourceType, Player player) {
        this.resourceType = resourceType;
        this.player = player;
    }

    /*Getters*/
    public Player getPlayer(){
        return this.player;
    }
    public Class<? extends PointResource> getResourceType(){
        return resourceType;
    }
    public void setTrackCell(){
        if(trackCell.getResourceType().equals(this.getResourceType()))
        trackCell.setMarkerDisc(this);
    }
    //TODO FINIRE ANCORA LA CLASSE CON I SET PER LE TRACK CELL

}
