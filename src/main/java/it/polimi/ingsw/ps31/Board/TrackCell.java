package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.GameThings.PointResource;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.Iterator;
        import java.util.List;

/**
 * Created by Giuseppe on 18/05/2017.
 */
public class TrackCell {
    private List<MarkerDisc> markerDiscList= new ArrayList<>();
    private final Class<? extends PointResource> resourceType;
    private final int value;
    private PointResource resourceValue;

    public TrackCell(Class<? extends PointResource> resourceType, int value) {
        this.resourceType = resourceType;
        this.value = value;
    }

    /*Getters & Setters*/
    public void setMarkerDisc(MarkerDisc markerDisc){
        this.markerDiscList.add(markerDisc);
    }
    public MarkerDisc unSetMarkerDisc(Player player){       //toglie il pezzo del colore del player che invoca il metodo e restituisce il pezzo che andrà risettato
        Iterator<MarkerDisc> iterator = markerDiscList.iterator();
        while(iterator.hasNext()) {

            MarkerDisc currentItem = iterator.next();
            if (currentItem.getPlayer().getColor() == player.getColor()) {
                this.markerDiscList.remove(currentItem);
                return currentItem;
            }
        }
        return null;
    }
    public void setExtraValue(PointResource resourceValue){
        this.resourceValue=resourceValue;
    }
    public int getValue(){
        return this.value;
    }
    public List<MarkerDisc> getMarkerDisc(){
        return this.markerDiscList;
    }
    public PointResource getExtraValue(){
        return this.resourceValue;
    }
    public Class<? extends PointResource> getResourceType(){
        return this.resourceType;
    }
}
