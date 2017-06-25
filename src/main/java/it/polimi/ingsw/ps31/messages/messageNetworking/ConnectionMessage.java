package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.ClientNetworkingThread;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.networking.NetworkingThread;

/**
 * Created by Francesco on 21/06/2017.
 */
public class ConnectionMessage extends NetworkingMessage {
    private final String username;
    private final String password;
    private final TypeOfView typeOfView;

    /* Constructor */
    public ConnectionMessage(String username, String password, TypeOfView typeOfView)
    {
        this.username = username;
        this.password = password;
        this.typeOfView = typeOfView;
    }

    /* Getters */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public TypeOfView getTypeOfView(){
        return this.typeOfView;
    }

    @Override
    public void update(NetworkingThread clientNetworkingThread) {

    }
}
