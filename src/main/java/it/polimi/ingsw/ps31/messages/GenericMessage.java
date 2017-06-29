package it.polimi.ingsw.ps31.messages;

import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.networking.DeliverableMessageType;

/**
 * Created by Francesco on 13/06/2017.
 */
public abstract class GenericMessage {

    public abstract ConcreteEnvelope wrap();

}
