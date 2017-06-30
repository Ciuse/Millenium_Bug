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
import java.util.List;

/**
 * Created by Francesco on 11/06/2017.
 */

public class ClientNetworkingThread extends Thread {
    private ClientNetworkInterface clientNetworkInterface;
    private final ClientViewThread clientViewThread;
    private boolean closeClientNetworkingInterface = false;
    protected View view = null;
    private ClientMessageHistory clientMessageHistory;
    private List<VCVisitable> bufferVC;   //inboundBuffer di uscita VCVisitable
    private List<NetworkingMessage> bufferNetworking;   //inboundBuffer di uscita NetworkingMessage

    final private Object outgoingVCBufferLock = new Object();
    final private Object outgoingNetworkingBufferLock = new Object();


    /* Constructor */
    public ClientNetworkingThread(ClientNetworkInterface clientNetworkInterface, ClientViewThread clientViewThread) {

        this.bufferVC = new ArrayList<>();
        this.bufferNetworking = new ArrayList<>();

        this.clientNetworkInterface = clientNetworkInterface;
        this.clientViewThread = clientViewThread;

        //invio al server il messaggio di connessione
        //try {sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
        clientNetworkInterface.sendConnectionMessage();

        System.out.println("In attesa di altri giocatori. Un po' di pazienza...");

        //Rimango in attesa della view dal server
        GenericMessage msgFromServer = clientNetworkInterface.readViewMessageFromServer();
        System.out.println("COSE?");


        while (msgFromServer == null) {
            System.out.println("ClientNetworkingThread : init() >Aspetto...");
            try {
                sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            msgFromServer = clientNetworkInterface.readFromServer(true);
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
            //Controllo eventuali messaggi sulla rete
            MVVisitable msgFromServer = clientNetworkInterface.readFromServer(true);
            if( msgFromServer != null )
                clientMessageHistory.newMessage(msgFromServer);
            //Controllo eventuali messaggi nei inboundBuffer
            while( !bufferVC.isEmpty() )
            {
                synchronized (this.outgoingVCBufferLock){
                    clientNetworkInterface.sendToServer(bufferVC.remove(0));
                }
            }
            while( !bufferNetworking.isEmpty() )
            {
                synchronized (this.outgoingNetworkingBufferLock){
                    clientNetworkInterface.sendToServer(bufferNetworking.remove(0));
                }
            }

            try { sleep(700); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    private void bufferizeMessage(VCVisitable msg) {
        synchronized (this.outgoingVCBufferLock) {
            bufferVC.add(msg);
        }
    }

    private void bufferizeMessage(NetworkingMessage msg) {
        synchronized (this.outgoingNetworkingBufferLock) {
            bufferNetworking.add(msg);
        }
    }

    public void sendMessage (VCVisitable message)
    {
        bufferizeMessage(message);
    }

    public void sendMessage (NetworkingMessage message)
    {
        bufferizeMessage(message);
    }

    public ClientNetworkInterface getClientNetworkInterface()
    {
        return this.clientNetworkInterface;
    }

    public void setCloseClientNetworkingInterface (boolean haveToClose)
    {
        this.closeClientNetworkingInterface = haveToClose;
    }

}
