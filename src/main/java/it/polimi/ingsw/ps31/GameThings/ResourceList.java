package it.polimi.ingsw.ps31.GameThings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Giuseppe on 10/05/2017.
 */
public class ResourceList {
    private List<Resource> resource;

    public ResourceList() {
    }

    public void setCoin() {
        System.out.println("Inserisci il valore delle monete: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Resource coin= new Coin(0);
        coin.addValue(value);
        if (coin.getValue()!=0) {
            this.resource.add(coin);
        }
    }
    public void setWood() {
        System.out.println("Inserisci il valore dei legni: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Resource wood= new Wood(0);
        wood.addValue(value);
        if (wood.getValue()!=0) {
            this.resource.add(wood);
        }
    }
    public void setstone() {
        System.out.println("Inserisci il valore delle pietre: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Resource stone= new Stone(0);
        stone.addValue(value);
        if (stone.getValue()!=0) {
            this.resource.add(stone);
        }
    }
    public void setServant() {
        System.out.println("Inserisci il valore dei servitori: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Resource servant= new Servant(0);
        servant.addValue(value);
        if (servant.getValue()!=0) {
            this.resource.add(servant);
        }
    }
    public void setMilitaryStrength() {
        System.out.println("Inserisci il valore dei punti militari: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Resource militaryStrength= new MilitaryStrength(0);
        militaryStrength.addValue(value);
        if (militaryStrength.getValue()!=0) {
            this.resource.add(militaryStrength);
        }
    }

    public void setFaithPoint() {
        System.out.println("Inserisci il valore dei punti fede: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Resource faithPoint= new FaithPoint(0);
        faithPoint.addValue(value);
        if (faithPoint.getValue()!=0) {
            this.resource.add(faithPoint);
        }
    }
    public void setVictoryPoint() {
        System.out.println("Inserisci il valore dei punti vittoria: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Resource victoryPoint= new VictoryPoint(0);
        victoryPoint.addValue(value);
        if (victoryPoint.getValue()!=0) {
            this.resource.add(victoryPoint);
        }
    }


    public List<Resource> getResourceList(){
        return new ArrayList<>(this.resource);
    }

}
