package it.polimi.ingsw.ps31.gameThings;

import it.polimi.ingsw.ps31.board.MarkerDisc;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class CouncilPrivilege extends Resource {
    private boolean different;

    public CouncilPrivilege(int value, boolean different) {
        super(value);
        this.different = different;
    }

    public boolean isDifferent() {
        return different;
    }

    public void setDifferent(Boolean different){
        this.different=different;
    }

    @Override
    public void addResource(Player player){
        player.getPlayerActionSet().chooseDifferentPrivilege(this.getValue(),this.isDifferent());
    }

    @Override
    public int getPhysicalResourceValue() {
        return 0;
    }

    @Override
    public int getPointResourceValue() {
        return 0;
    }
}
