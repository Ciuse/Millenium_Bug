package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Board.TowerCardSpace;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionTakeCard extends Action {
    private TowerCardSpace towerCardSpace = null;
    private CardColor cardColor = null;

    public ActionTakeCard(Player player)
    {
        super(player);
    }

    public void setCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }

    public void setCardColor(CardColor cardColor)
    {
        this.cardColor = cardColor;
    }

    public void resetCardSpace()
    {
        this.towerCardSpace = null;
    }

    public void resetCardColor()
    {
        this.cardColor = null;
    }

    public TowerCardSpace getCardSpace()
    {
        return this.towerCardSpace;
    }

    @Override
    public void activate()
    {
        //this.player.addDevelopmentCard(this.towerCardSpace.getCard());
    }
}
