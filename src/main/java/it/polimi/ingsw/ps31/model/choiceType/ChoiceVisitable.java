package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public interface ChoiceVisitable {
    void acceptChoice(ChoiceVisitor choiceVisitor);
}
