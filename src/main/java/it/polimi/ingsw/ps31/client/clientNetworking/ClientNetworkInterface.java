package it.polimi.ingsw.ps31.client.clientNetworking;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.networking.JsonNetworking;
import javafx.scene.input.GestureEvent;

import static java.lang.Thread.sleep;

/**
 * Created by Francesco on 10/06/2017.
 */
//Classe che unifica le connessioni Socket e RMI sotto un'unica interfaccia di funzionamento
public abstract class ClientNetworkInterface {
    protected boolean viewReceived = false;
    protected final ConnectionMessage connectionMessage;

    /* Constructor */
    public ClientNetworkInterface(ConnectionMessage connectionMessage)
    {
        this.connectionMessage = connectionMessage;
        //this.view = firstConnectionProcedure(this.connectionMessage);
    }

//    public final View firstConnectionProcedure(ConnectionMessage connectionMessage)
//    {
//        //TODO: IMPLEMENTARE DECENTEMENTE
//
//        if( this.viewAsked )
//            return null;
//
//        //Rimango in attesa di un messaggio dal server
//        GenericMessage firstMessageFromServer = readFromServer();
//        clientNetworkingVisitor.visit(firstMessageFromServer);
//
//        //Invio le informazioni di connessione
//        sendToServer(connectionMessage);
//
//        View answerFromServer;
//        boolean exitDoWhile = false;
//        do{
//            //Aspetto la risposta
//            answerFromServer = readViewFromServer();
//
//            //Se non c'è risposta, aspetto 1 secondo prima di ciclare nuovamente
//            if ( answerFromServer == null )
//                try
//                {
//                    sleep(1000);
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//
//            //se la risposta è del tipo atteso, esco dal ciclo
//            else if ( answerFromServer.getClass().equals(View.class) )
//                exitDoWhile = true;
//
//            //NB: per esclusione, le risposte diverse da quella attesa vengono scartate e implicano il rientro nel ciclo
//
//        } while ( !exitDoWhile );
//
//
//        //TODO: istruzione di test da cancellare
//        System.out.println("client> letto ok");
//
//        //impedisco che il metodo venga invocato più di una volta
//        viewAsked = true;
//
//        //todo ritornare view non nulla
//        return null;
//    }

    //invia un messaggio in un oggetto, serializzandolo
    public final void sendToServer(GenericMessage msg){
        writeOnNetwork(serialize(msg));
    }

    //invia un messaggio di tipo VCVisitable
    public final void sendToServer(VCVisitable msg){
        writeOnNetwork(serialize(msg));
    }

    //legge un messaggio proveninete dal server, lo deserializza e lo restituisce
    public final GenericMessage readFromServer(){
        return deserialize(readFromNetwork());
    }

//    //Metodo che riceve la view dal server
//    public final View readViewFromServer(){
//        String serializedMsg = readFromNetwork();
//
//        //Creo gson
//        Gson gson = new Gson();
//
//        //Deserializzo l'oggetto
//        View view = gson.fromJson(serializedMsg, View.class);
//
//        return view;
//    }

    private final String serialize(GenericMessage genericMessage)
    {
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Serializzo l'oggetto
        String strObj = gson.toJson(genericMessage);


        return strObj;
    }

    private final GenericMessage deserialize(String msg){
        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Deserializzo l'oggetto
        GenericMessage strObj = gson.fromJson(msg, GenericMessage.class);

        return strObj;
    }

    public void sendConnectionMessage()
    {
        sendToServer(this.connectionMessage);
    }

    /* Abstract Methods */
    protected abstract void writeOnNetwork(String msgStr);
    protected abstract String readFromNetwork();
}