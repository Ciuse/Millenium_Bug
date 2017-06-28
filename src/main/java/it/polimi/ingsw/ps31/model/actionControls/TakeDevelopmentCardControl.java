package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.PersonalBoardCardCell;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 31/05/2017.
 */
public class TakeDevelopmentCardControl extends Control {
    private DevelopmentCard developmentCard = null;
    private List<CardColor> colorListRequirementToIgnore = new ArrayList<>();

    /* Constructor */
    public TakeDevelopmentCardControl(Player player) {
        super(player);
    }

    @Override
    public String getControlStringError() {
        return "non puoi prendere la carta: "+developmentCard.getCardId()+" "+developmentCard.getName()+ "perchè non hai abbastanza risorse";
    }

    /* Setters & Getters */
    public void setDevelopmentCard(DevelopmentCard developmentCard)
    {
        this.developmentCard = developmentCard;
    }

    public DevelopmentCard getDevelopmentCard()
    {
        return this.developmentCard;
    }

    /* Resetters */
    public void resetDevelopmentCard()
    {
        this.developmentCard = null;
    }

    /* Execution Method */
    @Override
    public boolean execute() {
        //Controllo che i parametri siano settati
        if( this.developmentCard == null )
        {
            //todo eccezione
            return false;
        }

        //Controllo che il player abbia spazio nella personal board per la carta in questione
        if(player.getPlayerActionSet().getActionControlSet().getPlayerCardNumberControl().execute()) {
            //Controllo requisiti
            boolean ignoreRequirement = false;
            for (CardColor cardColor : colorListRequirementToIgnore
                    ) {
                if (developmentCard.getCardColor().equals(cardColor))
                    ignoreRequirement = true;
            }

            if (!ignoreRequirement) {
                PersonalBoardCardCell personalBoardCardCell = super.player.getPersonalBoard().getSpecificPersonalBoardCardList(developmentCard.getCardColor()).getFirstEmptyCardCell();
                if (personalBoardCardCell.getExtraPointRequired() != null
                        && !super.player.getPlayerResources().greaterThan(personalBoardCardCell.getExtraPointRequired())) {
                    resetDevelopmentCard();
                    return false;
                }
            }
        }
        resetDevelopmentCard();
        return true;
    }

    public void addColorCardToIgnore(CardColor cardColor)
    {
        this.colorListRequirementToIgnore.add(cardColor);
    }
}
