package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.model.StateModel.*;
import it.polimi.ingsw.ps31.model.StateModel.StateVisitor;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public class MesStateInfoVisitor implements StateVisitor {
    View view;

    public void setView(View view){
        this.view=view;
    }
    @Override
    public void visit(StateInfoPlayer stateInfoPlayer) {
        view.updateInfoPlayer(stateInfoPlayer);
    }

    @Override
    public void visit(StatePlayerResources statePlayerResources) {
        view.updatePlayerResources(statePlayerResources);
    }

    @Override
    public void visit(StateFamilyMember stateFamilyMember) {
        view.updateFamilyMember(stateFamilyMember);
    }

    @Override
    public void visit(StateAllFamilyMember stateAllFamilyMember) {

    }

    @Override
    public void visit(StateCardBox stateCardBox) {

    }



    @Override
    public void visit(StateGame stateGhame) {

    }

    @Override
    public void visit(StatePersonalBoard statePersonalBoard) {

    }

    @Override
    public void visit(StatePlayerAction statePlayerAction) {

    }


}
