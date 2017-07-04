package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceFamilyMember;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 03/07/2017.
 */
public class ChangeFamilyValueEffect extends Effect {
    private final int newFamilyMemberValue;

    public ChangeFamilyValueEffect(int cardId, int newFamilyMemberValue) {
        super(cardId);
        this.newFamilyMemberValue = newFamilyMemberValue;
    }

    @Override
    public void activate(Player player) {
        FamilyMember familyMember;
        player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "A quale family member vuoi cambiare il valore?", new ChoiceFamilyMember()));
        familyMember = player.getModel().getModelChoices().waitFamilyMemberChosen();
        if (familyMember != null) {
            familyMember.setDiceValue(newFamilyMemberValue);
        }
        else
            player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
    }
}
