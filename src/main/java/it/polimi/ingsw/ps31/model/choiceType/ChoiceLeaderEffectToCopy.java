package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 20/06/2017.
 *
 * Scelta di quale leader si vuole copiare (effetto Lorendo de Medici)
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceLeaderEffectToCopy extends ChoiceType {
    private int leaderCardId;

    public ChoiceLeaderEffectToCopy(int leaderCardId) {
        this.leaderCardId = leaderCardId;
    }

    public int getLeaderCardId() {
        return leaderCardId;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
