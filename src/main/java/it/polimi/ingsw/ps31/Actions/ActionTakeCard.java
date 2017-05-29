package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Board.TowerCardSpace;
import it.polimi.ingsw.ps31.Card.DevelopmentCard;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Player.Player;

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
        if ( towerCardSpace == null)
        {
            //TODO: gestire
        }else
        {
            DevelopmentCard takenCard = this.towerCardSpace.takeCard();

            this.player.addDevelopmentCard(takenCard);
            //takenCard. TODO: aggiornare quando avrò i metodi per attivare gli effetti delle carte
        }



    }
}
