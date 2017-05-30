package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.gameThings.VictoryPoint;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class VictoryPointTrack extends Track {

    private static VictoryPointTrack ourInstance = new VictoryPointTrack();

    public static VictoryPointTrack getInstance() {
        if(ourInstance == null) {
            ourInstance = new VictoryPointTrack();
        }
        return ourInstance;    }

    private VictoryPointTrack() {
        super(100,VictoryPoint.class);
    }
}
