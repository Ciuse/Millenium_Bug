package it.polimi.ingsw.ps31.GameThings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Giuseppe on 10/05/2017.
 */
public class ResourceList {
    private List<Resource> resourceList = new ArrayList<>(7);           //TODO da provare/verificare come funziona

    /* Constructor */
    public ResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
    public ResourceList(){}

    /*Getters & Setters*/
    public void setCoin() {
        System.out.println("Inserisci il valore delle monete: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Coin coin= new Coin(0);
        coin.addValue(value);
        if (coin.getValue()!=0) {
            this.resourceList.add(coin);
        }
    }
    public void setWood() {
        System.out.println("Inserisci il valore dei legni: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Wood wood= new Wood(0);
        wood.addValue(value);
        if (wood.getValue()!=0) {
            this.resourceList.add(wood);
        }
    }
    public void setStone() {
        System.out.println("Inserisci il valore delle pietre: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Stone stone= new Stone(0);
        stone.addValue(value);
        if (stone.getValue()!=0) {
            this.resourceList.add(stone);
        }
    }
    public void setServant() {
        System.out.println("Inserisci il valore dei servitori: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        Servant servant= new Servant(0);
        servant.addValue(value);
        if (servant.getValue()!=0) {
            this.resourceList.add(servant);
        }
    }
    public void setMilitaryStrength() {
        System.out.println("Inserisci il valore normale dei punti militari: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        System.out.println("Inserisci il valore minimo di punti militari richiesti : ");
        Scanner scanner1 = new Scanner(System.in);
        int valueRequest = scanner1.nextByte();
        MilitaryStrength militaryStrength= new MilitaryStrength(0,0);
        militaryStrength.addValue(value);
        militaryStrength.setValueRequest(valueRequest);
        if (militaryStrength.getValue()!=0) {
            this.resourceList.add(militaryStrength);
        }
    }
    public void setFaithPoint() {
        System.out.println("Inserisci il valore dei punti fede: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        FaithPoint faithPoint= new FaithPoint(0);
        faithPoint.addValue(value);
        if (faithPoint.getValue()!=0) {
            this.resourceList.add(faithPoint);
        }
    }
    public void setVictoryPoint() {
        System.out.println("Inserisci il valore dei punti vittoria: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        VictoryPoint victoryPoint= new VictoryPoint(0);
        victoryPoint.addValue(value);
        if (victoryPoint.getValue()!=0) {
            this.resourceList.add(victoryPoint);
        }
    }
    public void setCouncilPrivilege() {
        System.out.println("Inserisci il numero dei privilegi del consiglio: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextByte();
        System.out.println("sono diversi?: ");
        boolean different= scanner.nextBoolean();
        CouncilPrivilege councilPrivilege= new CouncilPrivilege(0, false);
        councilPrivilege.addValue(value);
        councilPrivilege.setDifferent(different);
        if (councilPrivilege.getValue()!=0) {
            this.resourceList.add(councilPrivilege);
        }
    }

    public void addSpecificResource(Resource resource){
        this.resourceList.add(resource);
    }


    public List<Resource> getResourceList(){
        return new ArrayList<>(this.resourceList);
    }

}
