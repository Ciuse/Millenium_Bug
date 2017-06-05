package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameThings.PointResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 31/05/2017.
 */
public class PersonalBoardCardList {
    private final static int MAX_CARD_OF_SAME_COLOR = 6 ;
    private final CardColor cardColor;
    PointResource[] extraResourceRequired;
    private final List<PersonalBoardCardCell> personalBoardCardCellList=new ArrayList<>();

    public PersonalBoardCardList(CardColor cardColor) {
        this.cardColor = cardColor;
        for(int i = 0; i< MAX_CARD_OF_SAME_COLOR; i++){
            personalBoardCardCellList.add(new PersonalBoardCardCell(i,extraResourceRequired[i]));
        }
    }

    protected static int getMaxCardOfSameColor() {
        return MAX_CARD_OF_SAME_COLOR;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public PointResource[] getExtraResourceRequired() {
        return extraResourceRequired.clone();
    }

    public void setExtraResourceRequired(PointResource[] extraResourceRequired) {
        this.extraResourceRequired = extraResourceRequired;
    }

    public List<PersonalBoardCardCell> getPersonalBoardCardCellList() {
        return new ArrayList<>(personalBoardCardCellList);
    }

    public void addCard(DevelopmentCard card){
        boolean trovato=false;
        for (int i = 0; i< MAX_CARD_OF_SAME_COLOR; i++){
            if(trovato==false && personalBoardCardCellList.get(i).getCard()==null){
                personalBoardCardCellList.get(i).setCard(card);
                trovato=true;
            }
        }

    }
}