package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.client.view.View;

/**
 * Created by Giuseppe on 06/06/2017.
 *
 * Implementazione del pattern visitor all'interno della logica degli stati.
 * Ogni stato sa quale metodo di aggiornamento deve invocare sulla view.
 */
public class MVStateInfoVisitor implements StateVisitor {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void visit(StatePlayer stateInfoPlayer) {
        view.updateInfoPlayer(stateInfoPlayer);
    }

    @Override
    public void visit(StatePlayerResources statePlayerResources) {
        view.updatePlayerResources(statePlayerResources);
    }

    @Override
    public void visit(StateActionSpace stateActionSpace) {
        view.updateActionSpace(stateActionSpace);
    }

    @Override
    public void visit(StateMarkerDisc stateMarkerDisc) {
        view.updateMarkerDisc(stateMarkerDisc);
    }

    @Override
    public void visit(StateTower stateTower) {
        view.updateTower(stateTower);
    }

    @Override
    public void visit(StateDevelopmentCard stateDevelopmentCard) {
        view.updateDevelopmentCard(stateDevelopmentCard);
    }

    @Override
    public void visit(StateEffect stateEffect) {
        //non viene mai mandato questo stato da solo dato che non cambiano mai durante il gioco
    }

    @Override
    public void visit(StatePersonalBonusTiles statePersonalBonusTiles) {
        view.updatePersonalBonusTiles(statePersonalBonusTiles);
    }

    @Override
    public void visit(StateLeaderCard stateLeaderCard) {
        view.updateLeaderCard(stateLeaderCard);
    }

    @Override
    public void visit(StateAllDevelopmentCard stateAllDevelopmentCard) {
        view.updateAllDevelopmentCard(stateAllDevelopmentCard);
    }

    @Override
    public void visit(StateExcommunication stateExcommunication) {
        view.updateExcommunication(stateExcommunication);
    }

    @Override
    public void visit(StateFamilyMember stateFamilyMember) {
        view.updateFamilyMember(stateFamilyMember);
    }

    @Override
    public void visit(StateAllFamilyMember stateAllFamilyMember) {
        view.updateAllFamilyMember(stateAllFamilyMember);
    }

    @Override
    public void visit(StateCardBox stateCardBox) {
        view.updateCardBox(stateCardBox);
    }

    @Override
    public void visit(StateGame stateGame) {
        view.updateGame(stateGame);
    }

    @Override
    public void visit(StatePersonalBoard statePersonalBoard) {
        view.updatePersonalBoard(statePersonalBoard);
    }

    @Override
    public void visit(StatePlayerAction statePlayerAction) {
        view.updatePlayerAction(statePlayerAction);
    }

}