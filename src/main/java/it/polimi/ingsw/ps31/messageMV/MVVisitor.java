package it.polimi.ingsw.ps31.messageMV;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public interface MVVisitor {
    public void visit(MVAskColor MVAskColor);
    public void visit(MVAskName MVAskName);
    public void visit(MVToPrint mvToPrint);
    public void visit(MVStateInfo mvStateInfo);
    public void visit(MVAskChoice mvAskChoice);
}
