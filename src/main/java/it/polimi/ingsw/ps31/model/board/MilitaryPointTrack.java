package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.gameResource.MilitaryStrength;

/**
 * Created by Giuseppe on 19/05/2017.
 *
 * Tracciato punti MIlitari
 */
public class MilitaryPointTrack extends Track {

    public MilitaryPointTrack() {
        super(25, MilitaryStrength.class);
    }
}
