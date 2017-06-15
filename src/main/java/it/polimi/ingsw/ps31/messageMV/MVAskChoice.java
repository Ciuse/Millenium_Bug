package it.polimi.ingsw.ps31.messageMV;

import it.polimi.ingsw.ps31.model.StateChoice.ChoiceInfo;

/**
 * Created by giulia on 14/06/2017.
 */
public class MVAskChoice implements MVVisitable {
    private ChoiceInfo choiceInfo;

    public MVAskChoice(ChoiceInfo choiceInfo) {
        this.choiceInfo = choiceInfo;
    }

    public ChoiceInfo getChoiceInfo() {
        return choiceInfo;
    }

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}
