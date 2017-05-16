package it.polimi.ingsw.ps31.Json;

import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.Constants.PlayerColor.GREEN;

/**
 * Created by giulia on 16/05/2017.
 */
public abstract class CreationCard {

    public static List<DevelopmentCard> createCardList(){
         List<DevelopmentCard> cardList = new ArrayList<>();
         for (int i=0;i<3;i++){
             for(int j=1;j<=8;j++){
                 String name=null;
                 List<ResourceList> cost=null;
                 int cardNumber = i*8+j;
                 int period = i+1;
                 CardColor cardColor = CardColor.GREEN;
                 cardList.add(new Territory(cardNumber,name,period,cost,null,null));

                 String name2=null;
                 List<ResourceList> cost2=null;
                 int cardNumber2 = i*8+j+24;
                 int period2 = i+1;
                 CardColor cardColor2 = CardColor.YELLOW;
                 cardList.add(new Building(cardNumber2,name2,period2,cost2,null,null));

                 String name3=null;
                 List<ResourceList> cost3=null;
                 int cardNumber3 = i*8+j+48;
                 int period3 = i+1;
                 CardColor cardColor3 = CardColor.BLUE;
                 cardList.add(new Character(cardNumber3,name3,period3,cost3,null,null));

                 String name4=null;
                 List<ResourceList> cost4=null;
                 int cardNumber4 = i*8+j+72;
                 int period4 = i+1;
                 CardColor cardColor4 = CardColor.PURPLE;
                 cardList.add(new Venture(cardNumber4,name4,period4,cost4,null,null));
             }

         }


        return cardList;
    }

}
