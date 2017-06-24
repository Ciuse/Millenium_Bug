package it.polimi.ingsw.ps31.model.choiceType;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;

import java.util.List;

/**
 * Created by giulia on 20/06/2017.
 */
public class ChoiceColor extends ChoiceType {
    List<PlayerColor> playerColorList;

    public ChoiceColor(List<PlayerColor> playerColorList) {
        this.playerColorList = playerColorList;
    }

    public List<PlayerColor> getPlayerColorList() {
        return playerColorList;
    }

    @Override
    public void acceptChoice(ChoiceVisitor choiceVisitor) {
        choiceVisitor.visit(this);
    }
}
