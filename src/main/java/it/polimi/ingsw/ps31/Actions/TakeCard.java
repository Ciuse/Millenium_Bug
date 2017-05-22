package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Board.Tower;
import it.polimi.ingsw.ps31.Board.TowerCardSpace;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class TakeCard extends Actions{

    private TowerCardSpace towerCardSpace = null;

    public TakeCard(Player player)
    {
        super(player);
    }

    public void setCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }

    public void resetCardSpace()
    {
        this.towerCardSpace = null;
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
