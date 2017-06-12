package it.polimi.ingsw.ps31.messageMV;


/**
 * Created by giulia on 01/06/2017.
 */
public interface MVVisitable {
    void accept(MVVisitor mvVisitor);
}
