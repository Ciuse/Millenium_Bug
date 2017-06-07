package it.polimi.ingsw.ps31.server.message;

/**
 * Created by giulia on 07/06/2017.
 */
public class MexToPrint implements MexVisitable {

    @Override
    public void accept(MexVisitor mexVisitor) {
        mexVisitor.visit(this);
    }
}
