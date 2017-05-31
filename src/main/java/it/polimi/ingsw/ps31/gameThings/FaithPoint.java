package it.polimi.ingsw.ps31.gameThings;

import it.polimi.ingsw.ps31.board.FaithPointTrack;
import it.polimi.ingsw.ps31.board.MarkerDisc;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class FaithPoint extends PointResource{

    private static FaithPointTrack faithTrack = FaithPointTrack.getInstance();

    public FaithPoint(int value) {
        super(value);
    }

    @Override
    public void addResource(Player player){
        MarkerDisc markerDiscToMove=faithTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).unSetMarkerDisc(player);
        player.getPlayerResources().addResources(this);
        faithTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).setMarkerDisc(markerDiscToMove);
    }
}
