package it.polimi.ingsw.ps31.networking;

/**
 * Created by Francesco on 13/06/2017.
 */
public class MexProva {
    private String state;

    public MexProva(String state)
    {
        this.state = state;
    }

    public String visit()
    {
        return this.state;
    }

    @Override
    public String toString()
    {
        return this.state;
    }
}
