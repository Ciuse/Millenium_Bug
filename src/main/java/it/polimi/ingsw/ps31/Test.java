package it.polimi.ingsw.ps31;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.networking.JsonNetworking;

/**
 * Created by Francesco on 28/06/2017.
 */
public class Test {

    public static String serialize(GenericMessage genericMessage) {

        //Creo gson
        Gson gson = JsonNetworking.networkingBuilder();

        //Serializzo l'oggetto
        String strObj = gson.toJson(genericMessage);

        return strObj;
    }

    private static void debug(String msg)
    {
        System.out.println(msg);
    }

    public static void main(String args[])
    {
        debug("Inizio");
        ViewTest viewTest = new ViewTest(PlayerId.ONE, 4);
        debug("View creata");
//        ViewMessage viewMessage = new ViewMessage();


    }
}
