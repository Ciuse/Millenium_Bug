package it.polimi.ingsw.ps31.server;

/**
 * Created by Francesco on 08/06/2017.
 */
public abstract class ServerNetworkInterface {
    private int maxConnections;

    public abstract void sendMsg(String msg);
    public abstract String receiveMsg();
    public abstract void setMaxConnections();

}
