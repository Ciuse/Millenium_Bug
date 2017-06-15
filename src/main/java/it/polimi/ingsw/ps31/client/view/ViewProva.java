package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.ClientNetworking.ClientNetworkInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Thread.sleep;

/**
 * Created by Francesco on 11/06/2017.
 */
public class ViewProva extends GenericView{
    private InputStreamReader consoleStream = new InputStreamReader(System.in);
    private BufferedReader consoleReader = new BufferedReader(consoleStream);
    private ClientNetworkInterface networkInterface;

    private String state = "void state";

    public ViewProva()
    {

    }

    @Override
    public void attachNetworkInterface(ClientNetworkInterface networkInterface)
    {
        this.networkInterface = networkInterface;
    }

    @Override
    public void switchOn()
    {
        if( this.networkInterface == null)
        {
            //todo:eccezione
            System.out.println("Client> Nessuna NI collegata alla view. Esco.");

            return;
        }

        System.out.println("Client> Client avviato. Digitare 'exit' per terminare");

        String command = "";
        String waiting = null;

        boolean exit = false;
        while(!exit)
        {
//            System.out.print("Client> In attesa di richieste dal server");
//
//            while(waiting != "waiting")
//                waiting = networkInterface.readFromBuffer();

            System.out.println("\nClient> Stato corrente della view: "+this.state);
            System.out.print("Client> Inserire un comando: ");
            try {
                command = consoleReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (command.equals("exit"))
            {
                exit = true;
                System.out.println("Client ViewProva : switchOn()> Exit = true");

            }else
            {

                //System.out.println("Client> "+command);

                networkInterface.sendToServer(command);

                System.out.println("Client> In attesa di risposta dal server: ");
                String answer = networkInterface.readFromBuffer();
                System.out.println("Client> Risposta dal server: "+answer);

                this.state = answer;
            }

        }

        networkInterface.sendToServer(command);

        System.out.println("\nClient> sessione terminata. Disconnessione in corso");
        this.networkInterface.close();
        System.out.println("Client> disconnessione effettuta. Ciao!");

    }

    public void asynchronousInput(String async)
    {
        //todo implementare
    }

}
