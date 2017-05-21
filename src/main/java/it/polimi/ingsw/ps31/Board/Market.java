package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.Effect.GetResource;
import it.polimi.ingsw.ps31.GameThings.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class Market {

    private List<ActionSpace> actionBox = new ArrayList<ActionSpace>();
    private int numberOfActionSpace=0;

    public Market()
    {
        //Aggiungo i primi due activate box del mercato (che sono sempre disponibili)           io gli metterei nel main della creazione del gioco e lascerei questo costruttore vuoto
        //this.actionBox.add(new ActionSpace(1, 1, null));    //TODO: specificare effetto immediato
        //this.actionBox.add(new ActionSpace(1, 1, null));    //TODO: specificare effetto immediato
        //TODO: creazione degli altri spazi in base al numero di giocatori
    }

    public Market(List<ActionSpace> actionSpaceList){   //lo creo già con tutta la lista pronta e setto il numero degli spazi in base a quanto era grande la lista
        this.actionBox = actionSpaceList;
        numberOfActionSpace=actionSpaceList.size();
    }
//    public void add2PlayerMarketSpace(ActionSpace actionSpace){     // TODO fare tutto ciò nella creazione del file aggiunge i primi 2 spazi del mercato
//        if(numberOfActionSpace==0) {
//            ResourceList resourceList = new ResourceList();
//            resourceList.addSpecificResource(new Coin(5));
//            GetResource effect = new GetResource(resourceList);
//            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effect ));
//            numberOfActionSpace++;
//        }
//        if(numberOfActionSpace==1) {
//            ResourceList resourceList = new ResourceList();
//            resourceList.addSpecificResource(new Servant(5));
//            GetResource effect = new GetResource(resourceList);
//            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effect ));
//            numberOfActionSpace++;
//        }
//
//    }
//    public void add4PlayerMarketSpace(ActionSpace actionSpace){    //aggiunge gli spazi 3 e 4 del mercato
//        if(numberOfActionSpace==2) {
//            ResourceList resourceList = new ResourceList();
//            resourceList.addSpecificResource(new MilitaryStrength(3));
//            resourceList.addSpecificResource(new Coin(2));
//            GetResource effect = new GetResource(resourceList);
//            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effect ));
//            numberOfActionSpace++;
//        }
//        if(numberOfActionSpace==3) {
//            ResourceList resourceList = new ResourceList();
//            resourceList.addSpecificResource(new CouncilPrivilege(2, true));
//            GetResource effect = new GetResource(resourceList);
//            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effect ));
//            numberOfActionSpace++;
//        }
//    }
    public void remove4PlayerActionSpace(){                     //rimuove gli ultimi 2 spazi del mercato
        if(numberOfActionSpace==4) {
            this.actionBox.remove(numberOfActionSpace);
            numberOfActionSpace--;
            this.actionBox.remove(numberOfActionSpace);
            numberOfActionSpace--;
        }
    }

    public List<ActionSpace> getActionBox (){
        return new ArrayList<>(actionBox);
    }
}
