package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.PersonalBoard;
import it.polimi.ingsw.ps31.model.player.PersonalBoardCardCell;
import it.polimi.ingsw.ps31.model.player.PersonalBoardCardList;

import java.util.List;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePersonalBoard extends StateInfo{
    private PlayerId playerId;
    private final PersonalBoard personalBoard;
    private final List<StateCardBox> stateCardBoxes;

    public StatePersonalBoard(PlayerId playerId,PersonalBoard personalBoard, List<StateCardBox> stateCardBoxes) {
        this.playerId = playerId;
        this.personalBoard = personalBoard;
        this.stateCardBoxes = stateCardBoxes;
        this.createListCardBox();
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public List<StateCardBox> getStateCardBoxes() {
        return stateCardBoxes;
    }

    public void createListCardBox(){
        for (PersonalBoardCardList list: personalBoard.getPersonalBoardCardList()
                ) {
            for (PersonalBoardCardCell cell: list.getPersonalBoardCardCellList()
                    ) {
                stateCardBoxes.add(cell.GetStateCardBox());
            }
        }
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
