package it.polimi.ingsw.ps31.model.gameResource;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 09/05/2017.
 *
 * Classe astratta che rappresenta le risorse generiche
 */
public abstract class Resource {
    /**
     * valore associato alla risorsa
     */
    private int value;

    public Resource(int value) {
        this.value = value;

    }

    public Resource() {
    }

    /**
     * aggiunge al player la risorsa nella sua lista di risorse
     */
    public void addResource(Player player) {
        player.addResources(this);
    }

    /**
     * aggiunge al player la risorsa nella sua lista di risorse temporanee
     */
    public void addTempResource(Player player) {
        player.addTempResources(this);
    }

    public void addValue(int value) {

        this.value = this.value + value;

    }

    public void subValue(int value) {
        this.value = this.value - value;
    }

    /**
     * simile alla sottrazione ma impedisce di andare a valori negativi in quanto uno sconto non può farti perdere risorse
     */
    public void discountValue(int value) {
        if (value >= 0)
            if (this.value >= value) {
                this.value = this.value - value;
            } else {
                this.value = 0;
            }
    }

    public void multiplyValue(int factor) {

        this.value = this.value * factor;
    }

    public abstract Resource cloneResource(Resource resource);

    public abstract int getPhysicalResourceValue();

    public abstract int getPointResourceValue();

    /*Getters*/
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + this.getValue() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return value == resource.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    public boolean lessOrEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return value <= resource.value;
    }
}
