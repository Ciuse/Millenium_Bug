package it.polimi.ingsw.ps31;

import java.util.List;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class JsonTypeObject {
    private List<DevelopmentCard> developementCard;
    private List<Resource> costlist;


    public JsonTypeObject(List<DevelopmentCard> developementCard, List<Resource> costlist) {
        this.developementCard = developementCard;
        this.costlist = costlist;
    }

    public List<DevelopmentCard> getCardList(){  //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.developementCard;
    }

    public List<Resource> getCostlist(){   //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.costlist;
    }

}