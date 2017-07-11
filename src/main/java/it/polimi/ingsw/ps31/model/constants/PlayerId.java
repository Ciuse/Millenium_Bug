package it.polimi.ingsw.ps31.model.constants;

/**
 * Created by giulia on 07/06/2017.
 *
 * Enumeratore degli Id dei player (per identificare il player e gli oggetti di un determinato player in modo univoco)
 */
public enum PlayerId {
    ONE, TWO, THREE, FOUR;

    public int toInt(){
        return ( this.ordinal()+1 );
    }
}
