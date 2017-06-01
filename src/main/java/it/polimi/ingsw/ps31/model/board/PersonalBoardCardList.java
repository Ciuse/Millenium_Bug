package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameThings.PointResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 31/05/2017.
 */
public class PersonalBoardCardList {
    private final static int MAXCARDNUMBER = 6 ;
    private final CardColor cardColor;
    PointResource[] extraResourceRequired;
    private final List<PersonalBoardCardCell> personalBoardCardCellList=new ArrayList<>();

    public PersonalBoardCardList(CardColor cardColor) {
        this.cardColor = cardColor;
        for(int i=0; i<MAXCARDNUMBER;i++){
            personalBoardCardCellList.add(new PersonalBoardCardCell(i,extraResourceRequired[i]));
        }
    }

    public static int getMAXCARDNUMBER() {
        return MAXCARDNUMBER;
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
        for (int i=0; i<MAXCARDNUMBER; i++){
            if(trovato==false && personalBoardCardCellList.get(i).getCard()==null){
                personalBoardCardCellList.get(i).setCard(card);
                trovato=true;
            }
        }

    }
}
