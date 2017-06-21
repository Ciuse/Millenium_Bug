package it.polimi.ingsw.ps31.messages;

/**
 * Created by Francesco on 13/06/2017.
 */
public class GenericMessage {
    private String state = null;

    public GenericMessage(){}
    public GenericMessage(String state)
    {
        this.state = state;
    }

    public String visit()
    {
        return this.state;
    }
}
