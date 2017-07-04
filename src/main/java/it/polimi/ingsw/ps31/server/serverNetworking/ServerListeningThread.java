package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;

import java.io.IOException;

/**
 * Created by Francesco on 25/06/2017.
 */

//Thread con il compito di rimanere in attesa di messaggi sulla socket
public class ServerListeningThread extends Thread {
    private ServerConnectionInterface serverConnectionInterface;
    private ServerInputBuffer serverInputBuffer;
    private boolean closeConnection;

    public ServerListeningThread ( ServerConnectionInterface serverConnectionInterface, ServerInputBuffer serverInputBuffer)
    {
        this.serverConnectionInterface = serverConnectionInterface;
        this.serverInputBuffer = serverInputBuffer;
        this.closeConnection = false;
    }

    public void run()
    {
        System.out.println("ServerListeningThread:run> inizio ascolto del client");
        while (!closeConnection)
        {
            VCVisitable msgFromNetwork;
            try {
                msgFromNetwork = serverConnectionInterface.readFromClient();

                serverInputBuffer.bufferizeMessage(msgFromNetwork);
            } catch (IOException e)
            {
                System.out.println("ServerListeningThread:run> client "+serverConnectionInterface.getConnectionInfo()+" disconnesso");
                closeConnection = true; //Esce dal ciclo e disconnette il client
            }

        }
        //todo: istruzioni per disconnettere il client
        //            matchTable.disconnectClient(serverConnectionInterface, networkInterface.getMatch(), this.playerId);
        serverConnectionInterface.disconnect();
        serverInputBuffer.disconnect();

    }






//    private ServerConnectionInterface serverConnectionInterface;
//    private boolean closeConnection = false;
//    private MatchTable matchTable;
//    private NetworkInterface networkInterface;
//    private PlayerId playerId;
//    protected List<VCVisitable> inboundBuffer;
//    private List<MVVisitable> outgoingMVBuffer;
//    private List<NetworkingMessage> outgoingNetworkingBuffer;
//
//
//    final private Object outgoingMVBufferLock = new Object();
//    final private Object outgoingNetworkingBufferLock = new Object();
//
//    /* Constructor */
//    public ServerListeningThread(ServerConnectionInterface serverConnectionInterface, MatchTable matchTable) {
//        this.inboundBuffer = new ArrayList<>();
//        this.serverConnectionInterface = serverConnectionInterface;
//        this.matchTable = matchTable;
//        this.playerId = null;
//
//        this.outgoingMVBuffer = new ArrayList<>();
//        this.outgoingNetworkingBuffer = new ArrayList<>();
//
//    }
//
//    public void setPlayerId(PlayerId playerId)
//    {
//        this.playerId = playerId;
//    }
//
//    public void bufferizeMessage(VCVisitable msg) {
//        System.out.println("ServerListeningThread:bufferizeMessage> Messaggio in entrata al inboundBuffer");
//        inboundBuffer.add(msg);
//    }
//
////    public void start(){}
//
//    @Override
//    public void run()
//    {
//        System.out.println("ServerListeningThread:run> thread avviato. Inizializzo connessione");
//
//        //Istruzioni di instaurazione della connessione
//        initializeConnection();
//        System.out.println("ServerListeningThread:run> ConnectionMessage:"+serverConnectionInterface.getConnectionMessage());
//
//        while (!closeConnection) {
//            //Leggo eventuali messaggi in ingresso
//            VCVisitable msgFromClient = null;
//            try {
//                System.out.println("ServerListeningThread:run>Ascolto socket");
//                msgFromClient = serverConnectionInterface.readFromClient();
//            } catch (SocketException se)
//            {
//                matchTable.disconnectClient(serverConnectionInterface, networkInterface.getMatch(), this.playerId);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (msgFromClient != null)
//                bufferizeMessage(msgFromClient);
//
//            //Scrivo eventuali messaggi nel inboundBuffer in uscita
//            System.out.println("ServerListeningThread:run> ascolto bufferNetworking");
//            while ( !outgoingNetworkingBuffer.isEmpty() )
//            {
//                System.out.println("ServerListeningThread:run> bufferNetworking non vuoto. Invio messaggio");
//                synchronized (this.outgoingNetworkingBufferLock){
//                    serverConnectionInterface.sendToClient(outgoingNetworkingBuffer.remove(0));
//                }
//            }
//            System.out.println("ServerListeningThread:run> ascolto bufferMV");
//            while ( !outgoingMVBuffer.isEmpty() )
//            {
//                System.out.println("ServerListeningThread:run> bufferMV non vuoto. Invio messaggio");
//                synchronized (this.outgoingMVBufferLock){
//                    serverConnectionInterface.sendToClient(outgoingMVBuffer.remove(0));
//                }
//            }
//
//            try {sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
//        }
//
//
//        /*while ( listenNetworkInterfaces )
//        {
//            for ( PlayerId playerId : networkInterface.getPlayerIdList() )
//                networkInterface.readFromClient(playerId);
//
//            try {
//                sleep(700);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }*/
//    }
//
//    protected final void initializeConnection() {
//        //Invio al server una stringa per comunicare che la connessione è avvenuta con successo
//        serverConnectionInterface.writeOnNetwork("Connection ok");
//        serverConnectionInterface.waitForConnectionMessage();
//    }
//
//    public void closeConnection() {
//        this.closeConnection = true;
//    }
//
//    public boolean receivedCM()
//    {
//        return ( serverConnectionInterface.receivedCM());
//    }
//
//    public ConnectionMessage getConnectionMessage()
//    {
//        return serverConnectionInterface.getConnectionMessage();
//    }
//
//    public VCVisitable readFromClient() throws IOException
//    {
//        return serverConnectionInterface.readFromClient();
//    }
//
//    public void  sendToClient(MVVisitable msg)
//    {
//        synchronized (this.outgoingMVBufferLock) {
//            outgoingMVBuffer.add(msg);
//        }
//    }
//
//    public void  sendToClient(NetworkingMessage msg)
//    {
//        synchronized (this.outgoingNetworkingBufferLock){
//            outgoingNetworkingBuffer.add(msg);
//        }
//    }
//
//
//    public void setNetworkInterface(NetworkInterface networkInterface)
//    {
//        this.networkInterface = networkInterface;
//    }
//
//    public String toString()
//    {
//        String res = "USERNAME = " + this.serverConnectionInterface.getConnectionMessage().getUsername() + "; " +
//                     "PASSWORD = " + this.serverConnectionInterface.getConnectionMessage().getPassword();
//
//        return res;
//    }
}