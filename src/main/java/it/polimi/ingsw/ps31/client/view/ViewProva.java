package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.ClientNetworking.ClientNetworkInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Francesco on 11/06/2017.
 */
public class ViewProva {
    private InputStreamReader consoleStream = new InputStreamReader(System.in);
    private BufferedReader consoleReader = new BufferedReader(consoleStream);
    private ClientNetworkInterface networkInterface;

    private String state = "void state";

    public ViewProva()
    {

    }

    public void setNetworkInterface(ClientNetworkInterface networkInterface)
    {
        this.networkInterface = networkInterface;
    }

    public void switchOn()
    {
        System.out.println("Client> Client avviato.");

        String command = "";
        String waiting = null;

        while(!command.equals("exit"))
        {
//            System.out.print("Client> In attesa di richieste dal server");
//
//            while(waiting != "waiting")
//                waiting = networkInterface.readFromServer();

            System.out.println("\nClient> Stato corrente: "+this.state);
            System.out.print("Client> Inserire un comando: ");
            try {
                command = consoleReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Client> "+command);

            networkInterface.sendToServer(command);

            System.out.println("Client> In attesa di risposta dal server: ");
            String answer = networkInterface.readFromServer();
            System.out.println("Client> Risposta dal server: "+answer);

            this.state = new String(answer);


        }
    }

}
