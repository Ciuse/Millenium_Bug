package it.polimi.ingsw.ps31.client;

/**
 * Created by Francesco on 28/06/2017.
 */
public class ClientViewThread {
//    private ClientMessageHistory clientMessageHistory;
//    private ClientReadingThread clientReadingThread;
//    private View view;
//    private final TypeOfView typeOfView;
//
//    public ClientViewThread(TypeOfView typeOfView)
//    {
//        this.typeOfView = typeOfView;
//        this.clientMessageHistory = new ClientMessageHistory(this);
//    }
//
//    public ClientMessageHistory initView(ViewMessage viewMessage)
//    {
//        switch ( typeOfView )
//        {
//            case CLI:
//                this.view = new CmdLineView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
//                System.out.println("ClientViewThread:initView> creata CLI");
//                break;
//
//            case GUI:
//                this.view = new GuiView(viewMessage.getPlayerId(), viewMessage.getMaxPlayerNumber());
//                System.out.println("ClientViewThread:initView> creata GUI");
//                break;
//
//        }
//        clientMessageHistory.addView(view);
//        view.addController(clientMessageHistory);
//
//        return clientMessageHistory;
//    }
//
//    public void setClientReadingThread(ClientReadingThread networkingThread)
//    {
//        this.clientReadingThread = networkingThread;
//    }
//
//    public void sendMessage(VCVisitable message)
//    {
//        clientReadingThread.sendMessage(message);
//    }
}
