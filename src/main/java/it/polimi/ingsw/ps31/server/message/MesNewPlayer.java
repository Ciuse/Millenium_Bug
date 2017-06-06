package it.polimi.ingsw.ps31.server.message;

/**
 * Created by giulia on 06/06/2017.
 */
public class MesNewPlayer implements Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
