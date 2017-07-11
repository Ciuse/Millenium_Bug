package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateMarkerDisc;

/**
 * Created by Giuseppe on 18/05/2017.
 *
 * Elemento che si muove lungo le caselle dei tracciati per segnare i punti
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
    public void setTrackCell(TrackCell trackCell){
        if(trackCell.getResourceType().equals(this.getResourceType())){
            this.trackCell=trackCell;
            trackCell.setMarkerDisc(this);
        }
    }

    public StateMarkerDisc getStateMarkerDisc(){
        StateMarkerDisc stateMarkerDisc = new StateMarkerDisc(player.getPlayerId(),resourceType.getTypeName(),trackCell.getValue());
        return stateMarkerDisc;
    }

}
