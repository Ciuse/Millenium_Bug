package it.polimi.ingsw.ps31.gameThings;

import it.polimi.ingsw.ps31.board.MarkerDisc;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class FaithPoint extends PointResource{

    public FaithPoint(int value) {
        super(value);
    }

    @Override
    public void addResource(Player player){
        MarkerDisc markerDiscToMove=super.gameBoard.getFaithPointTrack().getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).unSetMarkerDisc(player);
        player.getPlayerResources().addResources(this);
        super.gameBoard.getFaithPointTrack().getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).setMarkerDisc(markerDiscToMove);
    }
}
