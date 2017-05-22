package it.polimi.ingsw.ps31.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 21/05/2017.
 */
public class EffectList {
    private List<Effect> effectList = new ArrayList<>();

    /* Constructor */
    public EffectList(List<Effect> effectList) {
        this.effectList = effectList;
    }
    public EffectList(){}

    /*Getters & Setters*/
    public void addSpecificEffect(Effect effect){
        this.effectList.add(effect);
    }

    public List<Effect> getEffectList(){
        return new ArrayList<>(this.effectList);
    }

}
