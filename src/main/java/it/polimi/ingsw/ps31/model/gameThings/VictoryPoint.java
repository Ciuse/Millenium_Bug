package it.polimi.ingsw.ps31.model.gameThings;

import it.polimi.ingsw.ps31.model.board.MarkerDisc;
import it.polimi.ingsw.ps31.model.board.VictoryPointTrack;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class VictoryPoint extends PointResource{
    private static VictoryPointTrack victoryTrack = VictoryPointTrack.getInstance();

    public VictoryPoint(int value) {
        super(value);
    }

    @Override
    public void addResource(Player player){
        MarkerDisc markerDiscToMove=victoryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(VictoryPoint.class)).unSetMarkerDisc(player);
        player.addResources(this);
        victoryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(VictoryPoint.class)).setMarkerDisc(markerDiscToMove);
    }
}
