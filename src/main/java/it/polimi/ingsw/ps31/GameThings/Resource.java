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

        this.value=this.value+value;

    }
    public static int insertValue() {
        System.out.println("Inserisci il valore della risorsa:");
        Scanner scanner =new Scanner(System.in);
        int value= 0;
        try {
            value = scanner.nextByte();
        } catch (InputMismatchException e) {
            System.out.println("Eccezione catturata (valore non numerico)");
            insertValue();
        }
        Resource stone= new Stone(0);
        stone.addValue(value);
        return value;
    }
    public void controlValueInsert(int value){
        if(value<0);
    }

    public int getValue(){
        return this.value;
    }



    public final String toString() {
        return this.getClass().getSimpleName()+"["+this.getValue()+"]";
    }


}
