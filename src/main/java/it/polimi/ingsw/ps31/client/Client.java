package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientSocketConnection;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Client {
    static final int PORT = 2727;
    private static NetworkingThread networkingThread;
    private static ViewThread viewThread;


    private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String chosenView;
        String chosenConnection;
        String username;
        String password;


        //chiedo username
        System.out.print("Inserisci nome utente: ");
        username = console.readLine();

        //chiedo password
        System.out.print("Inserisci password");
        password = console.readLine();

        boolean exitDoWhile;

        //chiedo per view
        TypeOfView typeOfView = null;
        do{
            exitDoWhile = true;

            System.out.print("Vuoi giocare con la CLI o con la GUI? [c/g]");
            chosenView = console.readLine();

            if( chosenView.equalsIgnoreCase("c") ) {
                typeOfView = TypeOfView.CLI;
                System.out.println("Il gioco partirà con la CLI");
            }
            else if (chosenView.equalsIgnoreCase("g") ){
                typeOfView = TypeOfView.GUI;
                System.out.println("Il gioco partirà con la GUI");}
            else {
                System.out.println("Risposta non valida");
                exitDoWhile = false;
            }


        } while(!exitDoWhile);


        //Preparo il messaggio contenente le informazioni di connessione
        ConnectionMessage connectionMessage = new ConnectionMessage(username, password, typeOfView);


        //chiedo per connessione
        do{
            exitDoWhile = true;

            System.out.print("Vuoi connetterti con le SOCKET o con RMI? [s/r]");
            chosenConnection = console.readLine();

            if( chosenConnection.equalsIgnoreCase("s") )
                networkingThread = new NetworkingThread(new ClientSocketConnection(PORT, connectionMessage));
            else if (chosenConnection.equalsIgnoreCase("r") )
            {
                ///networkingThread = new NetworkingThread(new clientRMIConnection());
                System.out.println("RMI non disponibile in questa versione. Mi connetto con le socket :) ");
                networkingThread = new NetworkingThread(new ClientSocketConnection(PORT, connectionMessage));
            }
            else{
                System.out.println("Risposta non valida");
                exitDoWhile = false;
            }

        }while(!exitDoWhile);

        System.out.println("In attesa di altri giocatori. Un po' di pazienza...");

        //Chiedo al networking thread di restituire la view e la inserisco in un thread
//        while ( networkingThread. )
//  todo      viewThread = new ViewThread(networkingThread.askServerForView(connectionMessage));

        //Passo ai due thread i relativi riferimenti
        viewThread.setNetworking(networkingThread);
        networkingThread.setViewThread(viewThread);

        //Faccio partire i thread
        viewThread.start();
        networkingThread.start();













//        Socket socket = new Socket("127.0.0.1", PORT);
//        System.out.println("Socket Client Demo");
//
//        InputStreamReader inputStreamReader= new InputStreamReader(socket.getInputStream());
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//
//
//        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
//        BufferedWriter socketWriter = new BufferedWriter(socketOutStream);
//
//        InputStreamReader consoleStream = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(consoleStream);
//
//        String consoleIn;
//
//        do{
////            String lettura="";
////            if(reader.readLine()!=null && lettura!=""&&lettura.startsWith("exit")){
////                lettura=lettura+reader.readLine();
////                System.out.println("Server>"+lettura);
////                consoleIn="exit";
////            }
////            else{
////                System.out.println("Server>"+lettura);
//                System.out.print("Client>");
//                consoleIn=consoleReader.readLine();
//                socketWriter.write(consoleIn+"\n");
//                socketWriter.flush();
////            }
//
//
//        } while(!consoleIn.equals("exit"));
//
//        reader.close();
//        socketOutStream.close();
//        socketWriter.close();
//        consoleStream.close();
//        consoleReader.close();
//        socket.close();
    }
}
