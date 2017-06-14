package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateEffect;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.List;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateViewDevelopmentCard {
    private String cardName = null;
    private int cardId = -1;
    private  CardColor cardColor = null;
    private List<StateEffect> stringImmediateEffects = null;
    private List<StateEffect> stringPermanentEffects = null;
    private List<String> stringCosts = null;

}
