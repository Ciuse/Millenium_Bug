package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Player.FamilyMember;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class Production extends ActionSpace {

    public final void activate(FamilyMember member)
    {
        //TODO: richiamare il metodo di attivazione della produzione del giocatore
    }

    public Production(int diceCost, int familyMemberLimit)
    {
        super(diceCost, familyMemberLimit, null);    //TODO: specificare l'effetto immediato
    }
}
