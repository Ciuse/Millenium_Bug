package it.polimi.ingsw.ps31.server.message;

/**
 * Created by Giuseppe on 02/06/2017.
 */
public class Messaggio1 implements Visitable {

    @Override
    public void accept(it.polimi.ingsw.ps31.server.message.Visitor visitor) {
        visitor.visit(this);
    }
}
