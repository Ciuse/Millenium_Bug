package it.polimi.ingsw.ps31.model.choiceType;

import java.util.List;

/**
 * Created by Giuseppe on 19/06/2017.
 */
public class ChoiceStartLeaderCard extends ChoiceType {
    private List<Integer> leaderId;
    private List<String> leaderName;

    public ChoiceStartLeaderCard(List<Integer> leaderId, List<String> leaderName) {
        this.leaderId = leaderId;
        this.leaderName = leaderName;
    }

    public List<Integer> getLeaderId() {
        return leaderId;
    }

    public List<String> getLeaderName() {
        return leaderName;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
