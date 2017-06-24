package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionTakeCard extends Action {
    private TowerCardSpace towerCardSpace = null;
    private CardColor cardColor = null;
    private Map<CardColor, Integer> cardDiceBonuses;

    /* Constructor */
    public ActionTakeCard(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
        this.cardDiceBonuses = new HashMap<>();
        for (CardColor cardColor : CardColor.values())
            this.cardDiceBonuses.put(cardColor, 0);
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
        if ( towerCardSpace == null || ( cardColor != null && !cardColor.equals(towerCardSpace.getTowerColor()) ) )
        {
            //TODO: gestire
        }else
        {
            //eseguo i controlli
            if(this.actionControlSet.takeDevelopmentCardControl(this.towerCardSpace.getCard()))
            {
                super.player.addDevelopmentCard(this.towerCardSpace.takeCard());
            }else
            {
                //todo eccezione
            }

            resetCardColor();
            resetCardSpace();
            super.notifyViews(new MVUpdateState("Aggiornato stato Player Personal Board",player.getPersonalBoard().getStatePersonalBoard()));
            super.notifyViews(new MVUpdateState("Aggiornato stato tower card space",towerCardSpace.getStateTowerCardBox()));
        }
    }

    /* Modifiers */
    public void addCardDiceBonus (CardColor cardColor, Integer bonus)
    {
        //bonus può anche essere negativo (es. scomuniche)
        Integer currentBonus = this.cardDiceBonuses.get(cardColor);
        cardDiceBonuses.put(cardColor, currentBonus+bonus);

    }
}
