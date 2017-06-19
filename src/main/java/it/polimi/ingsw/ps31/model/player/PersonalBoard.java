package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.stateModel.StatePersonalBoard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PersonalBoard {
    private final PlayerId playerId;
    private final List<PersonalBoardCardList> personalBoardCardList=null;
    private final static int NUM_OF_CARD_LIST =4;


    /* Constructor */
    public PersonalBoard(List<PointResource[]> pointResourceRequired, PlayerId playerId) {
        this.playerId = playerId;
        CardColor[] cardColor= CardColor.values();
        for(int i = 0; i< NUM_OF_CARD_LIST; i++){
            personalBoardCardList.add(new PersonalBoardCardList(playerId,cardColor[i]));
            personalBoardCardList.get(i).setExtraResourceRequired(pointResourceRequired.get(i));
        }

    }

    /* Getters & Setters */

    public List<PersonalBoardCardList> getPersonalBoardCardList() {
        return personalBoardCardList;
    }

    public StatePersonalBoard getStatePersonalBoard(){
        List<StateCardBox> stateCardBoxes = new ArrayList<>();
        for (PersonalBoardCardList list: personalBoardCardList
             ) {
            for (PersonalBoardCardCell cell : list.getPersonalBoardCardCellList()
                    ) {
                stateCardBoxes.add(cell.getStatePersonalCardBox());
            }

        }
        StatePersonalBoard statePersonalBoard = new StatePersonalBoard(playerId,stateCardBoxes);
        return statePersonalBoard;
    }

    public PersonalBoardCardList getSpecificPersonalBoardCardList(CardColor  cardColor) {
        for (PersonalBoardCardList list : personalBoardCardList
                ) {
            if (list.getCardColor().equals(cardColor)) {
                return list;
            }
        }
        return null;
    }

    protected static int getNumOfCardList() {
        return NUM_OF_CARD_LIST;
    }

    public DevelopmentCardList getPlayerCardList() {
        DevelopmentCardList developmentCardList = new DevelopmentCardList();
        for (PersonalBoardCardList list: personalBoardCardList
                ) {
            for (PersonalBoardCardCell cell: list.getPersonalBoardCardCellList()
                    ) {
                developmentCardList.add(cell.getCard());
            }
        }
        return developmentCardList;
    }

    public void addCard(DevelopmentCard card){
        for(int i = 0; i< NUM_OF_CARD_LIST; i++){
            if(personalBoardCardList.get(i).getCardColor().equals(card.getCardColor())){
                personalBoardCardList.get(i).addCard(card);
            }
        }
    }

}
