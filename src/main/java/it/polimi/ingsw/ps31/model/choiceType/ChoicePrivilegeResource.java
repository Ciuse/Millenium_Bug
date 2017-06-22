package it.polimi.ingsw.ps31.model.choiceType;

import java.util.List;

/**
 * Created by giulia on 21/06/2017.
 */
public class ChoicePrivilegeResource extends ChoiceType {
    List<String> resourceListToChoice;

    public ChoicePrivilegeResource(List<String> resourceListToChoice) {
        this.resourceListToChoice = resourceListToChoice;
    }

    public List<String> getResourceListToChoice() {
        return resourceListToChoice;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
