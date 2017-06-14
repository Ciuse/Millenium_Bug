package it.polimi.ingsw.ps31.client;

import it.polimi.ingsw.ps31.client.ClientNetworking.ClientSocketConnection;
import it.polimi.ingsw.ps31.client.view.ViewProva;

import java.io.IOException;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Client {
    static final int PORT = 2727;
    private static NetworkingThread networkingThread;
    private static ViewThread viewThread;

    public static void main(String[] args) throws IOException {

        //todo: chiedere CLI/GUI
        //todo: chiedere socket/RMI

        //Creo la view (cli finchè non avremo la gui, poi if-else)
        //todo: gestire i parametri del costruttore
        //TerminalView terminalView = new TerminalView(null, null, null, null);
        ViewProva viewProva = new ViewProva();

        //Creo la connessione (socket finchè non avremo rmi, poi if-else)
        ClientSocketConnection clientSocketConnection = new ClientSocketConnection(PORT);

        //Inserisco view e networking in due thread dedicati
        viewThread = new ViewThread();
        networkingThread = new NetworkingThread(clientSocketConnection);

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
