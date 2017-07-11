package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by giulia on 22/06/2017.
 *
 * SCelta del numero di servitori che si vuole pagare
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceNumberOfServantsToPay extends ChoiceType {

    public ChoiceNumberOfServantsToPay() {
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
