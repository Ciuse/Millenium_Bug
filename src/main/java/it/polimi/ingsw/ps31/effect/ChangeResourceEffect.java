package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

import java.util.List;
import java.util.Scanner;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChangeResourceEffect extends Effect {
    private final List<ResourceList> resourceToPayList; // alcune carte hanno una doppia scelta sulle risorse da pagare per ottenere nuove risorse
    private final List<ResourceList> resourceToGainList; // alcune carte potrebbero avere una doppia scelta di risorse da guadagnare

    public ChangeResourceEffect(List<ResourceList> resourceToPayList, List<ResourceList> resourceToGainList) {

        this.resourceToPayList = resourceToPayList;
        this.resourceToGainList = resourceToGainList;
    }

    public List<ResourceList> getResourceToPayList() {
        return resourceToPayList;
    }

    public List<ResourceList> getResourceToGainList() {
        return resourceToGainList;
    }

    //TODO spostare la richiesta di inserimento alla view
    @Override
    public void activate(Player player) {
        System.out.println("Giocatore scegli quale risorsa vuoi cambiare: ");
        Scanner scanner =new Scanner(System.in);
        int listChoose= scanner.nextInt();
        player.getPlayerActionSet().payResources(resourceToPayList.get(listChoose));
        player.getPlayerActionSet().getResources(resourceToGainList.get(listChoose));
    }
}
