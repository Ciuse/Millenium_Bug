package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class LostExtraFinalVictoryPointBonus extends Bonus {
    private final CardColor cardColor;


    public LostExtraFinalVictoryPointBonus(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public CardColor getCardColor(){
        return this.cardColor;
    }

    @Override
    public void activate(Player player) {
        //viene attivata nel momento in cui facciamo il get del bonus e quest'ultimo risulta diverso da null poichè vuol dire che abbiamo
        //una scomunica o un leader che va ad influenzare le condizioni del ciclo finale che assegna i punti vittoria
    }
}
