package it.polimi.ingsw.ps31.Json;

import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.Effect.GetResource;
import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.GameThings.Wood;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static it.polimi.ingsw.ps31.Constants.PlayerColor.GREEN;

/**
 * Created by giulia on 16/05/2017.
 */
public abstract class CreationCard {

    public static List<DevelopmentCard>  createCardList(){
        List<DevelopmentCard> cardList = new ArrayList<>();
        //creazione di tutti i nomi delle carte
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
        //creazione degli effetti di tutte le carte
        int contatore2=0;

        List<EffectList> immediateEffectGreenList = new ArrayList<>();  //Creazione lista principale

        EffectList immediateEffectGreen1= new EffectList();           //creazione primo effetto
        immediateEffectGreen1.addSpecificEffect(null);
        immediateEffectGreenList.add(0,immediateEffectGreen1);  //aggiunta primo effetto alla lista principale

        EffectList immediateEffectGreen2= new EffectList();         //creazione secondo effetto
        ResourceList resourceList2 = new ResourceList();
        resourceList2.addSpecificResource(new Wood(1));
        immediateEffectGreen2.addSpecificEffect(new GetResource(resourceList2));
        immediateEffectGreenList.add(1,immediateEffectGreen2);      //aggiunta secondo effetto alla lista principale




         // creazione di tutte le carte
        for (int i=0;i<3;i++){
            for(int j=1;j<=8;j++){
                //creazione della carta verde
                int cardNumber1 = i*8+j;
                System.out.println("scrivi il costo della carta verde numero : "+cardNumber1);
                //inserimento costi nulli alle carti verdi
                List<ResourceList> costListGreen= new ArrayList<>();
                ResourceList costGreen = new ResourceList(null);
                costListGreen.add(costGreen);
                //inserimento altri parametri
                int period = i+1;
                String name=greenCardName[contatore];
                CardColor cardColor = CardColor.GREEN;
                cardList.add(new Territory(cardNumber1,name,period, costListGreen,null,null));
                //creazione della carta giallo
                int cardNumber2 = i*8+j+24;
                System.out.println("scrivi il costo della carta gialla numero : "+cardNumber2);
                // inserimento costi nelle carte gialle
                List<ResourceList> costListYellow = new ArrayList<>();
                ResourceList costYellow = new ResourceList();
                costYellow.setCoin();
                costYellow.setWood();
                costYellow.setStone();
                costYellow.setServant();
                costListYellow.add(costYellow);
                //inserimento altri parametri
                int period2 = i+1;
                String name2=yellowCardName[contatore];
                CardColor cardColor2 = CardColor.YELLOW;
                cardList.add(new Building(cardNumber2,name2,period2,costListYellow,null,null));
                // creazione carte blu
                int cardNumber3 = i*8+j+48;
                System.out.println("scrivi il costo della carta blu numero : "+cardNumber3);
                //inserimento costi nelle carte blu
                List<ResourceList> costListBlue = new ArrayList<>();
                ResourceList costBlue = new ResourceList() ;
                costBlue.setCoin();
                costListBlue.add(costBlue);
                //inserimento altri parametri
                int period3 = i+1;
                String name3=blueCardName[contatore];
                CardColor cardColor3 = CardColor.BLUE;
                cardList.add(new Character(cardNumber3,name3,period3,costListBlue,null,null));

                //creazione delle carte viola
                int cardNumber4 = i*8+j+72;
                System.out.println("scrivi il costo della carta viola numero : "+cardNumber4);
                System.out.println("quanti possibili costi ha la carta? : ");
                Scanner scanner =new Scanner(System.in);
                int numberCosts= scanner.nextByte();
                //inserimento costi nelle carte viola
                List<ResourceList> costListPurple = new ArrayList<>();
                for (i=0;i<numberCosts;i++){
                    ResourceList costPurple = new ResourceList();
                    costPurple.setCoin();
                    costPurple.setWood();
                    costPurple.setStone();
                    costPurple.setServant();
                    costPurple.setMilitaryStrength();
                    costListPurple.add(costPurple);
                }

                //inserimento altri parametri
                int period4 = i+1;
                String name4=purpleCardName[contatore];
                CardColor cardColor4 = CardColor.PURPLE;
                cardList.add(new Venture(cardNumber4,name4,period4,costListPurple,null,null));

                contatore++;
            }
        }
        return cardList;
    }

}
