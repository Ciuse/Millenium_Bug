package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 20/06/2017.
 */
public class GainVictoryPointFromLeaderCardBonus extends Bonus {
    private final Resource extraResourceOfVaticanReport; //la carta leader Sisto IV mi permette di guadagnare 5 punti vittoria in più

    public GainVictoryPointFromLeaderCardBonus( Resource extraResourceOfVaticanReport) {
        this.extraResourceOfVaticanReport = extraResourceOfVaticanReport;
    }

    public Resource getExtraResourceOfVaticanReport() {
        return extraResourceOfVaticanReport;
    }

    @Override
    public void activate(Player player) {
        //viene attivata nel momento in cui facciamo il get del bonus e quest'ultimo risulta diverso da null poichè vuol dire che abbiamo
        //una scomunica o un leader che va ad influenzare le condizioni del ciclo dei rapporti con il vaticano che assegna i punti fede
    }
}
