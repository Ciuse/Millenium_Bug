package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientMessageHistory;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientSocketConnection;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.GuiView;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Client extends Thread {
    private static final int PORT = 2727;
    private static ClientNetworkInterface clientNetworkInterface;
    private static ClientViewThread clientViewThread;

    private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));


    /* Main */
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
        do {
            exitDoWhile = true;

            System.out.print("Vuoi giocare con la CLI o con la GUI? [c/g]");
            chosenView = console.readLine();

            if (chosenView.equalsIgnoreCase("c")) {
                typeOfView = TypeOfView.CLI;
                System.out.println("Il gioco partirà con la CLI");
            } else if (chosenView.equalsIgnoreCase("g")) {
                typeOfView = TypeOfView.GUI;
                System.out.println("Il gioco partirà con la GUI");
            } else {
                System.out.println("Risposta non valida");
                exitDoWhile = false;
            }


        } while (!exitDoWhile);

        //Creo il thread che deve contenere la view
//        clientViewThread = new ClientViewThread(typeOfView);

        //Preparo il messaggio contenente le informazioni di connessione
        ConnectionMessage connectionMessage = new ConnectionMessage(username, password, typeOfView);

        //chiedo per connessione
        do {
            exitDoWhile = true;

            System.out.print("Vuoi connetterti con le SOCKET o con RMI? [s/r]");
            chosenConnection = console.readLine();

            if (chosenConnection.equalsIgnoreCase("s"))
                clientNetworkInterface = new ClientSocketConnection(PORT, connectionMessage);
            else if (chosenConnection.equalsIgnoreCase("r")) {
                ///clientNetworkInterface = new ClientReadingThread(new clientRMIConnection());
                System.out.println("RMI non disponibile in questa versione. Mi connetto con le socket :) ");
                clientNetworkInterface = new ClientSocketConnection(PORT, connectionMessage);
            } else {
                System.out.println("Risposta non valida");
                exitDoWhile = false;
            }

        } while (!exitDoWhile);

        //invio al server il messaggio di connessione
        //try {sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
        clientNetworkInterface.sendConnectionMessage();

        System.out.println("In attesa di altri giocatori. Un po' di pazienza...");

        //Rimango in attesa della view dal server
        ViewMessage viewMessage = clientNetworkInterface.readViewMessageFromServer();
        System.out.println("VIEW RICEVUTA!!!");

        View view;
        switch (typeOfView) {
            case CLI:
                view = new CmdLineView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
                System.out.println("ClientViewThread:initView> creata CLI");
                break;

            case GUI:
                view = new GuiView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
                System.out.println("ClientViewThread:initView> creata GUI");
                break;

            default:
                view = new CmdLineView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());

        }

        //Creo e interfaccio la cronologia di messaggi
        ClientMessageHistory clientMessageHistory = new ClientMessageHistory(clientNetworkInterface);
        clientNetworkInterface.setClientMessageHistory(clientMessageHistory);
        clientMessageHistory.addView(view);
        view.addController(clientMessageHistory);

        //Faccio partire la view
       // view.runTerminal();

        //Inizio l'ascolto da socket
        clientNetworkInterface.startReading();


    }
}
