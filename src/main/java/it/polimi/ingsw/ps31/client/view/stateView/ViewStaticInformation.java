package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by giulia on 08/06/2017.
 */
public class ViewStaticInformation {
    private static int Family_Member_Number = 4;
    private static int Personal_Board_Identical_Box_Max= 6;
    private static int Personal_Board_Card_List_Number = 4;
    private static int Tower_Identical_Box_Max= 4;
    private static int Number_Of_Tower=4;
    private static int Max_Player_Name_Len=5;
    private static int Number_Of_DevelopmentCard=96;
    private static int Number_Of_ActionSpace = 25;
    private static int Max_number_Of_MarkerDisc = 3;
    private static CardColor[] cardColors=CardColor.values();
    private static DiceColor[] diceColors=DiceColor.values();
    private static PlayerId[] playerIds=PlayerId.values();
    private static PlayerColor[] playerColors=PlayerColor.values();
    private static StateViewEffect[] actionSpaceEffect={
            new StateViewEffect(null,new Wood(2).toString(),+10), new StateViewEffect(null,new Wood(1).toString(),+10), null, null,
            new StateViewEffect(null,new Stone(2).toString(),+10), new StateViewEffect(null,new Stone(1).toString(),+10),null, null,
            new StateViewEffect(null,new MilitaryStrength(2).toString(),+10), new StateViewEffect(null,new MilitaryStrength(1).toString(),+10),null, null,
            new StateViewEffect(null,new Coin(2).toString(),+10),new StateViewEffect(null,new Coin(1).toString(),+10),null,null,
            new StateViewEffect("Council",new ResourceList(new Coin(1),new CouncilPrivilege(1,false) ).toString(),+10 ),
            new StateViewEffect("Harv",null,0),new StateViewEffect("Harv",null,-3),
            new StateViewEffect("Prod",null,0),new StateViewEffect("Prod",null,-3),
            new StateViewEffect(null,new Coin(5).toString(),+10),new StateViewEffect(null,new Servant(5).toString(),+10),
            new StateViewEffect(null,new ResourceList(new MilitaryStrength(3),new Coin(2)).toString(),+10),
            new StateViewEffect(null,new CouncilPrivilege(2,true).toString(),+10)};
    private static int[] diceActionSpaceValue={7,5,3,1,7,5,3,1,7,5,3,1,7,5,3,1,1,1,1,1,1,1,1,1,1};
    private static String[] stringPointResourceType = {"MilitaryStrength","VictoryPoint","FaithPoint"};
    private static List<ResourceList> resourceListFromCouncilPrivilege = Arrays.asList(new ResourceList(new Wood(1),new Stone(1)), new ResourceList(new Servant(2)),
            new ResourceList(new Coin(2)), new ResourceList(new MilitaryStrength(2)),new ResourceList(new FaithPoint(1)));


    public static int getFamily_Member_Number() {
        return Family_Member_Number;
    }

    public static int getPersonal_Board_Identical_Box_Max() {
        return Personal_Board_Identical_Box_Max;
    }

    public static int getPersonal_Board_Card_List_Number() {
        return Personal_Board_Card_List_Number;
    }

    public static int getTower_Identical_Box_Max() {
        return Tower_Identical_Box_Max;
    }

    public static int getNumber_Of_Tower() {
        return Number_Of_Tower;
    }

    public static int getMax_Player_Name_Len() {
        return Max_Player_Name_Len;
    }

    public static int getNumber_Of_DevelopmentCard() {
        return Number_Of_DevelopmentCard;
    }

    public static CardColor[] getCardColors() {
        return cardColors;
    }

    public static DiceColor[] getDiceColors() {
        return diceColors;
    }

    public static PlayerId[] getPlayerIds() {
        return playerIds;
    }

    public static PlayerColor[] getPlayerColors() {
        return playerColors;
    }

    public static int getNumber_Of_ActionSpace() {
        return Number_Of_ActionSpace;
    }

    public static StateViewEffect[] getActionSpaceEffect() {
        return actionSpaceEffect;
    }

    public static int[] getDiceActionSpaceValue() {
        return diceActionSpaceValue;
    }

    public static int getMax_number_Of_MarkerDisc() {
        return Max_number_Of_MarkerDisc;
    }

    public static String[] getStringPointResourceType() {
        return stringPointResourceType;
    }

    public static List<ResourceList> getResourceListFromCouncilPrivilege() {
        return resourceListFromCouncilPrivilege;
    }
}
