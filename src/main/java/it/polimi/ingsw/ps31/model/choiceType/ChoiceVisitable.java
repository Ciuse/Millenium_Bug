package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 *
 *  Interfaccia del pattern Visitor (delle scelte), implementata dagli oggetti visitabili, i quali sanno come accettarsi
 * @see it.polimi.ingsw.ps31.model.choiceType.ChoiceType
 */
public interface ChoiceVisitable {
    void acceptChoice(ChoiceVisitor choiceVisitor);
}
