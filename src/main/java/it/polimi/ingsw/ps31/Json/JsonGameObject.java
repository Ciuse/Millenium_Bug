package it.polimi.ingsw.ps31.Json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.GameThings.*;

import java.util.List;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class JsonGameObject {
    private List<DevelopmentCard> developementCard;
    private List<Resource> costlist;


    public JsonGameObject(List<DevelopmentCard> developementCard, List<Resource> costlist) {
        this.developementCard = developementCard;
        this.costlist = costlist;
    }

    public static Gson gsonGameBuilder(){

//Serve per poter far riconoscere a Json una Lista di classi polimorfe
        RuntimeTypeAdapterFactory<Resource> resourceAdapterFactory = RuntimeTypeAdapterFactory.of(Resource.class, "ResourceType");
        resourceAdapterFactory.registerSubtype(Coin.class, "Coin");
        resourceAdapterFactory.registerSubtype(Wood.class, "Wood");
        resourceAdapterFactory.registerSubtype(Stone.class, "Stone");
        resourceAdapterFactory.registerSubtype(Servant.class, "Servant");

        RuntimeTypeAdapterFactory<DevelopmentCard> developementCardAdapterFactory = RuntimeTypeAdapterFactory.of(DevelopmentCard.class, "CardType");
        developementCardAdapterFactory.registerSubtype(Territory.class, "Territory");
        developementCardAdapterFactory.registerSubtype(Building.class, "Building");
        developementCardAdapterFactory.registerSubtype(Character.class, "Character");
        developementCardAdapterFactory.registerSubtype(Venture.class, "Venture");

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).registerTypeAdapterFactory(resourceAdapterFactory).registerTypeAdapterFactory(developementCardAdapterFactory);
        Gson gson = builder.create();

        return gson;
    }



    public List<DevelopmentCard> getCardList(){  //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.developementCard;
    }

    public List<Resource> getCostlist(){   //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.costlist;
    }

}