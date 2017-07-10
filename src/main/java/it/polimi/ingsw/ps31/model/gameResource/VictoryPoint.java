package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 15/05/2017.
 * Classe raffigurante la risorsa VictoryPoint
 * @see Resource
 */
public class VictoryPoint extends PointResource{

    public VictoryPoint(int value) {
        super(value);
    }

    @Override
    public Resource cloneResource(Resource resource) {
        return new VictoryPoint(resource.getValue());
    }

//    @Override
//    public void addResource(player player){
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
