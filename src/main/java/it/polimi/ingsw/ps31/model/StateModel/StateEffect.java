package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.Effect;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateEffect {
    private  String nameEffect;
    private  String resourceToPay;
    private  String resourceToGain;
    private  CardColor cardColor;
    private  int diceValue;
    private String resourceDiscount;
    private String requiredResource;
    private StateEffect stateEffect;


    public StateEffect(Effect effect) {

    }
}
