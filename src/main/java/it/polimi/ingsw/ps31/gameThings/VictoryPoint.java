package it.polimi.ingsw.ps31.gameThings;

import it.polimi.ingsw.ps31.board.MarkerDisc;
import it.polimi.ingsw.ps31.board.VictoryPointTrack;
import it.polimi.ingsw.ps31.player.Player;

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
        player.getPlayerResources().addResources(this);
        victoryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(VictoryPoint.class)).setMarkerDisc(markerDiscToMove);
    }
}
