package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class VCColorChoice extends VCVisitable {
    private  PlayerColor playerColor;

    public VCColorChoice(PlayerId viewId, PlayerColor playerColor) {
        super(viewId);
        this.playerColor = playerColor;
    }

    public VCColorChoice(PlayerId viewId) {
        super(viewId);
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
