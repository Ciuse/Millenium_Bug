package it.polimi.ingsw.ps31.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Client {
    static final int PORT = 2727;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", PORT);
        System.out.println("Socket Client Demo");

        InputStreamReader inputStreamReader= new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);


        OutputStreamWriter socketOutStream = new OutputStreamWriter(socket.getOutputStream());
        BufferedWriter socketWriter = new BufferedWriter(socketOutStream);

        InputStreamReader consoleStream = new InputStreamReader(System.in);
        BufferedReader consoleReader = new BufferedReader(consoleStream);

        String consoleIn;

        do{
//            String lettura="";
//            if(reader.readLine()!=null && lettura!=""&&lettura.startsWith("exit")){
//                lettura=lettura+reader.readLine();
//                System.out.println("Server>"+lettura);
//                consoleIn="exit";
//            }
//            else{
//                System.out.println("Server>"+lettura);
                System.out.print("Client>");
                consoleIn=consoleReader.readLine();
                socketWriter.write(consoleIn+"\n");
                socketWriter.flush();
//            }


        } while(!consoleIn.equals("exit"));

        reader.close();
        socketOutStream.close();
        socketWriter.close();
        consoleStream.close();
        consoleReader.close();
        socket.close();
    }
}
