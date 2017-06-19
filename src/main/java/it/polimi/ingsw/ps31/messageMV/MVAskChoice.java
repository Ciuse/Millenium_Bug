package it.polimi.ingsw.ps31.messageMV;

import it.polimi.ingsw.ps31.model.stateChoice.ChoiceType;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 14/06/2017.
 */
public class MVAskChoice extends MVVisitable {
    private String stringToPrint;
    private ChoiceType choiceType;

    public MVAskChoice(PlayerId playerToNotify, String stringToPrint, ChoiceType choiceType) {
        this.stringToPrint = stringToPrint;
        this.choiceType = choiceType;
        super.setNotifyAll(false);
        super.setNotifySinglePlayer(playerToNotify);
    }

    public ChoiceType getChoiceType() {
        return choiceType;
    }

    public String getStringToPrint() {
        return stringToPrint;
    }

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}
