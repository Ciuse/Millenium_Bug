package it.polimi.ingsw.ps31.server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class ServerThread extends Thread {
    protected Socket socket;

    public ServerThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        DataOutputStream outputStream = null;
        try {

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String lineRead;
        while (true) {
            try {
                lineRead = bufferedReader.readLine();
                if ((lineRead.equalsIgnoreCase("exit"))) {
                    socket.close();
                    inputStream.close();
                    outputStream.close();
                    return;
                } else {

                    outputStream.writeBytes(lineRead + "\n\r");
                    System.out.println("Server>"+lineRead);
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}