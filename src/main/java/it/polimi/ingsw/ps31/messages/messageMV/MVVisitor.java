package it.polimi.ingsw.ps31.messages.messageMV;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public interface MVVisitor {
    public void visit(MVStringToPrint mvStringToPrint);
    public void visit(MVUpdateState mvUpdateState);
    public void visit(MVAskChoice mvAskChoice);
}
