package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.messages.ConcreteEnvelope;

/**
 * Created by Francesco on 21/06/2017.
 *
 * Messaggio che contiene le informazioni scelte dall'utente all'avvio del client.
 * Serve a gestire la creazione del Player lato server
 */
public class
ConnectionMessage extends NetworkingMessage {
    /** Username del giocatore*/
    private final String username;

    /** Password del giocatore */
    private final String password;

    /** Tipo di view scelta dal giocatore */
    private final TypeOfView typeOfView;

    /* Constructor */
    public ConnectionMessage(String username, String password, TypeOfView typeOfView)
    {
        super();
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

    /**
     * Confronta un'altro ConnectionMessage e restituisce true sse lo username coincide ma la password no
     * @param otherCM il connectionMessage da confrontare con this
     * @return true sse username coincide ma la password no
     */
    public boolean wrongPassword(ConnectionMessage otherCM){
        if ( this.getUsername().equals(otherCM.getUsername()) &&
            !this.getPassword().equals(otherCM.getPassword()) )
            return true;
        return false;
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
