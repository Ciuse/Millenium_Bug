package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Classe che raccoglie tutti gli action space della zone del mercato
 */
public class Market {

    private List<ActionSpace> actionSpaceList = new ArrayList<>();
    private int numberOfActionSpace=0;

    public Market(){
    }

    public Market(List<ActionSpace> actionSpaceList){   //lo creo già con tutta la lista pronta e setto il numero degli spazi in base a quanto era grande la lista
        this.actionSpaceList = actionSpaceList;
        numberOfActionSpace=actionSpaceList.size();
    }
    public void add2PlayerMarketSpace(EffectList effectList1, EffectList effectList2){
        if(numberOfActionSpace==0) {

            this.actionSpaceList.add(numberOfActionSpace,new ActionSpace(1, 1, effectList1 ));
            numberOfActionSpace++;
        }
        if(numberOfActionSpace==1) {
            this.actionSpaceList.add(numberOfActionSpace,new ActionSpace(1, 1, effectList2 ));
            numberOfActionSpace++;
        }

    }
    public void add4PlayerMarketSpace(EffectList effectList3, EffectList effectList4){    //aggiunge gli spazi 3 e 4 del mercato
        if(numberOfActionSpace==2) {
            this.actionSpaceList.add(numberOfActionSpace,new ActionSpace(1, 1, effectList3 ));
            numberOfActionSpace++;
        }
        if(numberOfActionSpace==3) {
            this.actionSpaceList.add(numberOfActionSpace,new ActionSpace(1, 1, effectList4 ));
            numberOfActionSpace++;
        }
    }
    public void remove4PlayerActionSpace(){                     //rimuove gli ultimi 2 spazi del mercato
        if(numberOfActionSpace==4) {
            this.actionSpaceList.remove(numberOfActionSpace);
            numberOfActionSpace--;
            this.actionSpaceList.remove(numberOfActionSpace);
            numberOfActionSpace--;
        }
    }

    public List<ActionSpace> getActionSpaceList(){
        return actionSpaceList;
    }
}
