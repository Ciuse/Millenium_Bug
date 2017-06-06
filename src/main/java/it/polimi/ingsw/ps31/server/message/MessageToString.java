package it.polimi.ingsw.ps31.server.message;

/**
 * Created by giulia on 06/06/2017.
 */
public class MessageToString implements Visitable {
    Object objectToString ;

    public MessageToString(Object objectToString) {
        this.objectToString = objectToString;
    }

    public Object getObjectToString() {
        return objectToString;
    }

    public void setObjectToString(Object objectToString) {
        this.objectToString = objectToString;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
