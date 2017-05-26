package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.ActionControls.*;
import it.polimi.ingsw.ps31.Player.Player;
import it.polimi.ingsw.ps31.Player.PlayerActionSet;

/**
 * Created by Francesco on 25/05/2017.
 */
public class ActionControlSet {
    private final CardRequirementsControl cardRequirementsControl;
    private final DiceValueControl diceValueControl;
    private final OccupiedActionSpaceControl occupiedActionSpaceControl;
    private final PayResources payResources;
    private final PlacedFamilyMemberControl placedFamilyMemberControl;
    private final PlayerCardNumberControl playerCardNumberControl;

    /* Constructor */
    public ActionControlSet(Player player)
    {
        this.cardRequirementsControl = new CardRequirementsControl(player);
        this.diceValueControl = new DiceValueControl(player);
        this.occupiedActionSpaceControl = new OccupiedActionSpaceControl(player);
        this.payResources = new PayResources(player);
        this.placedFamilyMemberControl = new PlacedFamilyMemberControl(player);
        this.playerCardNumberControl = new PlayerCardNumberControl(player);
    }

    /**/
}
