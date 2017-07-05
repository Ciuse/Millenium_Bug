package it.polimi.ingsw.ps31.model.gameResource;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 * Classe raffigurante la risorsa CouncilPrivilege
 * @see Resource
 */

public class CouncilPrivilege extends Resource {
    /**
     * booleano che indica se i CouncilPrivilege sono diversi tra di loro o uguali
     */
    private boolean different;

    public CouncilPrivilege(int value, boolean different) {
        super(value);
        this.different = different;
    }

    public boolean isDifferent() {
        return different;
    }

    public void setDifferent(Boolean different) {
        this.different = different;
    }

    @Override
    public void addTempResource(Player player) {
        player.getPlayerActionSet().chooseDifferentPrivilege(this.getValue(), this.isDifferent());
    }

    @Override
    public Resource cloneResource(Resource resource) {
        if (resource.getValue() > 1) {
            return new CouncilPrivilege(resource.getValue(), true);
        } else return new CouncilPrivilege(resource.getValue(), false);

    }

    @Override
    public void addResource(Player player) {
        player.getPlayerActionSet().chooseDifferentPrivilege(this.getValue(), this.isDifferent());
    }

    @Override
    public int getPhysicalResourceValue() {
        return 0;
    }

    @Override
    public int getPointResourceValue() {
        return 0;
    }

    @Override
    public String toString() {
        if (this.different) {
            return "CP" + "!=" + this.getValue();
        } else return "CP" + "=" + this.getValue();
    }
}
