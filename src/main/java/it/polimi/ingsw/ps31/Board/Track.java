package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.GameThings.PointResource;

/**
 * Created by Giuseppe on 18/05/2017.
 */
public abstract class Track {
   private TrackCell[]  trackCell;

   public Track(int MAXNUMBER, Class<? extends PointResource> resourceType){
       for(int i=0; i<MAXNUMBER; i++){
           this.trackCell[i]=new TrackCell(resourceType,i);
       }
   }

   public TrackCell[] getTrackCell(){
       return this.trackCell;
   }
}
