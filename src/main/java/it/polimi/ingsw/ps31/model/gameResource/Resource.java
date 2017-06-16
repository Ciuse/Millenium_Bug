package it.polimi.ingsw.ps31.model.gameResource;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Resource {
    private int value;
    private int malusValue;

    public Resource(int value) {
//        if(value > 0)
//            this.value=value;
//        else
//        {
//            //TODO: eccezione
//            //this.value = Math.abs(value);
//        }

    }


    public void addResource(Player player){
        player.addResources(this);
    }

    public void addTempResource(Player player){
        player.addTempResources(this);
    }

    public void addValue(int value)
    {
        if (value >= 0)
            this.value = this.value+value;
        else
            this.subValue(Math.abs(value));
    }

    public void subValue(int value)
    {
        if(value >= 0 )
            if ( this.value >= value)
                this.value = this.value-value;
            else
            {
                //TODO: gestire (eccezione?)
            }
        else
            this.addValue(Math.abs(value));
    }

    public void multvalue(int factor){

        this.value=this.value*factor;
    }

/*Getters*/
    public int getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"["+this.getValue()+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return value == resource.value;
    }

    public abstract int getPhysicalResourceValue();
    public abstract int getPointResourceValue();



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

    public final String toStringName() {
        return this.getClass().getSimpleName();
    }

}
