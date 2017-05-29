package it.polimi.ingsw.ps31.GameThings;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Resource {
    private int value;

    public Resource(int value){
        this.value=value;

    }

    public void addValue(int value){
        this.value = this.value+value;
    }
    public void subValue(int value)
    {
        if ( this.value >= value)
            this.value = this.value-value;
        else
        {
            //TODO: gestire (eccezione?)
        }
    }

    public void multvalue(int factor){

        this.value=this.value*factor;
    }

/*Getters*/
    public int getValue(){
        return this.value;
    }

    @Override
    public final String toString() {
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
