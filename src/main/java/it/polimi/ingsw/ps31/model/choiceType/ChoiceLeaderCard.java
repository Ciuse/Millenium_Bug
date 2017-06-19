package it.polimi.ingsw.ps31.model.choiceType;

import java.util.List;

/**
 * Created by Giuseppe on 19/06/2017.
 */
public class ChoiceLeaderCard extends ChoiceType {
    List<String> leaderName;
    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
