package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions.GetResources;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChangeResource extends Effect {
    private final List<ResourceList> resourceToPayList; // la carta ha delle risorse da pagare per poter ottenere nuove risorse
    private final List<ResourceList> resourceToGainList; // alcune carte hanno una doppia scelta sulle risorse da pagare per ottenere nuove risorse

    public ChangeResource(List<ResourceList> resourceToPayList, List<ResourceList> resourceToGainList) {

        this.resourceToPayList = resourceToPayList;
        this.resourceToGainList = resourceToGainList;
    }
    //TODO spostare la richiesta di inserimento alla view
    @Override
    public void activate(Player player) {
        System.out.println("Giocatore scegli quale risorsa vuoi cambiare: ");
        Scanner scanner =new Scanner(System.in);
        int listChoose= scanner.nextInt();
        if (listChoose==0){
            GetResources getResourcesToPay = new GetResources(player);
            getResourcesToPay.setResourcesToGet(resourceToPayList.get(0));
            getResourcesToPay.activate();
            GetResources getResourcesToGained = new GetResources(player);
            getResourcesToGained.setResourcesToGet(resourceToGainList.get(0));
            getResourcesToGained.activate();
        }
        if (listChoose==1){
            GetResources getResourcesToPay = new GetResources(player);
            getResourcesToPay.setResourcesToGet(resourceToPayList.get(1));
            getResourcesToPay.activate();
            GetResources getResourcesToGained = new GetResources(player);
            getResourcesToGained.setResourcesToGet(resourceToGainList.get(1));
            getResourcesToGained.activate();
        }

    }
}
