package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateDevelopmentCard;
import it.polimi.ingsw.ps31.model.StateModel.StateEffect;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateViewDevelopmentCard {
    private String cardName = null;
    private final int cardId;
    private CardColor cardColor;
    private List<StateViewEffect> stateViewImmediateEffectList = new ArrayList<>();
    private List<StateViewEffect> stateViewPermanentEffectList = new ArrayList<>();
    private List<String> stringCosts = null;


    public StateViewDevelopmentCard(int cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardId() {
        return cardId;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public List<StateViewEffect> getStateViewImmediateEffectList() {
        return stateViewImmediateEffectList;
    }

    public List<StateViewEffect> getStateViewPermanentEffectList() {
        return stateViewPermanentEffectList;
    }

    public List<String> getStringCosts() {
        return stringCosts;
    }

    public void updateState(StateDevelopmentCard stateDevelopmentCard){
        if(stateDevelopmentCard.getCardId()!=0) {
            this.cardName = stateDevelopmentCard.getCardName();
            this.cardColor = stateDevelopmentCard.getCardColor();
            this.stringCosts = stateDevelopmentCard.getStringCosts();
            int i = 0;
            for (StateEffect stateEffect : stateDevelopmentCard.getImmediateEffectList()
                    ) {
                stateViewImmediateEffectList.add(new StateViewEffect());
            }
            for (StateViewEffect effect : stateViewImmediateEffectList
                    ) {

                effect.updateState(stateDevelopmentCard.getImmediateEffectList().get(i));
                i++;
            }

            int j = 0;
            for (StateEffect stateEffect : stateDevelopmentCard.getPermanentEffectList()
                    ) {
                stateViewPermanentEffectList.add(new StateViewEffect());
            }
            for (StateViewEffect effect : stateViewPermanentEffectList
                    ) {
                effect.updateState(stateDevelopmentCard.getImmediateEffectList().get(j));
                j++;
            }
        }
    }
}
