package it.polimi.ingsw.ps31.server.message;


/**
 * Created by giulia on 01/06/2017.
 */
public interface Visitable {
    void accept(Visitor visitor);
}
