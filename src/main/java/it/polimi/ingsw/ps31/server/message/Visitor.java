package it.polimi.ingsw.ps31.server.message;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public interface Visitor {
    public void visit(Messaggio1 messaggio1);
    public void visit(Messaggio2 messaggio1);

}
