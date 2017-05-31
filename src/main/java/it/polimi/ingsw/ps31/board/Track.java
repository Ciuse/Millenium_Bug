package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.gameThings.PointResource;

/**
 * Created by Giuseppe on 18/05/2017.
 */
public abstract class Track {
   private TrackCell[]  trackCell;
   private final int maxNumber;

   public Track(int maxNumber, Class<? extends PointResource> resourceType){
       this.maxNumber=maxNumber;
       TrackCell[] trackCell = null;
       for(int i=0; i<this.maxNumber; i++){
           trackCell[i]=new TrackCell(resourceType,i);
       }
       this.trackCell=trackCell;
   }

   public TrackCell[] getTrackCell(){
       return this.trackCell.clone();
   }

   public int getMaxNumber() {
       return maxNumber;
   }

   public void setTrackCellExtraValue(PointResource[] extraResourceValue)
   {
       for(int i=0; i<this.maxNumber; i++){
           trackCell[i].setExtraValue(extraResourceValue[i]);
       }

   }
}
