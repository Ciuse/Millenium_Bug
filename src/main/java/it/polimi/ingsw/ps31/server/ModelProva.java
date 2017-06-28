package it.polimi.ingsw.ps31.server;

/**
 * Created by Francesco on 10/06/2017.
 */
public class ModelProva {
//    private String state;
//    private Match match;
//    private NetworkInterface networkInterface;
//
//    public ModelProva(Match match, NetworkInterface networkInterface) {
//        this.match = match;
//        this.networkInterface = networkInterface;
//    }
//
//    public void startModel()
//    {
//        networkInterface.readFromClient(PlayerId.ONE);
//    }
//
//    public void setState(String state, PlayerId sender)
//    {
//        System.out.println("Model> Messaggio in ingresso: " + state + " proveniente dal player "+sender);
//
//        this.state = state;
//        generateAnswer(sender);
//    }
//
//    private void generateAnswer(PlayerId sendBackTo)
//    {
//        this.state = new StringBuilder(this.state).reverse().toString();
//
//        genero l'oggetto di risposta
//        GenericMessage answerObj = new GenericMessage(this.state);
//
//        notifyState(sendBackTo, answerObj);
//
//
//    }
//
//    public void notifyState(PlayerId receiver)
//    {
//        GenericMessage msgToSend = new GenericMessage(this.state);
//        networkInterface.sendToClient(msgToSend, receiver);
//        System.out.println("Server> Risposta spedita: "+this.state+". In attesa di ulteriore comando...");
//
//    }
//
//    public void notifyState(PlayerId receiver, GenericMessage obj)
//    {
//        System.out.println("Server> receiver: "+receiver+"; obj: "+obj);
//        networkInterface.sendToClient(obj, receiver);
//    }
//
}
