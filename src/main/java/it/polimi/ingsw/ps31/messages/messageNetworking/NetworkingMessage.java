package it.polimi.ingsw.ps31.messages.messageNetworking;


import it.polimi.ingsw.ps31.messages.GenericMessage;

/**
 * Created by Francesco on 21/06/2017.
 * Messaggio destinato all'infrastruttura di networking e non all'architettura MVC
 */
public abstract class NetworkingMessage extends GenericMessage {

    /* Constructor */
    public NetworkingMessage()
    {
        super.isViewUpdate = false;
    }
}
