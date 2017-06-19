package it.polimi.ingsw.ps31.model.stateModel;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public interface StateVisitor {
    public void visit(StateTypePlayer stateInfoPlayer);
    public void visit(StateCardBox stateCardBox);
    public void visit(StateFamilyMember stateFamilyMember);
    public void visit(StateAllFamilyMember stateAllFamilyMember);
    public void visit(StateGame stateGame);
    public void visit(StatePersonalBoard statePersonalBoard);
    public void visit(StatePlayerAction statePlayerAction);
    public void visit(StatePlayerResources statePlayerResources);
    public void visit(StateActionSpace stateActionSpace);
    public void visit(StateMarkerDisc stateMarkerDisc);
    public void visit(StateTower stateTower);
    public void visit(StateDevelopmentCard stateDevelopmentCard);
    public void visit(StateEffect stateEffect);
}
