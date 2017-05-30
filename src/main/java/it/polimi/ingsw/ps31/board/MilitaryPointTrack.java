package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.gameThings.MilitaryStrength;

/**
 * Created by Giuseppe on 19/05/2017.
 */
public class MilitaryPointTrack extends Track {

    private static MilitaryPointTrack ourInstance = new MilitaryPointTrack();

    public static MilitaryPointTrack getInstance() {
        if(ourInstance == null) {
            ourInstance = new MilitaryPointTrack();
        }
        return ourInstance;
    }

    private MilitaryPointTrack() {
        super(25, MilitaryStrength.class);
    }
}
