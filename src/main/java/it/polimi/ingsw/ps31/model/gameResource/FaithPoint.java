package it.polimi.ingsw.ps31.model.gameResource;

import it.polimi.ingsw.ps31.model.board.FaithPointTrack;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.board.MarkerDisc;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class FaithPoint extends PointResource{

    private static FaithPointTrack faithTrack= GameBoard.getFaithPointTrack();

    public FaithPoint(int value) {
        super(value);
    }

    @Override
    public void addResource(Player player){
        MarkerDisc markerDiscToMove=faithTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).unSetMarkerDisc(player);
        player.addResources(this);
        faithTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).setMarkerDisc(markerDiscToMove);
    }
}
