package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientMessageHistory;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.clientNetworking.ClientSocketConnection;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Client extends Thread {
    private static final int PORT = 2727;
    private static ClientNetworkInterface clientNetworkInterface;

    private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));


    /* Main */
    public static void main(String[] args) throws IOException {
        String chosenView;
        String chosenConnection;
        String username;
        String password;
        boolean error = false;
        boolean redo = false;

        do {
            //chiedo username
            do {
                DebugUtility.debuggedUserMessage("Inserisci nome utente: ", false);
                username = console.readLine();
            } while (username.trim().equals(""));

            //chiedo password
            DebugUtility.debuggedUserMessage("Inserisci password: ", false);
            password = console.readLine();

            boolean exitDoWhile;

            //chiedo per view
            TypeOfView typeOfView = null;
            do {
                exitDoWhile = true;

                DebugUtility.debuggedUserMessage("Vuoi giocare con la CLI o con la GUI? [c/g] ", false);
                chosenView = console.readLine();

                if (chosenView.equalsIgnoreCase("c")) {
                    typeOfView = TypeOfView.CLI;
                    DebugUtility.debuggedUserMessage("Il gioco partirà con la CLI");
                } else if (chosenView.equalsIgnoreCase("g")) {
                    typeOfView = TypeOfView.GUI;
                    DebugUtility.debuggedUserMessage("Il gioco partirà con la GUI");
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

                DebugUtility.debuggedUserMessage("Vuoi connetterti con le SOCKET o con RMI? [s/r] ", false);
                chosenConnection = console.readLine();

                try {
                    if (chosenConnection.equalsIgnoreCase("s"))
                        clientNetworkInterface = new ClientSocketConnection(PORT, connectionMessage);
                    else if (chosenConnection.equalsIgnoreCase("r")) {
                        ///clientNetworkInterface = new ClientReadingThread(new clientRMIConnection());
                        DebugUtility.debuggedUserMessage("RMI non disponibile in questa versione. Mi connetto con le socket :) ");
                        clientNetworkInterface = new ClientSocketConnection(PORT, connectionMessage);
                    } else {
                        DebugUtility.debuggedUserMessage("Risposta non valida");
                        exitDoWhile = false;
                    }
                } catch (ConnectException e) {
                    DebugUtility.debuggedUserMessage("Il server è offline. Impossibile connettersi.");
                    error = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    DebugUtility.debuggedUserMessage("Errore di rete. Controllare la connessione a internet e riprovare");
                    error = true;
                }

            } while (!exitDoWhile);

            if (!error) {
                //invio al server il messaggio di connessione
                //try {sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                clientNetworkInterface.sendConnectionMessage();

                //Rimango in attesa della view dal server
                ViewMessage viewMessage = null;
                try {
                    viewMessage = clientNetworkInterface.readViewMessageFromServer();
                } catch (IOException e) {
                    DebugUtility.simpleUserMessage("La connessione è stata interrotta. " +
                            "Controllare i collegamenti alla rete e riavviare il gioco.");
                    error = true;
                }
                //DebugUtility.simpleDebugMessage("VIEW RICEVUTA!!!");

                if( viewMessage.getPlayerId() == null )
                    redo = true;
                else
                if (!error) {
                    View view;
                    switch (typeOfView) {
                        case CLI:
                            view = new CmdLineView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
                            DebugUtility.debuggedUserMessage("Creata CLI");
                            break;

                        case GUI:
                            view = new GuiView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
                            DebugUtility.debuggedUserMessage("Creata GUI");
                            break;

                        default:
                            view = new CmdLineView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());

                    }

                    DebugUtility.debuggedUserMessage("In attesa di altri giocatori. Un po' di pazienza...");

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
            if ( redo && !error )
                DebugUtility.debuggedUserMessage("Password errata. Riprovare\n", true);
        } while ( redo && !error);
        if( error )
            DebugUtility.debuggedUserMessage("Arrivederci!");
    }
}
