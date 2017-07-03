package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 15/05/2017.
 *
 * Classe raffigurante la risorsa FaithPoint
 * @see Resource
 */
public class FaithPoint extends PointResource{


    public FaithPoint(int value) {
        super(value);
    }

    @Override
    public Resource cloneResource(Resource resource) {
        return new FaithPoint(resource.getValue());
    }

//    @Override
//    public void addResource(Player player){
//        MarkerDisc markerDiscToMove=faithTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).unSetMarkerDisc(player);
//        player.addResources(this);
//        faithTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(FaithPoint.class)).setMarkerDisc(markerDiscToMove);
//    }

    @Override
    public String toString(){
        return "FP"+this.getValue();
    }

    @Override
    public String getPointResourceType(){
        return "FaithPoint";
    }
}
