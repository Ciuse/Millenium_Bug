package it.polimi.ingsw.ps31.messageMV;

/**
 * Created by Giuseppe on 02/06/2017.
 */
public class MVAskColor implements MVVisitable {
    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}