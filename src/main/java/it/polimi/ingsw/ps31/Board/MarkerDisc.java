package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.GameThings.PointResource;
import it.polimi.ingsw.ps31.Player.Player;

import java.awt.*;

/**
 * Created by Giuseppe on 18/05/2017.
 */
public class MarkerDisc {
    private PointResource resourceType;
    private final Player player;

    public MarkerDisc(Player player) {
        this.player = player;
    }

    public MarkerDisc(PointResource resourceType, Player player) {
        this.resourceType = resourceType;
        this.player = player;
    }

    /*Getters*/
    public Player getPlayer(){
        return this.player;
    }
    public PointResource getResourceType(){
        return resourceType;
    }
}
