package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Malus che influenza il conteggio finale dei punti del giocatore, non facendoti contare i punti derivanti
 * da uno specifico colore di carta
 *
 * @see it.polimi.ingsw.ps31.model.game.GameUtility
 */
public class LostExtraFinalVictoryPointBonus extends Bonus {
    private final CardColor cardColor;


    public LostExtraFinalVictoryPointBonus(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    /**
     * Se il get ritorna un colore di carta vuol dire che il giocatore ha questa scomunica e quindi il suo punteggio
     * andrà calcolato in maniera diversa
     * @return
     */
    public CardColor getCardColor(){
        return this.cardColor;
    }

    /**
     *
     //viene attivata nel momento in cui facciamo il get del bonus e quest'ultimo risulta diverso da null poichè vuol dire che abbiamo
     //una scomunica o un leader che va ad influenzare le condizioni del ciclo finale che assegna i punti vittoria.
     * @param player
     */
    @Override
    public void activate(Player player) {
    }
}
