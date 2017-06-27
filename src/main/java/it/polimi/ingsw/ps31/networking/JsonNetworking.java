package it.polimi.ingsw.ps31.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.model.json.RuntimeTypeAdapterFactory;

/**
 * Created by Giuseppe on 27/06/2017.
 */
public class JsonNetworking {


    public static Gson networkingBuilder(){

        RuntimeTypeAdapterFactory<GenericMessage> typerOfMessage = RuntimeTypeAdapterFactory.of(GenericMessage.class, "ResourceType");

        GsonBuilder builder = new GsonBuilder();

        builder.serializeNulls().registerTypeAdapterFactory(typerOfMessage);
        Gson gson = builder.create();

        return gson;
    }
}
