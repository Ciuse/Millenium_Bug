package it.polimi.ingsw.ps31.Json;

import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.Effect.GetResource;
import it.polimi.ingsw.ps31.Effect.HarvestEffect;
import it.polimi.ingsw.ps31.GameThings.*;

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
        //effetto immediato prima carta
        EffectList immediateEffectGreen1 = new EffectList();           //creazione primo effetto
        immediateEffectGreen1.addSpecificEffect(null);
        immediateEffectGreenList.add(0, immediateEffectGreen1);  //aggiunta effetto alla lista principale
        //effetto immeddiato seconda carte
        EffectList immediateEffectGreen2 = new EffectList();         //creazione effetto
        ResourceList resourceList2 = new ResourceList();
        resourceList2.addSpecificResource(new Wood(1));
        immediateEffectGreen2.addSpecificEffect(new GetResource(resourceList2));
        immediateEffectGreenList.add(1, immediateEffectGreen2);      //aggiunta effetto alla lista principale
        //effetto immediato terza carta
        EffectList immediateEffectGreen3 = new EffectList();           //creazione effetto
        immediateEffectGreen3.addSpecificEffect(null);
        immediateEffectGreenList.add(2, immediateEffectGreen3);  //aggiunta effetto alla lista principale
        //effetto immediato quarta carta
        EffectList immediateEffectGreen4 = new EffectList();         //creazione effetto
        ResourceList resourceList4 = new ResourceList();
        resourceList4.addSpecificResource(new Stone(2));
        immediateEffectGreen4.addSpecificEffect(new GetResource(resourceList4));
        immediateEffectGreenList.add(3, immediateEffectGreen4);      //aggiunta effetto alla lista principale
        //effetto immediato quinta carta
        EffectList immediateEffectGreen5 = new EffectList();         //creazione effetto
        ResourceList resourceList5 = new ResourceList();
        resourceList5.addSpecificResource(new Wood(1));
        immediateEffectGreen5.addSpecificEffect(new GetResource(resourceList5));
        immediateEffectGreenList.add(4, immediateEffectGreen5);      //aggiunta effetto alla lista principale
        //effetto immediato sesta carta
        EffectList immediateEffectGreen6 = new EffectList();         //creazione  effetto
        ResourceList resourceList6 = new ResourceList();
        resourceList6.addSpecificResource(new MilitaryStrength(2));
        resourceList6.addSpecificResource(new Servant(1));
        immediateEffectGreen6.addSpecificEffect(new GetResource(resourceList6));
        immediateEffectGreenList.add(5, immediateEffectGreen6);      //aggiunta effetto alla lista principale
        //effetto immediato settima carta
        EffectList immediateEffectGreen7 = new EffectList();           //creazione effetto
        immediateEffectGreen7.addSpecificEffect(null);
        immediateEffectGreenList.add(6, immediateEffectGreen7);
        //effetto immediato ottava carta
        EffectList immediateEffectGreen8 = new EffectList();         //creazione  effetto
        ResourceList resourceList8 = new ResourceList();
        resourceList8.addSpecificResource(new Coin(3));
        immediateEffectGreen8.addSpecificEffect(new GetResource(resourceList8));
        immediateEffectGreenList.add(7, immediateEffectGreen8);      //aggiunta  effetto alla lista principale
        //effetto immediato nona carta
        EffectList immediateEffectGreen9 = new EffectList();         //creazione  effetto
        ResourceList resourceList9 = new ResourceList();
        resourceList9.addSpecificResource(new Coin(1));
        immediateEffectGreen9.addSpecificEffect(new GetResource(resourceList9));
        immediateEffectGreenList.add(8, immediateEffectGreen9);      //aggiunta  effetto alla lista principale
        //effetto immediato decima carta
        EffectList immediateEffectGreen10 = new EffectList();         //creazione  effetto
        ResourceList resourceList10 = new ResourceList();
        resourceList10.addSpecificResource(new Servant(1));
        immediateEffectGreen10.addSpecificEffect(new GetResource(resourceList10));
        immediateEffectGreenList.add(9, immediateEffectGreen10);      //aggiunta  effetto alla lista principale
        //effetto immediato undicesima carta
        EffectList immediateEffectGreen11 = new EffectList();         //creazione  effetto
        ResourceList resourceList11 = new ResourceList();
        resourceList11.addSpecificResource(new Servant(2));
        resourceList11.addSpecificResource(new Stone(1));
        immediateEffectGreen11.addSpecificEffect(new GetResource(resourceList11));
        immediateEffectGreenList.add(10, immediateEffectGreen11);      //aggiunta  effetto alla lista principale
        //effetto immediato dodicesima carta
        EffectList immediateEffectGreen12 = new EffectList();         //creazione  effetto
        ResourceList resourceList12 = new ResourceList();
        resourceList12.addSpecificResource(new Wood(1));
        immediateEffectGreen12.addSpecificEffect(new GetResource(resourceList12));
        immediateEffectGreenList.add(11, immediateEffectGreen12);      //aggiunta  effetto alla lista principale
        //effetto immediato tredicesima carta
        EffectList immediateEffectGreen13 = new EffectList();         //creazione  effetto
        ResourceList resourceList13 = new ResourceList();
        resourceList13.addSpecificResource(new Wood(1));
        resourceList13.addSpecificResource(new Servant(2));
        immediateEffectGreen13.addSpecificEffect(new GetResource(resourceList13));
        immediateEffectGreenList.add(12, immediateEffectGreen13);      //aggiunta  effetto alla lista principale
        //effetto immediato quattordicesima carta
        EffectList immediateEffectGreen14 = new EffectList();         //creazione  effetto
        ResourceList resourceList14 = new ResourceList();
        resourceList14.addSpecificResource(new FaithPoint(1));
        immediateEffectGreen14.addSpecificEffect(new GetResource(resourceList14));
        immediateEffectGreenList.add(13, immediateEffectGreen14);      //aggiunta  effetto alla lista principale
        //effetto immediato quindicesima carta
        EffectList immediateEffectGreen15 = new EffectList();         //creazione  effetto
        immediateEffectGreen15.addSpecificEffect(null);
        immediateEffectGreenList.add(14, immediateEffectGreen15);      //aggiunta  effetto alla lista principale
        //effetto immediato sedicesima carta
        EffectList immediateEffectGreen16 = new EffectList();         //creazione  effetto
        ResourceList resourceList16 = new ResourceList();
        resourceList16.addSpecificResource(new Coin(4));
        immediateEffectGreen16.addSpecificEffect(new GetResource(resourceList16));
        immediateEffectGreenList.add(15, immediateEffectGreen16);      //aggiunta  effetto alla lista principale
        //effetto immediato diciasettesima carta
        EffectList immediateEffectGreen17 = new EffectList();         //creazione  effetto
        ResourceList resourceList17 = new ResourceList();
        resourceList17.addSpecificResource(new Coin(1));
        resourceList17.addSpecificResource(new Servant(1));
        immediateEffectGreen17.addSpecificEffect(new GetResource(resourceList17));
        immediateEffectGreenList.add(16, immediateEffectGreen17);      //aggiunta  effetto alla lista principale
        //effetto immediato diciottesima carta
        EffectList immediateEffectGreen18 = new EffectList();         //creazione  effetto
        ResourceList resourceList18 = new ResourceList();
        resourceList18.addSpecificResource(new Wood(1));
        resourceList18.addSpecificResource(new VictoryPoint(1));
        immediateEffectGreen18.addSpecificEffect(new GetResource(resourceList18));
        immediateEffectGreenList.add(17, immediateEffectGreen18);      //aggiunta  effetto alla lista principale
        //effetto immediato diciannovesima carta
        EffectList immediateEffectGreen19 = new EffectList();         //creazione  effetto
        ResourceList resourceList19 = new ResourceList();
        resourceList19.addSpecificResource(new MilitaryStrength(2));
        immediateEffectGreen19.addSpecificEffect(new GetResource(resourceList19));
        immediateEffectGreenList.add(18, immediateEffectGreen19);      //aggiunta  effetto alla lista principale
        //effetto immediato ventesima carta
        EffectList immediateEffectGreen20 = new EffectList();         //creazione  effetto
        ResourceList resourceList20 = new ResourceList();
        resourceList20.addSpecificResource(new VictoryPoint(3));
        immediateEffectGreen20.addSpecificEffect(new GetResource(resourceList20));
        immediateEffectGreenList.add(19, immediateEffectGreen20);      //aggiunta  effetto alla lista principale
        //effetto immediato ventunesima carta
        EffectList immediateEffectGreen21 = new EffectList();         //creazione  effetto
        ResourceList resourceList21 = new ResourceList();
        resourceList21.addSpecificResource(new Stone(1));
        resourceList21.addSpecificResource(new CouncilPrivilege(1, false));
        immediateEffectGreen21.addSpecificEffect(new GetResource(resourceList21));
        immediateEffectGreenList.add(20, immediateEffectGreen21);      //aggiunta  effetto alla lista principale
        //effetto immediato ventiduesima carta
        EffectList immediateEffectGreen22 = new EffectList();         //creazione  effetto
        ResourceList resourceList22 = new ResourceList();
        resourceList22.addSpecificResource(new FaithPoint(1));
        immediateEffectGreen22.addSpecificEffect(new GetResource(resourceList22));
        immediateEffectGreenList.add(21, immediateEffectGreen22);      //aggiunta  effetto alla lista principale
        //effetto immediato ventitreesima carta
        EffectList immediateEffectGreen23 = new EffectList();         //creazione  effetto
        ResourceList resourceList23 = new ResourceList();
        resourceList23.addSpecificResource(new Coin(2));
        resourceList23.addSpecificResource(new VictoryPoint(2));
        immediateEffectGreen23.addSpecificEffect(new GetResource(resourceList23));
        immediateEffectGreenList.add(22, immediateEffectGreen23);      //aggiunta  effetto alla lista principale
        //effetto immediato ventiquattreesima carta
        EffectList immediateEffectGreen24 = new EffectList();         //creazione  effetto
        ResourceList resourceList24 = new ResourceList();
        resourceList24.addSpecificResource(new Servant(1));
        resourceList24.addSpecificResource(new MilitaryStrength(2));
        immediateEffectGreen24.addSpecificEffect(new GetResource(resourceList24));
        immediateEffectGreenList.add(23, immediateEffectGreen24);      //aggiunta  effetto alla lista principale

        List<EffectList> permanentEffectGreenList = new ArrayList<>();  //Creazione lista principale

        //creazione effetto permanente prima carta
        EffectList permanentEffectGreen1= new EffectList();         //creazione  effetto
        ResourceList resourceList1 = new ResourceList();
        resourceList1.addSpecificResource(new Coin (1));
        permanentEffectGreen1.addSpecificEffect(new HarvestEffect(1,new GetResource(resourceList1)));
        permanentEffectGreenList.add(0,permanentEffectGreen1);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente seconda carta
        EffectList permanentEffectGreen2= new EffectList();         //creazione  effetto
        resourceList2.clearResourceList();
        resourceList2.addSpecificResource(new Wood (1));
        permanentEffectGreen2.addSpecificEffect(new HarvestEffect(2,new GetResource(resourceList2)));
        permanentEffectGreenList.add(1,permanentEffectGreen2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente terza carta
        EffectList permanentEffectGreen3= new EffectList();         //creazione  effetto
        ResourceList resourceList3 = new ResourceList();
        resourceList3.addSpecificResource(new Coin(1));
        resourceList3.addSpecificResource(new Servant(1));
        permanentEffectGreen3.addSpecificEffect(new HarvestEffect(3,new GetResource(resourceList3)));
        permanentEffectGreenList.add(2,permanentEffectGreen3);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quarta carta
        EffectList permanentEffectGreen4= new EffectList();         //creazione  effetto
        resourceList4.clearResourceList();
        resourceList4.addSpecificResource(new Stone (2));
        permanentEffectGreen4.addSpecificEffect(new HarvestEffect(4,new GetResource(resourceList4)));
        permanentEffectGreenList.add(3,permanentEffectGreen4);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quinta carta
        EffectList permanentEffectGreen5= new EffectList();         //creazione  effetto
        resourceList5.clearResourceList();
        resourceList5.addSpecificResource(new Wood (3));
        permanentEffectGreen5.addSpecificEffect(new HarvestEffect(5,new GetResource(resourceList5)));
        permanentEffectGreenList.add(4,permanentEffectGreen5);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sesta carta
        EffectList permanentEffectGreen6= new EffectList();         //creazione  effetto
        resourceList6.clearResourceList();
        resourceList6.addSpecificResource(new Stone(1));
        resourceList6.addSpecificResource(new FaithPoint(1));
        permanentEffectGreen6.addSpecificEffect(new HarvestEffect(6,new GetResource(resourceList6)));
        permanentEffectGreenList.add(5,permanentEffectGreen6);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente settima carta
        EffectList permanentEffectGreen7= new EffectList();         //creazione  effetto
        ResourceList resourceList7 = new ResourceList();
        resourceList7.addSpecificResource(new Stone (1));
        resourceList7.addSpecificResource(new MilitaryStrength(2));
        permanentEffectGreen7.addSpecificEffect(new HarvestEffect(5,new GetResource(resourceList7)));
        permanentEffectGreenList.add(6,permanentEffectGreen7);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ottava carta
        EffectList permanentEffectGreen8= new EffectList();         //creazione  effetto
        resourceList8.clearResourceList();
        resourceList8.addSpecificResource(new CouncilPrivilege (1,false));
        permanentEffectGreen8.addSpecificEffect(new HarvestEffect(6,new GetResource(resourceList8)));
        permanentEffectGreenList.add(7,permanentEffectGreen8);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente nona carta
        EffectList permanentEffectGreen9= new EffectList();         //creazione  effetto
        resourceList9.clearResourceList();
        resourceList9.addSpecificResource(new Coin (2));
        permanentEffectGreen9.addSpecificEffect(new HarvestEffect(1,new GetResource(resourceList9)));
        permanentEffectGreenList.add(8,permanentEffectGreen9);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente decima carta
        EffectList permanentEffectGreen10= new EffectList();         //creazione  effetto
        resourceList10.clearResourceList();
        resourceList10.addSpecificResource(new Wood (2));
        resourceList10.addSpecificResource(new MilitaryStrength(1));
        permanentEffectGreen10.addSpecificEffect(new HarvestEffect(3,new GetResource(resourceList10)));
        permanentEffectGreenList.add(9,permanentEffectGreen10);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente undicesima carta
        EffectList permanentEffectGreen11= new EffectList();         //creazione  effetto
        resourceList11.clearResourceList();
        resourceList11.addSpecificResource(new Stone (2));
        resourceList11.addSpecificResource(new Servant(1));
        permanentEffectGreen11.addSpecificEffect(new HarvestEffect(4,new GetResource(resourceList11)));
        permanentEffectGreenList.add(10,permanentEffectGreen2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente dodicesima carta
        EffectList permanentEffectGreen12= new EffectList();         //creazione  effetto
        resourceList12.clearResourceList();
        resourceList12.addSpecificResource(new Stone (3));
        permanentEffectGreen12.addSpecificEffect(new HarvestEffect(3,new GetResource(resourceList12)));
        permanentEffectGreenList.add(11,permanentEffectGreen12);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente tredicesima carta
        EffectList permanentEffectGreen13= new EffectList();         //creazione  effetto
        resourceList13.clearResourceList();
        resourceList13.addSpecificResource(new Coin (1));
        resourceList13.addSpecificResource(new Wood(2));
        permanentEffectGreen13.addSpecificEffect(new HarvestEffect(4,new GetResource(resourceList13)));
        permanentEffectGreenList.add(12,permanentEffectGreen13);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quattordicesima carta
        EffectList permanentEffectGreen14= new EffectList();         //creazione  effetto
        resourceList14.clearResourceList();
        resourceList14.addSpecificResource(new FaithPoint (1));
        permanentEffectGreen14.addSpecificEffect(new HarvestEffect(2,new GetResource(resourceList14)));
        permanentEffectGreenList.add(13,permanentEffectGreen14);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quindicesima carta
        EffectList permanentEffectGreen15= new EffectList();         //creazione  effetto
        ResourceList resourceList15 = new ResourceList();
        resourceList15.addSpecificResource(new Servant (2));
        resourceList15.addSpecificResource(new MilitaryStrength(2));
        permanentEffectGreen15.addSpecificEffect(new HarvestEffect(5,new GetResource(resourceList15)));
        permanentEffectGreenList.add(14,permanentEffectGreen15);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sedicesima carta
        EffectList permanentEffectGreen16= new EffectList();         //creazione  effetto
        resourceList16.clearResourceList();
        resourceList16.addSpecificResource(new Wood (2));
        resourceList16.addSpecificResource(new Stone(1));
        resourceList16.addSpecificResource(new Coin(1));
        permanentEffectGreen16.addSpecificEffect(new HarvestEffect(6,new GetResource(resourceList16)));
        permanentEffectGreenList.add(15,permanentEffectGreen16);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciassettesima carta
        EffectList permanentEffectGreen17= new EffectList();         //creazione  effetto
        resourceList17.clearResourceList();
        resourceList17.addSpecificResource(new Coin (3));
        permanentEffectGreen17.addSpecificEffect(new HarvestEffect(1,new GetResource(resourceList17)));
        permanentEffectGreenList.add(16,permanentEffectGreen17);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciottesima carta
        EffectList permanentEffectGreen18= new EffectList();         //creazione  effetto
        resourceList18.clearResourceList();
        resourceList18.addSpecificResource(new Wood (2));
        resourceList18.addSpecificResource(new VictoryPoint(2));
        permanentEffectGreen18.addSpecificEffect(new HarvestEffect(3,new GetResource(resourceList18)));
        permanentEffectGreenList.add(17,permanentEffectGreen18);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciannovesima carta
        EffectList permanentEffectGreen19= new EffectList();         //creazione  effetto
        resourceList19.clearResourceList();
        resourceList19.addSpecificResource(new Wood (1));
        resourceList19.addSpecificResource(new VictoryPoint(4));
        permanentEffectGreen19.addSpecificEffect(new HarvestEffect(5,new GetResource(resourceList19)));
        permanentEffectGreenList.add(18,permanentEffectGreen19);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventesima carta
        EffectList permanentEffectGreen20= new EffectList();         //creazione  effetto
        resourceList20.clearResourceList();
        resourceList20.addSpecificResource(new Stone (2));
        resourceList20.addSpecificResource(new VictoryPoint(1));
        permanentEffectGreen20.addSpecificEffect(new HarvestEffect(2,new GetResource(resourceList20)));
        permanentEffectGreenList.add(19,permanentEffectGreen20);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventunesima carta
        EffectList permanentEffectGreen21= new EffectList();         //creazione  effetto
        resourceList21.clearResourceList();
        resourceList21.addSpecificResource(new Stone(1));
        resourceList21.addSpecificResource(new VictoryPoint(4));
        permanentEffectGreen21.addSpecificEffect(new HarvestEffect(6,new GetResource(resourceList21)));
        permanentEffectGreenList.add(20,permanentEffectGreen21);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiduesima carta
        EffectList permanentEffectGreen22= new EffectList();         //creazione  effetto
        resourceList22.clearResourceList();
        resourceList22.addSpecificResource(new Coin(1));
        resourceList22.addSpecificResource(new FaithPoint(1));
        permanentEffectGreen22.addSpecificEffect(new HarvestEffect(1,new GetResource(resourceList22)));
        permanentEffectGreenList.add(21,permanentEffectGreen22);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventitreesima carta
        EffectList permanentEffectGreen23= new EffectList();         //creazione  effetto
        resourceList23.clearResourceList();
        resourceList23.addSpecificResource(new Servant(1));
        resourceList23.addSpecificResource(new MilitaryStrength(3));
        permanentEffectGreen23.addSpecificEffect(new HarvestEffect(4,new GetResource(resourceList23)));
        permanentEffectGreenList.add(22,permanentEffectGreen23);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiquattresima carta
        EffectList permanentEffectGreen24= new EffectList();         //creazione  effetto
        resourceList24.clearResourceList();
        resourceList24.addSpecificResource(new MilitaryStrength(1));
        resourceList24.addSpecificResource(new Servant(2));
        permanentEffectGreen24.addSpecificEffect(new HarvestEffect(2,new GetResource(resourceList24)));
        permanentEffectGreenList.add(23,permanentEffectGreen24);      //aggiunta  effetto alla lista principale




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
                cardList.add(new Territory(cardNumber1,name,period, costListGreen,immediateEffectGreenList.get(cardNumber1),permanentEffectGreenList.get(cardNumber1)));
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
