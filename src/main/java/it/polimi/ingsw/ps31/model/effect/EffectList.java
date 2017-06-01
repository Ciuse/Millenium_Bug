package it.polimi.ingsw.ps31.model.effect;

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
    public EffectList(Effect effect){
        this.effectList.add(effect);
    }
    public EffectList(){}


    //metodi tipici delle liste
    public void add(Effect effect){
        this.effectList.add(effect);
    }
    public Effect remove(int index){
        return this.effectList.remove(index);
    }
    public int size(){
        return this.effectList.size();
    }
    public Effect get(int index){
        return this.effectList.get(index);
    }
    public void clear(){
        this.effectList.clear();
    }
    /*Getters & Setters*/
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

    @Override
    public int hashCode() {
        return effectList != null ? effectList.hashCode() : 0;
    }
}
