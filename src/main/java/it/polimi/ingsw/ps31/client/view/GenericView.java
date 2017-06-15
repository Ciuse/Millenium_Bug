package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.ClientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.NetworkingThread;

/**
 * Created by Francesco on 14/06/2017.
 */
public abstract class GenericView {

    public abstract void switchOn();
    public abstract void attachNetworkInterface(ClientNetworkInterface clientNetworkInterface);
}
