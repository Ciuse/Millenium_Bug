package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.GameThings.VictoryPoint;

import java.awt.*;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class VictoryPointTrack extends Track {

    private static VictoryPointTrack ourInstance = new VictoryPointTrack();

    public static VictoryPointTrack getInstance() {
        return ourInstance;
    }

    private VictoryPointTrack() {
        super(100,VictoryPoint.class);
    }
}
