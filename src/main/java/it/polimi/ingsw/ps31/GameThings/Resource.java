package it.polimi.ingsw.ps31.GameThings;

import java.util.InputMismatchException;
import java.util.Scanner;


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

//    public static int insertValue() {       //solo una prova
//        System.out.println("Inserisci il valore della risorsa:");
//        Scanner scanner =new Scanner(System.in);
//        int value= 0;
//        try {
//            value = scanner.nextByte();
//        } catch (InputMismatchException e) {
//            System.out.println("Eccezione catturata (valore non numerico)");
//            insertValue();
//        }
//        Resource stone= new Stone(0);
//        stone.addValue(value);
//        return value;
//    }

//TODO    public void controlValueInsert(int value){
//        if(value<0);
//    }

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

    public boolean minusOrEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return value <= resource.value;
    }

    public final String toStringName() {
        return this.getClass().getSimpleName();
    }

}
