package it.polimi.ingsw.ps31.messages.messageVC;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class VCStartLeaderChoice implements VCVisitable {
    private int leaderId;

    public VCStartLeaderChoice(int leaderId) {
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
