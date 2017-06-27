package it.polimi.ingsw.ps31.messages.messageNetworking;

import it.polimi.ingsw.ps31.client.view.View;

/**
 * Created by Francesco on 24/06/2017.
 */
public class ViewMessage extends NetworkingMessage{
    private final View view;

    /* Constructor */
    public ViewMessage(View view)
    {
        this.view = view;
    }

    public View getView()
    {
        return view;
    }

}
