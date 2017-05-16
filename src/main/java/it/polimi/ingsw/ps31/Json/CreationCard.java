package it.polimi.ingsw.ps31.Json;

import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.Constants.PlayerColor.GREEN;

/**
 * Created by giulia on 16/05/2017.
 */
public abstract class CreationCard {

    public static List<DevelopmentCard>  createCardList(){
         List<DevelopmentCard> cardList = new ArrayList<>();
         int contatore=0;
         String[] greenCardName = {"Avamposto Commerciale","Bosco","Borgo","Cava di Ghiaia","Foresta","Monastero","Rocca","Città","Eremo","Cava di Pietra",
                 "Villaggio Minerario","Possedimento","Ducato","Villaggio Montanaro","Maniero","Miniera d'Oro","Città Mercantile","Tenuta","Colonia","Cava di Marmo","Provincia",
         "Santuario","Castello","Città Fortificata",};
         String[] yellowCardName = { "Zecca","Esattoria","arco di Trionfo","Teatro","Falegnameria","Tagliapietre","Cappella","Residenza",
                "Mercato","Tesoreria","Gilda dei Pittori","Gilda degli Scultori","Gilda dei Costruttori","Battistero","Caserma","Fortezza",
                "Banca","Fiera","Giardino","Castelletto","Palazzo","Basilica","Accademia Militare","Cattedrale",};
         String[] blueCardName = { "Condottiero","Costruttore","Dama","Cavalliere","Contadino","Artigiano","Predicatore","Badessa","Capitano",
                "Architetto","Mecenate","Eroe","Fattore","Studioso","Messo Papale","Messo Reale","Nobile","Governatore","Cortigiana","Araldo",
                "Cardinale","Vescovo","Generale","Ambasciatore",};
         String[] purpleCardName = { "Ingaggiare Reclute","Riparare la Chiesa","Costruire le Mura","Innalzare una Statua","Campagna Militare",
                "Ospitare i Mendicanti","Combattere le Eresie","Sostegno al Vescovo","Ingaggiare Soldati","Riparare l'Abbazia","Costruire i Bastioni",
                "Scavare Canalizzazioni","Supporto al Re","Accogliere gli Stranieri","Crociata","Sostegno al Cardinale","Ingaggiare Mercenari","Riparare la Cattedrale",
                "Costruire le Torri","Commissionare Arte Sacra","Conquista Militare","Migliorare le Strade","Guerra Santa","Sostegno al Papa",};


         for (int i=0;i<3;i++){
             for(int j=1;j<=8;j++){
                 List<ResourceList> cost=null;
                 int cardNumber = i*8+j;
                 int period = i+1;
                 String name=greenCardName[contatore];
                 CardColor cardColor = CardColor.GREEN;
                 cardList.add(new Territory(cardNumber,name,period,cost,null,null));


                 List<ResourceList> cost2=null;
                 int cardNumber2 = i*8+j+24;
                 int period2 = i+1;
                 String name2=yellowCardName[contatore];
                 CardColor cardColor2 = CardColor.YELLOW;
                 cardList.add(new Building(cardNumber2,name2,period2,cost2,null,null));


                 List<ResourceList> cost3=null;
                 int cardNumber3 = i*8+j+48;
                 int period3 = i+1;
                 String name3=blueCardName[contatore];
                 CardColor cardColor3 = CardColor.BLUE;
                 cardList.add(new Character(cardNumber3,name3,period3,cost3,null,null));


                 List<ResourceList> cost4=null;
                 int cardNumber4 = i*8+j+72;
                 int period4 = i+1;
                 String name4=purpleCardName[contatore];
                 CardColor cardColor4 = CardColor.PURPLE;
                 cardList.add(new Venture(cardNumber4,name4,period4,cost4,null,null));
                 contatore++;
             }

         }


        return cardList;
    }

}
