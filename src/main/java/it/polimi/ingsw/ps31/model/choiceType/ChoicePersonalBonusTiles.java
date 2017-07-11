package it.polimi.ingsw.ps31.model.choiceType;

import it.polimi.ingsw.ps31.model.stateModel.StatePersonalBonusTiles;

import java.util.List;

/**
 * Created by giulia on 20/06/2017.
 *
 * Scelta iniziale di quale personal bonus tiles tenersi
 *
 * @see it.polimi.ingsw.ps31.client.view.View
 */
public class ChoicePersonalBonusTiles extends ChoiceType {
    private List<StatePersonalBonusTiles> statePersonalBonusTilesList;

    public ChoicePersonalBonusTiles(List<StatePersonalBonusTiles> statePersonalBonusTilesList) {
        this.statePersonalBonusTilesList = statePersonalBonusTilesList;
    }

    public List<StatePersonalBonusTiles> getStatePersonalBonusTilesList() {
        return statePersonalBonusTilesList;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
