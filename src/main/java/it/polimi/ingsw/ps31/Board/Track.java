package it.polimi.ingsw.ps31.Board;

/**
 * Created by Giuseppe on 18/05/2017.
 */
public abstract class Track {
   private TrackCell[]  trackCell;

   public Track(int MAXNUMBER){
       for(int i=0; i<MAXNUMBER; i++){
           this.trackCell[i]=new TrackCell(i);
       }
   }
}
