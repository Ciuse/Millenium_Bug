package it.polimi.ingsw.ps31.model.stateModel;

/**
 * Created by Giuseppe on 06/06/2017.
 *
 * Interfaccia del pattern Visitor (degli stati), implementata dall'oggetto visitatore che sa come visitare i vari oggetti diversi
 * @see MVStateInfoVisitor
 */
public interface StateVisitor {

    void visit(StatePlayer stateInfoPlayer);

    void visit(StateCardBox stateCardBox);

    void visit(StateFamilyMember stateFamilyMember);

    void visit(StateAllFamilyMember stateAllFamilyMember);

    void visit(StateGame stateGame);

    void visit(StatePersonalBoard statePersonalBoard);

    void visit(StatePlayerAction statePlayerAction);

    void visit(StatePlayerResources statePlayerResources);

    void visit(StateActionSpace stateActionSpace);

    void visit(StateMarkerDisc stateMarkerDisc);

    void visit(StateTower stateTower);

    void visit(StateDevelopmentCard stateDevelopmentCard);

    void visit(StateEffect stateEffect);

    void visit(StatePersonalBonusTiles statePersonalBonusTiles);

    void visit(StateLeaderCard stateLeaderCard);

    void visit(StateAllDevelopmentCard stateAllDevelopmentCard);

    void visit(StateExcommunication stateExcommunication);
}
