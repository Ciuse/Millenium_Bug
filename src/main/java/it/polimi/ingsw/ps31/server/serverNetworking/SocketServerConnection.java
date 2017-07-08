package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.networking.ConnectionType;

import java.io.*;
import java.net.Socket;

/**
 * Created by Francesco on 09/06/2017.
 */
public class SocketServerConnection extends ServerConnectionInterface {
    private final Socket socket;
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;

    //socket lock (locket)
    private final Object readLocket = new Object();
    private final Object writeLocket = new Object();

    /* Constructor */
    public SocketServerConnection(Socket socket) throws IOException {
        super(ConnectionType.SOCKET);

        this.socket = socket;

        //Creo il reader da socket
        InputStreamReader inputStreamReader = null;
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        this.socketReader = new BufferedReader(inputStreamReader);

        //creo il writer sulla socket
        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        this.socketWriter = new BufferedWriter(socketOutStream);
    }


    @Override
    protected String readFromNetwork() throws IOException
    {
        String msgToReturn = null;
        synchronized (readLocket) {
            //System.out.println("SocketServerconnection:readFromNetwork> entro nel synch. State = "+socketReader.ready());
            if( !socket.isClosed() )
                msgToReturn = socketReader.readLine();
            //System.out.println("SocketServerconnection:readFromNetwork> esco dal synch. State = "+socketReader.ready());
        }

        return msgToReturn;
    }

    @Override
    protected void writeOnNetwork(String msg)
    {
        //System.out.println("SocketServerconnection:writeOnNetwork> msg = "+msg);

        boolean ret = false;
        try
        {

            synchronized (writeLocket) {
                //System.out.println("SocketServerconnection:writeOnNetwork> entro nel synch");

                if( !socket.isClosed() ){
                    socketWriter.write(msg + "\n");
                    socketWriter.flush();
                    DebugUtility.simpleDebugMessage("Messaggio inviato al client "+getConnectionInfo()+": "+msg);
                }
                //System.out.println("SocketServerconnection:writeOnNetwork> esco dal synch");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getConnectionInfo() {
        return socket.getInetAddress().toString() + ":" + ((Integer)socket.getPort()).toString();
        //Integer boh = socket.getPort();
        //return boh.toString();
    }

    @Override
    protected void closePhysicalConnection()
    {
        //System.out.println("SocketServerConnection: closePhysicalConnection>Chiudo socket");
        try
        {
            synchronized (readLocket) {
                //System.out.println("SocketServerConnection: closePhysicalConnection>entro nel sync read");
                synchronized (writeLocket) {
                    //System.out.println("SocketServerConnection: closePhysicalConnection>entro nel sync write ");
                    this.socketWriter.close();
                    this.socketReader.close();
                    this.socket.close();
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        //System.out.println("SocketServerConnection: closePhysicalConnection>Chiuso tutto");

    }
}
