package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.ClientNetworkingThread;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

/**
 * Created by Francesco on 24/06/2017.
 */
public class ViewMessage extends NetworkingMessage{
    private View view;

    public ViewMessage(View view)
    {
        this.view = view;
    }

    public void update(ClientNetworkingThread clientNetworkingThread) {
        System.out.println("View spacchettata");
        clientNetworkingThread.setView(this.view);
        System.out.println("View aggiunta");

    }

    @Override
    public void update(NetworkingThread networkingThread) {
        if ( networkingThread.getClass() == ClientNetworkingThread.class )
            this.update(networkingThread);
        else
            return;
    }
}
