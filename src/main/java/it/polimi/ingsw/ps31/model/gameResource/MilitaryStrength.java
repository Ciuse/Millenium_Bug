package it.polimi.ingsw.ps31.model.gameResource;

/**
 * Created by Giuseppe on 15/05/2017.
 *
 * Classe raffigurante la risorsa MilitaryStrength
 * @see Resource
 */
public class MilitaryStrength extends PointResource {
    /**
     * valore opzionale che a volte può esserci, indica quanti punti militari servono per poter usare questa risorsa
     */
    private int valueRequest = 0;

    public MilitaryStrength(int value) {
        super(value);
    }

    @Override
    public Resource cloneResource(Resource resource) {
        return new MilitaryStrength(resource.getValue());
    }

    public MilitaryStrength(int value, int valueRequest) {
        super(value);
        this.valueRequest = valueRequest;
    }

    public int getValueRequest() {
        return valueRequest;
    }

    public void setValueRequest(int valueRequest) {
        this.valueRequest = valueRequest;
    }

//            MarkerDisc markerDiscToMove=militaryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(MilitaryStrength.class)).unSetMarkerDisc(player);
//            player.addResources(this);
//            militaryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(MilitaryStrength.class)).setMarkerDisc(markerDiscToMove);


    @Override
    public String toString() {
        return "MS" + this.getValue();
    }

    @Override
    public String getPointResourceType() {
        return "MilitaryStrength";
    }

}
