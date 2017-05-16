package it.polimi.ingsw.ps31.GameThings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    public void setCoin(Coin coin) {
        System.out.println("Inserisci il valore delle monete");
        if (coin.getValue()!=0) {
            this.resource.add(coin);
        }
    }
    public void setWood(Wood wood) {
        System.out.println("Inserisci il valore delle monete del legno");
        if (wood.getValue()!=0) {
            this.resource.add(wood);
        }
    }
    public void setstone(Stone stone) {
        System.out.println("Inserisci il valore delle pietre");
        if (stone.getValue()!=0) {
            this.resource.add(stone);
        }
    }
    public void setServant(Servant servant) {
        System.out.println("Inserisci il valore dei servitori");
        if (servant.getValue()!=0) {
            this.resource.add(servant);
        }
    }
    public void setMilitaryStrength(MilitaryStrength militaryStrength) {
        System.out.println("Inserisci il valore dei punti militari");
        if (militaryStrength.getValue()!=0) {
            this.resource.add(militaryStrength);
        }
    }

    public void setFaithPoint(FaithPoint faithPoint) {
        System.out.println("Inserisci il valore dei punti fede");
        if (faithPoint.getValue()!=0) {
            this.resource.add(faithPoint);
        }
    }
    public void setVictoryPoint( VictoryPoint victoryPoint) {
        System.out.println("Inserisci il valore dei punti vittoria");
        if (victoryPoint.getValue()!=0) {
            this.resource.add(victoryPoint);
        }
    }

    public void setGenericResource(Resource resource){
        if (resource.getValue()!=0) {
            this.resource.add(resource);
        }
    }
    public void createList() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, InvocationTargetException {
        String typeRead;
        int i=0;
        System.out.println("inserisci il tipo di risorsa da aggiungere alla lista");
        Scanner in = new Scanner(System.in);
        typeRead=in.next();
        if(typeRead!=Resource.class.getSimpleName()){
            System.out.println("inserisci val-ore risorsa");
            Object resource= Class.forName(typeRead).newInstance();
            int resourceRead = in.nextByte();
            Class risorsa = Class.forName(typeRead);
            Constructor con = risorsa.getConstructor(int.class);
            Object obj = con.newInstance(resourceRead);
            this.setGenericResource((Resource) obj);
            //TODO PROVARLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        }
    }

    public List<Resource> getResourceList(){
        return new ArrayList<>(this.resource);
    }

}
