package it.polimi.ingsw.ps31.messageMV;

/**
 * Created by Giuseppe on 02/06/2017.
 */
public class MVAskName implements MVVisitable {

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}
