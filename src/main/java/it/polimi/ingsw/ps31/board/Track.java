package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.gameThings.PointResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 18/05/2017.
 */
public abstract class Track {
   private List<TrackCell> trackCell=new ArrayList<>();
   private final int maxNumber;

   public Track(int maxNumber, Class<? extends PointResource> resourceType){
       this.maxNumber=maxNumber;
       for(int i=0; i<this.maxNumber; i++){
           trackCell.add(new TrackCell(resourceType,i));
       }
   }

   public List<TrackCell> getTrackCell(){
       return new ArrayList<>(this.trackCell);
   }

   public int getMaxNumber() {
       return maxNumber;
   }

   public void setTrackCellExtraValue(PointResource[] extraResourceValue)
   {
       for(int i=0; i<this.maxNumber; i++){
           trackCell.get(i).setExtraValue(extraResourceValue[i]);
       }

   }
}
