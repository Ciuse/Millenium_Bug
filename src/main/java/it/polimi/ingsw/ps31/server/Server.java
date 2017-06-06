package it.polimi.ingsw.ps31.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Server {
    static final int PORT = 2727;
    protected static final int MAXCONNECTION=4;
    protected static int connection=0;


    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;


        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (connection<MAXCONNECTION ) {
            try {
                System.out.println("Echo Server attesa connessione ...");
                socket = serverSocket.accept();
                connection++;
//                OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
//                BufferedWriter socketWriter = new BufferedWriter(socketOutStream);
                System.out.println("Connessione Accettata");
//                socketWriter.write("Connessione Accettata");
//                socketWriter.flush();
//                socketWriter.close();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new threa for a client
            new ServerThread(socket).start();
        }
        while(connection==MAXCONNECTION){

            try {
                System.out.println("Massimo connessioni raggiunto");
                socket = serverSocket.accept();
//                OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
//                BufferedWriter socketWriter = new BufferedWriter(socketOutStream);
//                socketWriter.write("exit");
//                socketWriter.flush();
//                socketWriter.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
