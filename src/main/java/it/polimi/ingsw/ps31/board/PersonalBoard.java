package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.card.DevelopmentCard;
import it.polimi.ingsw.ps31.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.gameThings.MilitaryStrength;
import it.polimi.ingsw.ps31.gameThings.PointResource;
import it.polimi.ingsw.ps31.player.Player;

import java.util.List;

/**
 * Created by Francesco on 15/05/2017.
 */
public class PersonalBoard {
    private final Player player;
    private final List<PersonalBoardCardList> personalBoardCardList=null;
    private final static int MAXCARDSPACELIST=4;



    /* Constructor */
    public PersonalBoard(Player player, List<PointResource[]> pointResourceRequired) {
        CardColor[] cardColor= {CardColor.YELLOW,CardColor.GREEN,CardColor.PURPLE,CardColor.BLUE};
        this.player = player;
        for(int i=0; i<MAXCARDSPACELIST;i++){
            personalBoardCardList.add(new PersonalBoardCardList(cardColor[i]));
            personalBoardCardList.get(i).setExtraResourceRequired(pointResourceRequired.get(i));
        }

    }

    /* Getters & Setters */
    public Player getPlayer()
    {
        return this.player;
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
        for(int i=0; i<MAXCARDSPACELIST;i++){
            if(personalBoardCardList.get(i).getCardColor().equals(card.getCardColor())){
                personalBoardCardList.get(i).addCard(card);
            }
        }
    }
}
