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
       for(int i=0; i<maxNumber; i++){
           this.trackCell[i]=new TrackCell(resourceType,i);
       }
   }

   public TrackCell[] getTrackCell(){
       return this.trackCell;
   }

   public int getMaxNumber() {
       return maxNumber;
   }
}
