package it.polimi.ingsw.ps31.messageVC;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class VCLeaderChoice implements VCVisitable {
    int leaderId;

    public VCLeaderChoice(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getLeaderId() {
        return leaderId;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
