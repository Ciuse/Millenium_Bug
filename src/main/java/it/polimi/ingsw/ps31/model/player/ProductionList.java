package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceIfActiveEffect;
import it.polimi.ingsw.ps31.model.effect.HarvestEffect;
import it.polimi.ingsw.ps31.model.effect.ProductionEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 *
 * Rappresenta la lsita con tutti gli effetti permanenti di tipo Production di un giocatore
 *
 * @see List
 * @see ProductionList
 * @see Player
 */
public class ProductionList extends  HarvestProductionList {
    private List<ProductionEffect> effectList = new ArrayList<>();

    /* Constructor */
    public ProductionList(Player player, ProductionEffect firstProduction) {
        super(player);
        this.addEffect(firstProduction);
    }

    /* Class Methods */

    public void addEffect(ProductionEffect effectToAdd) {
        this.effectList.add(effectToAdd);
    }

    public List<ProductionEffect> getEffectList() {
        return effectList;
    }


    /**
     * Attivazione della lista: attivo tutti gli effetti sul giocatore se e solo se il loro valore di attivazione è inferiore a quello del dado
     * Durante l'attivazione: chiedo al giocatore se vuole attivare o no gli effetti che prevedono un costo
     * Dopo l'attivazione: aggiungo le risorse provenienti dalla lista temporale, su cui gli effetti si sono attivati, al player
     *
     * @param diceValue valore del dado/carta con cui si è attivata la produzione
     */
    @Override
    public void activate(int diceValue) {
        for (ProductionEffect currentEffect : effectList) {
            //non chiedo nulla, questo tipo di effetto va attivato per forza
            if (diceValue >= currentEffect.getProductionActionValue()) {
                if (currentEffect.getGetResourceEffect() != null) {
                    currentEffect.getGetResourceEffect().activate(super.getPlayer());
                }
                //non chiedo nulla, questo tipo di effetto va attivato per forza
                if (currentEffect.getGetResourceEffectFromCard() != null) {
                    currentEffect.getGetResourceEffectFromCard().activate(super.getPlayer());
                }
                //chiedo al player se vuoile attivare questo tipo di effetto o no
                if (currentEffect.getChangeResourceEffect() != null) {
                    String string = " Vuoi attivare questo effetto?";
                    super.getPlayer().getModel().notifyViews(new MVAskChoice(super.getPlayer().getPlayerId(), string, new ChoiceIfActiveEffect(currentEffect.getChangeResourceEffect().getCardId())));
                    boolean choice = super.getPlayer().getModel().getModelChoices().waitActiveEffect();
                    if (choice) {
                        currentEffect.getChangeResourceEffect().activate(super.getPlayer());
                    }
                }
            }
            super.getPlayer().addTempResoucesToPlayerResources();
        }

    }
}
