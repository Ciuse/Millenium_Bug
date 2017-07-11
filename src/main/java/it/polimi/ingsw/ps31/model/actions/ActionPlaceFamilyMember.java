package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 26/05/2017.
 *
 * Azione generica di piazzamento di un famigliare
 * Contiene una lista di spazi azioni proibiti al giocatore derivanti da
 * malus vari (all' inizio sarà vuota)
 *
 * Le due azioni di piazzamento sono state divise per gestire meglio i controlli in quanto
 * le due azioni se pur simili avranno controlli diversi.
 */
public abstract class ActionPlaceFamilyMember extends Action{
    private boolean used = false;
    protected FamilyMember familyMember = null;
    protected List<Integer> defaultDenyActionSpaces;

    /* Constructor */
    public ActionPlaceFamilyMember(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
        this.defaultDenyActionSpaces= new ArrayList<>();
    }

    /* Getters & Setters */
    public void setFamilyMember(FamilyMember familyMember)
    {
        this.familyMember = familyMember;
    }

    public void resetFamilyMember()
    {
        this.familyMember = null;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    /* Modifiers */
    public void addDefaultDenyActionSpace(List<Integer> actionSpaceId) {

        for (Integer idToAdd : actionSpaceId
                ) {
            boolean found = false;
            for (Integer idOld : defaultDenyActionSpaces
                    ) {
                if (idToAdd.equals(idOld)) {
                    found = true;
                }
            }
            if (!found) {
                defaultDenyActionSpaces.add(idToAdd);
            }
        }
    }

}
