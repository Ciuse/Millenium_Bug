package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;
import it.polimi.ingsw.ps31.server.Match;

/**
 * Created by Francesco on 21/06/2017.
 */
public class
ConnectionMessage extends NetworkingMessage {
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

    public Match getDisconnectedFrom()
    {
        return null;//this.disconnectedFrom;
    }

    /* Setters */
    public void setDisconnectionMatch(Match match)
    {
        //this.disconnectedFrom = match;
    }


    /* Abstract methods implementation */
    public ConcreteEnvelope wrap()
    {
        return new ConcreteEnvelope(this);
    }

    public String toString()
    {
        return "["+username+"|"+password+"|"+typeOfView+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionMessage)) return false;

        ConnectionMessage that = (ConnectionMessage) o;

        if (!getUsername().equals(that.getUsername())) return false;
        return getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getTypeOfView().hashCode();
        return result;
    }
}
