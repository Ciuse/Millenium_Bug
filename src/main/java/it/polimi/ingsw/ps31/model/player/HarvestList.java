package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.effect.HarvestEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 27/05/2017.
 *
 * Rappresenta la lsita con tutti gli effetti permanenti di tipo Harvest di un giocatore
 *
 * @see List
 * @see HarvestEffect
 * @see Player
 */
public class HarvestList extends HarvestProductionList {
    private List<HarvestEffect> effectList = new ArrayList<>();

    /* Constructor */
    public HarvestList(Player player, HarvestEffect firstHarvest) {
        super(player);
        this.addEffect(firstHarvest);
    }

    /* Class Methods */
    public void addEffect(HarvestEffect effectToAdd) {
        this.effectList.add(effectToAdd);
    }

    /**
     * Attivazione della lista: attivo tutti gli effetti sul giocatore se e solo se il loro valore di attivazione è inferiore a quello del dado
     * Dopo l'attivazione: aggiungo le risorse provenienti dalla lista temporale, su cui gli effetti si sono attivati, al player
     *
     * @param diceValue valore del dado/carta con cui si è attivato il raccolto
     */
    @Override
    public void activate(int diceValue) {
        for (HarvestEffect currentEffect : effectList) {
            if (diceValue >= currentEffect.getHarvestActionValue())

                currentEffect.getGetResourceEffect().activate(super.getPlayer());
        }
        super.getPlayer().addTempResoucesToPlayerResources();
    }
}
