package it.polimi.ingsw.ps31.Json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Effect.*;
import it.polimi.ingsw.ps31.GameThings.*;

import java.util.List;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class JsonGameObject {
    private List<DevelopmentCard> developementCardList;
    private List<EffectList> actionSpaceEffectList;
    private int[] faithTrackExtraValue;
    private int[] bonusVictoryPointFromCharacterCard;
    private int[] bonusVictoryPointFromPlayerResources;
    private int[] bonusVictoryPointFromTerritory;

    public JsonGameObject(List<DevelopmentCard> developementCardList) {
        this.developementCardList = developementCardList;
    }

    public static Gson gsonGameBuilder(){

//Serve per poter far riconoscere a Json una Lista di classi polimorfe
        RuntimeTypeAdapterFactory<Resource> resourceAdapterFactory = RuntimeTypeAdapterFactory.of(Resource.class, "ResourceType");
        resourceAdapterFactory.registerSubtype(Coin.class, "Coin");
        resourceAdapterFactory.registerSubtype(Wood.class, "Wood");
        resourceAdapterFactory.registerSubtype(Stone.class, "Stone");
        resourceAdapterFactory.registerSubtype(Servant.class, "Servant");
        resourceAdapterFactory.registerSubtype(FaithPoint.class, "FaithPoint");
        resourceAdapterFactory.registerSubtype(MilitaryStrength.class, "MilitaryStrength");
        resourceAdapterFactory.registerSubtype(VictoryPoint.class, "VictoryPoint");
        resourceAdapterFactory.registerSubtype(CouncilPrivilege.class, "CouncilPrivilege");

        RuntimeTypeAdapterFactory<DevelopmentCard> developementCardAdapterFactory = RuntimeTypeAdapterFactory.of(DevelopmentCard.class, "CardType");
        developementCardAdapterFactory.registerSubtype(Territory.class, "Territory");
        developementCardAdapterFactory.registerSubtype(Building.class, "Building");
        developementCardAdapterFactory.registerSubtype(Character.class, "Character");
        developementCardAdapterFactory.registerSubtype(Venture.class, "Venture");

        RuntimeTypeAdapterFactory<Effect> effectAdapterFactory = RuntimeTypeAdapterFactory.of(Effect.class, "EffectType");
        effectAdapterFactory.registerSubtype(HarvestEffect.class, "HarvestEffect");
        effectAdapterFactory.registerSubtype(ProductionEffect.class, "ProductionEffect");
        effectAdapterFactory.registerSubtype(HarvestActivation.class, "HarvestActivation");
        effectAdapterFactory.registerSubtype(ProductionActivation.class, "ProductionActivation");
        effectAdapterFactory.registerSubtype(CardCostDiscount.class, "CardCostDiscount");
        effectAdapterFactory.registerSubtype(ActionDiscount.class, "ActionDiscount");
        effectAdapterFactory.registerSubtype(NoImmediateEffect.class, "NoImmediateEffect");
        effectAdapterFactory.registerSubtype(GetResource.class, "GetResource");
        effectAdapterFactory.registerSubtype(ChangeResource.class, "ChangeResource");
        effectAdapterFactory.registerSubtype(ChooseCard.class, "ChooseCard");
        effectAdapterFactory.registerSubtype(ChooseCardWithDiscount.class, "ChooseCardWithDiscount");

        //TODO MANCANO I LEADER EFFECT



//Creazione del builder adatto a riconoscere tutti gli oggetti polimorfi
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).registerTypeAdapterFactory(resourceAdapterFactory).registerTypeAdapterFactory(developementCardAdapterFactory).registerTypeAdapterFactory(effectAdapterFactory);
        Gson gson = builder.create();

        return gson;
    }

/*Getters & Setters*/

    public List<DevelopmentCard> getCardList(){  //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.developementCardList;
    }
    public List<EffectList> getEffectList(){  //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.actionSpaceEffectList;
    }

    public int[] getFaithTrackExtraValue() {
        return faithTrackExtraValue;
    }

    public int[] getBonusVictoryPointFromCharacterCard() {
        return bonusVictoryPointFromCharacterCard;
    }

    public int[] getBonusVictoryPointFromPlayerResources() {
        return bonusVictoryPointFromPlayerResources;
    }

    public int[] getBonusVictoryPointFromTerritory() {
        return bonusVictoryPointFromTerritory;
    }
}