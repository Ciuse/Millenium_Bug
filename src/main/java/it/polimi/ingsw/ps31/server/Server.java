package it.polimi.ingsw.ps31.server;

import it.polimi.ingsw.ps31.server.serverNetworking.MatchTable;
import it.polimi.ingsw.ps31.server.serverNetworking.SocketAccepter;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Server {
//    static final int PORT = 2727;
//    protected static final int MAXCONNECTION=4;
//    protected static int connection=0;
    private static SocketAccepter connectionAccepter;
    private static MatchTable matchTable;


    public static void main(String args[])
    {
        //Crea tabella partite
        matchTable = MatchTable.getInstance();

        //Crea socket di ascolto
        connectionAccepter = SocketAccepter.createInstance(matchTable);

        //Avvio il socket
        connectionAccepter.startAccepting();

        //Spegnimento del server
        return;
    }






//    public static void main(String args[]) {
//        ServerSocket serverSocket = null;
//        Socket socket = null;
//
//        todo creare matchTable
//        todo creare connection accepter (solo socket)
//        try {
//            serverSocket = new ServerSocket(PORT);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        while (connection<MAXCONNECTION ) {
//            try {
//                System.out.println("Echo Server attesa connessione ...");
//                socket = serverSocket.accept();
//                connection++;
//                OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
//                BufferedWriter socketWriter = new BufferedWriter(socketOutStream);
//                System.out.println("Connessione Accettata");
//                socketWriter.write("Connessione Accettata");
//                socketWriter.flush();
//                socketWriter.close();
//            } catch (IOException e) {
//                System.out.println("I/O error: " + e);
//            }
//             new threa for a client
//            new ServerThread(socket).start();
//        }
//        while(connection==MAXCONNECTION){
//
//            try {
//                System.out.println("Massimo connessioni raggiunto");
//                socket = serverSocket.accept();
//                OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
//                BufferedWriter socketWriter = new BufferedWriter(socketOutStream);
//                socketWriter.write("exit");
//                socketWriter.flush();
//                socketWriter.close();
//                socket.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}
