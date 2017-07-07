package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 07/07/2017.
 */
public class VCLeaderToCopyChoice extends VCVisitable {
    private final int leaderIdToReplace;
    private final int leaderIdToCopy;


    public VCLeaderToCopyChoice(PlayerId viewId, int leaderIdToReplace, int leaderIdToCopy) {
        super(viewId);
        this.leaderIdToReplace = leaderIdToReplace;
        this.leaderIdToCopy = leaderIdToCopy;
    }

    public int getLeaderIdToReplace() {
        return leaderIdToReplace;
    }

    public int getLeaderIdToCopy() {
        return leaderIdToCopy;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
