package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public abstract class Bonus implements BonusActivation  {

    protected Bonus() {}
 //   protected Bonus(Action actionToModify) {}

   // private final Action actionToModify;

//    protected Bonus(Action actionToModify) {
//        this.actionToModify = actionToModify;
//    }

//    public Action getActionToModify() {
//        return actionToModify;
//    }

    public CardColor getCardColor(){
        return null;
    }

    public CardColor getCardColorForCostCard() {
        return null;
    }

    public PointResource getPointResource() {
        return null;
    }

    public ResourceList getResourceList() {
        return null;
    }

    public String getString() {
        return null;
    }

    public Resource getExtraResourceOfVaticanReport(){
        return null;
    }
}
