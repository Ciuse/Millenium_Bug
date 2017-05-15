package it.polimi.ingsw.ps31.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class Market {

    private List<ActionSpace> actionBox = new ArrayList<ActionSpace>();

    public Market()
    {
        //Aggiungo i primi due action box del mercato (che sono sempre disponibili)
        this.actionBox.add(new ActionSpace(1, 1, null));    //TODO: specificare effetto immediato
        this.actionBox.add(new ActionSpace(1, 1, null));    //TODO: specificare effetto immediato

        //TODO: creazione degli altri spazi in base al numero di giocatori
    }


}
