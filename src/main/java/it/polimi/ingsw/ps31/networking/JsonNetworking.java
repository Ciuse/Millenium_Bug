package it.polimi.ingsw.ps31.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.*;
import it.polimi.ingsw.ps31.model.bonus.*;
import it.polimi.ingsw.ps31.model.card.*;
import it.polimi.ingsw.ps31.model.card.Character;
import it.polimi.ingsw.ps31.model.effect.*;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.json.RuntimeTypeAdapterFactory;

/**
 * Created by Giuseppe on 27/06/2017.
 */
public class JsonNetworking {


    public static Gson networkingBuilder(){

        /*
        RuntimeTypeAdapterFactory<GenericMessage> genericMessageAdapterFactory = RuntimeTypeAdapterFactory.of(GenericMessage.class, "GenericMessage");
        genericMessageAdapterFactory.registerSubtype(NetworkingMessage.class, "NetworkingMessage");
        genericMessageAdapterFactory.registerSubtype(MVVisitable.class, "MVVisitable");
        genericMessageAdapterFactory.registerSubtype(VCVisitable.class, "VCVisitable");
*/

        RuntimeTypeAdapterFactory<NetworkingMessage> networkingMessageAdapterFactory = RuntimeTypeAdapterFactory.of(NetworkingMessage.class, "NetworkingMessage");
        networkingMessageAdapterFactory.registerSubtype(ConnectionMessage.class, "ConnectionMessage");
        networkingMessageAdapterFactory.registerSubtype(ViewMessage.class, "ViewMessage");

        RuntimeTypeAdapterFactory<MVVisitable> mVVisitableAdapterFactory = RuntimeTypeAdapterFactory.of(MVVisitable.class, "MVVisitable");
        mVVisitableAdapterFactory.registerSubtype(MVAskChoice.class, "MVAskChoice");
        mVVisitableAdapterFactory.registerSubtype(MVStringToPrint.class, "MVStringToPrint");
        mVVisitableAdapterFactory.registerSubtype(MVUpdateState.class, "MVUpdateState");

        RuntimeTypeAdapterFactory<VCVisitable> vCVisitableAdapterFactory = RuntimeTypeAdapterFactory.of(VCVisitable.class, "VCVisitable");
        vCVisitableAdapterFactory.registerSubtype(VCActionSpace.class, "VCActionSpace");
        vCVisitableAdapterFactory.registerSubtype(VCActiveEffectChoice.class, "VCActiveEffectChoice");
        vCVisitableAdapterFactory.registerSubtype(VCColorChoice.class, "VCColorChoice");
        vCVisitableAdapterFactory.registerSubtype(VCCouncilPrivilegeChoice.class, "VCCouncilPrivilegeChoice");
        vCVisitableAdapterFactory.registerSubtype(VCFamilyMemberChoice.class, "VCFamilyMemberChoice");
        vCVisitableAdapterFactory.registerSubtype(VCLeaderToActiveChoice.class, "VCLeaderToActiveChoice");
        vCVisitableAdapterFactory.registerSubtype(VCLeaderToDiscardChoice.class, "VCLeaderToDiscardChoice");
        vCVisitableAdapterFactory.registerSubtype(VCListToPayChoice.class, "VCListToPayChoice");
        vCVisitableAdapterFactory.registerSubtype(VCPersonalTilesChoice.class, "VCPersonalTilesChoice");
        vCVisitableAdapterFactory.registerSubtype(VCPlayerAction.class, "VCPlayerAction");
        vCVisitableAdapterFactory.registerSubtype(VCServantToPayChoice.class, "VCServantToPayChoice");
        vCVisitableAdapterFactory.registerSubtype(VCStartLeaderChoice.class, "VCStartLeaderChoice");
        vCVisitableAdapterFactory.registerSubtype(VCSupportTheChurchChoice.class, "VCSupportTheChurchChoice");
        vCVisitableAdapterFactory.registerSubtype(VCTowerCardSpaceChoice.class, "VCTowerCardSpaceChoice");


        RuntimeTypeAdapterFactory<View> viewAdapterFactory = RuntimeTypeAdapterFactory.of(View.class, "View");
        viewAdapterFactory.registerSubtype(CmdLineView.class, "CmdLineView");
        viewAdapterFactory.registerSubtype(GuiView.class, "GuiView");


        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls()./*registerTypeAdapterFactory(genericMessageAdapterFactory).*/
                registerTypeAdapterFactory(networkingMessageAdapterFactory).
                registerTypeAdapterFactory(mVVisitableAdapterFactory).
                registerTypeAdapterFactory(vCVisitableAdapterFactory).
                registerTypeAdapterFactory(viewAdapterFactory);

        Gson gson = builder.create();

        return gson;
    }
}
