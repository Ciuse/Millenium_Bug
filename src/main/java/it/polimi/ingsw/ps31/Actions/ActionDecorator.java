package it.polimi.ingsw.ps31.Actions;

import java.util.List;

/**
 * Created by Francesco on 19/05/2017.
 */
public abstract class ActionDecorator implements PlayerActions{

    private List<Action> decorable;
}
