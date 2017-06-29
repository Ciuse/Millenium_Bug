package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientMessageHistory;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

import java.awt.*;
import java.util.*;

/**
 * Created by Francesco on 11/06/2017.
 */

public class ClientNetworkingThread extends Thread {
    private ClientNetworkInterface clientNetworkInterface;
    private final ClientViewThread clientViewThread;
    private boolean closeClientNetworkingInterface = false;
    protected View view = null;
    private ClientMessageHistory clientMessageHistory;
    protected java.util.List<MVVisitable> buffer;


    /* Constructor */
    public ClientNetworkingThread(ClientNetworkInterface clientNetworkInterface, ClientViewThread clientViewThread) {

        this.buffer = new ArrayList<>();

        this.clientNetworkInterface = clientNetworkInterface;
        this.clientViewThread = clientViewThread;

        //invio al server il messaggio di connessione
        clientNetworkInterface.sendConnectionMessage();

        System.out.println("In attesa di altri giocatori. Un po' di pazienza...");

        //Rimango in attesa della view dal server
        GenericMessage msgFromServer = clientNetworkInterface.readViewMessageFromServer();
        while (msgFromServer == null) {
            System.out.println("ClientNetworkingThread : init() >Aspetto...");
            try {
                sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            msgFromServer = clientNetworkInterface.readFromServer();
        }

        System.out.println("VIEW RICEVUTA!!!");
        clientMessageHistory = clientViewThread.initView((ViewMessage)(NetworkingMessage)msgFromServer);
    }

    @Override
    public void run()
    {
        System.out.println("ClientNetworkingThread:run> Inizio ascolto socket. Booleano closeClientNetworkInterface = " + closeClientNetworkingInterface);
        while(!closeClientNetworkingInterface)
        {
            MVVisitable msgFromServer = clientNetworkInterface.readFromServer();
            if( msgFromServer != null )
                    clientMessageHistory.newMessage(msgFromServer);
            try { sleep(700); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void bufferizeMessage(MVVisitable msg) {
        buffer.add(msg);

    }

    public ClientNetworkInterface getClientNetworkInterface()
    {
        return this.clientNetworkInterface;
    }

    public void setCloseClientNetworkingInterface (boolean haveToClose)
    {
        this.closeClientNetworkingInterface = haveToClose;
    }

    public MVVisitable nextMessage()
    {
        return readMessage();
    }

    public MVVisitable readMessage()
    {
        if( buffer.isEmpty())
            return null;
        return buffer.remove(0);
    }

    public void sendMessage (VCVisitable message)
    {
        this.clientNetworkInterface.sendToServer(message);
    }


}
