package it.polimi.ingsw.ps31.model.gameResource;

import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.board.MarkerDisc;
import it.polimi.ingsw.ps31.model.board.VictoryPointTrack;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class VictoryPoint extends PointResource{
    private static VictoryPointTrack victoryTrack= GameBoard.getVictoryPointTrack();

    public VictoryPoint(int value) {
        super(value);
    }

    @Override
    public void addResource(Player player){
        MarkerDisc markerDiscToMove=victoryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(VictoryPoint.class)).unSetMarkerDisc(player);
        player.addResources(this);
        victoryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(VictoryPoint.class)).setMarkerDisc(markerDiscToMove);
    }

    @Override
    public String toString(){
        return "VP"+this.getValue();
    }

    @Override
    public String getPointResourceType(){
        return "VictoryPoint";
    }
}
