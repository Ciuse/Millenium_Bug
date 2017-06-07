package it.polimi.ingsw.ps31.server.message;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public interface MexVisitor {
    public void visit(Messaggio1 messaggio1);
    public void visit(Messaggio2 messaggio1);
    public void visit(MexStateInfo mexStateInfo);
    public void visit(MexToPrint mexToPrint);

}
