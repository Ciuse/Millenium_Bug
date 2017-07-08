package it.polimi.ingsw.ps31.messages;

/**
 * Created by Francesco on 13/06/2017.
 */
public abstract class GenericMessage {
    protected boolean isViewUpdate = true;

    public final boolean isViewUpdate(){return isViewUpdate;}

    public abstract ConcreteEnvelope wrap();

}
