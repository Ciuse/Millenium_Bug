package it.polimi.ingsw.ps31.model.choiceType;

import java.util.List;

/**
 * Created by giulia on 21/06/2017.
 *
 * Scelta di quale privilegio del consiglio ottenere in base a quelli possibili
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoicePrivilegeResource extends ChoiceType {
    private List<String> resourceListToChoice;

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
