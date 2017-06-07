package it.polimi.ingsw.ps31.server.message;

/**
 * Created by Giuseppe on 02/06/2017.
 */
public class AskColor implements MexVisitable {
    @Override
    public void accept(MexVisitor mexVisitor) {
        mexVisitor.visit(this);
    }
}
