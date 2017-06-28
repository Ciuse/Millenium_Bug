package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientMessageHistory;
import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;

/**
 * Created by Francesco on 28/06/2017.
 */
public class ClientViewThread /*extends Thread*/ {
    private ClientMessageHistory clientMessageHistory;
    private ClientNetworkingThread clientNetworkingThread;  //TODO metodo per mandare messaggi dalla view al model
    private View view;
    private final TypeOfView typeOfView;

    public ClientViewThread(TypeOfView typeOfView)
    {
        this.typeOfView = typeOfView;
        this.clientMessageHistory = new ClientMessageHistory();
    }

//    public void run()
//    {
//
//    }

    public ClientMessageHistory initView(ViewMessage viewMessage)
    {
        switch ( typeOfView )
        {
            case CLI: this.view = new CmdLineView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
            case GUI: this.view = new GuiView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
        }
        clientMessageHistory.addView(view);

        return clientMessageHistory;
    }
}
