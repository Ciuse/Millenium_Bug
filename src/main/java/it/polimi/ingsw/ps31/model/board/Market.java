package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class Market {

    private List<ActionSpace> actionBox = new ArrayList<>();
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
    public void add2PlayerMarketSpace(EffectList effectList1, EffectList effectList2){     // TODO fare tutto ciò nella creazione del file aggiunge i primi 2 spazi del mercato
        if(numberOfActionSpace==0) {

            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effectList1 ));
            numberOfActionSpace++;
        }
        if(numberOfActionSpace==1) {
            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effectList2 ));
            numberOfActionSpace++;
        }

    }
    public void add4PlayerMarketSpace(EffectList effectList3, EffectList effectList4){    //aggiunge gli spazi 3 e 4 del mercato
        if(numberOfActionSpace==2) {
            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effectList3 ));
            numberOfActionSpace++;
        }
        if(numberOfActionSpace==3) {
            this.actionBox.add(numberOfActionSpace,new ActionSpace(1, 1, effectList4 ));
            numberOfActionSpace++;
        }
    }
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
