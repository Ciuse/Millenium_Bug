package it.polimi.ingsw.ps31.model.choiceType;

import it.polimi.ingsw.ps31.model.stateModel.StatePersonalBonusTiles;

import java.util.List;

/**
 * Created by giulia on 20/06/2017.
 */
public class ChoicePersonalBonusTiles extends ChoiceType {
    List<StatePersonalBonusTiles> statePersonalBonusTilesList;

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
