package it.polimi.ingsw.ps31.model.choiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 *
 * Scelta di quale lista di costi si vuole pagare
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoiceListToPay extends ChoiceType {
    private final int cardId;

    public ChoiceListToPay(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
