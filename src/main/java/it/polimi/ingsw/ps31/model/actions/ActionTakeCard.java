package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionTakeCard extends Action {
    private TowerCardSpace towerCardSpace = null;
    private CardColor cardColor = null;

    /* Constructor */
    public ActionTakeCard(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    /* Setters & Getters */
    public void setCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }
    public void setCardColor(CardColor cardColor)
    {
        this.cardColor = cardColor;
    }

    public TowerCardSpace getCardSpace()
    {
        return this.towerCardSpace;
    }
    public CardColor getCardColor()
    {
        return this.cardColor;
    }

    /* Resetters */
    public void resetCardSpace()
    {
        this.towerCardSpace = null;
    }
    public void resetCardColor()
    {
        this.cardColor = null;
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if ( towerCardSpace == null || ( cardColor != null && !cardColor.equals(towerCardSpace.getColor()) ) )
        {
            //TODO: gestire
        }else
        {
            //eseguo i controlli
            if(this.actionControlSet.takeDevelopmentCardControl(this.towerCardSpace.getCard()))
            {
                DevelopmentCard takenCard = this.towerCardSpace.takeCard();
                super.player.addDevelopmentCard(takenCard);
            }else
            {
                //todo eccezione
            }

            resetCardColor();
            resetCardSpace();
        }
    }
}
