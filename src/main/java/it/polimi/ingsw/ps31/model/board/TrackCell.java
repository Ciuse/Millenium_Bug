package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.gameThings.PointResource;
import it.polimi.ingsw.ps31.model.player.Player;

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
    private PointResource extraResource;

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
            if (currentItem.getPlayer().getPlayerColor() == player.getPlayerColor()) {
                this.markerDiscList.remove(currentItem);
                return currentItem;
            }
        }
        return null;
    }
    public void setExtraValue(PointResource resourceValue){
        this.extraResource=resourceValue;
    }
    public int getValue(){
        return this.value;
    }
    public List<MarkerDisc> getMarkerDisc(){
        return new ArrayList<>(this.markerDiscList);
    }
    public PointResource getExtraValue(){
        return this.extraResource;
    }
    public Class<? extends PointResource> getResourceType(){
        return this.resourceType;
    }
}
