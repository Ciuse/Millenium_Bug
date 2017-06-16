package it.polimi.ingsw.ps31.messageMV;

import it.polimi.ingsw.ps31.model.StateChoice.ChoiceType;

/**
 * Created by giulia on 14/06/2017.
 */
public class MVAskChoice implements MVVisitable {
    private ChoiceType choiceType;

    public MVAskChoice(ChoiceType choiceType) {
        this.choiceType = choiceType;
    }

    public ChoiceType getChoiceType() {
        return choiceType;
    }

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}
