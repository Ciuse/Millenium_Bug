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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EffectList that = (EffectList) o;

        return effectList != null ? effectList.equals(that.effectList) : that.effectList == null;
    }


}
