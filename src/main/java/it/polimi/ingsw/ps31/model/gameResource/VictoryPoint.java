package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class VictoryPoint extends PointResource{

    public VictoryPoint(int value) {
        super(value);
    }

//    @Override
//    public void addResource(Player player){
//        MarkerDisc markerDiscToMove=victoryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(VictoryPoint.class)).unSetMarkerDisc(player);
//        player.addResources(this);
//        victoryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(VictoryPoint.class)).setMarkerDisc(markerDiscToMove);
//    }

    @Override
    public String toString(){
        return "VP"+this.getValue();
    }

    @Override
    public String getPointResourceType(){
        return "VictoryPoint";
    }
}
