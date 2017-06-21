package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 20/06/2017.
 */
public class ChoiceLeaderEffectToCopy extends ChoiceType {
    int leaderCardId;

    public ChoiceLeaderEffectToCopy(int leaderCardId) {
        this.leaderCardId = leaderCardId;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
